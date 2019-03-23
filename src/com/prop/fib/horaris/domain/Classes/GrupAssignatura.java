package com.prop.fib.horaris.domain.Classes;

import com.prop.fib.horaris.Enumerations.TipusAula;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Sebastian Brito
 */
public class GrupAssignatura {

    private int numGrup;
    private int numPlaces;
    private TipusAula tipusAula;
    private String idAssignatura;
    private Aula aula;
    private Horari horari;

    // Usado para el algoritmo de forward checking
    private List<Posibilidad> posibilidades;

    /**
     * Constructor complet
     * @param numGrup Número del grup
     * @param numPlaces Nombre de places
     * @param tipusAula Tipus d'aula
     * @param idAssignatura Identificador de l'assignatura
     * @param aula Aula
     * @param horari Horari
     */
    public GrupAssignatura(int numGrup, int numPlaces, TipusAula tipusAula, String idAssignatura, Aula aula, Horari horari){
        this.numGrup = numGrup;
        this.numPlaces = numPlaces;
        this.tipusAula = tipusAula;
        this.idAssignatura = idAssignatura;
        this.aula = aula;
        this.horari = horari;
        this.posibilidades = new ArrayList<>();
    }

    /**
     * Constructor sense aula ni horari
     * @param numGrup Número del grup
     * @param numPlaces Nombre de places
     * @param tipusAula Tipus d'aula
     * @param idAssignatura Identificador de l'assignatura
     */
    public GrupAssignatura(int numGrup, int numPlaces, TipusAula tipusAula, String idAssignatura){
        this.numGrup = numGrup;
        this.numPlaces = numPlaces;
        this.tipusAula = tipusAula;
        this.idAssignatura = idAssignatura;
        this.posibilidades = new ArrayList<>();
    }

    /**
     * Genera todas las posibles combinaciones de horarios y aulas
     * @param horaris Horaris
     * @param e Escenari
     */
    public void generarPosibilidades(List<Horari> horaris, Escenari e){
        List<Aula> aulas = e.getAulas();
        for(Aula aula : aulas) {
            if(isValid(aula)){
                for (Horari horari : horaris) {
                    if(Restriccions.horariDinsInterval(horari, idAssignatura, e)) {
                        posibilidades.add(new Posibilidad(aula, horari));
                    }
                }
            }
        }
    }

    /**
     * @param aula Aula
     * @return Cert si l'aula es vàlida per aquest grup
     */
    private boolean isValid(Aula aula){
        if(!Restriccions.grupFitsAula(this, aula)) return false;
        if(!Restriccions.aulaCorrecta(this, aula)) return false;

        return true;
    }

    /**
     * Constructor fail, usat a l'algoritme de backtracking a la generació de l'horari
     * @param numGrup Número del grup
     */
    public GrupAssignatura(int numGrup){
        this.numGrup = numGrup;
    }

    /**
     * @return Número del grup
     */
    public int getNumGrup() {
        return numGrup;
    }

    /**
     * @return Nombre de places
     */
    public int getNumPlaces() {
        return numPlaces;
    }

    /**
     * @return Tipus d'aula
     */
    public TipusAula getTipusAula() {
        return tipusAula;
    }

    /**
     * @return Identificador de l'assignatura
     */
    public String getIdAssignatura() {
        return idAssignatura;
    }

    /**
     * @return Aula
     */
    public Aula getAula() {
        return aula;
    }

    /**
     * @return Horari
     */
    public Horari getHorari() {
        return horari;
    }

    /**
     * @return Posibilidades
     */
    public List<Posibilidad> getPosibilidades() {
        return posibilidades;
    }

    /**
     * @param numGrup Número del grup a assignar
     */
    public void setNumGrup(int numGrup) {
        this.numGrup = numGrup;
    }

    /**
     * @param numPlaces Número de places a assignar
     */
    public void setNumPlaces(int numPlaces) {
        this.numPlaces = numPlaces;
    }

    /**
     * @param tipusAula Tipus d'aula a assignar
     */
    public void setTipusAula(TipusAula tipusAula) {
        this.tipusAula = tipusAula;
    }

    /**
     * @param idAssignatura Identificador de l'assignatura a assignar
     */
    public void setIdAssignatura(String idAssignatura) {
        this.idAssignatura = idAssignatura;
    }

    /**
     * @param aula Aula a assignar
     */
    public void setAula(Aula aula) {
        this.aula = aula;
    }

    /**
     * @param horari Horari a assignar
     */
    public void setHorari(Horari horari) {
        this.horari = horari;
    }

    /**
     * @param posibilidades Posibilidades a assignar
     */
    public void setPosibilidades(List<Posibilidad> posibilidades) {
        this.posibilidades = new ArrayList<>(posibilidades);
    }

    public void print(){
        System.out.println("Num Grup: "+numGrup);
        System.out.println("Num Places: "+numPlaces);
        System.out.println("Assignatura: "+idAssignatura);
        System.out.println("TipusAula: "+tipusAula);
        if(aula != null) {
            System.out.println();
            aula.print();

        }
        if(horari != null){
            System.out.println();
            horari.print();
        }


        System.out.println(".............................");
    }

    /**
     * @param obj Object
     * @return Cert si els grups són iguals
     */
    @Override
    public boolean equals(Object obj){
        GrupAssignatura g = (GrupAssignatura) obj;
        return numGrup == g.getNumGrup() && numPlaces == g.getNumPlaces() && tipusAula == g.getTipusAula() &&
               idAssignatura.equals(g.getIdAssignatura()) && aula.getNom().equals(g.getAula().getNom()) && horari.equals(g.getHorari());
    }
}
