package pl.edu.amu.wmi.projekt_szi;

import pl.edu.amu.wmi.projekt_szi.movement.Waiter;

public interface ApplicationConstants {

    String FCL_FILE_NAME = "res/fieldHandling.fcl";

    boolean VERBOSE_MODE = true;

    int WIDTH = 16;

    int HEIGHT = 16;

    int WAITER_START_X = 1;

    int WAITER_START_Y = 1;

    Waiter.Direction WAITER_START_DIRECTION = Waiter.Direction.RIGHT;

}
