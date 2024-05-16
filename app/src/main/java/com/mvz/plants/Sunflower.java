package com.mvz.plants;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mvz.Plant;
import com.mvz.Sun;

public class Sunflower extends Plant {
    private transient ScheduledExecutorService executorService;
    private boolean readyToGenerateSun = true;

    public Sunflower(Integer x, Integer y) {
        super("Sunflower", 50, 100.0f,  0.0f, 0.0f, 0, 10, false, x, y);
        executorService = Executors.newSingleThreadScheduledExecutor();
        scheduleSunGeneration();
    }

    public Sunflower() {
        super("Sunflower", 50, 100.0f,  0.0f, 0.0f, 0, 10, false);
    }

    private void scheduleSunGeneration() {
        executorService.scheduleAtFixedRate(() -> {
            if (readyToGenerateSun) {
                Sun.increaseSun(25);
                readyToGenerateSun = false;
                executorService.schedule(() -> readyToGenerateSun = true, 3, TimeUnit.SECONDS);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void initExecutorService() {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void action() {

    }

    public void initSunflowerExecutorService() {
        initExecutorService();
    }
}
