package com.prop.fib.horaris.domain.Test;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.domain.Classes.Aula;
import com.prop.fib.horaris.domain.Classes.PeriodeLectiu;
import com.prop.fib.horaris.presentation.inout;

import static com.prop.fib.horaris.data.CtrlFitxerPeriodeLectiu.intToDia;

public class DriverPeriodeLectiu {

    private static PeriodeLectiu a;
    private inout sc = new inout();

    public void Executa() throws Exception{

        mostrarMenu();
        int op;
        op = sc.readint();

        while (op != 0) {
            switch (op) {
                case 1:
                    TestPeriodeLectiu(sc);
                    break;
                case 2:
                    TestConsultarNom();
                    break;
                case 3:
                    TestConsultarId();
                    break;
                case 4:
                    TestConsultarHoraInici();
                    break;
                case 5:
                    TestConsultarHoraFinal();
                    break;
                case 6:
                    TestConsultarDiaIni();
                    break;
                case 7:
                    TestConsultarDiaFi();
                    break;
                case 8:
                    TestModificarNom(sc);
                    break;
                case 9:
                    TestModificarId(sc);
                    break;
                case 10:
                    TestModificarHoraIni(sc);
                    break;
                case 11:
                    TestModificarHoraFi(sc);
                    break;
                case 12:
                    TestModificarDiaIni(sc);
                    break;
                case 13:
                    TestModificarDiaFi(sc);
                    break;
                case 14:
                    Testprint();
                    break;
                case 15:
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
        sc.writeln("-                   DRIVERS PERIODE LECTIU                         -\n");
        sc.writeln("-           [opcio   Operacio(Atributs)                            -\n");
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("-   0  Sortir DriverPeriodeLectiu                                  -\n");
        sc.writeln("-   1  TestPeriodeLectiu(String nom, String Id, int HoraIni,       -\n");
        sc.writeln("-   int HoraFi, int DiaIni, intDiaFi)                              -\n");
        sc.writeln("-   2  TestConsultarNom()                                          -\n");
        sc.writeln("-   3  TestConsultarId()                                           -\n");
        sc.writeln("-   4  TestConsultarHoraIni()                                      -\n");
        sc.writeln("-   5  TestConsultarHoraFi()                                       -\n");
        sc.writeln("-   6  TestConsultarDiaIni()                                       -\n");
        sc.writeln("-   7  TestConsultarDiaFi()                                        -\n");
        sc.writeln("-   8  TestModificarNom(String nom)                                -\n");
        sc.writeln("-   9  TestModificarId(String id)                                  -\n");
        sc.writeln("-   10 TestModificarHoraIni(int n)                                 -\n");
        sc.writeln("-   11 TestModificarHoraFi(int n)                                  -\n");
        sc.writeln("-   12 TestModificarDiaIni(int n)                                  -\n");
        sc.writeln("-   13 TestModificarDiaFi(int n)                                   -\n");
        sc.writeln("-   14 TestPrintPeriodeLectiu()                                    -\n");
        sc.writeln("-   15 DriverAutomatic()                                           -\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("--------------------------------------------------------------------\n");
    }

    public static void DriverAutomatico(){
        System.out.println("Atributs inicials");
        a = new PeriodeLectiu("Periode1", "P1", 8,20,DiaSetmana.DILLUNS,DiaSetmana.DIVENDRES);
        a.print();
        System.out.println("Canvi de Nom a Periode2");
        System.out.println("Canvi de id a P2");
        System.out.println("Canvi de hora inici 9");
        System.out.println("Canvi de hora final 19");
        System.out.println("Canvi de diaI DIMARTS");
        System.out.println("Canvi de diaF DISSABTE");
        System.out.println();
        System.out.println("Resultat dels canvis");
        a.setNom("Periode2");
        a.setId("P2");
        a.setHoraInici(9);
        a.setHoraFinal(19);
        a.setDataInici(DiaSetmana.DIMARTS);
        a.setDataFinal(DiaSetmana.DISSABTE);
        a.print();
    }

    public static void TestPeriodeLectiu(inout sc) {
        try {
            sc.writeln("Escriu nom");
            String nom = sc.readword();
            sc.writeln("Escriu id");
            String id = sc.readword();
            sc.writeln("Escriu horaInici");
            int horaIni = sc.readint();
            sc.writeln("Escriu horaFi");
            int horaFi = sc.readint();
            sc.writeln("Escriu diaIni (entre 1 i 7");
            int DiaIni = sc.readint();
            sc.writeln("Escriu diaFi (entre 1 i 7");
            int DiaFi = sc.readint();

            DiaSetmana ini = intToDia(DiaIni);
            DiaSetmana fi = intToDia(DiaFi);

            a = new PeriodeLectiu(nom,id,horaIni,horaFi,ini,fi);
            sc.writeln("PeriodeLectiu entrada correctament");
        } catch (Exception e) {
            System.out.print(e);
        }
    }




    public static void TestConsultarNom() {
        try {
            System.out.println("Nom " + a.getNom());
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestConsultarId() {
        try {
            System.out.println("Id " + a.getId());
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestConsultarHoraInici() {
        try {
            System.out.println("Hora Ini " + a.getHoraInici());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarHoraFinal() {
        try {
            System.out.println("Hora Fi " + a.getHoraFinal());
        } catch (Exception e) {
            System.out.print(e);
        }
    }



    public static void TestConsultarDiaIni() {
        try {
            System.out.println("Dia Ini " + a.getDataInici());
        } catch (Exception e) {
            System.out.print(e);
        }
    }



    public static void TestConsultarDiaFi() {
        try {
            System.out.println("Dia Fi " + a.getDataFinal());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarNom(inout sc) {
        try {
            sc.writeln("Escriu nom");
            String s = sc.readword();
            a.setNom(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestModificarId(inout sc) {
        try {
            sc.writeln("Escriu id");
            String s = sc.readword();
            a.setId(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestModificarHoraIni(inout sc) {
        try {
            sc.writeln("Escriu horaIni");
            int s = sc.readint();
            a.setHoraInici(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarHoraFi(inout sc) {
        try {
            sc.writeln("Escriu horaFi");
            int s = sc.readint();
            a.setHoraFinal(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarDiaIni(inout sc) {
        try {
            sc.writeln("Escriu diaIni (int entre 1 i 7)");
            int s = sc.readint();
            DiaSetmana d = intToDia(s);
            a.setDataInici(d);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarDiaFi(inout sc) {
        try {
            sc.writeln("Escriu diaFi (int entre 1 i 7)");
            int s = sc.readint();
            DiaSetmana d = intToDia(s);
            a.setDataFinal(d);
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
