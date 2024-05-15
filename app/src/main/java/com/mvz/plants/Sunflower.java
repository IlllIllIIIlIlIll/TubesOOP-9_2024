package com.mvz.plants;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mvz.Plant;
import com.mvz.Sun; 

public class Sunflower extends Plant {
    private ScheduledExecutorService executorService;
    
    public Sunflower(Integer x, Integer y) {
        super("Sunflower", 50, 100.0f,  0.0f, 0.0f, 0, 10, false, x, y);
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public Sunflower() {
        super("Sunflower", 50, 100.0f,  0.0f, 0.0f, 0, 10, false);
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    // produce 25 sun per 10 sec
    public void action(){
        executorService.scheduleAtFixedRate(() -> Sun.increaseSun(25), 0, 3, TimeUnit.SECONDS);
    }
}
