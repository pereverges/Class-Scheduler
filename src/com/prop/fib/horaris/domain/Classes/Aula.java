package com.prop.fib.horaris.domain.Classes;

import com.prop.fib.horaris.Enumerations.TipusAula;

/**
 * @author Pere Verges Boncompte
 */
public class Aula {

    private String nom;
    private TipusAula tipusAula;
    private int aforament;

    /**
     * Class constructor
     * @param nom Nom de l'aula
     * @param tipusAula Tipus d'aula
     * @param aforament Aforament
     */
    public Aula(String nom, TipusAula tipusAula, int aforament){
        this.nom = nom;
        this.tipusAula = tipusAula;
        this.aforament = aforament;
    }

    /**
     * @return Nom de l'aula
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return Tipus d'aula
     */
    public TipusAula getTipusAula() {
        return tipusAula;
    }

    /**
     * @return Aforament
     */
    public int getAforament() { return aforament; }

    /**
     * @param nom Nom de l'aula
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param tipusAula Tipus d'aula
     */
    public void setTipusAula(TipusAula tipusAula) {
        this.tipusAula = tipusAula;
    }

    /**
     * @param aforament Aforament
     */
    public void setAforament(int aforament) {
        this.aforament = aforament;
    }

    public void print(){
        System.out.println("Aula: "+ nom);
        System.out.println("TipusAula: "+ tipusAula);
        System.out.println("Aformanet: "+ aforament);
    }
}
