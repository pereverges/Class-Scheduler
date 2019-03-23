package com.prop.fib.horaris.domain.Classes;

import com.prop.fib.horaris.Enumerations.DiaSetmana;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Sebastian Brito
 */
public class Horari {

    private int horaInici;
    private int durada;
    private DiaSetmana dia;

    /**
     * Class constructor
     * @param horaInici Hora d'inici
     * @param durada Durada
     * @param dia Dia de la setmana
     */
    public Horari(int horaInici, int durada, DiaSetmana dia){
        this.horaInici = horaInici;
        this.durada = durada;
        this.dia = dia;
    }

    /**
     * @return Hora d'inici
     */
    public int getHoraInici() {
        return horaInici;
    }

    /**
     * @return Durada
     */
    public int getDurada() {
        return durada;
    }

    /**
     * @return Dia
     */
    public DiaSetmana getDia() {
        return dia;
    }

    /**
     * @param horaInici Hora a assignar
     */
    public void setHoraInici(int horaInici) {
        this.horaInici = horaInici;
    }

    /**
     * @param durada Durada a assignar
     */
    public void setDurada(int durada) {
        this.durada = durada;
    }

    /**
     * @param dia Dia a assignar
     */
    public void setDia(DiaSetmana dia) {
        this.dia = dia;
    }

    /**
     * @param horari Horari
     * @return Cert si se solapen, fals si no
     */
    public boolean solapa(Horari horari){

        int horaInici2 = horari.getHoraInici();
        int horaFin = horaInici + durada;
        int horaFin2 = horaInici2 + horari.getDurada();

        if(dia == horari.getDia()){

            if(horaInici == horaInici2) return true;
            if(horaInici < horaInici2 && horaInici2 < horaFin) return true;
            if(horaInici2 < horaInici && horaInici < horaFin2) return true;
        }
        return false;
    }

    /**
     * @param periodeLectiu Periode lectiu a comprovar
     * @return Llista amb tots el possibles horaris dins d'un periode lectiu
     */
    public static List<Horari> generateAllPossibleHoraris(PeriodeLectiu periodeLectiu) {
        int horaInici = periodeLectiu.getHoraInici();
        int horaFinal = periodeLectiu.getHoraFinal();
        DiaSetmana diaInici = periodeLectiu.getDataInici();
        DiaSetmana diaFinal = periodeLectiu.getDataFinal();

        List<Horari> result = new ArrayList<>();
        for(int i = diaInici.ordinal(); i <= diaFinal.ordinal(); i++){
            for(int j = horaInici; j < horaFinal; j++){
                result.add(new Horari(j, 2, DiaSetmana.values()[i]));
            }
        }
        return result;
    }

    public void print(){
        System.out.println("Hora inici: " + horaInici);
        System.out.println("Durada: " + durada);
        System.out.println("Dia: " + dia);
    }

    /**
     * @param obj Object
     * @return Cert si els horaris són iguals
     */
    @Override
    public boolean equals(Object obj){
        Horari h = (Horari) obj;
        return horaInici == h.getHoraInici() && durada == h.getDurada() && dia == h.getDia();
    }
}
