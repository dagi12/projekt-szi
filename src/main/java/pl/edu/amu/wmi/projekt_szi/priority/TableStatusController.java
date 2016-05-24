package pl.edu.amu.wmi.projekt_szi.priority;

import pl.edu.amu.wmi.projekt_szi.util.RunnableController;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TableStatusController extends RunnableController {

    public TableStatusController(List<Table> tables) {
        servedTablesQueue = new LinkedList<>();
        waitingTablesQueue = new LinkedList<>();
        for (Table table : tables) {
            if (table.isWaiting()) {
                waitingTablesQueue.add(table);
            } else {
                servedTablesQueue.add(table);
            }
        }
    }

    @Override
    protected void doRun() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changeTableStatus();
    }

    private void changeTableStatus() {
        Table table = servedTablesQueue.poll();
        table.setWaiting(true);
        waitingTablesQueue.add(table);
    }

}
