package pl.edu.amu.wmi.projekt_szi;

import pl.edu.amu.wmi.projekt_szi.model.Map;
import pl.edu.amu.wmi.projekt_szi.movement.WaiterMovementController;
import pl.edu.amu.wmi.projekt_szi.priority.TableStatusController;
import pl.edu.amu.wmi.projekt_szi.util.RunnableController;
import pl.edu.amu.wmi.projekt_szi.view.WindowManager;

public class Application {

    public static void main(String[] args) throws Exception {
        Initialization initialization = new Initialization();
        initialization.start();
    }

    private static class Initialization {

        Map map;

        Initialization() {
            this.map = new Map();
        }

        void start() {
            RunnableController.setEnd(false);
            WindowManager windowManager = new WindowManager();
            WaiterMovementController waiterMovementController = new WaiterMovementController(map, windowManager);
            TableStatusController tableStatusController = new TableStatusController(map.getTables());
//        DiagnosticWindow.main(args);
            Thread waiterMovementThread = new Thread(waiterMovementController);
            Thread tableWaitingThread = new Thread(tableStatusController);
            waiterMovementThread.start();
            tableWaitingThread.start();
        }

    }

}

