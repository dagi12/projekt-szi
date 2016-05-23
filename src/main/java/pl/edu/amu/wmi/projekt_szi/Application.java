package pl.edu.amu.wmi.projekt_szi;

import pl.edu.amu.wmi.projekt_szi.lifecycle.TableStatusController;
import pl.edu.amu.wmi.projekt_szi.model.Map;
import pl.edu.amu.wmi.projekt_szi.priority.FuzzyLogicServiceHandler;

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
            FuzzyLogicServiceHandler fuzzyLogicServiceHandler = new FuzzyLogicServiceHandler();
            TableStatusController tableStatusController = new TableStatusController(map.getTABLES());

//        DiagnosticWindow.main(args);
            Thread mainFuzzyLogicThread = new Thread(fuzzyLogicServiceHandler);
            Thread tableWaitingThread = new Thread(tableStatusController);
            mainFuzzyLogicThread.start();
            tableWaitingThread.start();
        }

    }

}

