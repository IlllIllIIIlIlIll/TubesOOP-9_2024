package com.mvz.plants;

public interface PlantComponent{
    boolean canBePlacedOnLilyPad();
    boolean addOnLilypad(PlantComponent plant);
    void removeOnLilypad(PlantComponent plant);

}