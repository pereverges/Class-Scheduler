package com.prop.fib.horaris.domain.Test;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.domain.Classes.Aula;
import com.prop.fib.horaris.domain.Classes.Horari;
import com.prop.fib.horaris.presentation.inout;

import static com.prop.fib.horaris.data.CtrlFitxerPeriodeLectiu.intToDia;

public class DriverHorari {

    private static Horari a;
    private inout sc = new inout();

    public void Executa() throws Exception{


        mostrarMenu();
        int op;
        op = sc.readint();

        while (op != 0) {
            switch (op) {
                case 1:
                    TestHorari(sc);
                    break;
                case 2:
                    TestConsultarHoraInici();
                    break;
                case 3:
                    TestConsultarDurada();
                    break;
                case 4:
                    TestConsultarDiaSetmana();
                    break;
                case 5:
                    TestModificarHoraInici(sc);
                    break;
                case 6:
                    TestModificarDurada(sc);
                    break;
                case 7:
                    TestModificarDiaSetmana(sc);
                    break;
                case 8:
                    TestSolapa(sc);
                    break;
                case 9:
                    TestEquals(sc);
                    break;
                case 10:
                    Testprint();
                    break;
                case 11:
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
        sc.writeln("-                    DRIVERS HORARI                                -\n");
        sc.writeln("-           [opcio   Operacio(Atributs)                            -\n");
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("-   0  Sortir DriverHorari                                         -\n");
        sc.writeln("-   1  TestHorari(int HoraiInici, int durada, int DiaSetmana)      -\n");
        sc.writeln("-   2  TestConsultarHoraInici()                                    -\n");
        sc.writeln("-   3  TestConsultarDurada()                                       -\n");
        sc.writeln("-   4  TestConsultarDiaSetmana()                                   -\n");
        sc.writeln("-   5  TestModificarHoraInici(int n)                               -\n");
        sc.writeln("-   6  TestModificarDurada(int n)                                  -\n");
        sc.writeln("-   7  TestModificarDiaSetmana(int n)                              -\n");
        sc.writeln("-   8  TestSolapaHorari(horari h)                                  -\n");
        sc.writeln("-   9  TestEquals(horari h)                                        -\n");
        sc.writeln("-   10 TestPrintHorari()                                           -\n");
        sc.writeln("-   11 DriverAutomatico()                                          -\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("--------------------------------------------------------------------\n");
    }


    public static void DriverAutomatico(){
        System.out.println("Atributs inicials");
        a = new Horari(8, 2, DiaSetmana.DILLUNS);
        a.print();
        System.out.println("Canvi de hora a 10");
        System.out.println("Canvi de durada a 1");
        System.out.println("Canvi de dia a DIMARTS");
        System.out.println();
        System.out.println("Resultat dels canvis");
        a.setHoraInici(10);
        a.setDurada(2);
        a.setDia(DiaSetmana.DIMARTS);
        a.print();
        System.out.println();
        System.out.println("Canvi de dia a DIMECRES");
        System.out.println();
        a.setDia(DiaSetmana.DIMECRES);
        System.out.println("Resultat dels canvis");
        a.print();

        System.out.println();
        System.out.println("Comparem dos Horaris diferents (ha de sortir false)");
        Horari b = new Horari(8, 2, DiaSetmana.DIVENDRES);
        System.out.println(a.equals(b));
        System.out.println();
        System.out.println("Comparem dos Horaris iguals (ha de sortir true)");
        System.out.println(a.equals(a));

        System.out.println();
        System.out.println("Comparem dos Horaris que no es solapen (ha de sortir false)");
        System.out.println(a.solapa(b));
        System.out.println();
        System.out.println("Comparem dos Horaris que es solapen (ha de sortir true)");
        System.out.println(a.equals(a));

    }


    public static void TestHorari(inout sc) {
        try {
            sc.writeln("Escriu HoraInici");
            int horaIni = sc.readint();
            sc.writeln("Escriu durada");
            int durada = sc.readint();
            sc.writeln("Escriu DiaSetmana (int entre 1 i 7)");
            int dia = sc.readint();

            DiaSetmana d = intToDia(dia);

            a = new Horari(horaIni, durada, d);
            sc.writeln("Horari entrada correctament");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestSolapa(inout sc) {
        try {
            Horari aux;
            sc.writeln("Escriu l'horari que vols veure si solapen");
            sc.writeln("Escriu HoraInici");
            int horaIni = sc.readint();
            sc.writeln("Escriu durada");
            int durada = sc.readint();
            sc.writeln("Escriu DiaSetmana (int entre 1 i 7)");
            int dia = sc.readint();
            DiaSetmana d = intToDia(dia);

            aux = new Horari(horaIni, durada, d);

            boolean b = a.solapa(aux);
            if (b) sc.writeln("l'horari solapa");
            else sc.writeln("l'horari no solapa");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestEquals(inout sc) {
        try {
            Horari aux;
            sc.writeln("Escriu l'horari que vols comparar");
            sc.writeln("Escriu HoraInici");
            int horaIni = sc.readint();
            sc.writeln("Escriu durada");
            int durada = sc.readint();
            sc.writeln("Escriu DiaSetmana (int entre 1 i 7)");
            int dia = sc.readint();
            DiaSetmana d = intToDia(dia);

            aux = new Horari(horaIni, durada, d);

            boolean b = a.equals(aux);
            if (b) sc.writeln("l'horari es igual");
            else sc.writeln("l'horari no es igual");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarHoraInici() {
        try {
            System.out.println("HoraInici " + a.getHoraInici());
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestConsultarDurada() {
        try {
            System.out.println("Durada " + a.getDurada());
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestConsultarDiaSetmana() {
        try {
            System.out.println("Dia " + a.getDia());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarHoraInici(inout sc) {
        try {
            sc.writeln("Escriu hora");
            int s = sc.readint();
            a.setHoraInici(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarDurada(inout sc) {
        try {
            sc.writeln("Escriu durada");

            int s = sc.readint();
            a.setDurada(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarDiaSetmana(inout sc) {
        try {
            sc.writeln("Escriu dia (int entre 1 i 7)");
            int s = sc.readint();
            DiaSetmana d = intToDia(s);
            a.setDia(d);
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void Testprint() {
        try {
            a.print();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
