package com.mvz.serialization;

import com.google.gson.InstanceCreator;
import com.mvz.Character;
import java.lang.reflect.Type;

// Implements the InstanceCreator interface for Gson serialization/deserialization of Character objects
public class CharacterInstanceCreator implements InstanceCreator<Character> {
    @Override
    // Overridden method to create a new instance of Character during deserialization
    public Character createInstance(Type type) {
        // Returns a new Character object initialized with default values
        return new Character("DefaultCharacter", 100.0f, false, 1.0f, 10.0f, 0, 0) {
            @Override
            // Provides an empty implementation for the action method, as required by the Character interface
            public void action() {
            }
        };
    }
}
