package com.prop.fib.horaris.domain.Classes;

import java.util.List;

/**
 * @author Pere Verges Boncompte
 */
public class Assignatura {

    private String id;
    private String nom;
    private int numCredits;
    private int numPlaces;
    private int quadrimestre;
    private int numGrups;
    private int numSubgrupsPerGrup;
    private int numSessionsSetmanals;
    private int horesSetmanals;
    private String fase;

    private List<String> correquisits;
    private List<String> inverseCorrequisits;


    /**
     * Class constructor
     * @param id ID de l'assignatura
     * @param nom Nom de l'assignatura
     * @param numCredits Nummero de credits de l'assignatura
     * @param numPlaces Numero de places de l'assignatura
     * @param quadrimestre Quadrimestre que es fa l'assignatura
     * @param numGrups Numero de grups a l'assignatura
     * @param numSubgrupsPerGrup Numero de subgrups de cada grup de l'assignatura
     * @param numSessionsSetmanals Numero de sessions a la setmana
     * @param horesSetmanals Hores de classe a la setmana
     * @param correquisits Correquisits de l'assignatura
     * @param inverseCorrequisits Assignatures de les que es correquisit
     * @param fase Fase de l'assignatura (Inicial, obligatoria, especialitat...)
     */
    public Assignatura(String id, String nom, int numCredits, int numPlaces, int quadrimestre, int numGrups, int numSubgrupsPerGrup, int numSessionsSetmanals, int horesSetmanals, List<String> correquisits, List<String> inverseCorrequisits, String fase){
        this.id = id;
        this.nom = nom;
        this.numCredits = numCredits;
        this.numPlaces = numPlaces;
        this.quadrimestre = quadrimestre;
        this.numGrups = numGrups;
        this.numSubgrupsPerGrup = numSubgrupsPerGrup;
        this.numSessionsSetmanals = numSessionsSetmanals;
        this.horesSetmanals = horesSetmanals;
        this.correquisits = correquisits;
        this.inverseCorrequisits = inverseCorrequisits;
        this.fase = fase;
    }

    /**
     * @return Identificador de l'assignatura
     */
    public String getId(){ return id; }

    /**
     * @return Nom de l'assignatura
     */
    public String getNom(){ return nom; }

    /**
     * @return Numero de credits de l'assignatura
     */
    public int getNumCredits(){ return numCredits; }

    /**
     * @return Quadrimestre  de l'assignatura
     */
    public int getQuadrimestre(){ return quadrimestre; }

    /**
     * @return Numero de grups de l'assignatura
     */
    public int getNumGrups(){ return numGrups; }

    /**
     * @return Numero de subgrups de cada grup de l'assignatura
     */
    public int getNumSubgrupsPerGrup(){ return numSubgrupsPerGrup; }

    /**
     * @return Numero de sessions setmanalas de l'assignatura
     */
    public int getNumSessionsSetmanals(){ return numSessionsSetmanals; }

    /**
     * @return Hores setmanals de l'assignatura
     */
    public int getHoresSetmanals(){ return horesSetmanals; }

    /**
     * @return Llista de les assignatures correquisists
     */
    public List<String> getCorrequisits(){return correquisits;}

    /**
     * @return Llista de les assignatures de les que es correquisist
     */
    public List<String> getInverseCorrequisits(){return inverseCorrequisits;}

    /**
     * @return Fase de l'assignatura
     */
    public String getFase() {return fase;}

    /**
     * @return Numero de places de l'assignatura
     */
    public int getNumPlaces(){ return numPlaces;}

    /**
     * @param id ID de assignatura
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @param nom Nom de l'assignatura
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param numPlaces Places de l'assignatura
     */
    public void setNumPlaces(int numPlaces) {
        this.numPlaces = numPlaces;
    }

    /**
     * @param fase Fase de l'assignatura
     */
    public void setFase(String fase) {
        this.fase = fase;
    }

    /**
     * @param correquisits Correquisits de l'assignatura
     */
    public void setCorrequisits(List<String> correquisits) {
        this.correquisits = correquisits;
    }

    /**
     * @param horesSetmanals Hores de l'assignatura
     */
    public void setHoresSetmanals(int horesSetmanals) {
        this.horesSetmanals = horesSetmanals;
    }

    /**
     * @param inverseCorrequisits InverseCorrequisits de l'assignatura
     */
    public void setInverseCorrequisits(List<String> inverseCorrequisits) {
        this.inverseCorrequisits = inverseCorrequisits;
    }

    /**
     * @param numCredits Credits de l'assignatura
     */
    public void setNumCredits(int numCredits) {
        this.numCredits = numCredits;
    }

    /**
     * @param numGrups Grups de l'assignatura
     */
    public void setNumGrups(int numGrups) {
        this.numGrups = numGrups;
    }

    /**
     * @param numSessionsSetmanals Sessions de l'assignatura
     */
    public void setNumSessionsSetmanals(int numSessionsSetmanals) {
        this.numSessionsSetmanals = numSessionsSetmanals;
    }

    /**
     * @param numSubgrupsPerGrup Subgrups de l'assignatura
     */
    public void setNumSubgrupsPerGrup(int numSubgrupsPerGrup) {
        this.numSubgrupsPerGrup = numSubgrupsPerGrup;
    }

    /**
     * @param quadrimestre Quadri de l'assignatura
     */
    public void setQuadrimestre(int quadrimestre) {
        this.quadrimestre = quadrimestre;
    }


    public void print(){
        System.out.println("ID Assig: "+ id);
        System.out.println("Nom Assig: "+ nom);
        System.out.println("NumCredits: "+ numCredits);
        System.out.println("NumPlaces: "+ numPlaces);
        System.out.println("NumGrups: "+ numGrups);
        System.out.println("NumSubgrups: "+ numSubgrupsPerGrup);
        System.out.println("NumSessions: "+ numSessionsSetmanals);
        System.out.println("HoresSetmanals: "+ horesSetmanals);
        System.out.println("Quadrimestre: "+ quadrimestre);
        System.out.println("Fase: "+ fase);

        System.out.print("Correquisits: ");
        for (int i = 0; i < correquisits.size(); ++i){
            System.out.print(correquisits.get(i)+ " ");
        }
        System.out.println();

        System.out.print("InverseCorrequisits: ");
        for (int i = 0; i < inverseCorrequisits.size(); ++i){
            System.out.print(inverseCorrequisits.get(i)+ " ");
        }

        System.out.println();
        System.out.println();
    }
}
