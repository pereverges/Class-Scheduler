package com.prop.fib.horaris.domain.Controllers;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.data.*;
import com.prop.fib.horaris.domain.Classes.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Pere Verges Boncompte
 */
public class CtrlDominio {

    private static final String PATH_HORARIS = "DATA/HorarisGenerats/";
    private CtrlDominioMantUser ctrlDominioMantUser;
    private Escenari escenari;
    private List<String> nomFitxersAAF;
    private List<GrupAssignatura> horari;
    private CtrlFitxerGrupAssig ctrlFitxerGrupAssig;

    public CtrlDominio() {
        this.ctrlDominioMantUser = new CtrlDominioMantUser();
        this.ctrlFitxerGrupAssig = CtrlFitxerGrupAssig.getInstance();
        this.escenari = Escenari.getInstance();
        this.cargarUsers("User");
    }

    // public CtrlDominioMantUser getCtrlDominioMantUser() { return this.ctrlDominioMantUser; }

    /**
     * @param idUsuari String
     * @param contrasenya String
     * @return Cert si las credencials són correctes
     */
    public boolean isLoginOk(String idUsuari, String contrasenya) {
        return ctrlDominioMantUser.isLoginOk(idUsuari, contrasenya);
    }

    /**
     * @return Escenari
     */
    public Escenari getEscenari(){
        return escenari;
    }

    /**
     * @return Llista de grups amb aula i hora assignada
     */
    public void generarHorari(List<String> nomFitxers) throws FileNotFoundException{
        this.nomFitxersAAF = nomFitxers;
        List<Assignatura> assignatures = cargarAsignaturas(nomFitxers.get(0));
        List<Aula> aules = CtrlFitxerAules.getAll(nomFitxers.get(1));
        PeriodeLectiu periodeLectiu = CtrlFitxerPeriodeLectiu.getAll(nomFitxers.get(2)).get(0);
        List<Restriccions> restriccions = CtrlFitxerRestriccions.getAll(nomFitxers.get(3));
        escenari.setEscenari(assignatures, aules, periodeLectiu,restriccions);
        horari = new AdminController().generarHorari(escenari);
    }


    /**
     * Guarda l'horari actual
     * @param filename Filename
     */
    public void guardarHorari(String filename){
        ctrlFitxerGrupAssig.guardarHorari(horari, nomFitxersAAF, filename);
    }

    public void aux() throws FileNotFoundException {
        List<Assignatura> assignatures = cargarAsignaturas("AssignaturesAmbCorrequisits");
        List<Aula> aules = CtrlFitxerAules.getAll("AulesPetita");
        PeriodeLectiu periodeLectiu = CtrlFitxerPeriodeLectiu.getAll("PeriodesLectius").get(0);
        List<Restriccions> restriccions = CtrlFitxerRestriccions.getAll("Restriccions");
        escenari.setEscenari(assignatures, aules, periodeLectiu, restriccions);

        for(int i = 0; i < escenari.getAssignaturas().size(); ++i){
            escenari.getAssignaturas().get(i).print();
        }

        for(int i = 0; i < escenari.getAulas().size(); ++i){
            escenari.getAulas().get(i).print();
        }
        System.out.println();
        escenari.addAula(new Aula("A5201", TipusAula.TEORIA, 60));
        escenari.addAula(new Aula("A5201", TipusAula.TEORIA, 60));
        escenari.removeAula("A6201");
        for(int i = 0; i < escenari.getAulas().size(); ++i){
            escenari.getAulas().get(i).print();
        }

        guardarEscenariDeAulas("NewEscenariAula");
    }


