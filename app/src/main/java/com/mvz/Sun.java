package com.mvz;

public class Sun {
    // Static variable to store the sun value
    private static Integer value;

    // Synchronized method to get the current sun value
    public static synchronized Integer getSun() {
        return value;
    }

    // Synchronized method to set a new sun value
    public static synchronized void setSun(Integer value) {
        Sun.value = value;
    }

    // Synchronized method to decrease the sun value by a given amount
    public static synchronized void decreaseSun(Integer value) {
        Sun.value = Math.max(0, Sun.value - value); // min sun value = 0
    }

    // Synchronized method to increase the sun value by a given amount
    public static synchronized void increaseSun(Integer value) {
        Sun.value += value;
    }
}
