package com.prop.fib.horaris.domain.Classes;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Character.getNumericValue;

/**
 * @author Juan Sebastian Brito i Pere Verges
 */
public class Restriccions {

    private String tipus;
    private String id;
    private List<String> ids;

    /**
     * Class constructor
     * @param id String
     * @param tipus String
     * @param ids List<String>
     */
    public Restriccions(String id, String tipus, List<String> ids){
        this.id = id;
        this.tipus = tipus;
        this.ids = ids;
    }

    /**
     * @return Nom
     */
    public String getNom() {
        return tipus;
    }

    /**
     * @return Llista d'ids
     */
    public List<String> getIds() {
        return ids;
    }

    /**
     * @return Id
     */
    public String getId() {
        return id;
    }

    public void print(){
        System.out.println(tipus);
        System.out.println(id);
        for (int i = 0; i < ids.size(); ++i){
            System.out.println(ids.get(i));
        }
    }

    /**
     * @param vfuturas List<GrupAssignatura>
     * @param vactual GrupAssignatura
     * @param e Escenari
     * Propaga las restricciones
     */
    public static void propagarRestricciones(List<GrupAssignatura> vfuturas, GrupAssignatura vactual, Escenari e){
        for(GrupAssignatura vfutura : vfuturas){
            propagarCorrequisitos(vfutura, vactual, e);
            propagarGruposDiferenteAula(vfutura, vactual);
            propagarTeoriaAndLabDifferentDay(vfutura, vactual);
            propagarNoSolapamentMateixaFase(vfutura, vactual, e);
            propagarRestriccionesPersonalizadas(vfutura, vactual, e);
        }
    }

    /**
     * @param grupToCheck GrupAssignatura
     * @param grup GrupAssignatura
     * Poda los grupos que tienen clase de teoria y lab el mismo dia
     */
    private static void propagarTeoriaAndLabDifferentDay(GrupAssignatura grupToCheck, GrupAssignatura grup) {
        int numGrup = grup.getNumGrup();

        int numGrupToCheck = grupToCheck.getNumGrup();

        if(grup.getIdAssignatura().equals(grupToCheck.getIdAssignatura()) && numGrupToCheck/10 == numGrup/10){
            if(numGrup % 10 == 0 && numGrup != numGrupToCheck || numGrupToCheck%10 == 0){
                List<Posibilidad> posibilidades = grupToCheck.getPosibilidades();
                for(int i = 0; i < posibilidades.size(); i++){
                    if(posibilidades.get(i).getHorari().getDia() == grup.getHorari().getDia()){
                        posibilidades.remove(i--);
                    }
                }
            }
        }
    }

    /**
     * @param grupToCheck GrupAssignatura
     * @param grup GrupAssignatura
     * @param e Escenari
     * Poda los grupos que se solapan con sus correquisitos
     */
    private static void propagarCorrequisitos(GrupAssignatura grupToCheck, GrupAssignatura grup, Escenari e) {
        Assignatura assignatura = null;
        List<Assignatura> assignatures = e.getAssignaturas();
        for(Assignatura a : assignatures){
            if(a.getId().equals(grup.getIdAssignatura())) {
                assignatura = a;
                break;
            }
        }

        if(assignatura != null) {
            List<String> assigsToCheck = new ArrayList<>();
            assigsToCheck.addAll(assignatura.getCorrequisits());
            assigsToCheck.addAll(assignatura.getInverseCorrequisits());

            if (assigsToCheck.contains(grupToCheck.getIdAssignatura())) {
                // TODO: Mejorar combinacion de grupos de teoria y lab
                if (grup.getNumGrup() == grupToCheck.getNumGrup()) {
                    List<Posibilidad> posibilidades = grupToCheck.getPosibilidades();
                    for(int i = 0; i < posibilidades.size(); i++){
                        if(grup.getHorari().solapa(posibilidades.get(i).getHorari())) {
                            posibilidades.remove(i--);
                        }
                    }
                }
            }
        }
    }

    /**
     * @param grupToCheck GrupAssignatura
     * @param grup GrupAssignatura
     * Poda los grupos que tienen clase en la misma aula a la misma hora
     */
    private static void propagarGruposDiferenteAula(GrupAssignatura grupToCheck, GrupAssignatura grup) {
        if(grup.getNumGrup() != grupToCheck.getNumGrup() || !grup.getIdAssignatura().equals(grupToCheck.getIdAssignatura())){
            List<Posibilidad> posibilidades = grupToCheck.getPosibilidades();
            for(int i = 0; i < posibilidades.size(); i++){
                String aula1 = posibilidades.get(i).getAula().getNom();
                String aula2 = grup.getAula().getNom();
                if(grup.getHorari().solapa(posibilidades.get(i).getHorari()) && aula1.equals(aula2)) {
                    posibilidades.remove(i--);
                }
            }
        }
    }

