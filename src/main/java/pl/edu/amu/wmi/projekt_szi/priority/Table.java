/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.projekt_szi.priority;


import pl.edu.amu.wmi.projekt_szi.model.AbstractField;
import pl.edu.amu.wmi.projekt_szi.util.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Table extends AbstractField implements Comparable<Table> {

    private static final String SERVED_TABLE_PATH = "res/servedTable.png";

    private static final String WAITING_TABLE_PATH = "res/waitingTable.png";

    private static BufferedImage waitingTable;

    private static BufferedImage servedTable;

    private final Timer timer = new Timer();

    private final Richness richness;

    private double priority;

    private boolean waiting;

    public Table(Richness richness, Integer x, Integer y) {
        super(x, y, FieldType.TABLE);
        this.waiting = false;
        this.richness = richness;
        try {
            waitingTable = ImageIO.read(new File(WAITING_TABLE_PATH));
            servedTable = ImageIO.read(new File(SERVED_TABLE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Table o) {
        double result = this.getPriority() - o.getPriority();
        return (int) result;
    }

    public Integer getTime() {
        return timer.getCurrentTime();
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        timer.setOn(waiting);
        this.waiting = waiting;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public Richness getRichness() {
        return richness;
    }

    @Override
    public void draw(Graphics g) {
        if (waiting) {
            g.drawImage(waitingTable, TILE_SIZE * getLocation().getX(), TILE_SIZE * getLocation().getY(), null);
        } else {
            g.drawImage(servedTable, TILE_SIZE * getLocation().getX(), TILE_SIZE * getLocation().getY(), null);
        }
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    public enum Richness {

        POOR(1), MEDIUM(2), RICH(3);

        private final int value;

        Richness(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }


}
