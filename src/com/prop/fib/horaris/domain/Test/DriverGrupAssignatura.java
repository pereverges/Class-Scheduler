package com.prop.fib.horaris.domain.Test;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.data.CtrlFitxerPeriodeLectiu;
import com.prop.fib.horaris.domain.Classes.Aula;
import com.prop.fib.horaris.domain.Classes.GrupAssignatura;
import com.prop.fib.horaris.domain.Classes.Horari;
import com.prop.fib.horaris.presentation.inout;

import static com.prop.fib.horaris.data.CtrlFitxerAules.intToTipusAula;
import static com.prop.fib.horaris.data.CtrlFitxerGrupAssig.intToDia;

public class DriverGrupAssignatura {

    private static GrupAssignatura a;
    private inout sc = new inout();

    public void Executa() throws Exception{


        mostrarMenu();
        int op;
        op = sc.readint();

        while (op != 0) {
            switch (op) {
                case 1:
                    TestGrupAssig(sc);
                    break;
                case 2:
                    TestConsultarNumGrup();
                    break;
                case 3:
                    TestConsultarNumPlaces();
                    break;
                case 4:
                    TestConsultarTipusAula();
                    break;
                case 5:
                    TestConsultarIdAssig();
                    break;
                case 6:
                    TestConsultarAula();
                    break;
                case 7:
                    TestConsultarHorari();
                    break;
                case 8:
                    TestModificarNumGrup(sc);
                    break;
                case 9:
                    TestModificarNumPlaces(sc);
                    break;
                case 10:
                    TestModificarTipusAula(sc);
                    break;
                case 11:
                    TestModificarIdAssig(sc);
                    break;
                case 12:
                    TestModificarAula(sc);
                    break;
                case 13:
                    TestModificarHorari(sc);
                    break;
                case 14:
                    Testprint();
                    break;
                case 15:
                    TestGrupAssig2(sc);
                    break;
                case 16:
                    compararAssignatures(sc);
                    break;
                case 17:
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
        sc.writeln("-                    DRIVERS GRUPASSIGNATURA                       -\n");
        sc.writeln("-           [opcio   Operacio(Atributs)                            -\n");
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("-   0   Sortir DriverGrupAssigatura                                -\n");
        sc.writeln("-   1   TestGrupAssig(int numGrup, int numPlaces, int tipusAula,   -\n");
        sc.writeln("-   String, IdAssignatura, Aula aula, Horari horari)               -\n");
        sc.writeln("-   2  TestConsultarNumGrup()                                      -\n");
        sc.writeln("-   3  TestConsultarNumPlaces()                                    -\n");
        sc.writeln("-   4  TestConsultarTipusAula()                                    -\n");
        sc.writeln("-   5  TestConsultarIdAssig()                                      -\n");
        sc.writeln("-   6  TestConsultarAula()                                         -\n");
        sc.writeln("-   7  TestConsultarHorari()                                       -\n");
        sc.writeln("-   8  TestModificarNumGrup(int n)                                 -\n");
        sc.writeln("-   9  TestModificarNumPlaces(int n)                               -\n");
        sc.writeln("-   10 TestModificarTipusAula(int n)                               -\n");
        sc.writeln("-   11 TestModificarIdAssig(String assig)                          -\n");
        sc.writeln("-   12 TestModificarAula(aula a)                                   -\n");
        sc.writeln("-   13 TestModificarHorari(horari h)                               -\n");
        sc.writeln("-   14 TestPrintGrupAssignatura()                                  -\n");
        sc.writeln("-   15 TestGrupAssig2(int numGrup, int numPlaces, int tipusAula,   -\n");
        sc.writeln("-   String, IdAssignatura)                                         -\n");
        sc.writeln("-   16 compararAssignatures(grupAssignatura g)                     -\n");
        sc.writeln("-   17 DriverAutomatico()                                          -\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("--------------------------------------------------------------------\n");
    }

    public static void DriverAutomatico(){
        Aula aula;
        Horari horari;
        aula = new Aula("A5202", TipusAula.LABORATORI, 30);
        horari = new Horari(8,2,DiaSetmana.DILLUNS);
        System.out.println("Atributs inicials");
        a = new GrupAssignatura(10, 20, TipusAula.TEORIA, "PROP", null, null);
        a.print();
        System.out.println("Canvi de numGrup a 11");
        System.out.println("Canvi de numPlaces a 30");
        System.out.println("Canvi de tipusAula a LABORATORI");
        System.out.println("Canvi de idAssig a IDI");
        System.out.println("Canvi afegim aula A5202, LABORATORI, aformanet 30");
        System.out.println("Canvi afegim horari 8, durada 2, DILLUNS");
        System.out.println();
        System.out.println("Resultat dels canvis");
        a.setNumGrup(11);
        a.setNumPlaces(30);
        a.setTipusAula(TipusAula.LABORATORI);
        a.setIdAssignatura("IDI");
        a.setAula(aula);
        a.setHorari(horari);
        a.print();
        Horari horari1 = new Horari(9,2,DiaSetmana.DILLUNS);
        System.out.println();
        System.out.println("Comparem dos GrupAssignatura diferents");
        GrupAssignatura a = new GrupAssignatura(10, 20, TipusAula.TEORIA, "PROP", aula, horari);
        GrupAssignatura b = new GrupAssignatura(10, 20, TipusAula.TEORIA, "PROP", aula, horari1);
        System.out.println(a.equals(b));
        System.out.println();
        System.out.println("Comparem dos GrupAssignatura iguals");
        System.out.println(a.equals(a));

    }

    public static void compararAssignatures(inout sc) throws Exception {
        Aula aula;
        Horari horari;
        GrupAssignatura b;
        sc.writeln("Escriu numGrup");
        int numGrup = sc.readint();
        sc.writeln("Escriu numPlaces");
        int numPlaces = sc.readint();
        sc.writeln("Escriu TipusAula del grup (int entre 1 i 3)");
        int tipus = sc.readint();
        TipusAula a1 = intToTipusAula(tipus);
        sc.writeln("Escriu idAssignatura");
        String idAssignatura = sc.readword();


        sc.writeln("Entra l'aula");
        sc.writeln("Nom aula");
        String nomAula = sc.readword();
        sc.writeln("Tipus de l'aula (int entre 1 i 3)");
        int tipusAula = sc.readint();
        TipusAula a2 = intToTipusAula(tipusAula);
        sc.writeln("Aforament de l'aula");
        int aforament = sc.readint();
        aula = new Aula(nomAula, a2, aforament);

        sc.writeln("Entra l'horari");
        sc.writeln("Escriu HoraInici");
        int horaIni = sc.readint();
        sc.writeln("Escriu durada");
        int durada = sc.readint();
        sc.writeln("Escriu DiaSetmana (int entre 1 i 7)");
        int dia = sc.readint();
        DiaSetmana d = intToDia(dia);
        horari = new Horari(horaIni,durada,d);

        b = new GrupAssignatura(numGrup,numPlaces,a1, idAssignatura,aula,horari);
        if (a == null){
            System.out.println("No has entrat la assignatura principal");
        }
        else {
            if (a.equals(b)) {
                System.out.println("Els dos grupAssignatura son iguals");
            } else System.out.println("Els dos grupAssignatura son diferents");
        }
    }

    public static void TestGrupAssig(inout sc) {
        try {

            Aula aula;
            Horari horari;
            sc.writeln("Escriu numGrup");
            int numGrup = sc.readint();
            sc.writeln("Escriu numPlaces");
            int numPlaces = sc.readint();
            sc.writeln("Escriu TipusAula del grup (int entre 1 i 3)");
            int tipus = sc.readint();
            TipusAula a1 = intToTipusAula(tipus);
            sc.writeln("Escriu idAssignatura");
            String idAssignatura = sc.readword();


            sc.writeln("Entra l'aula");
            sc.writeln("Nom aula");
            String nomAula = sc.readword();
            sc.writeln("Tipus de l'aula (int entre 1 i 3)");
            int tipusAula = sc.readint();
            TipusAula a2 = intToTipusAula(tipusAula);
            sc.writeln("Aforament de l'aula");
            int aforament = sc.readint();
            aula = new Aula(nomAula, a2, aforament);

            sc.writeln("Entra l'horari");
            sc.writeln("Escriu HoraInici");
            int horaIni = sc.readint();
            sc.writeln("Escriu durada");
            int durada = sc.readint();
            sc.writeln("Escriu DiaSetmana (int entre 1 i 7)");
            int dia = sc.readint();
            DiaSetmana d = intToDia(dia);
            horari = new Horari(horaIni,durada,d);

            a = new GrupAssignatura(numGrup,numPlaces,a1, idAssignatura,aula,horari);


            sc.writeln("GrupAssignatura entrada correctament");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestGrupAssig2(inout sc) {
        try {

            Aula aula;
            Horari horari;
            sc.writeln("Escriu numGrup");
            int numGrup = sc.readint();
            sc.writeln("Escriu numPlaces");
            int numPlaces = sc.readint();
            sc.writeln("Escriu TipusAula del grup (int entre 1 i 3)");
            int tipus = sc.readint();
            TipusAula a1 = intToTipusAula(tipus);
            sc.writeln("Escriu idAssignatura");
            String idAssignatura = sc.readword();


            a = new GrupAssignatura(numGrup,numPlaces,a1, idAssignatura,null,null);


            sc.writeln("GrupAssignatura entrada correctament");
        } catch (Exception e) {
            System.out.print(e);
        }
    }



    public static void TestConsultarNumGrup() {
        try {
            System.out.println("NumGrup " + a.getNumGrup());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarNumPlaces() {
        try {
            System.out.println("NumPlaces " + a.getNumPlaces());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarTipusAula() {
        try {
            System.out.println("TipusAula " + a.getTipusAula());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarIdAssig() {
        try {
            System.out.println("IdAssignatura " + a.getIdAssignatura());
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestConsultarAula() {
        try {
            System.out.println("Aula: "+ a.getAula().getNom());
            System.out.println("TipusAula: "+ a.getAula().getTipusAula());
            System.out.println("Aformanet: "+ a.getAula().getAforament());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarHorari() {
        try {
            System.out.println("Hora inici: " + a.getHorari().getHoraInici());
            System.out.println("Durada: " + a.getHorari().getDurada());
            System.out.println("Dia: " + a.getHorari().getDia());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarNumGrup(inout sc) {
        try {
            sc.writeln("Escriu numGrup");
            int s = sc.readint();
            a.setNumGrup(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestModificarNumPlaces(inout sc) {
        try {
            sc.writeln("Escriu numPlaces");
            int s = sc.readint();
            a.setNumPlaces(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarTipusAula(inout sc) {
        try {
            sc.writeln("Escriu aula(int entre 1 i 3)");

            int tipus = sc.readint();
            TipusAula tipo = intToTipusAula(tipus);
            a.setTipusAula(tipo);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarIdAssig(inout sc) {
        try {
            sc.writeln("Escriu idAssig");
            String s = sc.readword();
            a.setIdAssignatura(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestModificarAula(inout sc) {
        try {
            sc.writeln("Entra aula");
            sc.writeln("Escriu nom");
            String nom = sc.readword();
            sc.writeln("Escriu TipusAula (int entre 1 i 3)");
            int tipus = sc.readint();
            sc.writeln("Escriu aforament");
            int aforament = sc.readint();
            TipusAula tipo = intToTipusAula(tipus);

            Aula aula = new Aula(nom, tipo, aforament);
            a.setAula(aula);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarHorari(inout sc) {
        try {
            sc.writeln("Entra horari");
            sc.writeln("Escriu HoraInici");
            int horaIni = sc.readint();
            sc.writeln("Escriu durada");
            int durada = sc.readint();
            sc.writeln("Escriu DiaSetmana (int entre 1 i 7)");
            int dia = sc.readint();

            DiaSetmana d = CtrlFitxerPeriodeLectiu.intToDia(dia);

            Horari horari = new Horari(horaIni, durada, d);
            a.setHorari(horari);
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
