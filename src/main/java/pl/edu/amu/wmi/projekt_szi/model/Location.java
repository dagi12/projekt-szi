/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.projekt_szi.model;

public class Location implements Comparable<Location> {

    int x;

    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Double getEuclideanDistanceTo(Location location) {
        Double a = Math.abs((double) (location.getX() - this.x));
        Double b = Math.abs((double) (location.getY() - this.y));
        return Math.sqrt((a * a + b * b));

    }
    
    public int getManhattanDistanceTo(Location location) {
        int a = Math.abs(location.getX() - this.x);
        int b = Math.abs(location.getY() - this.y);
        return a + b;
    }

    @Override
    public int compareTo(Location o) {
        if (this.x == o.x) {
            if (this.y == o.y) {
                return 0;
            } else if (this.y < o.y) {
                return -1;
            } else if (this.y > o.y) {
                return 1;
            }
        } else {
            if (this.x < o.x) {
                return -1;
            } else if (this.x > o.x) {
                return 1;
            }
        }
        return 0;
    }
}