    /**
     * canvia d'aula i hora un grup, en el cas que no sigui possible treu un missatge explicitant el motiu
     * @param idAssignatura ID de l'assignatura
     * @param numGrup numGrup
     * @param numPlaces numPlaces
     * @param tipusAula tipusAula
     * @param nomAulaAnterior nomAulaAnterior
     * @param nomAulaPosterior nomAulaPosterior
     * @param horaIniAnterior horaIniAnterior
     * @param duradaAnterior duradaAnterior
     * @param diaAnterior diaAnterior
     * @param horaIniPosterior horaIniPosterior
     * @param duradaPosterior duradaPosterior
     * @param diaPosterior diaPosterior
     */
    public void modificar(String idAssignatura, int numGrup, int numPlaces, TipusAula tipusAula, String nomAulaAnterior, String nomAulaPosterior, int horaIniAnterior, int duradaAnterior, DiaSetmana diaAnterior, int horaIniPosterior, int duradaPosterior, DiaSetmana diaPosterior){
        Assignatura assignatura = null;
        Aula aulaAnterior = null;
        Aula aulaPosterior = null;
        List<GrupAssignatura> copiaHorari = new ArrayList<>();
        for(GrupAssignatura g: horari){
            copiaHorari.add(g);
        }
        if(!escenari.existeixAssignatura(idAssignatura)){
            System.out.println("L'assignatura no existeix");
            return;
        }
        if(!escenari.existeixAula(nomAulaAnterior)){
            System.out.println("L'aula anterior no existeix");
            return;
        }
        else aulaAnterior = escenari.getAula(nomAulaAnterior);
        if(!escenari.existeixAula(nomAulaPosterior)){
            System.out.println("L'aula posterior no existeix");
            return;
        }
        else aulaPosterior = escenari.getAula(nomAulaPosterior);
        GrupAssignatura anterior = new GrupAssignatura(numGrup, numPlaces, tipusAula, idAssignatura, aulaAnterior, new Horari(horaIniAnterior, duradaAnterior,diaAnterior));
        int index = copiaHorari.indexOf(anterior);
        if(index == -1) {
            System.out.println("Error al buscar el grupAssignatura");
            return;
        }
        else copiaHorari.remove(anterior);
        Horari nou = new Horari(horaIniPosterior,duradaPosterior,diaPosterior);
        copiaHorari.add(new GrupAssignatura(numGrup,numPlaces,tipusAula,idAssignatura,aulaPosterior,nou));
        if(new AdminController().canviCorrecte(copiaHorari,escenari)){
            horari = copiaHorari;
            System.out.println("Horari canviat satisfactoriamnet");
        }
        else {
            System.out.println("Horari no es pot canviar");
        }

    }


    /**
     * afegeix una aula a l'escenari si no existeix
     * @param nom nom
     * @param aforament aforament
     * @param tipusAula tipusAula
     */
    public void afegirAulaAlEscenari(String nom, TipusAula tipusAula, int aforament){
        if(escenari.existeixAula(nom)) System.out.println("L'aula ja existeix");
        else {
            Aula a = new Aula(nom, tipusAula, aforament);
            escenari.addAula(a);
        }
    }

    /**
     * elimina una aula a l'escenari si existeix
     * @param a identifcador de l'aula
     */
    public void eliminarAulaDelEscenari(String a){
        if(escenari.existeixAula(a)) escenari.removeAula(a);
        else System.out.println("L'aula no existeix");
    }

    /**
     * afegeix una assignatura a l'escenari si no existeix
     * @param id id
     * @param nom nom
     * @param numCredits numCredits
     * @param numPlaces numPlaces
     * @param quadrimestre quadrimestre
     * @param numGrups numGrups
     * @param numSubgrups numSubgrups
     * @param SessionsSetmanals SessionsSetmanals
     * @param horesSetmanals horesSetmanals
     * @param correquisists correquisists
     * @param inverseCorrequisits inverseCorrequisits
     * @param fase fase
     */
    public void afegirAssignaturaAlEscenari(String id, String nom, int numCredits, int numPlaces, int quadrimestre, int numGrups, int numSubgrups, int SessionsSetmanals, int horesSetmanals, List<String> correquisists, List<String> inverseCorrequisits, String fase){
        if(escenari.existeixAssignatura(id)) System.out.println("L'assignatura ja existeix");
        else {
            Assignatura a = new Assignatura(id, nom, numCredits, numPlaces, quadrimestre, numGrups, numSubgrups, SessionsSetmanals, horesSetmanals, correquisists, inverseCorrequisits, fase);
            escenari.addAssignatura(a);
        }
    }

    /**
     * elimina una assignatura a l'escenari si existeix
     * @param a identifcador de l'assignatura
     */
    public void eliminarAssignaturaDelEscenari(String a){
        if(escenari.existeixAssignatura(a)) escenari.removeAssignatura(a);
        else System.out.println("L'assignatura no existeix");
    }

    /**
     * afegiex una restrccio a l'escenari si existeix
     * @param id identifcador de restriccio
     * @param tipus tipus de restriccio
     * @param restriccio restriccio
     */
    public void afegirRestriccio(String id, String tipus, List<String> restriccio){
        if(escenari.existeixRestriccio(id)) System.out.println("La restriccio ja existeix");
        else {
            Restriccions r = new Restriccions(id, tipus, restriccio);
            escenari.addRestriccions(r);
        }
    }


