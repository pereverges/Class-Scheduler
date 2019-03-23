package com.prop.fib.horaris.domain.Test;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.domain.Classes.Horari;
import com.prop.fib.horaris.domain.Classes.PeriodeLectiu;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HorariTest {

    @Test
    public void solapa() {
        Horari h1 = new Horari(14, 2 , DiaSetmana.DIJOUS);
        Horari h2 = new Horari(14, 2 , DiaSetmana.DIJOUS);
        boolean solapa = true;
        assertEquals(h1.solapa(h2), solapa);
    }

    @Test
    public void generateAllPossibleHoraris() {
        PeriodeLectiu p = new PeriodeLectiu("TEST", "X", 10, 14, DiaSetmana.DILLUNS, DiaSetmana.DIMARTS);
        List<Horari> horaris = new ArrayList<>();
        horaris.add(new Horari(10, 2, DiaSetmana.DILLUNS));
        horaris.add(new Horari(11, 2, DiaSetmana.DILLUNS));
        horaris.add(new Horari(12, 2, DiaSetmana.DILLUNS));
        horaris.add(new Horari(13, 2, DiaSetmana.DILLUNS));
        horaris.add(new Horari(10, 2, DiaSetmana.DIMARTS));
        horaris.add(new Horari(11, 2, DiaSetmana.DIMARTS));
        horaris.add(new Horari(12, 2, DiaSetmana.DIMARTS));
        horaris.add(new Horari(13, 2, DiaSetmana.DIMARTS));
        Assert.assertEquals(Horari.generateAllPossibleHoraris(p), horaris);
    }
}