package com.prop.fib.horaris.domain.Classes;

import java.util.*;

/**
 * @author Pere Verges Boncompte
 */

public class Escenari {
    private static Escenari e;

    private Map<String, Assignatura> assignaturas;
    private Map<String, Aula> aulas;
    private PeriodeLectiu periodeLectiu;
    private Map<String, Restriccions> restriccions;

    public static Escenari getInstance() {
        e = new Escenari() {};
        return e;
    }
    private Escenari() {}


    /**
     * Crea un escenari
     * @param assignatures Aforament
     * @param aules Aforament
     * @param periodeLectiu Aforament
     * @param restriccions Aforament
     */
    public void setEscenari(List<Assignatura> assignatures, List<Aula> aules, PeriodeLectiu periodeLectiu, List<Restriccions> restriccions){
        Map<String, Assignatura> auxAsignaturas = new HashMap<>();
        for(int i = 0; i < assignatures.size(); ++i){
            auxAsignaturas.put(assignatures.get(i).getId(), assignatures.get(i));
        }
        this.assignaturas = auxAsignaturas;
        Map<String, Aula> auxAulas = new HashMap<>();
        for(int i = 0; i < aules.size(); ++i){
            auxAulas.put(aules.get(i).getNom(), aules.get(i));
        }
        this.aulas = auxAulas;
        Map<String, Restriccions> auxRestriccions = new HashMap<>();
        for(int i = 0; i < restriccions.size(); ++i){
            auxRestriccions.put(restriccions.get(i).getId(), restriccions.get(i));
        }
        this.restriccions = auxRestriccions;
        this.periodeLectiu = periodeLectiu;

    }

    /**
     * @return Les assignatures de l'escenari
     */
    public List<Assignatura> getAssignaturas(){
        return new ArrayList<Assignatura> (assignaturas.values());
    }

    /**
     * @param a Nom de l'assignatura a afegir
     */
    public void addAssignatura(Assignatura a){
        assignaturas.put(a.getId(), a);
    }

    /**
     * @param a Nom de l'assignatura a eliminar
     */
    public void removeAssignatura(String a){
        assignaturas.remove(a);
    }

    /**
     * @return Les aules de l'escenari
     */
    public List<Aula> getAulas(){
        return new ArrayList<Aula> (aulas.values());
    }

    /**
     * @return true si l'aula existeix en l'ecenari
     */
    public boolean existeixAula(String aula){
        return aulas.get(aula) != null;
    }

    /**
     * @return  true si l'assignatura existeix en l'ecenari
     */
    public boolean existeixAssignatura(String assig){
        return assignaturas.get(assig) != null;
    }

    /**
     * @return l'aula amb id nom
     */
    public Aula getAula(String nom){
        return aulas.get(nom);
    }

    /**
     * @return l'assignatura amb id "id"
     */
    public Assignatura getAssignatura(String id){
        return assignaturas.get(id);
    }

    /**
     * @param a Nom de l'aula a afegir
     */
    public void addAula(Aula a){
        aulas.put(a.getNom(), a);
    }

    /**
     * @param a Nom de l'aula a eliminar
     */
    public void removeAula(String a){
        aulas.remove(a);
    }

    /**
     * @return el periode lectiu de l'escenari
     */
    public PeriodeLectiu getPeriodeLectiu(){
        return periodeLectiu;
    }

    /**
     * @return true si existeix la restriccio amb id "a"
     */
    public boolean existeixRestriccio(String a){
        return restriccions.get(a) != null;
    }

    /**
     * @param r Nom de la restriccion a afegir
     */
    public void addRestriccions(Restriccions r){
        restriccions.put(r.getId(), r);
    }

    /**
     * @param a Nom de l'assignatura a eliminar
     */
    public void removeRestriccions(String a){
        restriccions.remove(a);
    }

    /**
     * @return Les restriccions de l'escenari
     */
    public List<Restriccions> getRestriccionsAsList(){
        return new ArrayList<Restriccions>(restriccions.values());
    }

    /**
     * @param restriccioNom String
     * @param idAssig String
     * Elimina de la restricció de subnivell amb nom restriccioNom l'assignatura idAssig
     */
    public void removeAssigFromSubnivell(String restriccioNom, String idAssig){
        for(Restriccions r : restriccions.values()){
            if(r.getNom().equals("Subnivell") && r.getId().equals(restriccioNom)){
                r.getIds().remove(idAssig);
            }
        }
    }
}
