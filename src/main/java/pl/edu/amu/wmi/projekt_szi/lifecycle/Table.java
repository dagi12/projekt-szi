/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.projekt_szi.lifecycle;


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

    public Integer getRichness() {
        return richness.getI();
    }

    public enum Richness {

        LOW(1), MEDIUM(2), HIGH(3);

        public int getI() {
            return i;
        }

        private int i;

        Richness(int i) {
            this.i = i;
        }
    }


}
