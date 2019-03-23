package com.prop.fib.horaris.domain.Test;
import com.prop.fib.horaris.domain.Classes.Alumne;
import com.prop.fib.horaris.domain.Classes.Assignatura;
import com.prop.fib.horaris.domain.Classes.GrupAssignatura;
import com.prop.fib.horaris.presentation.inout;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverAssignatura {

        private static Assignatura a;
        private inout sc = new inout();

        public void Executa() throws Exception{


            mostrarMenu();
            int op;
            op = sc.readint();

            while (op != 0) {
                switch (op) {
                    case 1:
                        TestAssignatura(sc);
                        break;
                    case 2:
                        TestConsultarId();
                        break;
                    case 3:
                        TestConsultarNom();
                        break;
                    case 4:
                        TestConsultarNumCredits();
                        break;
                    case 5:
                        TestConsultarQuadri();
                        break;
                    case 6:
                        TestConsultarNumGrups();
                        break;
                    case 7:
                        TestConsultarNumSubGrups();
                        break;
                    case 8:
                        TestConsultarHoresSetmanals();
                        break;
                    case 9:
                        TestConsultarSessionsSetmanals();
                        break;
                    case 10:
                        TestConsultarCorrequisits();
                        break;
                    case 11:
                        TestConsultarInverseCorrequisits();
                        break;
                    case 12:
                        TestConsultarFase();
                        break;
                    case 13:
                        TestModificarId(sc);
                        break;
                    case 14:
                        TestModificarNom(sc);
                        break;
                    case 15:
                        TestModificarCred(sc);
                        break;
                    case 16:
                        TestModificarQuad(sc);
                        break;
                    case 17:
                        TestModificarGrups(sc);
                        break;
                    case 18:
                        TestModificarSubGrups(sc);
                        break;
                    case 19:
                        TestModificarHores(sc);
                        break;
                    case 20:
                        TestModificarSessions(sc);
                        break;
                    case 21:
                        //TestModificarCorr(sc);
                        break;
                    case 22:
                        //TestModificarInverseCorr(sc);
                        break;
                    case 23:
                        TestModificarFase(sc);
                        break;
                    case 24:
                        Testprint();
                        break;
                    case 25:
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
        sc.writeln("-                    DRIVERS ASSIGNATURA                           -\n");
        sc.writeln("-           [opcio   Operacio(Atributs)                            -\n");
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("-   0   Sortir DriverAssignatura                                   -\n");
        sc.writeln("-   1   TestAssignatura(String id, String nom, int numCredits,     -\n");
        sc.writeln("-   int numPlaces, int quadrimestre, int numGrups,                 -\n");
        sc.writeln("-   int numSubgrupsPerGrup, int numSessionsSetmanals,              -\n");
        sc.writeln("-   int horesSetmanals, List<String> correquisits,                 -\n");
        sc.writeln("-   List<String> inverseCorrequisits, String fase)                 -\n");
        sc.writeln("-   2  TestConsultarId()                                           -\n");
        sc.writeln("-   3  TestConsultarNom()                                          -\n");
        sc.writeln("-   4  TestConsultarNumCredits()                                   -\n");
        sc.writeln("-   5  TestConsultarQuadri()                                       -\n");
        sc.writeln("-   6  TestConsultarNumGrups()                                     -\n");
        sc.writeln("-   7  TestConsultarNumSubGrups()                                  -\n");
        sc.writeln("-   8  TestConsultarHoresSetmanals()                               -\n");
        sc.writeln("-   9  TestConsultarSessionsSetmanals()                            -\n");
        sc.writeln("-   10 TestConsultarCorrequisits()                                 -\n");
        sc.writeln("-   11 TestConsultarInverseCorrequisits                            -\n");
        sc.writeln("-   12 TestConsultarFase()                                         -\n");
        sc.writeln("-   13 TestModificarId(String id)                                  -\n");
        sc.writeln("-   14 TestModificarNom(String nom)                                -\n");
        sc.writeln("-   15 TestModificarNumCredits(int n)                              -\n");
        sc.writeln("-   16 TestModificarQuadri(int n)                                  -\n");
        sc.writeln("-   17 TestModificarNumGrups(int n)                                -\n");
        sc.writeln("-   18 TestModificarNumSubGrups(int n)                             -\n");
        sc.writeln("-   19 TestModificarHoresSetmanals(int n)                          -\n");
        sc.writeln("-   20 TestModificarSessionsSetmanals(int n)                       -\n");
        sc.writeln("-   21 TestModificarCorrequisits(List<String> c)                   -\n");
        sc.writeln("-   22 TestModificarInverseCorrequisits(List<String> c)            -\n");
        sc.writeln("-   23 TestModificarFase(String Fase)                              -\n");
        sc.writeln("-   24 TestPrintAssignatura()                                      -\n");
        sc.writeln("-   25 DriverAutomatico()                                          -\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("--------------------------------------------------------------------\n");
    }

    public static void DriverAutomatico(){
        System.out.println("Atributs inicials");
        a = new Assignatura("LI", "Logica en l'informatica", 6, 60, 1,3,4, 2, 4, new ArrayList<>(), new ArrayList<>(), "inicial" );
        a.print();
        System.out.println("Canvi de ID a LP");
        System.out.println("Canvi de nom a Llengutages de programacio");
        System.out.println("Canvi de credits a 4");
        System.out.println("Canvi de numPlaces a 70");
        System.out.println("Canvi de numQuadri a 2");
        System.out.println("Canvi de numGrups a 4");
        System.out.println("Canvi de numSubGrups a 3");
        System.out.println("Canvi de numSessions a 3");
        System.out.println("Canvi de horesSetmanals a 6");
        System.out.println("Canvi de correquisits a PROP");
        System.out.println("Canvi de inversecorrequisits a A");
        System.out.println("Canvi de fase a Computacio");
        System.out.println();
        System.out.println("Resultat dels canvis");
        a.setId("LP");
        a.setNom("Llengutages de programacio");
        a.setNumCredits(4);
        a.setNumPlaces(70);
        a.setQuadrimestre(2);
        a.setNumGrups(4);
        a.setNumSubgrupsPerGrup(3);
        a.setNumSessionsSetmanals(3);
        a.setHoresSetmanals(6);
        List<String> l = new ArrayList<>();
        l.add("PROP");
        a.setCorrequisits(l);
        List<String> s = new ArrayList<>();
        s.add("A");
        a.setInverseCorrequisits(s);
        a.setFase("Computacio");
        a.print();
    }


        public static void TestAssignatura(inout sc) {
            try {
                sc.writeln("Escriu id");
                String id = sc.readword();
                sc.writeln("Escriu nom");
                String nom = sc.readword();
                sc.writeln("Escriu credits");
                int cred = sc.readint();
                sc.writeln("Escriu places");
                int places = sc.readint();
                sc.writeln("Escriu quadri");
                int quadri = sc.readint();
                sc.writeln("Escriu numGrups");
                int ngrups = sc.readint();
                sc.writeln("Escriu numSubGrups");
                int nsubgrups = sc.readint();
                sc.writeln("Escriu numSessions");
                int sessions = sc.readint();
                sc.writeln("Escriu numHoresSetmanals");
                int hores = sc.readint();
                List<String> corr = null;
                List<String> invcorr = null;
                sc.writeln("Escriu fase");
                String fase = sc.readword();
                a = new Assignatura(id,nom,cred,places,quadri,ngrups,nsubgrups,sessions,hores,corr,invcorr,fase);
                sc.writeln("Assignatura entrada correctament");
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

    public static void TestConsultarNom() {
        try {
            System.out.println("Nom " + a.getNom());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarNumCredits() {
        try {
            System.out.println("NumCredits " + a.getNumCredits());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarQuadri() {
        try {
            System.out.println("Quadri " + a.getQuadrimestre());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarNumGrups() {
        try {
            System.out.println("NumGrups " + a.getNumGrups());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarNumSubGrups() {
        try {
            System.out.println("NumSubGrups " + a.getNumSubgrupsPerGrup());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarHoresSetmanals() {
        try {
            System.out.println("HoresSetmanals " + a.getHoresSetmanals());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarSessionsSetmanals() {
        try {
            System.out.println("SessionsSetmanals " + a.getNumSessionsSetmanals());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarCorrequisits() {
        try {
            int n = a.getCorrequisits().size();
            for(int i = 0; i < n; ++i){
                System.out.println("Correquisists " + a.getCorrequisits().get(i));
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarInverseCorrequisits() {
        try {
            int n = a.getInverseCorrequisits().size();
            for(int i = 0; i < n; ++i){
                System.out.println("InverseCorrequisits " +a.getInverseCorrequisits().get(i));
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestConsultarFase() {
        try {
            System.out.println("Fase " +a.getFase());
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

    public static void TestModificarNom(inout sc) {
        try {
            sc.writeln("Escriu nom");
            String s = sc.readword();
            a.setNom(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarCred(inout sc) {
        try {
            sc.writeln("Escriu credits");
            int s = sc.readint();
            a.setNumCredits(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarQuad(inout sc) {
        try {
            sc.writeln("Escriu quadrimestre");
            int s = sc.readint();
            a.setQuadrimestre(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarGrups(inout sc) {
        try {
            sc.writeln("Escriu NumGrups");
            int s = sc.readint();
            a.setNumGrups(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarSubGrups(inout sc) {
        try {
            sc.writeln("Escriu numSubgrups");
            int s = sc.readint();
            a.setNumSubgrupsPerGrup(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarHores(inout sc) {
        try {
            sc.writeln("Escriu horesSetmanals");
            int s = sc.readint();
            a.setHoresSetmanals(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarSessions(inout sc) {
        try {
            sc.writeln("Escriu numSessions");
            int s = sc.readint();
            a.setNumSessionsSetmanals(s);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestModificarFase(inout sc) {
        try {
            sc.writeln("Escriu fase");
            String s = sc.readword();
            a.setFase(s);
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
