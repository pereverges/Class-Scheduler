package com.prop.fib.horaris.domain.Test;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.domain.Classes.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RestriccionsTest {

    @Test
    public void horariDinsPeriodeLectiu() {
        Horari h = new Horari(14, 2 , DiaSetmana.DIJOUS);
        PeriodeLectiu p = new PeriodeLectiu("TEST", "X", 10, 16, DiaSetmana.DILLUNS, DiaSetmana.DIVENDRES);
        boolean actual = Restriccions.horariDinsPeriodeLectiu(h, p);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void grupFitsAula() {
        Aula aula = new Aula("TEST", TipusAula.TEORIA, 50);
        Horari horari = new Horari(14, 2 , DiaSetmana.DIJOUS);
        GrupAssignatura grup = new GrupAssignatura(1, 50, TipusAula.PROBLEMES, "TEST", aula, horari);

        boolean actual = Restriccions.grupFitsAula(grup, grup.getAula());
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void aulaCorrecta() {
        Aula aula = new Aula("TEST", TipusAula.TEORIA, 50);
        Horari horari = new Horari(14, 2 , DiaSetmana.DIJOUS);
        GrupAssignatura grup = new GrupAssignatura(1, 50, TipusAula.PROBLEMES, "TEST", aula, horari);

        boolean actual = Restriccions.aulaCorrecta(grup, grup.getAula());
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void teoriaAndLabDifferentDay() {
        List<GrupAssignatura> solution = new ArrayList<>();
        solution.add(new GrupAssignatura(10, 50, TipusAula.PROBLEMES, "TEST",
                new Aula("TEST", TipusAula.TEORIA, 50),
                new Horari(14, 2 , DiaSetmana.DIMECRES)));

        solution.add(new GrupAssignatura(21, 50, TipusAula.PROBLEMES, "TEST",
                new Aula("TEST", TipusAula.TEORIA, 50),
                new Horari(14, 2 , DiaSetmana.DIJOUS)));

        GrupAssignatura grup = new GrupAssignatura(13, 50, TipusAula.PROBLEMES, "TEST",
                new Aula("TEST", TipusAula.TEORIA, 50),
                new Horari(14, 2 , DiaSetmana.DIMECRES));

        boolean actual = Restriccions.teoriaAndLabDifferentDay(solution, grup);
        boolean expected = false;
        assertEquals(expected, actual);
    }


    @Test
    public void correquisitos() {
        List<GrupAssignatura> solution = new ArrayList<>();
        solution.add(new GrupAssignatura(10, 50, TipusAula.PROBLEMES, "IA",
                new Aula("000", TipusAula.TEORIA, 50),
                new Horari(14, 2 , DiaSetmana.DIMECRES)));
        solution.add(new GrupAssignatura(10, 50, TipusAula.PROBLEMES, "LP",
                new Aula("000", TipusAula.TEORIA, 50),
                new Horari(15, 2 , DiaSetmana.DIMECRES)));

        GrupAssignatura grup = new GrupAssignatura(10, 50, TipusAula.PROBLEMES, "PROP",
                new Aula("111", TipusAula.TEORIA, 50),
                new Horari(14, 2 , DiaSetmana.DIMECRES));

        List<Assignatura> assignatures = new ArrayList<>();

        List<String> correquisits = new ArrayList<>();
        correquisits.add("EDA");
        List<String> inverseCorrequisits = new ArrayList<>();
        inverseCorrequisits.add("IA");
        assignatures.add(new Assignatura("PROP", "PROP", 10, 80, 1, 3,4,
                2, 4, correquisits, inverseCorrequisits, "Inicial"));

        correquisits = new ArrayList<>();
        correquisits.add("PROP");
        inverseCorrequisits = new ArrayList<>();
        inverseCorrequisits.add("APA");
        assignatures.add(new Assignatura("IA", "IA", 10, 80, 1, 3,4,
                2, 4, correquisits, inverseCorrequisits, "Computacio"));

        correquisits = new ArrayList<>();
        correquisits.add("IA");
        assignatures.add(new Assignatura("APA", "APA", 10, 80, 1, 3,4,
                2, 4, correquisits, inverseCorrequisits, "Computacio"));

        boolean actual = Restriccions.correquisitos(solution, grup, assignatures);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void gruposDiferenteAula() {
        List<GrupAssignatura> solution = new ArrayList<>();
        solution.add(new GrupAssignatura(12, 50, TipusAula.PROBLEMES, "TEST",
                new Aula("TEST", TipusAula.TEORIA, 50),
                new Horari(14, 2 , DiaSetmana.DIMECRES)));

        GrupAssignatura grup = new GrupAssignatura(23, 50, TipusAula.PROBLEMES, "TEST",
                new Aula("TEST", TipusAula.TEORIA, 50),
                new Horari(14, 2 , DiaSetmana.DIMECRES));

        boolean actual = Restriccions.gruposDiferenteAula(solution, grup);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void noSolapamentMateixaFase() {
        List<GrupAssignatura> solution = new ArrayList<>();
        solution.add(new GrupAssignatura(10, 50, TipusAula.PROBLEMES, "IA",
                new Aula("000", TipusAula.TEORIA, 50),
                new Horari(14, 2 , DiaSetmana.DIMECRES)));

        solution.add(new GrupAssignatura(10, 50, TipusAula.PROBLEMES, "LP",
                new Aula("000", TipusAula.TEORIA, 50),
                new Horari(15, 2 , DiaSetmana.DIMECRES)));

        GrupAssignatura grup = new GrupAssignatura(10, 50, TipusAula.PROBLEMES, "APA",
                new Aula("111", TipusAula.TEORIA, 50),
                new Horari(14, 2 , DiaSetmana.DIMECRES));

        List<Assignatura> assignatures = new ArrayList<>();

        assignatures.add(new Assignatura("PROP", "PROP", 10, 80, 1, 3,4,
                2, 4, new ArrayList<>(), new ArrayList<>(), "Inicial"));

        assignatures.add(new Assignatura("IA", "IA", 10, 80, 1, 3,4,
                2, 4, new ArrayList<>(), new ArrayList<>(), "Computacio"));

        assignatures.add(new Assignatura("APA", "APA", 10, 80, 1, 3,4,
                2, 4, new ArrayList<>(), new ArrayList<>(), "Computacio"));

        boolean actual = Restriccions.noSolapamentMateixaFase(solution, grup, assignatures);
        boolean expected = false;
        assertEquals(expected, actual);
    }
}