    /**
     * edita una restriccion de interval existent
     * @param id identifcador de restriccio
     * @param restriccio es una llista de restriccions encara que com a maxim tindra un element
     */
    public void editarRestriccioDeIntervalHorari(String id, List<String> restriccio){
        if(escenari.existeixRestriccio(id)){
            escenari.removeRestriccions(id);
            Restriccions r = new Restriccions(id,  "IntervalHorari", restriccio);
            escenari.addRestriccions(r);
        }
        else {
            System.out.println("La restriccio no existeix");
        }
    }

    /**
     * elimina una id de assignatura de la restriccio subnivell
     * @param id identifcador de restriccio
     * @param idAssig id de la assig que vols treure de la restricco
     */
    public void eliminarAssigDeRestriccioSubnivell(String id, String idAssig){
        if(escenari.existeixRestriccio(id)){
            escenari.removeAssigFromSubnivell(id,idAssig);
        }
        else {
            System.out.println("La restriccio no existeix");
        }
    }

    /**
     * elimina una restriccio a l'escenari si existeix
     * @param id identifcador de la restriccio
     */
    public void eliminarRestriccio(String id){
        if(escenari.existeixRestriccio(id)) escenari.removeRestriccions(id);
        else System.out.println("La restriccio no existeix");
    }

    /**
     * guarda l'escenari actual de aulas
     * @param filename filename
     */
    public void guardarEscenariDeAulas(String filename){
        guardarAula(escenari.getAulas(), filename);
    }


    /**
     * guarda l'escenari actual de assignaturas
     * @param filename filename
     */
    public void guardarEscenariDeAssignaturas(String filename){
        guardarAssignatura(escenari.getAssignaturas(), filename);
    }

    /**
     * @param filename Filename
     * @return Una llista de assignatures
     */
    public List<Assignatura> cargarAsignaturas(String filename) throws FileNotFoundException{
        return CtrlFitxerAssig.getAll(filename);
    }

    /**
     * @param filename Filename
     * @return Una llista de aules
     */
    public List<Aula> cargarAules(String filename) throws FileNotFoundException{
        return CtrlFitxerAules.getAll(filename);
    }

    /**
     * @param filename Filename
     * @return Una llista de users
     */
    public void cargarUsers(String filename) {
        this.ctrlDominioMantUser.carregarUsuaris(filename);
    }

    /**
     * @return Una llista de periodelectiu
     * @param filename Filename
     */
    public List<PeriodeLectiu> cargarPeriodeLectiu(String filename) throws FileNotFoundException{
        return CtrlFitxerPeriodeLectiu.getAll(filename);
    }

    /**
     * @return Una llista restriccions periodelectiu
     * @param filename Filename
     */
    public List<Restriccions> cargarRestriccions(String filename) throws FileNotFoundException{
        return CtrlFitxerRestriccions.getAll(filename);
    }

    /**
     * guarda les restriccions de l'escenari en un fitxer
     * @param filename Filename
     */
    public void guardarRestriccions(String filename) throws FileNotFoundException{
         CtrlFitxerRestriccions.guardarRestriccions(escenari.getRestriccionsAsList(), filename);
    }

    /**
     * @param filename Filename
     */
    public void cargarHorari(String filename) throws FileNotFoundException{
        LlistaGrupAssigYfilename llistaGrupAssigYfilename = ctrlFitxerGrupAssig.getAll(filename);
        this.horari = llistaGrupAssigYfilename.getHorari();
        printHorari();
        this.nomFitxersAAF = llistaGrupAssigYfilename.getFilenames();
        List<Assignatura> assignaturas = cargarAsignaturas(llistaGrupAssigYfilename.getFilenamAssignatura());
        List<Aula> aulas = cargarAules(llistaGrupAssigYfilename.getFilenameAula());
        PeriodeLectiu periodeLectiu = cargarPeriodeLectiu(llistaGrupAssigYfilename.getFilenamePeriodeLectiu()).get(0);
        List<Restriccions> restriccions = cargarRestriccions(llistaGrupAssigYfilename.getFilenameRestriccions());

        escenari.setEscenari(assignaturas, aulas, periodeLectiu, restriccions);
    }

    /**
     * @param filename Filename
     * @param horari Horari per a guardar
     */
    public void guardarHorari(List<GrupAssignatura> horari, String filename){
        ctrlFitxerGrupAssig.guardarHorari(horari, nomFitxersAAF, filename);
    }

