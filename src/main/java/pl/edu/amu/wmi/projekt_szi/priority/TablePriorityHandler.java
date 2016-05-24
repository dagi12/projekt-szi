package pl.edu.amu.wmi.projekt_szi.priority;

import pl.edu.amu.wmi.projekt_szi.model.Location;

import java.util.List;

public class TablePriorityHandler {

    private final FuzzyLogicService handler;

    private List<Table> tables;

    public TablePriorityHandler(List<Table> tables) {
        this.tables = tables;
        handler = new FuzzyLogicService();
    }

    public  void calculatePriorities(Location waiterLocation, List<Table> tables) {
        for (Table table : tables) {
            if (table.isWaiting()) {
                table.setPriority(handler.getTablePriority(table, waiterLocation));
            }
//            String toFormat = "D: %d, I: %.3f, R: %.3f, P: %.3f, X: %d, Y: %d\n";
//            System.out.printf(toFormat, table.getLocation().getManhattanDistanceTo(waiterLocation),
//                    table.getTime(), table.getRichness(), table.getPriority(), table.getLocation().getX(),
//                    table.getLocation().getY());
        }
    }

    public  Table maxPriorityTable() {
        double max = 0;
        Table maxTable = null;
        for (Table table : tables) {
            if (table.isWaiting() && (max < table.getPriority())) {
                max = table.getPriority();
                maxTable = table;
            }
        }
        return maxTable;
    }
}
