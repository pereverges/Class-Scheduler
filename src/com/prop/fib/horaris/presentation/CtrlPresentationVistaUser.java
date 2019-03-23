package com.prop.fib.horaris.presentation;


import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.data.CtrlFitxerRestriccions;
import com.prop.fib.horaris.domain.Classes.GrupAssignatura;
import com.prop.fib.horaris.domain.Classes.User;
import com.prop.fib.horaris.domain.Controllers.CtrlDominio;
import com.prop.fib.horaris.domain.Test.CtrlDrivers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jinson Pardo
 */
public class CtrlPresentationVistaUser {

    private inout io = new inout();
    private VistaUser vistaUser;
    private static final String PATH_HORARIS = "DATA/HorarisGenerats/";

    CtrlPresentacio ctrlPresentacio;

    public CtrlPresentationVistaUser(CtrlPresentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        vistaUser = new VistaUser();
    }

    public void iniciar() {
        int opcio = -1;
        while (opcio != 0) {
            try {
                vistaUser.mostrarMenu(ctrlPresentacio.imprimirDataUsuari());
                opcio = vistaUser.getOpcio();
                switch (opcio) {
                    case 0:
                        break;
                    case 1:
                        vistaUser.mostraMenu2("Generar Horari");
                        opcio = vistaUser.getOpcio();
                        if (opcio > 0 && opcio < 8) {
                            List<String> nomsFitxers = getNomsFitxers(opcio);
                            this.ctrlPresentacio.generarHorari(nomsFitxers);
                            String horari = this.ctrlPresentacio.getHorariToString();

                            System.out.println(horari);
                            String resp = vistaUser.preguntaGuardar();
                            if (resp.toUpperCase().equals("S")) {
                                String filename = vistaUser.nomHorari();
                                io.writeln(filename);
                                this.ctrlPresentacio.guardarHorari(ctrlPresentacio.getIdUsuari() + '/' + filename + ".json");
                                vistaUser.pause();
                            }
                            /*
                            if (!(solution.size() == 0 || solution.get((solution.size() - 1)).getNumGrup() == -1)){
                                String resp = vistaUser.preguntaGuardar();
                                if (resp.toUpperCase().equals("S")) {
                                    String filename = vistaUser.nomHorari();
                                    io.writeln(filename);
                                    ctrlDominio.guardarHorari(solution, usuari.getId() + '/' + filename + ".json");
                                    vistaUser.pause();
                                }
                            }
                            else vistaUser.pause();
                            */
                        }
                        opcio = -1;
                        break;
                    case 2:
                        listarDirHorarisGenerats();
                        break;
                    case 3:
                        CtrlDrivers drivers = new CtrlDrivers();
                        drivers.Executa();
                        break;
                    case 4:
                        modificarHorari();
                        break;
                    default:

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void modificarHorari() throws FileNotFoundException {
        //List<String> nomsFitxers = new ArrayList<>();
        //nomsFitxers.add("AssignaturesClasseGrupISubGrupNoPotSerElMateixDia");
        //nomsFitxers.add("AulesPetita");
        //nomsFitxers.add("AulesPetita");
        //ctrlPresentacio.generarHorari(nomsFitxers);
        String horari = ctrlPresentacio.getHorariToString();
        System.out.println(horari);
        ctrlPresentacio.modificar("LI", 33, 16, TipusAula.PROBLEMES, "A6204", "A6203", 12, 2, DiaSetmana.DIVENDRES, 18,2, DiaSetmana.DILLUNS);
        horari = ctrlPresentacio.getHorariToString();
        System.out.println(horari);
    }

    private List<String> getNomsFitxers(int opcio) throws Exception {
        List<String> nomsFitxers = new ArrayList<String>();
        switch (opcio) {
            case 1:
                nomsFitxers.add("Assignatures");
                nomsFitxers.add("Aules");
                nomsFitxers.add("PeriodesLectius");
                nomsFitxers.add("Restriccions");
                break;
            case 2:
                nomsFitxers.add("AssignaturesProvaGran");
                nomsFitxers.add("AulesProvaGran");
                nomsFitxers.add("PeriodesLectius");
                nomsFitxers.add("Restriccions");
                break;
            case 3:
                nomsFitxers.add("Assignatures");
                nomsFitxers.add("AulesPetita");
                nomsFitxers.add("PeriodesLectius");
                nomsFitxers.add("Restriccions");
                break;
            case 4:
                nomsFitxers.add("AssignaturesMateixNivell");
                nomsFitxers.add("AulesPetita");
                nomsFitxers.add("PeriodesLectius");
                nomsFitxers.add("Restriccions");
                break;
            case 5:
                nomsFitxers.add("AssignaturesAmbCorrequisits");
                nomsFitxers.add("AulesPetita");
                nomsFitxers.add("PeriodesLectius");
                nomsFitxers.add("Restriccions");
                break;
            case 6:
                nomsFitxers.add("AssignaturesClasseGrupISubGrupNoPotSerElMateixDia");
                nomsFitxers.add("AulesPetita");
                nomsFitxers.add("PeriodesLectius");
                nomsFitxers.add("Restriccions");
                break;
            case 7:
                io.write("Nom fitxer assignatura: ");
                nomsFitxers.add(io.readline());
                io.write("Nom fitxer aula: ");
                nomsFitxers.add(io.readline());
                io.write("Nom fitxer periodeLectiu: ");
                nomsFitxers.add(io.readline());
                io.write("Nom fitxer Restriccions: ");
                nomsFitxers.add(io.readline());
                break;
        }
        nomsFitxers.add("PeriodesLectius");
        return nomsFitxers;
    }

    private void listarDirHorarisGenerats() throws Exception {
        String cabecera = "Llistat d'horaris";
        String idUsuari = this.ctrlPresentacio.getIdUsuari();
        vistaUser.imprimirCabecera(cabecera);
        File dir = new File(PATH_HORARIS + idUsuari);
        String[] fitxers = dir.list();
        if (fitxers != null) {
            if (fitxers.length == 1) {
                this.ctrlPresentacio.cargarHorari(idUsuari + '/' + fitxers[0]);
                this.ctrlPresentacio.printHorari();
            }
            else if (fitxers.length > 1) {
                vistaUser.imprimirlistafitxers(fitxers);
                vistaUser.borders('*', cabecera.length());
                io.write("Tria un fitxer de l'1 al " + fitxers.length + ": ");
                int index = io.readint();
                if (index > 0 && index <= fitxers.length) {
                    this.ctrlPresentacio.cargarHorari(idUsuari + '/' + fitxers[index - 1]);
                    this.ctrlPresentacio.printHorari();
                    //modificarHorari();
                }
                else if (index != 0)
                    vistaUser.missatgeError("Número incorrecte!");
            }
        }
        else {
            vistaUser.missatgeError("No tens cap horari guardat.!");
        }
        vistaUser.pause();
    }
}