    /**
     * @param grupToCheck GrupAssignatura
     * @param grup GrupAssignatura
     * @param e Escenari
     * Poda los grupos de la misma fase que se solapan
     */
    private static void propagarNoSolapamentMateixaFase(GrupAssignatura grupToCheck, GrupAssignatura grup, Escenari e) {
        Assignatura assignatura = null;
        List<Assignatura> assignatures = e.getAssignaturas();
        for(int i = 0; i < assignatures.size(); i++){
            Assignatura assigAux = assignatures.get(i);
            if(assigAux.getId().equals(grup.getIdAssignatura())) {
                assignatura = assigAux;
                break;
            }
        }
        if(assignatura != null && !assignatura.getFase().equals("")) {
            String fase = assignatura.getFase();
            List<String> assigsToCheck = new ArrayList<>();
            for(int j = 0; j < assignatures.size(); ++j){
                if(fase.equals(assignatures.get(j).getFase())){
                    assigsToCheck.add(assignatures.get(j).getId());
                }
            }

            if (assigsToCheck.contains(grupToCheck.getIdAssignatura())) {
                // TODO: Mejorar combinacion de grupos de teoria y lab
                if (grup.getNumGrup() == grupToCheck.getNumGrup()) {
                    List<Posibilidad> posibilidades = grupToCheck.getPosibilidades();
                    for(int i = 0; i < posibilidades.size(); i++){
                        if (!grup.getIdAssignatura().equals(grupToCheck.getIdAssignatura()) && grup.getNumGrup() == grupToCheck.getNumGrup() && grup.getHorari().solapa(posibilidades.get(i).getHorari())){
                            posibilidades.remove(i--);
                        }
                    }
                }
            }
        }
    }

    /**
     * @param grupToCheck GrupAssignatura
     * @param grup GrupAssignatura
     * @param e Escenari
     * Poda los grupos que no cumplen las restricciones definidas por el usuario
     */
    public static void propagarRestriccionesPersonalizadas(GrupAssignatura grupToCheck, GrupAssignatura grup, Escenari e){
        String assig1 = grup.getIdAssignatura();
        String assig2 = grupToCheck.getIdAssignatura();
        if(!assig1.equals(assig2) && mismoSubnivel(grup, grupToCheck, e)){
            List<Posibilidad> posibilidades = grupToCheck.getPosibilidades();
            for(int i = 0; i < posibilidades.size(); i++){
                if(grup.getHorari().solapa(posibilidades.get(i).getHorari())) {
                    posibilidades.remove(i--);
                }
            }
        }
    }

    /**
     * @param grup String
     * @param grupToCheck String
     * @param e Escenari
     * Comprueba si dos asignaturas pertenecen al mismo subnivel
     */
    public static boolean mismoSubnivel(GrupAssignatura grup, GrupAssignatura grupToCheck, Escenari e){
        String assig1 = grup.getIdAssignatura();
        String assig2 = grupToCheck.getIdAssignatura();
        int num1 = grup.getNumGrup();
        int num2 = grupToCheck.getNumGrup();

        List<Restriccions> rList = e.getRestriccionsAsList();
        for(Restriccions r : rList){
            if(r.getNom().equals("Subnivell")){
                if (r.getIds().contains(assig1) && r.getIds().contains(assig2) && (num1/10 == num2/10)) return true;
            }
        }
        return false;
    }

