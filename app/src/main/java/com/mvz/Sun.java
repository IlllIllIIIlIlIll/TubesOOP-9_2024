package com.mvz;

public class Sun {
    private static Integer value = 10000000;

    public static synchronized Integer getSun() {
        return value;
    }

    public static synchronized void setSun(Integer value) {
        Sun.value = value;
    }

    public static synchronized void decreaseSun(Integer value) {
        Sun.value = Math.max(0, Sun.value - value); // min sun value = 0
    }

    public static synchronized void increaseSun(Integer value) {
        Sun.value += value;
    }
}
