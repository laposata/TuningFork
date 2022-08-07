package com.dreamtea.utils;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;

import static net.minecraft.state.property.Properties.NOTE;

public class PropertyUtils {
    public static <T extends Comparable<T>> T propertyWithDefault(BlockState state, Property<T> property, T defaultVal){
        return state.getProperties().contains(property)?
                     state.get(property)
                     : defaultVal;
    }

    public static boolean waterlogged(BlockState state){
        return propertyWithDefault(state, Properties.WATERLOGGED, false);
    }

    public static int note(BlockState state){
        return propertyWithDefault(state, NOTE, -1);
    }

}
