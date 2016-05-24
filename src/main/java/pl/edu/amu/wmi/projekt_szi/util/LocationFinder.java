package pl.edu.amu.wmi.projekt_szi.util;

import pl.edu.amu.wmi.projekt_szi.model.AbstractField;
import pl.edu.amu.wmi.projekt_szi.model.Location;

import java.util.List;

public class LocationFinder {

    public static <T extends AbstractField> T FindInList(List<T> list, Location location) {
        for (T abstractField : list) {
            if (abstractField.getLocation().equals(location)) {
                return abstractField;
            }
        }
        return null;
    }

    public static <T extends AbstractField> T FindInList(List<T> list, int x, int y) {
        for (T abstractField : list) {
            if (abstractField.getLocation().getX() == x &&
                    abstractField.getLocation().getY() == y) {
                return abstractField;
            }
        }
        return null;
    }
}
