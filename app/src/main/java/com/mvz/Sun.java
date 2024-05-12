package com.mvz;

public class Sun {
    private static Integer value = 25;

    public static Integer getSun(){
        return value;
    }

    public static void setSun(Integer value){
        Sun.value = value;
    }

    public static void decreaseSun(Integer value){
        Sun.value-=value;
    }

    public static void increaseSun(Integer value){
        Sun.value+=value;
    }
}