package pl.edu.amu.wmi.projekt_szi.movement;

import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import pl.edu.amu.wmi.projekt_szi.model.Location;
import pl.edu.amu.wmi.projekt_szi.model.Map;
import pl.edu.amu.wmi.projekt_szi.util.Pair;

import java.util.TreeMap;

/**
 * Created by eryk on 23.05.16.
 */
public class WaiterMovementNavigationGrid extends NavigationGrid<GridCell> {

    private GridCell[][] gridCell = new GridCell[7][7];

    public WaiterMovementNavigationGrid(TreeMap<Location, Field> treeMap) {
        for (java.util.Map.Entry<Location, Field> entry : treeMap.entrySet()) {
            int x = entry.getValue().getLocation().getX();
            int y = entry.getValue().getLocation().getY();
            gridCell[x][y] = new GridCell(x, y, entry.getValue().getWalkable());
        }
        for (Pair pair : Map.getInstance().treeLocations) {
            int x = pair.getA();
            int y = pair.getB();
            gridCell[x][y] = new GridCell(x, y, false);
        }
        nodes = gridCell;
        setHeight(7);
        setWidth(7);
    }

    @Override
    public boolean isWalkable(GridCell node) {
        return node.isWalkable();
    }

}