    /**
     * @param solution List<GrupS
     * @param grup GrupAssignatura
     * @param e Escenari
     * @return Cert si no se solapan dos grupos del mismo nivel
     * Poda los grupos que no cumplen las restricciones definidas por el usuario
     */
    public static boolean RestriccionesPersonalizadaSubnivel(List<GrupAssignatura> solution, GrupAssignatura grup, Escenari e){
        String assig1 = grup.getIdAssignatura();
        for (int i = 0; i < solution.size(); i++) {
            GrupAssignatura grupToCheck = solution.get(i);
            String assig2 = grupToCheck.getIdAssignatura();
            if(!assig1.equals(assig2) && mismoSubnivel(grup, grupToCheck, e)){
                if(grup.getHorari().solapa(grupToCheck.getHorari())) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * @param grupAssignatura GrupAssignatura
     * @param e Escenari
     * @return Cert si l'horari del grup es troba dins l'interval definit a les restricciones de l'escenari
     */
    public static boolean horaDinsInterval(GrupAssignatura grupAssignatura, Escenari e){
        List<Restriccions> rList = e.getRestriccionsAsList();
        for(Restriccions r : rList){
            if(r.getNom().equals("IntervalHorari")){
                if(r.getIds() != null && grupAssignatura.getHorari() != null) {
                    if (grupAssignatura.getIdAssignatura().equals(r.getIdAssigRestriccioInterval(r.getIds().get(0))) && (!(grupAssignatura.getHorari().getHoraInici() >= r.getHoraIniRestriccioInterval(r.getIds().get(0))) ||
                            !(grupAssignatura.getHorari().getHoraInici() + grupAssignatura.getHorari().getDurada() <= r.getHoraFiRestriccioInterval(r.getIds().get(0))))) {
                        return false;

                    }
                }
            }
        }
        return true;
    }

    /**
     * @param h Horari
     * @param idAssig String
     * @param e Escenari
     * @return Cert si l'horari es troba dins l'interval definit a les restricciones de l'escenari
     */
    public static boolean horariDinsInterval(Horari h, String idAssig, Escenari e){
        List<Restriccions> rList = e.getRestriccionsAsList();
        for(Restriccions r : rList){
            if(r.getNom().equals("IntervalHorari")){
                if(r.getIds() != null) {
                    if ( idAssig.equals(r.getIdAssigRestriccioInterval(r.getIds().get(0))) && (!(h.getHoraInici() >= r.getHoraIniRestriccioInterval(r.getIds().get(0))) ||
                            !(h.getHoraInici() + h.getDurada() <= r.getHoraFiRestriccioInterval(r.getIds().get(0))))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @param h Horari
     * @param p Periode lectiu
     * @return Cert si l'horari es troba dins del periode lectiu, fals si no
     */
    public static boolean horariDinsPeriodeLectiu(Horari h, PeriodeLectiu p){
        int periodeHoraInici = p.getHoraInici();
        int periodeHoraFinal = p.getHoraFinal();
        DiaSetmana periodeDiaInici = p.getDataInici();
        DiaSetmana periodeDiaFinal = p.getDataFinal();

        int durada = h.getDurada();
        int horaInici = h.getHoraInici();
        DiaSetmana dia = h.getDia();

        return (horaInici >= periodeHoraInici && horaInici + durada <= periodeHoraFinal
                && dia.ordinal() >= periodeDiaInici.ordinal() && dia.ordinal() <= periodeDiaFinal.ordinal());
    }

    /**
     * @param grup Grup
     * @param aula Aula
     * @return Cert si el grup cap a l'aula, fals si no
     */
    public static boolean grupFitsAula(GrupAssignatura grup, Aula aula){
        return grup.getNumPlaces() <= aula.getAforament();
    }

    /**
     * @param grup Grup
     * @param aula Aula
     * @return Cert si el grup té assignada una aula del tipus correcte, fals si no
     */
    public static boolean aulaCorrecta(GrupAssignatura grup, Aula aula){
        return grup.getTipusAula() == aula.getTipusAula();
    }

    /**
     * @param solution Llista de grups de la solució
     * @param grup Grup
     * @return Fals si el grup de teoria i algun subgrup tenen classe el mateix dia, cert si no
     */
    public static boolean teoriaAndLabDifferentDay(List<GrupAssignatura> solution, GrupAssignatura grup){
        int numGrup = grup.getNumGrup();
        // TEORIA
        if(numGrup % 10 == 0){
            for(int i = 0; i < solution.size(); i++){
                GrupAssignatura grupToCheck = solution.get(i);
                int numGrupToCheck = grupToCheck.getNumGrup();
                if(numGrup != numGrupToCheck && numGrupToCheck/10 == numGrup/10 && grup.getIdAssignatura().equals(grupToCheck.getIdAssignatura())) {
                    if(grup.getHorari().getDia() == grupToCheck.getHorari().getDia()) return false;
                }
            }
        }
        // LAB
        else {
            for(int i = 0; i < solution.size(); i++){
                GrupAssignatura grupToCheck = solution.get(i);
                int numGrupToCheck = grupToCheck.getNumGrup();
                if(numGrupToCheck%10 == 0 && numGrupToCheck/10 == numGrup/10 && grup.getIdAssignatura().equals(grupToCheck.getIdAssignatura())) {
                    if(grup.getHorari().getDia() == grupToCheck.getHorari().getDia()) return false;
                }
            }
        }

        return true;
    }

    /**
     * @param solution Llista de grups de la solució
     * @param grup Grup
     * @param assignatures Assignatures
     * @return Fals si es solapen els mateixos grups d'assignatures que son correquisits, cert si no
     */
    public static boolean correquisitos(List<GrupAssignatura> solution, GrupAssignatura grup, List<Assignatura> assignatures){

        Assignatura assignatura = null;
        for(int i = 0; i < assignatures.size(); i++){
            Assignatura assigAux = assignatures.get(i);
            if(assigAux.getId().equals(grup.getIdAssignatura())) assignatura = assigAux;
        }

        if(assignatura != null) {
            List<String> assigsToCheck = new ArrayList<>();
            assigsToCheck.addAll(assignatura.getCorrequisits());
            assigsToCheck.addAll(assignatura.getInverseCorrequisits());

            for (int i = 0; i < solution.size(); i++) {
                GrupAssignatura grupToCheck = solution.get(i);
                if (assigsToCheck.contains(grupToCheck.getIdAssignatura())) {
                    // TODO: Mejorar combinacion de grupos de teoria y lab
                    if (grup.getNumGrup() == grupToCheck.getNumGrup() && grup.getHorari().solapa(grupToCheck.getHorari())) return false;
                }
            }
        }
        return true;
    }

    /**
     * @param solution Llista de grups de la solució
     * @param grup Grup
     * @return Fals si dos grups tenen classe a la mateixa aula i mateixa hora, cert si no
     */
    public static boolean gruposDiferenteAula(List<GrupAssignatura> solution, GrupAssignatura grup){

        for(int i = 0; i < solution.size(); i++){

            GrupAssignatura grupToCheck = solution.get(i);
            if(grup.getNumGrup() != grupToCheck.getNumGrup() || !grup.getIdAssignatura().equals(grupToCheck.getIdAssignatura())){
                String aula1 = grupToCheck.getAula().getNom();
                String aula2 = grup.getAula().getNom();
                if(grup.getHorari().solapa(grupToCheck.getHorari()) && aula1.equals(aula2)) return false;
            }
        }

        return true;
    }

    /**
     * @param solution Llista de grups de la solució
     * @param grup Grup
     * @param assignatures Assignatures
     * @return Fals si es solapen els mateixos grups d'assignatures de la mateixa fase, cert si no
     */
    public static boolean noSolapamentMateixaFase(List<GrupAssignatura> solution, GrupAssignatura grup, List<Assignatura> assignatures){

        Assignatura assignatura = null;
        for(int i = 0; i < assignatures.size(); i++){
            Assignatura assigAux = assignatures.get(i);
            if(assigAux.getId().equals(grup.getIdAssignatura())) assignatura = assigAux;
        }

        if(assignatura != null && !assignatura.getFase().equals("")) {
            String fase = assignatura.getFase();
            List<String> assigsToCheck = new ArrayList<>();
            for(int j = 0; j < assignatures.size(); ++j){
                if(fase.equals(assignatures.get(j).getFase())){
                    assigsToCheck.add(assignatures.get(j).getId());
                }
            }

            for (int i = 0; i < solution.size(); i++) {
                GrupAssignatura grupToCheck = solution.get(i);
                if (assigsToCheck.contains(grupToCheck.getIdAssignatura())) {
                    // TODO: Mejorar combinacion de grupos de teoria y lab
                    if (!grup.getIdAssignatura().equals(grupToCheck.getIdAssignatura()) && grup.getNumGrup() == grupToCheck.getNumGrup() && grup.getHorari().solapa(grupToCheck.getHorari())) return false;
                }
            }
        }
        return true;
    }

    /**
     * @param restriccio String
     * @return Hora d'inici de la restricció d'interval
     */
    private int getHoraIniRestriccioInterval(String restriccio){
        String h = "";
        int i = 0;
        boolean trobat = false;
        while(i < restriccio.length() || !trobat){
            if(restriccio.charAt(i) == ' '){
                trobat = true;
                ++i;
                while(restriccio.charAt(i) != '-'){
                    h += restriccio.charAt(i);
                    i++;
                }
            }
            else i++;
        }
        if(h.length() == 1) return getNumericValue(h.charAt(0));
        else return Integer.parseInt(h);
    }

    /**
     * @param restriccio String
     * @return Hora final de la restricció d'interval
     */
    private int getHoraFiRestriccioInterval(String restriccio){
        String h = "";
        int i = 0;
        while(i < restriccio.length()){
            if(restriccio.charAt(i) == '-'){
                i++;
                while(i < restriccio.length()){
                    h += restriccio.charAt(i);
                    i++;
                }
            }
            else i++;
        }
        if(h.length() == 1) return getNumericValue(h.charAt(0));
        else return Integer.parseInt(h);
    }

    /**
     * @param restriccio String
     * @return Id de l'assignatura de la restricció d'interval
     */
    private String getIdAssigRestriccioInterval(String restriccio){
        String h = "";
        int i = 0;
        while(restriccio.charAt(i) != ' '){
            h += restriccio.charAt(i);
            i++;
        }
        return h;
    }
}
