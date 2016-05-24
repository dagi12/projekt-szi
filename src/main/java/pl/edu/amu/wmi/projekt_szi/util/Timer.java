package pl.edu.amu.wmi.projekt_szi.util;

public class Timer {

    private Long startTime;

    private Long currentTime;

    private boolean on;

    public Integer getCurrentTime() {
        currentTime = System.currentTimeMillis() - startTime;
        return Math.round(currentTime / 1000);
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
        if (!on) {
            currentTime = 0L;
        } else {
            startTime = System.currentTimeMillis();
        }
    }


}
