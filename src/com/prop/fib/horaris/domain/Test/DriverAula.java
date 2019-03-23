package com.prop.fib.horaris.domain.Test;

import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.Enumerations.TipusUser;
import com.prop.fib.horaris.domain.Classes.Assignatura;
import com.prop.fib.horaris.domain.Classes.Aula;
import com.prop.fib.horaris.domain.Classes.User;
import com.prop.fib.horaris.presentation.inout;

import java.util.List;

import static com.prop.fib.horaris.data.CtrlFitxerAules.intToTipusAula;
import static com.prop.fib.horaris.domain.Test.DriverAssignatura.TestConsultarNom;

public class DriverAula {

    private static Aula a;
    private inout sc = new inout();

    public void Executa() throws Exception{


        mostrarMenu();
        int op;
        op = sc.readint();

        while (op != 0) {
            switch (op) {
                case 1:
                    TestAula(sc);
                    break;
                case 2:
                    TestConsultarNom();
                    break;
                case 3:
                    TestConsultarTipusAula();
                    break;
                case 4:
                    TestConsultarAforament();
                    break;
                case 5:
                    TestModificarNom(sc);
                    break;
                case 6:
                    TestModificarTipusAula(sc);
                    break;
                case 7:
                    TestModificarAformanet(sc);
                    break;
                case 8:
                    Testprint();
                    break;
                case 9:
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
        sc.writeln("-                    DRIVERS AULA                                  -\n");
        sc.writeln("-           [opcio   Operacio(Atributs)                            -\n");
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("-   0   Sortir DriverAula                                          -\n");
        sc.writeln("-   1   TestAula(String nom, int TipusAula, int aforament)         -\n");
        sc.writeln("-   2  TestConsultarNom()                                          -\n");
        sc.writeln("-   3  TestConsultarTipusAula()                                    -\n");
        sc.writeln("-   4  TestConsultarAformanet()                                    -\n");
        sc.writeln("-   5  TestModificarNom(String nom)                                -\n");
        sc.writeln("-   6  TestModificarTipuAuls(int tipus)                            -\n");
        sc.writeln("-   7  TestModificarAformaent(int n)                               -\n");
        sc.writeln("-   8  TestPrintAula()                                             -\n");
        sc.writeln("-   9  DriverAutomatico()                                          -\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("--------------------------------------------------------------------\n");
    }

    public static void DriverAutomatico(){
        System.out.println("Atributs inicials");
        a = new Aula("A5201", TipusAula.TEORIA, 60);
        a.print();
        System.out.println("Canvi de Nom a A6101");
        System.out.println("Canvi de tipusAula a Problemes");
        System.out.println("Canvi de aforament a 50");
        System.out.println();
        System.out.println("Resultat dels canvis");
        a.setNom("A6101");
        a.setTipusAula(TipusAula.PROBLEMES);
        a.setAforament(50);
        a.print();
        System.out.println("Canvi de tipusAula a Laboratori");
        a.setTipusAula(TipusAula.LABORATORI);
        System.out.println();
        System.out.println("Resultat dels canvis");
        a.print();
    }

    public static void TestAula(inout sc) {
        try {
            sc.writeln("Escriu nom");
            String nom = sc.readword();
            sc.writeln("Escriu TipusAula (int entre 1 i 3)");
            int tipus = sc.readint();
            sc.writeln("Escriu aforament");
            int aforament = sc.readint();

            TipusAula tipo = intToTipusAula(tipus);

            a = new Aula(nom, tipo, aforament);
            sc.writeln("Aula entrada correctament");
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


    public static void TestConsultarTipusAula() {
        try {
            System.out.println("TipusAula " + a.getTipusAula());
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestConsultarAforament() {
        try {
            System.out.println("Aformanet " + a.getAforament());
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

    public static void TestModificarAformanet(inout sc) {
        try {
            sc.writeln("Escriu aformanet");
            int s = sc.readint();
            a.setAforament(s);
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
