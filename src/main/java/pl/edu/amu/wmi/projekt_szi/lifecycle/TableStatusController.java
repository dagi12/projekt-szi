package pl.edu.amu.wmi.projekt_szi.lifecycle;

import java.util.*;

public class TableStatusController implements Runnable {


    Queue<Table> servedTablesQueue = new LinkedList<>();
    Queue<Table> waitingTablesQueue = new LinkedList<>();

    public TableStatusController(List<Table> tables) {
        this.end = false;
        for (Table table : tables) {
            if (table.isWaiting()) {
                waitingTablesQueue.add(table);
            } else {
                servedTablesQueue.add(table);
            }
        }
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    private boolean end;

    @Override
    public void run() {
        while (!end) {
            try {
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            changeTableStatus();
        }
    }

    private void changeTableStatus() {
        Table table = servedTablesQueue.poll();
        table.setWaiting(true);
        waitingTablesQueue.add(table);
    }

}
