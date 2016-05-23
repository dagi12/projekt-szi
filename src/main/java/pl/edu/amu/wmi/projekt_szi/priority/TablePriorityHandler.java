/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.projekt_szi.priority;

import java.io.IOException;

import net.sourceforge.jFuzzyLogic.FIS;
import pl.edu.amu.wmi.projekt_szi.ApplicationConstants;
import pl.edu.amu.wmi.projekt_szi.model.Location;
import pl.edu.amu.wmi.projekt_szi.lifecycle.Table;

import static net.sourceforge.jFuzzyLogic.FIS.*;

public class TablePriorityHandler implements ApplicationConstants {

    private final FIS fuzzyLogicHandler;

    public TablePriorityHandler() {
        fuzzyLogicHandler = load(FCL_FILE_NAME, VERBOSE_MODE);
        if (fuzzyLogicHandler == null) {
            try {
                throw new IOException("Cannot load file " + FCL_FILE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Double getTablePriority(Table table, Location location) {
        fuzzyLogicHandler.setVariable("time", table.getTime());
        fuzzyLogicHandler.setVariable("richness", table.getRichness());
        Double calculatedDistance;
        Double manhattanDistance = (double) location.getManhattanDistanceTo(table.getLocation());

        calculatedDistance = (manhattanDistance / maxDistance()) * 100;
        fuzzyLogicHandler.setVariable("distance", calculatedDistance);

        fuzzyLogicHandler.evaluate();

        return fuzzyLogicHandler.getVariable("priority").getValue();
    }

    private Double maxDistance() {
        return (double) WIDTH + HEIGHT - 2.0;
    }

    public synchronized Table maxPriorityTable() {
        double max = 0;
        Location l = new Location(0, 0);
        for (java.util.Map.Entry<Location, Table> entry : treeMap.entrySet()) {
            if ((max < entry.getValue().getPriority())) {
                max = entry.getValue().getPriority();
                l = entry.getKey();
            }
        }
        return treeMap.get(l);
    }

}
