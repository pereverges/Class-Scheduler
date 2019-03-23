package com.prop.fib.horaris.domain.Test;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.Enumerations.TipusUser;
import com.prop.fib.horaris.domain.Classes.*;
import com.prop.fib.horaris.presentation.inout;

import java.util.ArrayList;
import java.util.List;

public class DriverRestriccions {

    private static Restriccions a;
    private inout sc = new inout();

    public void Executa() throws Exception{


        mostrarMenu();
        int op;
        op = sc.readint();

        while (op != 0) {
            switch (op) {
                case 1:
                    DriverAutomatico();
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
            System.out.println("Escull un altra opcio");
            op = sc.readint();
        }
    }

    private void  mostrarMenu() throws Exception{
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                    DRIVERS RESTRICCIONS                          -\n");
        sc.writeln("-           [opcio   Operacio(Atributs)                            -\n");
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("-   0 Sortir DriverUser                                            -\n");
        sc.writeln("-   1 DriverAutomatico()                                           -\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("--------------------------------------------------------------------\n");
    }

    public static void DriverAutomatico(){
        PeriodeLectiu periodeLectiu = new PeriodeLectiu("Periode1", "P1", 8,20,DiaSetmana.DILLUNS,DiaSetmana.DIVENDRES);
        Horari HorariDins = new Horari(8, 2, DiaSetmana.DILLUNS);
        Horari HorariFora1 = new Horari(7, 2, DiaSetmana.DILLUNS);
        Horari HorariFora2 = new Horari(20, 2, DiaSetmana.DILLUNS);
        System.out.println("Prova 1");
        System.out.println();
        System.out.println("Horari dins i acaba dins de periodeLectiu (sortide esperada True)");
        System.out.println(a.horariDinsPeriodeLectiu(HorariDins, periodeLectiu));
        System.out.println("Horari comença fora periodeLectiu (sortida esperada False)");
        System.out.println(a.horariDinsPeriodeLectiu(HorariFora1, periodeLectiu));
        System.out.println("Horari dins de periodeLectiu pero acaba fora (sortide esperada False)");
        System.out.println(a.horariDinsPeriodeLectiu(HorariFora2, periodeLectiu));
        //----------------
        System.out.println();
        System.out.println("Prova 2");
        System.out.println();
        GrupAssignatura grupAssignatura = new GrupAssignatura(10, 20, TipusAula.TEORIA, "PROP", null, null);
        Aula aula1 = new Aula("A5202", TipusAula.TEORIA, 30);
        Aula aula2 = new Aula("A5202", TipusAula.TEORIA, 10);
        System.out.println("GrupAssignatura cap a l'aula (sortide esperada True)");
        System.out.println(a.grupFitsAula(grupAssignatura, aula1));
        System.out.println("GrupAssignatura no cap a l'aula (sortide esperada False)");
        System.out.println(a.grupFitsAula(grupAssignatura, aula2));
        //----------------
        System.out.println();
        System.out.println("Prova 3");
        System.out.println();
        Aula aula3 = new Aula("A5202", TipusAula.LABORATORI, 30);
        GrupAssignatura grupAssignatura1 = new GrupAssignatura(10, 20, TipusAula.TEORIA, "PROP", aula1, null);
        GrupAssignatura grupAssignatura2 = new GrupAssignatura(10, 20, TipusAula.TEORIA, "PROP", aula3, null);
        System.out.println("GrupAssignatura te assignada aula correcte de tipus (sortide esperada True)");
        System.out.println(a.aulaCorrecta(grupAssignatura1, grupAssignatura1.getAula()));
        System.out.println("GrupAssignatura no te assignada aula correcte de tipus (sortide esperada False)");
        System.out.println(a.aulaCorrecta(grupAssignatura2, grupAssignatura2.getAula()));
        //----------------
        System.out.println();
        System.out.println("Prova 4");
        System.out.println();
        Horari horari3 = new Horari(20, 2, DiaSetmana.DILLUNS);
        Horari horari4 = new Horari(20, 2, DiaSetmana.DIMARTS);
        GrupAssignatura grupAssignatura3 = new GrupAssignatura(10, 20, TipusAula.TEORIA, "PROP", aula1, horari3);
        GrupAssignatura grupAssignatura4 = new GrupAssignatura(11, 20, TipusAula.LABORATORI, "PROP", aula3, horari3);
        GrupAssignatura grupAssignatura5 = new GrupAssignatura(11, 20, TipusAula.LABORATORI, "PROP", aula3, horari4);
        List<GrupAssignatura> l1 = new ArrayList<>();
        l1.add(grupAssignatura3);
        System.out.println("Teoria i laboratori en el mateix dia (sortide esperada False)");
        System.out.println(a.teoriaAndLabDifferentDay(l1, grupAssignatura4));
        System.out.println("Teoria i laboratori en diferents dies (sortide esperada True)");
        System.out.println(a.teoriaAndLabDifferentDay(l1, grupAssignatura5));
        //----------------
        System.out.println();
        System.out.println("Prova 5");
        System.out.println();
        Horari horari6 = new Horari(8, 2, DiaSetmana.DILLUNS);
        Horari horari7 = new Horari(8, 2, DiaSetmana.DIMARTS);
        GrupAssignatura grupAssignatura6 = new GrupAssignatura(10, 20, TipusAula.TEORIA, "PROP", aula1, horari6);
        GrupAssignatura grupAssignatura7 = new GrupAssignatura(20, 20, TipusAula.TEORIA, "PROP", aula1, horari6);
        GrupAssignatura grupAssignatura8 = new GrupAssignatura(30, 20, TipusAula.TEORIA, "PROP", aula1, horari7);
        List<GrupAssignatura> l2 = new ArrayList<>();
        l2.add(grupAssignatura6);
        System.out.println("Solapament de aula, hora i dia (sortide esperada False)");
        System.out.println(a.gruposDiferenteAula(l2, grupAssignatura7));
        System.out.println("No hi ha solapament de aula, hora i dia (sortide esperada True)");
        System.out.println(a.gruposDiferenteAula(l2, grupAssignatura8));
    }

}
