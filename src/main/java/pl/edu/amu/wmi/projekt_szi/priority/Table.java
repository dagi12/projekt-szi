/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.projekt_szi.priority;


import pl.edu.amu.wmi.projekt_szi.model.AbstractField;
import pl.edu.amu.wmi.projekt_szi.util.Timer;

public class Table extends AbstractField implements Comparable<Table> {

    @Override
    public int compareTo(Table o) {
        double result = this.getPriority() - o.getPriority();
        return (int) result;
    }

    public Integer getTime() {
        return timer.getCurrentTime();
    }

    private Timer timer = new Timer();

    public void setRichness(Richness richness) {
        this.richness = richness;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    private Richness richness;

    private Double priority;

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        timer.setOn(waiting);
        this.waiting = waiting;
    }

    private boolean waiting;

    public Table(Richness r, Integer x, Integer y) {
        super(x, y, FieldType.TABLE);
        this.waiting = false;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    public Richness getRichness() {
        return richness;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    public enum Richness {

        POOR(1), MEDIUM(2), RICH(3);

        public int getValue() {
            return value;
        }

        private int value;

        Richness(int value) {
            this.value = value;
        }

    }


}
