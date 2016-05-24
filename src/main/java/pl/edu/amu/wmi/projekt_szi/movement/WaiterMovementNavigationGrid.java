package pl.edu.amu.wmi.projekt_szi.movement;

import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import pl.edu.amu.wmi.projekt_szi.ApplicationConstants;
import pl.edu.amu.wmi.projekt_szi.model.AbstractField;
import pl.edu.amu.wmi.projekt_szi.model.Location;

import java.util.Map.Entry;
import java.util.TreeMap;

class WaiterMovementNavigationGrid extends NavigationGrid<GridCell> implements ApplicationConstants {

    public WaiterMovementNavigationGrid(TreeMap<Location, AbstractField> treeMap) {
        GridCell[][] gridCell = new GridCell[16][16];
        for (Entry<Location, ? extends AbstractField> entry : treeMap.entrySet()) {
            int x = entry.getValue().getLocation().getX();
            int y = entry.getValue().getLocation().getY();
            gridCell[x][y] = new GridCell(x, y, entry.getValue().isWalkable());
        }
        nodes = gridCell;
        setHeight(HEIGHT);
        setWidth(WIDTH);
    }

}
