package pl.edu.amu.wmi.projekt_szi.model;


import pl.edu.amu.wmi.projekt_szi.movement.Waiter;
import pl.edu.amu.wmi.projekt_szi.priority.Table;
import pl.edu.amu.wmi.projekt_szi.priority.Table.Richness;
import pl.edu.amu.wmi.projekt_szi.util.LocationFinder;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.TreeMap;

public class Map extends Observable {

    public List<Table> TABLES = Arrays.asList(
            new Table(Richness.POOR, 0, 2),
            new Table(Richness.POOR, 1, 2),
            new Table(Richness.POOR, 2, 2),
            new Table(Richness.POOR, 3, 2),
            new Table(Richness.POOR, 4, 2),
            new Table(Richness.POOR, 2, 4),
            new Table(Richness.POOR, 3, 4),
            new Table(Richness.POOR, 4, 4),
            new Table(Richness.POOR, 6, 4)
    );

    private volatile TreeMap<Location, AbstractField> treeMap;

    private Waiter waiter;

    public Map() {
        this.waiter = new Waiter();
        this.treeMap = new TreeMap<>();
        generateFields();
    }

    public void setFieldAt(Location location, AbstractField abstractField) {
        treeMap.put(location, abstractField);
    }

    public List<Table> getTables() {
        return TABLES;
    }

    private synchronized void generateFields() {
        Table table;
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                if ((table = LocationFinder.FindInList(TABLES, x, y)) != null) {
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


    public synchronized AbstractField getFieldAt(Location location) {
        return treeMap.get(location);
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public TreeMap<Location, AbstractField> getTreeMap() {
        return treeMap;
    }
}