    /**
     * @param filename Filename
     * @param assig Assignatures per a guardar
     */
    public void guardarAssignatura(List<Assignatura> assig, String filename){
        CtrlFitxerAssig.guardarAssignatura(assig,filename);
    }

    /**
     * @param filename Filename
     * @param aula Aules per a guardar
     */
    public void guardarAula(List<Aula> aula, String filename){
        CtrlFitxerAules.guardarAules(aula,filename);
    }

    /**
     * @param filename Filename
     * @param periodeLectius Periodes per a guardar
     */
    public void guardarPeriodeLectiu(List<PeriodeLectiu> periodeLectius, String filename){
        CtrlFitxerPeriodeLectiu.guardarPeriodeLectiu(periodeLectius,filename);
    }

    public void printGrupAssignatura(GrupAssignatura grupAssignatura) {
        System.out.println("Num Grup: "+grupAssignatura.getNumGrup());
        System.out.println("Num Places: "+grupAssignatura.getNumPlaces());
        System.out.println("TipuAula: "+grupAssignatura.getTipusAula());
        System.out.println("Assignatura: "+grupAssignatura.getIdAssignatura());

        if(grupAssignatura.getAula() != null){
            System.out.println("Aula :"+grupAssignatura.getAula().getNom());
        }
        if(grupAssignatura.getHorari() != null){
            System.out.println("Horari: "+grupAssignatura.getHorari().getHoraInici());
            System.out.println("Horari: "+grupAssignatura.getHorari().getDia());
        }
        System.out.println();
    }

    private char getCodeTipusAula(TipusAula tipus) {
        char ctipus = ' ';
        switch (tipus) {
            case TEORIA:
                ctipus = 'T'; break;
            case PROBLEMES:
                ctipus = 'P'; break;
            case LABORATORI:
                ctipus = 'L'; break;
        }
        return ctipus;
    }

    public List<GrupAssignatura> getHorari() { return this.horari; }

    public TreeMap<Integer, ArrayList<String>> getTreeMapHorari(String filename) throws FileNotFoundException {
        cargarHorari(filename);
        TreeMap<Integer, ArrayList<String>> dadesHorari = new TreeMap<Integer, ArrayList<String>>();

        if (horari.size() > 0 && horari.get((horari.size() - 1)).getNumGrup() == -1) System.out.println("No s'ha pogut crear cap horari amb aquestes restriccions");
        else {
            if (horari.size() > 0) {
                Collections.sort(horari, new Comparator<GrupAssignatura>() {
                    @Override
                    public int compare(GrupAssignatura o1, GrupAssignatura o2) {
                        int c = o1.getAula().getNom().compareTo(o2.getAula().getNom());
                        if (c == 0) c = o1.getHorari().getDia().compareTo(o2.getHorari().getDia());
                        if (c == 0) {
                            String hora1 = String.valueOf(o1.getHorari().getHoraInici());
                            if (hora1.length() == 1) hora1 = "0" + hora1;
                            String hora2 = String.valueOf(o2.getHorari().getHoraInici());
                            if (hora2.length() == 1) hora2 = "0" + hora2;
                            c = hora1.compareTo(hora2);
                        }
                        return c;
                    }
                });
            }

            GrupAssignatura actual;
            for (int i = 0; i < horari.size(); ++i) {
                actual = horari.get(i);
                String aula_act = actual.getAula().getNom();
                DiaSetmana dia_act = actual.getHorari().getDia();

                int horaIni = actual.getHorari().getHoraInici();
                int horaFi = horaIni+actual.getHorari().getDurada();
                int grup = actual.getNumGrup();
                String assig = actual.getIdAssignatura();
                TipusAula tipo = actual.getTipusAula();
                char ctipoAula = getCodeTipusAula(tipo);

                Integer key;
                for (int j = 0; j < actual.getHorari().getDurada(); ++j) {
                    key = ((horaIni + j) * 10) + dia_act.ordinal()+1;
                    String data = assig + ' ' + grup + ' ' + ctipoAula + " > " + aula_act;
                    if (!dadesHorari.containsKey(key))
                        dadesHorari.put(key, new ArrayList<String>());
                    dadesHorari.get(key).add(data);
                }
            }
        }
        return dadesHorari;
    }

