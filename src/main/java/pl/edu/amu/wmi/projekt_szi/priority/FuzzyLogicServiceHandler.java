package pl.edu.amu.wmi.projekt_szi.priority;

import pl.edu.amu.wmi.projekt_szi.model.FieldValueChanger;
import pl.edu.amu.wmi.projekt_szi.model.Map;
import pl.edu.amu.wmi.projekt_szi.model.Location;
import pl.edu.amu.wmi.projekt_szi.movement.MainWaiterMovementLogicService;
import pl.edu.amu.wmi.projekt_szi.movement.Waiter;
import pl.edu.amu.wmi.projekt_szi.lifecycle.Table;
import pl.edu.amu.wmi.projekt_szi.Application;
import pl.edu.amu.wmi.projekt_szi.view.WindowManager;

import java.awt.*;
import java.util.*;

/**
 * Created by softra43 on 20.04.2016.
 */
public class FuzzyLogicServiceHandler implements Runnable {

    private MainWaiterMovementLogicService mainWaiterMovementLogicService;
    private DecisionEvaluator decisionEvaluator;
    private WindowManager windowManager;
    
    private Waiter waiter;
    
    private Map map;

    public boolean end;

    public TablePriorityHandler handler;

    public FuzzyLogicServiceHandler() {
        init();
        this.end = false;
    }

    private void init() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        handler = new TablePriorityHandler();
        map = Map.getInstance();
        FieldValueChanger.getInstance().addObserver(this);

        decisionEvaluator = new DecisionEvaluator();
        Location waiterLocation = new Location(1, 1);
        calculatePriorities(waiterLocation, map.getTreeMap());

        waiter = Waiter.getInstance();
        waiter.setLocation(waiterLocation);
        windowManager = new WindowManager();
        windowManager.repaint();
        windowManager.initMap(this.waiter);

        mainWaiterMovementLogicService
                = new MainWaiterMovementLogicService();
    }

    private void calculatePriorities(Location waiterLocation, TreeMap<Location, Table> fields) {
        for (java.util.Map.Entry<Location, Table> entry : fields.entrySet()) {
            Table f = entry.getValue();
            map.setTablePriorityAt(entry.getKey(), handler.getFieldPriority(f, waiterLocation));

            if (Application.DEBUG) {
                String toFormat = "D: %d, I: %.3f, R: %.3f, P: %.3f, X: %d, Y: %d\n";
                System.out.printf(toFormat, f.getLocation().getManhattanDistanceTo(waiterLocation),
                        f.getTime(), f.getRichness(), f.getPriority(), f.getLocation().getX(), f.getLocation().getY());
            }
        }
        map.notifyThatAllFieldsChanged();
    }


    private Table getFieldWhichWaiterIsStandingOn() {
        Location l = Waiter.getInstance().getLocation();
        return map.getFieldAt(l);
    }

    @Override
    public void run() {
        do {
            while (!mainWaiterMovementLogicService.calculateWaiterTurn()) {
                try {
                    Thread.sleep(500);
                    if (Application.DEBUG) {
                        System.out.printf("X: %d Y: %d\n", Waiter.getInstance().getLocation().getX(), Waiter.getInstance().getLocation().getY());
                        System.out.printf("Target X: %d Y: %d\n", Waiter.getInstance().getTargetLocation().getX(), Waiter.getInstance().getTargetLocation().getY());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                windowManager.repaint();
            }
            Field f = getFieldWhichWaiterIsStandingOn();
            try {
                if(Application.DEBUG) {
                    System.out.printf("X: %d Y: %d\n", Waiter.getInstance().getLocation().getX(), Waiter.getInstance().getLocation().getY());
                    System.out.printf("Target X: %d Y: %d\n", Waiter.getInstance().getTargetLocation().getX(), Waiter.getInstance().getTargetLocation().getY());
                }
                Waiter.getInstance().makeFertilizationDecision(f);
                Waiter.getInstance().makeIrrigationDecision(f);
                calculatePriorities(Waiter.getInstance().getLocation(), map.getTreeMap());
                Waiter.getInstance().setTargetLocation(map.maxPriorityTable().getLocation());
                windowManager.repaint();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while (!end);
    }

}
