package com.kingpixel.cobblebosses;

import java.lang.reflect.Method;

public class InspectCobblemon {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.cobblemon.mod.common.battles.pokemon.BattlePokemon");
            for (Method m : clazz.getMethods()) {
                System.out.println(m.getName() + " - " + m.getReturnType().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
