package com.prop.fib.horaris.domain.Test;

import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.data.*;
import com.prop.fib.horaris.domain.Classes.*;
import com.prop.fib.horaris.domain.Controllers.CtrlDominio;
import com.prop.fib.horaris.presentation.inout;

import java.io.FileNotFoundException;
import java.util.List;

import static com.prop.fib.horaris.data.CtrlFitxerAules.intToTipusAula;

public class DriverCtrlDominio {


    private static CtrlDominio a = new CtrlDominio();
    private inout sc = new inout();
    private static List<GrupAssignatura> aux;

    public void Executa() throws Exception{


        mostrarMenu();
        int op;
        op = sc.readint();

        while (op != 0) {
            switch (op) {
                case 1:
                    TestCargarAssig(sc);
                    break;
                case 2:
                    TestCargarAules(sc);
                    break;
                case 3:
                    TestCargarUsers(sc);
                    break;
                case 4:
                    TestCargarPeriodeLectiu(sc);
                    break;
                case 5:
                    TestCargarHorari(sc);
                    break;
                case 6:
                    TestguardarHorari(sc);
                    break;
                case 7:
                    printHorari();
                    break;
                case 8:
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
        sc.writeln("-                  DRIVERS CTRL DOMINIO                            -\n");
        sc.writeln("-           [opcio   Operacio(Atributs)                            -\n");
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("-   0  Sortir DriverCtrlDominio                                    -\n");
        sc.writeln("-   1  TestCargarAssig()                                           -\n");
        sc.writeln("-   2  TestCargarAules()                                           -\n");
        sc.writeln("-   3  TestCargarUsers()                                           -\n");
        sc.writeln("-   4  TestCargarPeriodeLectiu(String nom)                         -\n");
        sc.writeln("-   5  TestCargarHorari(int tipus)                                 -\n");
        sc.writeln("-   6  TestguardarHorari(int n)                                    -\n");
        sc.writeln("-   7  printHorari()                                               -\n");
        sc.writeln("-   8  DriverAutomatico()                                          -\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("--------------------------------------------------------------------\n");
    }


    public static void DriverAutomatico() throws FileNotFoundException {
        System.out.println("Cargar fitxer assignaturas:");
        System.out.println();
        List<Assignatura> aux3 = a.cargarAsignaturas("Assignaturas");
        for(int i = 0; i < aux3.size(); ++i){
            aux3.get(i).print();
        }
        System.out.println("Cargar fitxer aules:");
        System.out.println();
        List<Aula> aux1 = a.cargarAules("Aules");
        for(int i = 0; i < aux1.size(); ++i){
            aux1.get(i).print();
        }
        System.out.println("Cargar fitxer periodeLectiu:");
        System.out.println();
        List<PeriodeLectiu> aux2 = a.cargarPeriodeLectiu("PeriodesLectius");
        for(int i = 0; i < aux2.size(); ++i){
            aux2.get(i).print();
        }
        System.out.println("Cargar un horari:");
        System.out.println();
        a.cargarHorari("/1/test1.json");
        aux = a.getHorari();
        for(int i = 0; i < aux.size(); ++i){
            aux.get(i).print();
        }
        System.out.println("Imprimir un horari bon format:");
        System.out.println();
        a.printHorari();

    }

    public static void TestCargarAssig(inout sc) {
        try {
            sc.writeln("Escriu nom fitxer Assignatures");
            String filename = sc.readword();
            List<Assignatura> aux = a.cargarAsignaturas(filename);
            for(int i = 0; i < aux.size(); ++i){
                aux.get(i).print();
            }

        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestCargarAules(inout sc) {
        try {
            sc.writeln("Escriu nom fitxer Aules");
            String filename = sc.readword();
            List<Aula> aux = a.cargarAules(filename);
            for(int i = 0; i < aux.size(); ++i){
                aux.get(i).print();
            }

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestCargarUsers(inout sc) {
        try {
            sc.writeln("No implementat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void TestCargarPeriodeLectiu(inout sc) {
        try {
            sc.writeln("Escriu nom fitxer PeriodeLectiu");
            String filename = sc.readword();
            List<PeriodeLectiu> aux = a.cargarPeriodeLectiu(filename);
            for(int i = 0; i < aux.size(); ++i){
                aux.get(i).print();
            }

        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void TestCargarHorari(inout sc) {
        try {
            sc.writeln("Escriu nom fitxer grupAssignatures (horari)");
            String filename = sc.readword();
            a.cargarHorari(filename);
            aux = a.getHorari();
            for(int i = 0; i < aux.size(); ++i){
                aux.get(i).print();
            }

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestguardarHorari(inout sc) {
        try {
            sc.writeln("Escull nom del fitxer");
            String filename = sc.readword();
            a.guardarHorari(aux,filename);

        } catch (Exception e) {
            System.out.print(e);
        }
    }


    public static void printHorari(){
        try {
            a.printHorari();

        } catch (Exception e) {
            System.out.print(e);
        }
    }

}
