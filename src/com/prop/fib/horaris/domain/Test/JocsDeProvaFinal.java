package com.prop.fib.horaris.domain.Test;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.domain.Classes.PeriodeLectiu;
import com.prop.fib.horaris.domain.Classes.Restriccions;
import com.prop.fib.horaris.domain.Controllers.CtrlDominio;
import com.prop.fib.horaris.presentation.inout;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class JocsDeProvaFinal {

    private static CtrlDominio ctrlDominio;
    private inout sc = new inout();


    private void mostrarMenu() throws Exception{
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                    Joc de Proves                                 -\n");
        sc.writeln("-           [opcio   Operacio(Atributs)                            -\n");
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("-   0  Sortir Joc de Proves                                        -\n");
        sc.writeln("-   1  Joc 1                                                       -\n");
        sc.writeln("-   2  Joc 2                                                       -\n");
        sc.writeln("-   3  Joc 3                                                       -\n");
        sc.writeln("-   4  Joc 4                                                       -\n");
        sc.writeln("-   5  Joc 5                                                       -\n");
        sc.writeln("-   6  Joc 6                                                       -\n");
        sc.writeln("-   7  Joc 7                                                       -\n");
        sc.writeln("-   8  Joc 8                                                       -\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("--------------------------------------------------------------------\n");
    }



    public void jocs() throws Exception {

        mostrarMenu();
        int op;
        op = sc.readint();

        while (op != 0) {
            switch (op) {
                case 1:
                    joc1();
                    break;
                case 2:
                    joc2();
                    break;
                case 3:
                    joc3();
                    break;
                case 4:
                    joc4();
                    break;
                case 5:
                    joc5();
                    break;
                case 6:
                    joc6();
                    break;
                case 7:
                    joc7();
                    break;
                case 8:
                    joc8();
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
            System.out.println("Escull un altra opcio");
            op = sc.readint();
        }

    }

    private void joc1() throws FileNotFoundException {
        System.out.println("/////////////////////////////////////////////////////");
        System.out.println("Joc 1");
        System.out.println("/////////////////////////////////////////////////////");

        ctrlDominio = new CtrlDominio();
        List<String> nomsFitxers = new ArrayList<>();
        nomsFitxers.add("AssignaturesPetitaPerRestriccionsDeIntervalISubnivell");
        nomsFitxers.add("Aules");
        nomsFitxers.add("PeriodesLectius");
        nomsFitxers.add("RestriccionsIntervalHorariSenzill");
        ctrlDominio.generarHorari(nomsFitxers);
        ctrlDominio.printHorari();
    }

    private void joc2() throws FileNotFoundException {
        System.out.println("/////////////////////////////////////////////////////");
        System.out.println("Joc 2");
        System.out.println("/////////////////////////////////////////////////////");
        ctrlDominio = new CtrlDominio();
        List<String> nomsFitxers = new ArrayList<>();
        nomsFitxers.add("AssignaturesPetitaPerRestriccionsDeIntervalISubnivell");
        nomsFitxers.add("Aules");
        nomsFitxers.add("PeriodesLectius");
        nomsFitxers.add("RestriccionsIntervalHorariSenzillRestrictiu");
        ctrlDominio.generarHorari(nomsFitxers);
        ctrlDominio.printHorari();
    }

    private void joc3() throws FileNotFoundException {
        System.out.println("/////////////////////////////////////////////////////");
        System.out.println("Joc 3");
        System.out.println("/////////////////////////////////////////////////////");
        ctrlDominio = new CtrlDominio();
        List<String> nomsFitxers = new ArrayList<>();
        nomsFitxers.add("AssignaturesPetitaPerRestriccionsDeIntervalISubnivell");
        nomsFitxers.add("AulesPetita");
        nomsFitxers.add("PeriodesLectius");
        nomsFitxers.add("RestriccionsBuit");
        ctrlDominio.generarHorari(nomsFitxers);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");

        ctrlDominio.modificar("LI", 10, 50, TipusAula.TEORIA, "A6202", "A6201", 14, 2, DiaSetmana.DILLUNS, 8,2,DiaSetmana.DILLUNS);

        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");

        List<String> s = new ArrayList<>();
        s.add("LI 8-12");
        ctrlDominio.afegirRestriccio("1", "IntervalHorari", s);
        List<Restriccions> l = ctrlDominio.getEscenari().getRestriccionsAsList();
        for(int i = 0; i < l.size(); ++i){
            l.get(i).print();
        }
        ctrlDominio.guardarRestriccions("RestriccionsBuidesEditadesIntervalHorari");
        List<String> nomsFitxers1 = new ArrayList<>();
        nomsFitxers1.add("AssignaturesPetitaPerRestriccionsDeIntervalISubnivell");
        nomsFitxers1.add("AulesPetita");
        nomsFitxers1.add("PeriodesLectius");
        nomsFitxers1.add("RestriccionsBuidesEditadesIntervalHorari");
        ctrlDominio.generarHorari(nomsFitxers1);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");
        ctrlDominio.modificar("LI", 10, 50, TipusAula.TEORIA, "A6202", "A6202", 8, 2, DiaSetmana.DIMARTS, 16,2,DiaSetmana.DIMARTS);
        ctrlDominio.modificar("LI", 10, 50, TipusAula.TEORIA, "A6202", "A6202", 8, 2, DiaSetmana.DIMARTS, 10,2,DiaSetmana.DIMARTS);
        ctrlDominio.printHorari();
    }

    private void joc4() throws FileNotFoundException {
        System.out.println("/////////////////////////////////////////////////////");
        System.out.println("Joc 4");
        System.out.println("/////////////////////////////////////////////////////");
        ctrlDominio = new CtrlDominio();
        List<String> nomsFitxers = new ArrayList<>();
        nomsFitxers.add("AssignaturesPetitaPerRestriccionsDeIntervalISubnivell");
        nomsFitxers.add("AulesPetita");
        nomsFitxers.add("PeriodesLectius");
        nomsFitxers.add("RestriccionsBuit");
        ctrlDominio.generarHorari(nomsFitxers);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");

        ctrlDominio.modificar("LI", 10, 50, TipusAula.TEORIA, "A6202", "A6201", 14, 2, DiaSetmana.DILLUNS, 8,2,DiaSetmana.DILLUNS);

        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");

        List<String> s = new ArrayList<>();
        s.add("LI");
        s.add("PROP");
        ctrlDominio.afegirRestriccio("1", "Subnivell", s);
        List<Restriccions> l = ctrlDominio.getEscenari().getRestriccionsAsList();
        for(int i = 0; i < l.size(); ++i){
            l.get(i).print();
        }
        ctrlDominio.guardarRestriccions("RestriccionsBuidesEditadesSubnivell");
        List<String> nomsFitxers1 = new ArrayList<>();
        nomsFitxers1.add("AssignaturesPetitaPerRestriccionsDeIntervalISubnivell");
        nomsFitxers1.add("AulesPetita");
        nomsFitxers1.add("PeriodesLectius");
        nomsFitxers1.add("RestriccionsBuidesEditadesSubnivell");
        ctrlDominio.generarHorari(nomsFitxers1);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");
        ctrlDominio.modificar("LI", 10, 50, TipusAula.TEORIA, "A6202", "A6201", 14, 2, DiaSetmana.DILLUNS, 8,2,DiaSetmana.DILLUNS);
        ctrlDominio.modificar("LI", 10, 50, TipusAula.TEORIA, "A6202", "A6201", 14, 2, DiaSetmana.DILLUNS, 10,2,DiaSetmana.DILLUNS);
        ctrlDominio.printHorari();
    }

    private void joc5() throws FileNotFoundException {
        System.out.println("/////////////////////////////////////////////////////");
        System.out.println("Joc 5");
        System.out.println("/////////////////////////////////////////////////////");

        ctrlDominio = new CtrlDominio();
        List<String> nomsFitxers = new ArrayList<>();
        nomsFitxers.add("AssignaturesPetitaPerRestriccionsDeIntervalISubnivell");
        nomsFitxers.add("AulesPetita");
        nomsFitxers.add("PeriodesLectius");
        nomsFitxers.add("RestriccionsSenzillesIntervalHorariISubnivell");
        ctrlDominio.generarHorari(nomsFitxers);
        ctrlDominio.printHorari();
        ctrlDominio.guardarHorari("admin/jocDeProves6.json");
    }

    private void joc6() throws FileNotFoundException {
        System.out.println("/////////////////////////////////////////////////////");
        System.out.println("Joc 6");
        System.out.println("/////////////////////////////////////////////////////");

        ctrlDominio = new CtrlDominio();
        ctrlDominio.cargarHorari("admin/jocDeProves6.json");
        System.out.println("/////////////////////////////////////////////////////");

        //Editar restriccio interval horari
        List<String> s = new ArrayList<>();
        s.add("LI 12-16");
        ctrlDominio.editarRestriccioDeIntervalHorari("3", s);
        List<Restriccions> l = ctrlDominio.getEscenari().getRestriccionsAsList();
        for(int i = 0; i < l.size(); ++i){
            l.get(i).print();
        }
        ctrlDominio.guardarRestriccions("restriccions6");
        ctrlDominio.guardarEscenariDeAssignaturas("assignatures6");
        ctrlDominio.guardarEscenariDeAulas("aules6");
        List<PeriodeLectiu> p = new ArrayList<>();
        p.add(ctrlDominio.getEscenari().getPeriodeLectiu());
        ctrlDominio.guardarPeriodeLectiu( p,"periodeLectiu6");

        List<String> nomsFitxers1 = new ArrayList<>();
        nomsFitxers1.add("assignatures6");
        nomsFitxers1.add("aules6");
        nomsFitxers1.add("periodeLectiu6");
        nomsFitxers1.add("restriccions6");
        ctrlDominio.generarHorari(nomsFitxers1);

        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");
        //Eliminar restriccio de subnivell
        ctrlDominio.eliminarRestriccio("1");
        ctrlDominio.guardarRestriccions("restriccions6eliminatSubnivell");
        List<String> nomsFitxers2 = new ArrayList<>();
        nomsFitxers2.add("assignatures6");
        nomsFitxers2.add("aules6");
        nomsFitxers2.add("periodeLectiu6");
        nomsFitxers2.add("restriccions6eliminatSubnivell");
        ctrlDominio.generarHorari(nomsFitxers2);
        List<Restriccions> l2 = ctrlDominio.getEscenari().getRestriccionsAsList();
        for(int i = 0; i < l2.size(); ++i){
            l2.get(i).print();
        }
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");

        ctrlDominio.modificar("LI", 10, 50, TipusAula.TEORIA, "A6202", "A6201", 14, 2, DiaSetmana.DILLUNS, 12,2,DiaSetmana.DIMARTS);
        ctrlDominio.printHorari();

    }

    private void joc7() throws FileNotFoundException {
        System.out.println("/////////////////////////////////////////////////////");
        System.out.println("Joc 7");
        System.out.println("/////////////////////////////////////////////////////");

        ctrlDominio = new CtrlDominio();
        List<String> nomsFitxers = new ArrayList<>();
        nomsFitxers.add("AssignaturesProvaGran");
        nomsFitxers.add("Aules");
        nomsFitxers.add("PeriodesLectius");
        nomsFitxers.add("RestriccionsIntervalHorariSenzill");
        ctrlDominio.generarHorari(nomsFitxers);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");

        List<String> s = new ArrayList<>();
        s.add("M2 14-20");
        ctrlDominio.afegirRestriccio("5", "IntervalHorari", s);
        ctrlDominio.guardarRestriccions("joc7restriccions");
        List<Restriccions> l = ctrlDominio.getEscenari().getRestriccionsAsList();
        for(int i = 0; i < l.size(); ++i){
            l.get(i).print();
        }
        List<String> nomsFitxers1 = new ArrayList<>();
        nomsFitxers1.add("AssignaturesProvaGran");
        nomsFitxers1.add("Aules");
        nomsFitxers1.add("PeriodesLectius");
        nomsFitxers1.add("joc7restriccions");
        ctrlDominio.generarHorari(nomsFitxers1);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");

        List<String> s1 = new ArrayList<>();
        s1.add("XC 8-10");
        ctrlDominio.afegirRestriccio("6", "IntervalHorari", s1);
        ctrlDominio.guardarRestriccions("joc7restriccions");
        List<Restriccions> l1 = ctrlDominio.getEscenari().getRestriccionsAsList();
        for(int i = 0; i < l1.size(); ++i){
            l1.get(i).print();
        }
        List<String> nomsFitxers2 = new ArrayList<>();
        nomsFitxers2.add("AssignaturesProvaGran");
        nomsFitxers2.add("Aules");
        nomsFitxers2.add("PeriodesLectius");
        nomsFitxers2.add("joc7restriccions");
        ctrlDominio.generarHorari(nomsFitxers2);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");

        List<String> s2 = new ArrayList<>();
        s2.add("IES 8-10");
        ctrlDominio.afegirRestriccio("7", "IntervalHorari", s2);
        ctrlDominio.guardarRestriccions("joc7restriccions");
        List<Restriccions> l2 = ctrlDominio.getEscenari().getRestriccionsAsList();
        for(int i = 0; i < l2.size(); ++i){
            l2.get(i).print();
        }
        List<String> nomsFitxers3 = new ArrayList<>();
        nomsFitxers3.add("AssignaturesProvaGran");
        nomsFitxers3.add("Aules");
        nomsFitxers3.add("PeriodesLectius");
        nomsFitxers3.add("joc7restriccions");
        ctrlDominio.generarHorari(nomsFitxers3);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");

        List<String> r = new ArrayList<>();
        r.add("IES 8-12");
        ctrlDominio.editarRestriccioDeIntervalHorari("7", r);
        ctrlDominio.guardarRestriccions("joc7restriccions");
        List<Restriccions> l3 = ctrlDominio.getEscenari().getRestriccionsAsList();
        for(int i = 0; i < l3.size(); ++i){
            l3.get(i).print();
        }
        List<String> nomsFitxers4 = new ArrayList<>();
        nomsFitxers4.add("AssignaturesProvaGran");
        nomsFitxers4.add("Aules");
        nomsFitxers4.add("PeriodesLectius");
        nomsFitxers4.add("joc7restriccions");
        ctrlDominio.generarHorari(nomsFitxers4);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");


    }

    private void joc8() throws FileNotFoundException {
        System.out.println("/////////////////////////////////////////////////////");
        System.out.println("Joc 8");
        System.out.println("/////////////////////////////////////////////////////");

        ctrlDominio = new CtrlDominio();
        List<String> nomsFitxers = new ArrayList<>();
        nomsFitxers.add("Assignatures");
        nomsFitxers.add("Aules");
        nomsFitxers.add("PeriodesLectius");
        nomsFitxers.add("joc8restriccions");
        ctrlDominio.generarHorari(nomsFitxers);
        ctrlDominio.printHorari();

        ctrlDominio.eliminarAssigDeRestriccioSubnivell("1", "EC");
        List<Restriccions> l3 = ctrlDominio.getEscenari().getRestriccionsAsList();
        for(int i = 0; i < l3.size(); ++i){
            l3.get(i).print();
        }
        ctrlDominio.guardarRestriccions("joc8restriccions1");

        List<String> nomsFitxers4 = new ArrayList<>();
        nomsFitxers4.add("Assignatures");
        nomsFitxers4.add("Aules");
        nomsFitxers4.add("PeriodesLectius");
        nomsFitxers4.add("joc8restriccions1");
        ctrlDominio.generarHorari(nomsFitxers4);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");



        ctrlDominio.eliminarRestriccio("3");
        List<Restriccions> l = ctrlDominio.getEscenari().getRestriccionsAsList();
        for(int i = 0; i < l.size(); ++i){
            l.get(i).print();
        }
        ctrlDominio.guardarRestriccions("joc8restriccions1");

        List<String> nomsFitxers2 = new ArrayList<>();
        nomsFitxers2.add("Assignatures");
        nomsFitxers2.add("Aules");
        nomsFitxers2.add("PeriodesLectius");
        nomsFitxers2.add("joc8restriccions1");
        ctrlDominio.generarHorari(nomsFitxers2);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");

        ctrlDominio.eliminarAssigDeRestriccioSubnivell("1", "PRO2");
        List<Restriccions> l2 = ctrlDominio.getEscenari().getRestriccionsAsList();
        for(int i = 0; i < l2.size(); ++i){
            l2.get(i).print();
        }
        ctrlDominio.guardarRestriccions("joc8restriccions1");

        List<String> nomsFitxers3 = new ArrayList<>();
        nomsFitxers3.add("Assignatures");
        nomsFitxers3.add("Aules");
        nomsFitxers3.add("PeriodesLectius");
        nomsFitxers3.add("joc8restriccions1");
        ctrlDominio.generarHorari(nomsFitxers3);
        ctrlDominio.printHorari();
        System.out.println("/////////////////////////////////////////////////////");

    }


}
