package pl.edu.amu.wmi.projekt_szi.util;

import pl.edu.amu.wmi.projekt_szi.priority.Table;

import java.util.Queue;

public abstract class RunnableController implements Runnable {

    protected static Queue<Table> servedTablesQueue;

    protected static Queue<Table> waitingTablesQueue;

    public static boolean isEnd() {
        return end;
    }

    public static void setEnd(boolean end) {
        RunnableController.end = end;
    }

    private static boolean end;

    public Queue<Table> getWaitingTablesQueue() {
        return waitingTablesQueue;
    }

    public void setWaitingTablesQueue(Queue<Table> waitingTablesQueue) {
        this.waitingTablesQueue = waitingTablesQueue;
    }

    public Queue<Table> getServedTablesQueue() {
        return servedTablesQueue;
    }

    public void setServedTablesQueue(Queue<Table> servedTablesQueue) {
        this.servedTablesQueue = servedTablesQueue;
    }

    @Override
    public void run() {
        do {
            doRun();
        } while(!end);
    }

    protected abstract void doRun();

}
