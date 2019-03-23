package com.prop.fib.horaris.domain.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Sebastian Brito
 */
public class PlaEstudis {

    private static String nom;
    private static String id;
    private static List<Assignatura> assignatures;
    private static PeriodeLectiu periodeLectiu;

    /**
     * Class constructor
     * @param nom Nom del pla d'estudis
     * @param id Identificador del pla d'estudis
     * @param periodeLectiu Periode lectiu
     */
    PlaEstudis(String nom, String id, PeriodeLectiu periodeLectiu){
        this.nom = nom;
        this.id = id;
        this.assignatures = new ArrayList<>();
        this.periodeLectiu = periodeLectiu;
    }

    /**
     * @return Nom del pla d'estudis
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return Identificador del pla d'estudis
     */
    public String getId() {
        return id;
    }

    /**
     * @return Llista d'assignatures
     */
    public List<Assignatura> getAssignatures() {
        return assignatures;
    }

    /**
     * @return Periode lectiu
     */
    public PeriodeLectiu getPeriodeLectiu() {
        return periodeLectiu;
    }

    /**
     * @param nom Nom del pla d'estudis
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param id Identificador del pla d'estudis
     */
    public void setId(String id) { this.id = id; }

    /**
     * @param assignatures Llista d'assignatures
     */
    public void setAssignatures(List<Assignatura> assignatures) {
        this.assignatures = assignatures;
    }

    /**
     * @param periodeLectiu Periode lectiu
     */
    public void setPeriodeLectiu(PeriodeLectiu periodeLectiu) {
        this.periodeLectiu = periodeLectiu;
    }

}
