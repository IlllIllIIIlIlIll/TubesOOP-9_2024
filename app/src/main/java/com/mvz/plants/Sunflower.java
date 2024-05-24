package com.mvz.plants;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mvz.Plant;
import com.mvz.Sun;

public class Sunflower extends Plant {
    public static long lastPlantedTime;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private transient ScheduledExecutorService executorService;
    private boolean readyToGenerateSun = true;

    public Sunflower(Integer x, Integer y) {
        super("Sunflower", 50, 100.0f,  0.0f, 0.0f, 0, 10, false, x, y);
        initScheduledExecutors();
    }

    public Sunflower() {
        super("Sunflower", 50, 100.0f,  0.0f, 0.0f, 0, 10, false);
    }

    private void initScheduledExecutors() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            synchronized (Sun.class) {
                if (readyToGenerateSun) {
                    Sun.increaseSun(25);
                    readyToGenerateSun = false;
                    executorService.schedule(() -> readyToGenerateSun = true, 3, TimeUnit.SECONDS);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = (currentTime - lastPlantedTime)/1000;
        System.out.println(ANSI_CYAN + "Remaining cooldown time: " + (getCD()-elapsedTime) + ANSI_RESET);
        return elapsedTime >= getCD();
    }

    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    public void action() {
        
    }

    public void initSunflowerExecutorService() {
        initScheduledExecutors();
    }
}
