package pl.edu.amu.wmi.projekt_szi.movement;

import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import pl.edu.amu.wmi.projekt_szi.ApplicationConstants;
import pl.edu.amu.wmi.projekt_szi.model.AbstractField;
import pl.edu.amu.wmi.projekt_szi.model.Location;

import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Created by eryk on 23.05.16.
 */
public class WaiterMovementNavigationGrid extends NavigationGrid<GridCell> implements ApplicationConstants {

    private GridCell[][] gridCell = new GridCell[7][7];

    public WaiterMovementNavigationGrid(TreeMap<Location, AbstractField> treeMap) {
        for (Entry<Location,? extends AbstractField> entry : treeMap.entrySet()) {
            int x = entry.getValue().getLocation().getX();
            int y = entry.getValue().getLocation().getY();
            gridCell[x][y] = new GridCell(x, y, entry.getValue().isWalkable());
        }
        nodes = gridCell;
        setHeight(HEIGHT);
        setWidth(WIDTH);
    }

}
