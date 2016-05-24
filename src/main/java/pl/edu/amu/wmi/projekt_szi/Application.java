package pl.edu.amu.wmi.projekt_szi;

import pl.edu.amu.wmi.projekt_szi.model.Map;
import pl.edu.amu.wmi.projekt_szi.movement.WaiterMovementController;
import pl.edu.amu.wmi.projekt_szi.util.AbstractController;
import pl.edu.amu.wmi.projekt_szi.view.WindowManager;

class Application {

    public static void main(String[] args) throws Exception {
        Initialization initialization = new Initialization();
        initialization.start();
    }

    private static class Initialization {

        private final Map map;

        Initialization() {
            this.map = new Map();
        }

        private void start() {
            WindowManager windowManager = new WindowManager(map.getTreeMap());
            WaiterMovementController waiterMovementController = new WaiterMovementController(map, windowManager);
//        DiagnosticWindow.main(args);
            waiterMovementController.mainLoop();
        }

    }

}

