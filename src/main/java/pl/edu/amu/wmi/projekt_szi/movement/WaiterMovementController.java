package pl.edu.amu.wmi.projekt_szi.movement;

import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import org.xguzm.pathfinding.grid.finders.AStarGridFinder;
import org.xguzm.pathfinding.grid.finders.GridFinderOptions;
import pl.edu.amu.wmi.projekt_szi.decision.WaiterDecisionController;
import pl.edu.amu.wmi.projekt_szi.model.AbstractField;
import pl.edu.amu.wmi.projekt_szi.model.Location;
import pl.edu.amu.wmi.projekt_szi.model.Map;
import pl.edu.amu.wmi.projekt_szi.priority.Table;
import pl.edu.amu.wmi.projekt_szi.priority.TablePriorityHandler;
import pl.edu.amu.wmi.projekt_szi.util.LocationFinder;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class WaiterMovementController {

    private static boolean end;

    private final Waiter waiter;

    private final Map map;

    private final TablePriorityHandler tablePriorityHandler;

    private final WaiterDecisionController waiterDecisionController;

    private final Component component;

    private Random random = new Random();

    private Table targetTable;

    public WaiterMovementController(Map map, Component component) {
        this.waiter = map.getWaiter();
        this.map = map;
        this.component = component;
        this.tablePriorityHandler = new TablePriorityHandler(map.getTables());
        waiterDecisionController = new WaiterDecisionController(waiter);
        changeTableStatus();
    }


    private void moveWaiter() {
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
            if (random.nextInt() % 3 == 0) {
                changeTableStatus();
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
        NavigationGrid<GridCell> navigationGrid = new WaiterMovementNavigationGrid(map.getTreeMap());

        GridFinderOptions opt = new GridFinderOptions();
        opt.allowDiagonal = true;
        AStarGridFinder<GridCell> finder = new AStarGridFinder<>(GridCell.class, opt);

        return finder.findPath(
                inputLocation.getX(), inputLocation.getY(),
                targetLocation.getX(), targetLocation.getY(),
                navigationGrid);

    }

//    private List<GridCell> neighbours(GridCell gridCell) {
//        List<GridCell> neighbours = new ArrayList<>();
//        for (java.util.Map.Entry<Location, AbstractField> entry : map.getTreeMap().entrySet()) {
//            Location neighbourLocation = entry.getKey();
//            boolean horizontal = ((gridCell.getX() + 1) == neighbourLocation.getX()) || (gridCell.getX() + -1) == neighbourLocation.getX();
//            boolean vertival = ((gridCell.getY() + 1) == neighbourLocation.getY()) || (gridCell.getY() + -1) == neighbourLocation.getY();
//            if (horizontal && vertival && entry.getValue().isWalkable()) {
//                neighbours.add(new GridCell(neighbourLocation.getX(), neighbourLocation.getY()));
//            }
//        }
//        return  neighbours;
//    }

    private void serve() {
        Table table = LocationFinder.FindInList(map.getTables(), targetTable.getLocation());
        if (table != null) {
            table.setWaiting(false);
        }
    }

    public void mainLoop() {
        do {
            if (targetTable != null) {
                moveWaiter();
                System.out.printf("X: %d Y: %d\n", waiter.getLocation().getX(), waiter.getLocation().getY());
                System.out.printf("Target X: %d Y: %d\n", targetTable.getLocation().getX(),
                        targetTable.getLocation().getY());
                waiterDecisionController.setServedTable(targetTable);
                serve();
            }


//        waiterDecisionController.makeServeDecision();

            // na podstawie priorytetu,
            // czasu oczekiwania klienta i
            // bogactwa stolika kelner podejmuje decyzje,
            // czy składa zamówienie do baru,
            // obsługuje kolejną osobę,
            // czy idzie do baru po jedzenie do roznoszenia


            tablePriorityHandler.calculatePriorities(waiter.getLocation(), map.getTables());
            targetTable = tablePriorityHandler.maxPriorityTable();
            component.repaint();
        } while (!end);
    }


    private void changeTableStatus() {
        for (Table table : map.getTables()) {
            if (!table.isWaiting()) {
                table.setWaiting(true);
                return;
            }
        }
    }

}
