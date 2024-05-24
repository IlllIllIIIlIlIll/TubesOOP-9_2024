package com.mvz.zombies;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mvz.Zombie;
import com.mvz.Sun;

// Class representing a Ra zombie, which extends the Zombie class
public class Ra extends Zombie {
    private transient ScheduledExecutorService executorService;

    // Initializes a Ra zombie with specific attributes and position
    public Ra(Integer x, Integer y) {
        super("Ra", 250.0f, 100.0f, 1.0f,10.0f, false, x, y);
        initScheduledExecutors();
    }

    // Default constructor initializes position at (0, 0)
    public Ra() {
        this(0, 0); // Default position for loading
    }

    // Initializes the scheduled executor to decrease Sun at regular intervals
    private void initScheduledExecutors() {
        executorService = Executors.newSingleThreadScheduledExecutor();

        // Executes the task every 11 seconds
        executorService.scheduleAtFixedRate(() -> {
            // Ensure only spawned Ra zombies can decrease Sun
            if (getXChar() < 10) {

                // Prevent concurrent modification
                synchronized (Sun.class) {
                    Sun.decreaseSun(25);
                }
            }
        }, 0, 11, TimeUnit.SECONDS);
    }

    @Override
    // Defines the action behavior of the Ra zombie
    public void action() {

    }

    @Override
    // Initializes zombie scheduled executors
    public void initZombieScheduledExecutors() {
        super.initZombieScheduledExecutors();
        initScheduledExecutors();
    }
}
