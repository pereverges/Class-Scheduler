package com.prop.fib.horaris.domain.Controllers;

import com.prop.fib.horaris.domain.Classes.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.List;

import static com.prop.fib.horaris.Enumerations.TipusAula.PROBLEMES;
import static com.prop.fib.horaris.Enumerations.TipusAula.TEORIA;

/**
 * @author Juan Sebastian Brito
 */
public class AdminController {

    private Escenari escenari;

    /**
     * @param escenari Escenari
     * @return Llista de grups que representen un horari
     */
    public List<GrupAssignatura> generarHorari(Escenari escenari){
        this.escenari = escenari;

        List<Horari> horaris = Horari.generateAllPossibleHoraris(escenari.getPeriodeLectiu());
        List<GrupAssignatura> grups = crearGrupos(escenari.getAssignaturas());

        for(GrupAssignatura g : grups) g.generarPosibilidades(horaris, escenari);

        return forwardChecking(grups, new ArrayList<>());
    }

    /**
     * @param vfuturas List<GrupAssignatura>
     * @param solution List<GrupAssignatura>
     * @return Llista de grups després de fer un backtracking amb forward checking
     */
    private List<GrupAssignatura> forwardChecking(List<GrupAssignatura> vfuturas, List<GrupAssignatura> solution){
        if(vfuturas.isEmpty()){
            return solution;
        }
        else {
            GrupAssignatura vactual = vfuturas.get(0);
            vfuturas.remove(0);

            boolean isValid = false;
            List<Posibilidad> posibilidades = vactual.getPosibilidades();
            for(int i = 0; i < posibilidades.size() && !isValid; i++) {

                vactual.setAula(posibilidades.get(i).getAula());
                vactual.setHorari(posibilidades.get(i).getHorari());

                solution.add(vactual);
                Restriccions.propagarRestricciones(vfuturas, vactual, escenari);

                if (!algunDominioVacio(vfuturas)) {
                    isValid = true;
                    solution = forwardChecking(vfuturas, solution);
                    if (!esFallo(solution)) return solution;
                    else solution.remove(solution.size() - 1);
                }
                else solution.remove(solution.size() - 1);
            }

            return fallo(solution);
        }
    }

    /**
     * @param vfuturas List<GrupAssignatura>
     * @return Cert si hi ha algun grup sense més possibles combinacions
     */
    private boolean algunDominioVacio(List<GrupAssignatura> vfuturas){
        for(GrupAssignatura g : vfuturas){
            if(g.getPosibilidades().size() == 0) return true;
        }
        return false;
    }

    /**
     * @param solution List<GrupAssignatura>
     * @return Cert si la solució es válida
     */
    private boolean isValid(List<GrupAssignatura> solution){
        if(!solution.isEmpty()){
            GrupAssignatura grup = solution.get(solution.size() - 1);
            if(!Restriccions.horariDinsPeriodeLectiu(grup.getHorari(), escenari.getPeriodeLectiu())) return false;
            if(!Restriccions.grupFitsAula(grup, grup.getAula())) return false;
            if(!Restriccions.aulaCorrecta(grup, grup.getAula())) return false;
            if(!Restriccions.teoriaAndLabDifferentDay(solution, grup)) return false;
            if(!Restriccions.correquisitos(solution, grup, escenari.getAssignaturas())) return false;
            if(!Restriccions.gruposDiferenteAula(solution, grup)) return false;
            if(!Restriccions.noSolapamentMateixaFase(solution, grup, escenari.getAssignaturas())) return false;
            if(!Restriccions.horaDinsInterval(grup, escenari)) return false;
            if(!Restriccions.RestriccionesPersonalizadaSubnivel(solution, grup, escenari)) return false;
        }
        return true;
    }

    /**
     * @param solution List<GrupAssignatura>
     * @param escenari Escenari
     * @return Cert si la solució es valida, fals si no
     */
    public boolean canviCorrecte(List<GrupAssignatura> solution, Escenari escenari){
        this.escenari = escenari;
        return isValid(solution);
    }

    /**
     * @param l List<GrupAssignatura>
     * @return Retorna la lista de grupos recibida como parámetro con un flag de fallo (último grupo con numGrup = -1)
     */
    private List<GrupAssignatura> fallo(List<GrupAssignatura> l){
        l.add(new GrupAssignatura(-1));
        return l;
    }

    /**
     * @param l List<GrupAssignatura>
     * @return Cierto si la lista es un fallo
     */
    private boolean esFallo(List<GrupAssignatura> l){
        return !l.isEmpty() && l.get(l.size()-1).getNumGrup() == -1;
    }

    /**
     * @param assignaturas Assignatures per a crear els subgrups
     * @return Llista de grups
     */
    private List<GrupAssignatura> crearGrupos(List<Assignatura> assignaturas){

        List<GrupAssignatura> grupAssignaturas = new ArrayList<>();

        for(int i = 0; i < assignaturas.size(); ++i){
            Assignatura assigActual = assignaturas.get(i);
            for(int j = 0; j < assigActual.getNumGrups(); ++j){
                int numGrup = (j+1)*10;
                int PlacesTotal = assigActual.getNumPlaces();
                String idAssig = assigActual.getId();
                grupAssignaturas.add(new GrupAssignatura(numGrup, PlacesTotal, TEORIA, idAssig, null, null));

                //printGrupAssignatura(new GrupAssignatura(numGrup, PlacesTotal, TEORIA, idAssig, null, null));

                int numSubGrups = assigActual.getNumSubgrupsPerGrup();
                for(int k = 0; k < numSubGrups; ++k){
                    grupAssignaturas.add(new GrupAssignatura(numGrup+k+1, (PlacesTotal/numSubGrups), PROBLEMES, idAssig, null, null));

                    //printGrupAssignatura(new GrupAssignatura(numGrup+k+1, (PlacesTotal/numSubGrups), PROBLEMES, idAssig, null, null));
                }

            }
        }

        return grupAssignaturas;
    }
}
