package pl.edu.amu.wmi.projekt_szi.model;

import java.awt.*;

public abstract class AbstractField {

    protected static final int TILE_SIZE = 80;

    private Location location;

    private final FieldType fieldType;

    protected AbstractField(int x, int y, FieldType fieldType) {
        this.location = new Location(x, y);
        this.fieldType = fieldType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public abstract void draw(Graphics g);

    public abstract boolean isWalkable();

    protected enum FieldType {
        FLOOR, WAITER, TABLE
    }

}
