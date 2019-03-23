package com.prop.fib.horaris.domain.Classes;

/**
 * @author Juan Sebastian Brito
 */
public class Posibilidad {
    private Aula aula;
    private Horari horari;

    /**
     * Class constructor
     * @param aula Aula
     * @param horari Horari
     */
    public Posibilidad(Aula aula, Horari horari) {
        this.aula = aula;
        this.horari = horari;
    }

    /**
     * @return Aula
     */
    public Aula getAula() {
        return aula;
    }

    /**
     * @param aula Aula a assignar
     */
    public void setAula(Aula aula) {
        this.aula = aula;
    }

    /**
     * @return Horari
     */
    public Horari getHorari() {
        return horari;
    }

    /**
     * @param horari Horari a assignar
     */
    public void setHorari(Horari horari) {
        this.horari = horari;
    }
}
