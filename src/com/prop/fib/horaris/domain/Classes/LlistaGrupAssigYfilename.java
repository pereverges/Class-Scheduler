package com.prop.fib.horaris.domain.Classes;

import java.util.List;


/**
 * @author Pere Verges Boncompte
 */
public class LlistaGrupAssigYfilename {

    private List<GrupAssignatura> horari;
    List<String> nomFitxers;


    /**
     * Class constructor
     * @param horari horari assignat als fitxers
     * @param nomFitxersAAP nom dels fitxers
     */
    public LlistaGrupAssigYfilename(List<GrupAssignatura> horari, List<String> nomFitxersAAP){
        this.horari = horari;
        this.nomFitxers = nomFitxersAAP;
    }

    /**
     * @return  nom del primer fitxer
     */
    public String getFilenamAssignatura() { return nomFitxers.get(0); }

    /**
     * @return nom del segon fitxer
     */
    public String getFilenameAula() {
        return nomFitxers.get(1);
    }

    /**
     * @return  nom del tercer fitxer
     */
    public String getFilenamePeriodeLectiu() { return nomFitxers.get(2); }

    /**
     * @return  nom del quart fitxer
     */
    public String getFilenameRestriccions() { return nomFitxers.get(3); }

    /**
     * @return llista de nom dels fitxers
     */
    public List<String> getFilenames() {
        return nomFitxers;
    }

    /**
     * @return l'horari assignat als fitxres
     */
    public List<GrupAssignatura> getHorari() {
        return horari;
    }

    /**
     * @param filenamAssignatura filenamAssignatura
     */
    public void setFilenamAssignatura(String filenamAssignatura) {
        this.nomFitxers.set(0, filenamAssignatura);
    }

    /**
     * @param filenameAula filenameAula
     */
    public void setFilenameAula(String filenameAula) {
        this.nomFitxers.set(1, filenameAula);
    }

    /**
     * @param filenamePeriodeLectiu filenamePeriodeLectiu
     */
    public void setFilenamePeriodeLectiu(String filenamePeriodeLectiu) { this.nomFitxers.set(2, filenamePeriodeLectiu); }

    /**
     * @param filenameRestriccions filenameRestriccions
     */
    public void setFilenameRestriccions(String filenameRestriccions) { this.nomFitxers.set(3, filenameRestriccions); }

    /**
     * @param horari horari
     */
    public void setHorari(List<GrupAssignatura> horari) {
        this.horari = horari;
    }

}

