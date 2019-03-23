package com.prop.fib.horaris.Enumerations;

import java.util.Random;

public enum DiaSetmana {
    DILLUNS,
    DIMARTS,
    DIMECRES,
    DIJOUS,
    DIVENDRES,
    DISSABTE,
    DIUMENGE;

    public static DiaSetmana getRandomDia() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    public static String[] getDies(int numDies) {
        String[] dies = new String[numDies + 1];
        if (numDies >= 0) dies[0] = ""; // Columne de les hores de la taula.
        if (numDies >= 1) dies[1] = DILLUNS.toString();
        if (numDies >= 2) dies[2] = DIMARTS.toString();
        if (numDies >= 3) dies[3] = DIMECRES.toString();
        if (numDies >= 4) dies[4] = DIJOUS.toString();
        if (numDies >= 5) dies[5] = DIVENDRES.toString();
        if (numDies >= 6) dies[6] = DISSABTE.toString();
        if (numDies >= 7) dies[7] = DIUMENGE.toString();
        return dies;
    }
}
