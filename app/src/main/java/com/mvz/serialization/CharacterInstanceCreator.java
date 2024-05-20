package com.mvz.serialization;

import com.google.gson.InstanceCreator;
import com.mvz.Character;
import java.lang.reflect.Type;

public class CharacterInstanceCreator implements InstanceCreator<Character> {
    @Override
    public Character createInstance(Type type) {
        return new Character("DefaultCharacter", 100.0f, false, 1.0f, 10.0f, 0, 0) {
            @Override
            public void action() {
            }
        };
    }
}
