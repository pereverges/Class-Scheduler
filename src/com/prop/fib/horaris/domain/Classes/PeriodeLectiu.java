package com.prop.fib.horaris.domain.Classes;

import com.prop.fib.horaris.Enumerations.DiaSetmana;

/**
 * @author Juan Sebastian Brito
 */
public class PeriodeLectiu {

    private String nom;
    private String id;
    private int horaInici;
    private int horaFinal;
    private DiaSetmana dataInici;
    private DiaSetmana dataFinal;

    /**
     * Class constructor
     * @param nom Nom del periode lectiu
     * @param id Identificador
     * @param horaInici Hora inicial
     * @param horaFinal Hora final
     * @param dataInici Dia inicial
     * @param dataFinal Dia final
     */
    public PeriodeLectiu(String nom, String id, int horaInici, int horaFinal, DiaSetmana dataInici, DiaSetmana dataFinal){
        this.nom = nom;
        this.id = id;
        this.horaInici = horaInici;
        this.horaFinal = horaFinal;
        this.dataInici = dataInici;
        this.dataFinal = dataFinal;
    }

    /**
     * @return Nom del periode lectiu
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return Identificador del periode lectiu
     */
    public String getId() {
        return id;
    }

    /**
     * @return Hora inicial
     */
    public int getHoraInici() {
        return horaInici;
    }

    /**
     * @return Hora final
     */
    public int getHoraFinal() {
        return horaFinal;
    }

    /**
     * @return Dia inicial
     */
    public DiaSetmana getDataInici() {
        return dataInici;
    }

    /**
     * @return Dia final
     */
    public DiaSetmana getDataFinal() {
        return dataFinal;
    }

    /**
     * @param nom Nom del periode lectiu
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param id Identificador del periode lectiu
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param horaInici Hora inicial a assignar
     */
    public void setHoraInici(int horaInici) {
        this.horaInici = horaInici;
    }

    /**
     * @param horaFinal Hora final a assignar
     */
    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * @param dataInici Data inicial a assignar
     */
    public void setDataInici(DiaSetmana dataInici) {
        this.dataInici = dataInici;
    }

    /**
     * @param dataFinal Data final a assignar
     */
    public void setDataFinal(DiaSetmana dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void print(){
        System.out.println("Nom: "+ nom);
        System.out.println("ID: "+ id);
        System.out.println("HoraIni: "+ horaInici);
        System.out.println("HoraFi: "+ horaFinal);
        System.out.println("DiaIni: "+ dataInici);
        System.out.println("DiaFinal: "+ dataFinal);
    }

}
