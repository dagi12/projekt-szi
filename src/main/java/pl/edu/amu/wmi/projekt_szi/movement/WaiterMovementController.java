package pl.edu.amu.wmi.projekt_szi.movement;

import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import org.xguzm.pathfinding.grid.finders.AStarGridFinder;
import org.xguzm.pathfinding.grid.finders.GridFinderOptions;
import pl.edu.amu.wmi.projekt_szi.decision.WaiterDecisionController;
import pl.edu.amu.wmi.projekt_szi.model.AbstractField;
import pl.edu.amu.wmi.projekt_szi.model.Location;
import pl.edu.amu.wmi.projekt_szi.model.Map;
import pl.edu.amu.wmi.projekt_szi.decision.DecisionEvaluator;
import pl.edu.amu.wmi.projekt_szi.decision.LevelledDecision;
import pl.edu.amu.wmi.projekt_szi.priority.Table;
import pl.edu.amu.wmi.projekt_szi.priority.TablePriorityHandler;
import pl.edu.amu.wmi.projekt_szi.util.RunnableController;

import java.awt.*;
import java.util.List;
import java.util.TreeMap;

public class WaiterMovementController extends RunnableController implements Runnable {

    volatile Table targetTable;

    private Waiter waiter;

    private List<Table> tables;

    private Map map;

    private TablePriorityHandler tablePriorityHandler;

    private WaiterDecisionController waiterDecisionController;

    private Component component;


    public WaiterMovementController(Map map, Component component) {
        this.waiter = map.getWaiter();
        this.tables = map.getTables();
        this.map = map;
        this.component = component;
        this.tablePriorityHandler = new TablePriorityHandler(tables);
        targetTable = tablePriorityHandler.maxPriorityTable();
        waiterDecisionController = new WaiterDecisionController(waiter);
    }

    public void moveWaiter() {
        List<GridCell> pathToEnd = processAstar(waiter.getLocation(), targetTable.getLocation());
        for (GridCell gridCell : pathToEnd) {
            changeWaiterLocation(gridCell.getX(), gridCell.getY());
            try {
                Thread.sleep(500);
                System.out.printf("X: %d Y: %d\n", waiter.getLocation().getX(), waiter.getLocation().getY());
                System.out.printf("Target X: %d Y: %d\n", targetTable.getLocation().getX(),
                        targetTable.getLocation().getY());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            component.repaint();
        }
    }

    private void changeWaiterLocation(int x, int y) {
        Location oldLocation = new Location(waiter.getLocation());
        Location newLocation = new Location(x, y);
        AbstractField abstractField = map.getFieldAt(newLocation);
        map.setFieldAt(newLocation, waiter);
        map.setFieldAt(oldLocation, abstractField);
    }

    private List<GridCell> processAstar(Location inputLocation, Location targetLocation) {
        GridCell[][] cells = new GridCell[7][7];
        NavigationGrid<GridCell> navigationGrid = new WaiterMovementNavigationGrid(map.getTreeMap());

        GridFinderOptions opt = new GridFinderOptions();
        opt.allowDiagonal = true;
        AStarGridFinder<GridCell> finder = new AStarGridFinder<>(GridCell.class, opt);
        return finder.findPath(
                inputLocation.getX(), inputLocation.getY(),
                targetLocation.getX(), targetLocation.getY(),
                navigationGrid);
    }

    @Override
    public void doRun() {
        moveWaiter();
        System.out.printf("X: %d Y: %d\n", waiter.getLocation().getX(), waiter.getLocation().getY());
        System.out.printf("Target X: %d Y: %d\n", targetTable.getLocation().getX(), targetTable.getLocation().getY());
        waiterDecisionController.setServedTable(targetTable);
        waiterDecisionController.makeServeDecision();

        // na podstawie priorytetu,
        // czasu oczekiwania klienta i
        // bogactwa stolika kelner podejmuje decyzje,
        // czy składa zamówienie do baru,
        // obsługuje kolejną osobę,
        // czy idzie do baru po jedzenie do roznoszenia


        tablePriorityHandler.calculatePriorities(waiter.getLocation(), map.getTables());
        targetTable = tablePriorityHandler.maxPriorityTable();
        component.repaint();
    }

}
