package controller;

import java.util.Timer;
import java.util.TimerTask;

public class MainTestes {
    public static void main(String[] args) {
        
        ControllerRegistry controllerR = new ControllerRegistry();
        
        Timer timer = new Timer();
        int delay = 0;
        int interval = 2000;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                controllerR.registerInDatabaseNewRegistry();
            }
        }, delay, interval);
    }
}
