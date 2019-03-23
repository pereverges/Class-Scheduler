package com.prop.fib.horaris.domain.Test;

import com.prop.fib.horaris.Enumerations.TipusUser;
import com.prop.fib.horaris.domain.Classes.Admin;
import com.prop.fib.horaris.domain.Classes.Alumne;
import com.prop.fib.horaris.domain.Classes.User;
import com.prop.fib.horaris.presentation.inout;

public class DriverAlumne {

    private static Alumne a;
    private inout sc = new inout();

    public void Executa() throws Exception{


        mostrarMenu();
        int op;
        op = sc.readint();

        while (op != 0) {
            switch (op) {
                case 1:
                    TestUser(sc);
                    break;
                case 2:
                    TestConsultarNom();
                    break;
                case 3:
                    TestConsultarId();
                    break;
                case 4:
                    TestConsultarTipusUser();
                    break;
                case 5:
                    TestConsultarPassword();
                    break;
                case 6:
                    TestModificarNom(sc);
                    break;
                case 7:
                    TestModificarId(sc);
                    break;
                case 8:
                    TestModificarTipusUser(sc);
                    break;
                case 9:
                    TestModificarPassword(sc);
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
        sc.writeln("-                    DRIVERS ALUMNE                                -\n");
        sc.writeln("-           [opcio   Operacio(Atributs)                            -\n");
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("-   0  Sortir DriverAlumne                                         -\n");
        sc.writeln("-   1  TestAlumne(String nom, String id, String pass)              -\n");
        sc.writeln("-   2  TestConsultarNom()                                          -\n");
        sc.writeln("-   3  TestConsultarId()                                           -\n");
        sc.writeln("-   4  TestConsultarTipusUser()                                    -\n");
        sc.writeln("-   5  TestConsultarPassword()                                     -\n");
        sc.writeln("-   6  TestModificarNom(String nom)                                -\n");
        sc.writeln("-   7  TestModificarIDString id)                                   -\n");
        sc.writeln("-   8  TestModificarTipuUser(int m)                                -\n");
        sc.writeln("-   9  TestModificarPassword(String pass)                          -\n");
        sc.writeln("-   10 TestPrintUser()                                             -\n");
        sc.writeln("-   11 DriverAutomatico()                                          -\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("--------------------------------------------------------------------\n");
    }

    public static void DriverAutomatico(){
        System.out.println("Atributs inicials");
        a = new Alumne("Joan", "1", "1234");
        System.out.println(a.toString());
        System.out.println("Canvi de ID a 2");
        System.out.println("Canvi de nom a Maria");
        System.out.println("Canvi de password a 5678");
        System.out.println();
        System.out.println("Resultat dels canvis");
        a.setId("2");
        a.setNom("Maria");
        a.setPassword("5678");
        System.out.println(a.toString());
    }



    public static void TestUser(inout sc) {
        try {
            sc.writeln("Escriu nom");
            String nom = sc.readword();
            sc.writeln("Escriu id");
            String id = sc.readword();
            sc.writeln("Escriu password");
            String password = sc.readword();

            a = new Alumne(nom, id, password);
            sc.writeln("Alumne entrada correctament");
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


    public static void TestConsultarTipusUser() {
        try {
            System.out.println("TipusUser " + a.getTipusUser());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarPassword() {
        try {
            System.out.println("Password " + a.getPassword());
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

    public static void TestModificarTipusUser(inout sc) {
        try {
            sc.writeln("Escriu tipusUser(int entre 1 i 2");

            int tipus = sc.readint();
            TipusUser tipo = null;
            if(tipus == 1) {
                tipo = TipusUser.ALUMNE;
            }
            else if(tipus == 2) {
                tipo = TipusUser.ADMIN;;
            }
            a.setTipusUser(tipo);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarPassword(inout sc) {
        try {
            sc.writeln("Escriu password");
            String s = sc.readword();
            a.setPassword(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void Testprint() {
        try {
            System.out.println(a.toString());
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
