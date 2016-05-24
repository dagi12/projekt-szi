/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.projekt_szi.priority;

import net.sourceforge.jFuzzyLogic.FIS;
import pl.edu.amu.wmi.projekt_szi.ApplicationConstants;
import pl.edu.amu.wmi.projekt_szi.model.Location;

import java.io.IOException;

import static net.sourceforge.jFuzzyLogic.FIS.load;

class FuzzyLogicService implements ApplicationConstants {

    private final FIS fuzzyLogicHandler;

    public FuzzyLogicService() {
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
        fuzzyLogicHandler.setVariable("richness", table.getRichness().getValue());
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

}
