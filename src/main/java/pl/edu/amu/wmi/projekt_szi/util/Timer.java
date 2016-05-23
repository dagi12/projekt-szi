package pl.edu.amu.wmi.projekt_szi.util;

/**
 * Created by eryk on 23.05.16.
 */
public class Timer {

    private Long startTime;

    public Integer getCurrentTime() {
        currentTime = System.currentTimeMillis() - startTime;
        return Math.round(currentTime / 1000);
    }

    private Long currentTime;

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        if (!on) {
            currentTime = 0L;
        } else {
            startTime = System.currentTimeMillis();
        }
    }

    private boolean on;


}
