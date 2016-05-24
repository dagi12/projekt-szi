package pl.edu.amu.wmi.projekt_szi.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractField {

    protected static final int TILE_SIZE = 80;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    private volatile Location location;

    private FieldType fieldType;

    public AbstractField(int x, int y, FieldType fieldType) {
        this.location = new Location(x, y);
        this.fieldType = fieldType;
    }

    protected enum FieldType {
        FLOOR, WAITER, TABLE;
    }

    public abstract void draw(Graphics g);

    public abstract boolean isWalkable();

}
