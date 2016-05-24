package pl.edu.amu.wmi.projekt_szi.priority;

import pl.edu.amu.wmi.projekt_szi.model.Location;

import java.util.List;

public class TablePriorityHandler {

    private List<Table> tables;

    public FuzzyLogicService handler;

    public TableStatusController tableStatusController;

    public TablePriorityHandler(List<Table> tables) {
        tableStatusController = new TableStatusController(this.tables);
        this.tables = tables;
        handler = new FuzzyLogicService();
    }

    public void calculatePriorities(Location waiterLocation, List<Table> tables) {
        for (Table table : tables) {
            table.setPriority(handler.getTablePriority(table, waiterLocation));
            String toFormat = "D: %d, I: %.3f, R: %.3f, P: %.3f, X: %d, Y: %d\n";
            System.out.printf(toFormat, table.getLocation().getManhattanDistanceTo(waiterLocation),
                    table.getTime(), table.getRichness(), table.getPriority(), table.getLocation().getX(), table.getLocation().getY());
        }
    }

    public synchronized Table maxPriorityTable() {
        double max = 0;
        Table maxTable = null;
        for (Table table : tableStatusController.getWaitingTablesQueue()) {
            if ((max < table.getPriority())) {
                max = table.getPriority();
                maxTable = table;
            }
        }
        return maxTable;
    }
}
