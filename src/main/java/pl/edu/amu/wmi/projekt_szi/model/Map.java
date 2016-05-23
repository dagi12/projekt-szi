package pl.edu.amu.wmi.projekt_szi.model;


import java.util.*;

import pl.edu.amu.wmi.projekt_szi.lifecycle.Table;
import pl.edu.amu.wmi.projekt_szi.lifecycle.Table.Richness;

public class Map extends Observable {

    private static Map instance;

    private volatile TreeMap<Location, AbstractField> treeMap;

    public Map() {
        treeMap = new TreeMap<>();
        generateFields();
    }

    public List<Table> getTABLES() {
        return TABLES;
    }

    public List<Table> TABLES = Arrays.asList(
            new Table(Richness.LOW, 0, 2),
            new Table(Richness.LOW, 1, 2),
            new Table(Richness.LOW, 2, 2),
            new Table(Richness.LOW, 3, 2),
            new Table(Richness.LOW, 4, 2),
            new Table(Richness.LOW, 2, 4),
            new Table(Richness.LOW, 3, 4),
            new Table(Richness.LOW, 4, 4),
            new Table(Richness.LOW, 6, 4)
    );

    private Table getTable(int x, int y) {
        for (Table table : TABLES) {
            if (table.getLocation().getX() == x
                    && table.getLocation().getY() == y) {
                return table;
            }
        }
        return null;
    }

    private Table getTable(Location location) {
        for (Table table : TABLES) {
            if (table.getLocation().equals(location)) {
                return table;
            }
        }
        return null;
    }

    private synchronized void generateFields() {
        Table table;
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                if ((table = getTable(x, y)) != null) {
                    treeMap.put(table.getLocation(), table);
                } else {
                    Floor floor = new Floor(x, y);
                    treeMap.put(floor.getLocation(), floor);
                }
            }
        }
        setChanged();
        notifyObservers(treeMap);
    }

    public synchronized void setTablePriorityAt(Location location, double priority) {
        Table table = getTable(location);
        if (table != null) {
            table.setPriority(priority);
        } else {
            throw new NoSuchElementException("No such table in requested location");
        }
//        setChanged();
//        notifyObservers(treeMap);
    }

    public void notifyThatAllFieldsChanged() {
        setChanged();
        notifyObservers(treeMap);
    }

    public synchronized AbstractField getFieldAt(Location location) {
        return treeMap.get(location);
    }

}
