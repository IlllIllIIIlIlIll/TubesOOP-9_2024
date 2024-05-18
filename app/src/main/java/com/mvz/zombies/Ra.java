package com.mvz.zombies;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mvz.Zombie;
import com.mvz.Sun;

public class Ra extends Zombie {
    private transient ScheduledExecutorService executorService;

    public Ra(Integer x, Integer y) {
        super("Ra", 250.0f, 100.0f, 1.0f, 7.0f, false, x, y);
        initScheduledExecutors();
    }

    public Ra() {
        this(0, 0); // Default parameter for load
    }

    private void initScheduledExecutors() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            if (getXChar() < 10) {
                synchronized (Sun.class) {
                    Sun.decreaseSun(25);
                }
            }
        }, 0, 11, TimeUnit.SECONDS);
    }

    @Override
    public void action() {
        // Custom action for Ra
    }

    @Override
    public void initZombieScheduledExecutors() {
        super.initZombieScheduledExecutors();
        initScheduledExecutors();
    }
}
