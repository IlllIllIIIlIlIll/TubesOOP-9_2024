package com.mvz.plants;

import com.mvz.Plant;

public interface PlantComponent{
    boolean canBePlacedOnLilyPad();
    boolean addOnLilypad(Plant plant);
    void removeOnLilypad(Plant plant);
}