    public void printHorari(){
        if (horari.size() > 0 && horari.get((horari.size() - 1)).getNumGrup() == -1) System.out.println("No s'ha pogut crear cap horari amb aquestes restriccions");
        else {
            if (horari.size() > 0) {
                Collections.sort(horari, new Comparator<GrupAssignatura>() {
                    @Override
                    public int compare(GrupAssignatura o1, GrupAssignatura o2) {
                        int c = o1.getAula().getNom().compareTo(o2.getAula().getNom());
                        if (c == 0) c = o1.getHorari().getDia().compareTo(o2.getHorari().getDia());
                        if (c == 0) {
                            String hora1 = String.valueOf(o1.getHorari().getHoraInici());
                            if (hora1.length() == 1) hora1 = "0" + hora1;
                            String hora2 = String.valueOf(o2.getHorari().getHoraInici());
                            if (hora2.length() == 1) hora2 = "0" + hora2;
                            c = hora1.compareTo(hora2);
                        }
                        return c;
                    }
                });
            }
            String aula_ant = "";
            DiaSetmana dia_ant = null;
            for (int i = 0; i < horari.size(); ++i) {
                GrupAssignatura actual = horari.get(i);
                String aula_act = actual.getAula().getNom();
                DiaSetmana dia_act = actual.getHorari().getDia();

                if(!aula_ant.equals(aula_act)) System.out.println("\nAula: " + aula_act);

                if(dia_ant != dia_act || !aula_ant.equals(aula_act)) System.out.println("Dia: " + dia_act);
                int horaIni = actual.getHorari().getHoraInici();
                int horaFi = horaIni+actual.getHorari().getDurada();
                int grup = actual.getNumGrup();
                String assig = actual.getIdAssignatura();
                TipusAula tipo = actual.getTipusAula();
                int places = actual.getNumPlaces();

                System.out.println(String.format("%02d", horaIni) + " - " + String.format("%02d", horaFi) + ": Grup " + grup + " " + assig + " " + tipo + " " + places);

                aula_ant = actual.getAula().getNom();
                dia_ant = actual.getHorari().getDia();
            }
        }
    }

    public String getHorariToString(){
        String sol = "";
        if (horari.size() > 0 && horari.get((horari.size() - 1)).getNumGrup() == -1) sol += "No s'ha pogut crear cap horari amb aquestes restriccions";
        else {
            if (horari.size() > 0) {
                Collections.sort(horari, new Comparator<GrupAssignatura>() {
                    @Override
                    public int compare(GrupAssignatura o1, GrupAssignatura o2) {
                        int c = o1.getAula().getNom().compareTo(o2.getAula().getNom());
                        if (c == 0) c = o1.getHorari().getDia().compareTo(o2.getHorari().getDia());
                        if (c == 0) {
                            String hora1 = String.valueOf(o1.getHorari().getHoraInici());
                            if (hora1.length() == 1) hora1 = "0" + hora1;
                            String hora2 = String.valueOf(o2.getHorari().getHoraInici());
                            if (hora2.length() == 1) hora2 = "0" + hora2;
                            c = hora1.compareTo(hora2);
                        }
                        return c;
                    }
                });
            }
            String aula_ant = "";
            DiaSetmana dia_ant = null;
            for (int i = 0; i < horari.size(); ++i) {
                GrupAssignatura actual = horari.get(i);
                String aula_act = actual.getAula().getNom();
                DiaSetmana dia_act = actual.getHorari().getDia();

                if(!aula_ant.equals(aula_act)) sol += "\nAula: " + aula_act + "\n";

                if(dia_ant != dia_act || !aula_ant.equals(aula_act))  sol += "Dia: " + dia_act + "\n";
                int horaIni = actual.getHorari().getHoraInici();
                int horaFi = horaIni+actual.getHorari().getDurada();
                int grup = actual.getNumGrup();
                String assig = actual.getIdAssignatura();
                TipusAula tipo = actual.getTipusAula();
                int places = actual.getNumPlaces();

                sol += String.format("%02d", horaIni) + " - " + String.format("%02d", horaFi) + ": Grup " + grup + " " + assig + " " + tipo + " " + places + "\n";

                aula_ant = actual.getAula().getNom();
                dia_ant = actual.getHorari().getDia();
            }
        }
        return sol;
    }

    public String getDataUsuari() {
        return this.ctrlDominioMantUser.getDataUsuari();
    }

    public String getIdUsuari() {
        return ctrlDominioMantUser.getIdUsuari();
    }

    public String[] getListaDirHorarisGenerats() {
        File dir = new File(PATH_HORARIS + getIdUsuari());
        String[] fitxers = dir.list();
        return fitxers;
    }

    public int getNumDiesPeriodeLectiu() {
        return escenari.getPeriodeLectiu().getDataFinal().ordinal() + 1;
    }
}

