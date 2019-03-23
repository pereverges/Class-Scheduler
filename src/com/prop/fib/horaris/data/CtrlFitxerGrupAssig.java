package com.prop.fib.horaris.data;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.domain.Classes.Aula;
import com.prop.fib.horaris.domain.Classes.GrupAssignatura;
import com.prop.fib.horaris.domain.Classes.Horari;
import com.prop.fib.horaris.domain.Classes.LlistaGrupAssigYfilename;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Pere Verges Boncompte
 */

public class CtrlFitxerGrupAssig {
    private static CtrlFitxerGrupAssig singletonObject;
    private static final String PATH_HORARIS = "DATA/HorarisGenerats/";

    public static CtrlFitxerGrupAssig getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlFitxerGrupAssig() {};
        return singletonObject;
    }

    /**
     * Class constructor
     */
    private CtrlFitxerGrupAssig(){}


    /**
     * guarda un horari
     * @param filename Filename
     */
    public void guardarHorari(List<GrupAssignatura> grups, List<String> nomFitxersAAF, String filename){
        JSONObject json = new JSONObject();
        JSONArray grupAssi = new JSONArray();
        GrupAssignatura grup;
        for(int i = 0; i < grups.size(); ++i){
            JSONObject grupJson = new JSONObject();
            JSONObject aula = new JSONObject();
            JSONObject horari = new JSONObject();

            grup = grups.get(i);
            grupJson.put("numGrup", grup.getNumGrup());
            grupJson.put("numPlaces", grup.getNumPlaces());
            TipusAula auxAula = grup.getTipusAula();

            int aulaInt = 1;
            if(auxAula == TipusAula.TEORIA) aulaInt = 1;
            else if(auxAula == TipusAula.LABORATORI) aulaInt = 2;
            else if(auxAula == TipusAula.PROBLEMES) aulaInt = 3;

            grupJson.put("tipusAula", aulaInt);
            grupJson.put("idAssig", grup.getIdAssignatura());
            Aula aulaAux = grup.getAula();
            aula.put("nom", aulaAux.getNom());
            TipusAula auxAula2 = aulaAux.getTipusAula();

            int aulaInt2 = 1;
            if(auxAula2 == TipusAula.TEORIA) aulaInt2 = 1;
            else if(auxAula2 == TipusAula.LABORATORI) aulaInt2 = 2;
            else if(auxAula2 == TipusAula.PROBLEMES) aulaInt2 = 3;

            aula.put("tipusAula", aulaInt2);
            aula.put("aforament", aulaAux.getAforament());
            grupJson.put("Aules", aula);
            Horari horariAux = grup.getHorari();
            horari.put("HoraInici", horariAux.getHoraInici());
            horari.put("durada", horariAux.getDurada());
            DiaSetmana diaAux = horariAux.getDia();

            int dia = 1;
            if (diaAux == DiaSetmana.DILLUNS) dia = 1;
            else if(diaAux == DiaSetmana.DIMARTS) dia = 2;
            else if(diaAux == DiaSetmana.DIMECRES) dia = 3;
            else if(diaAux == DiaSetmana.DIJOUS) dia = 4;
            else if(diaAux == DiaSetmana.DIVENDRES) dia = 5;
            else if(diaAux == DiaSetmana.DISSABTE) dia = 6;
            else if(diaAux == DiaSetmana.DIUMENGE) dia = 7;

            horari.put("DiaSetmana", dia);
            grupJson.put("Horari", horari);

            grupAssi.add(grupJson);

        }

        json.put("FitxerAssignatures", nomFitxersAAF.get(0));
        json.put("FitxerAules", nomFitxersAAF.get(1));
        json.put("FitxerPerdiodeLectiu", nomFitxersAAF.get(2));
        json.put("FitxerRestriccions", nomFitxersAAF.get(3));
        json.put("GrupAssig", grupAssi);

        String path = PATH_HORARIS + filename.substring(0, filename.indexOf("/"));
        File directory = new File(path);
        if (!directory.exists()){
            directory.mkdir();
        }
        try (FileWriter file = new FileWriter(PATH_HORARIS + filename)) {
            file.write(json.toJSONString());
            System.out.println("Horari guardat amb èxit...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filename Filename
     * @return Una llista de GrupAssignatura (un horari)
     */
    public LlistaGrupAssigYfilename getAll(String filename) throws FileNotFoundException{
        JSONParser parser = new JSONParser();
        LinkedList<GrupAssignatura> grupAssig = new LinkedList<>();
        List<String> nomFitxersAAP = new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader(
                    PATH_HORARIS + filename));

            JSONObject jsonObject = (JSONObject) obj;
            nomFitxersAAP.add(0,(String) jsonObject.get("FitxerAssignatures"));
            nomFitxersAAP.add(1,(String) jsonObject.get("FitxerAules"));
            nomFitxersAAP.add(2,(String) jsonObject.get("FitxerPerdiodeLectiu"));
            nomFitxersAAP.add(3,(String) jsonObject.get("FitxerRestriccions"));

            JSONArray grupAssi = (JSONArray) jsonObject.get("GrupAssig");
            int i = 0;
            while (i < grupAssi.size()) {
                JSONObject aux = (JSONObject) grupAssi.get(i);

                Long numGrup = (Long) aux.get("numGrup");
                Long numPlaces = (Long) aux.get("numPlaces");
                Long tipusAula1 = (Long) aux.get("tipusAula");

                TipusAula tipo1 = null;
                if(Integer.valueOf(tipusAula1.intValue()) == 1) tipo1 = TipusAula.TEORIA;
                else if(Integer.valueOf(tipusAula1.intValue()) == 2) tipo1 = TipusAula.LABORATORI;
                else if(Integer.valueOf(tipusAula1.intValue()) == 3) tipo1 = TipusAula.PROBLEMES;

                String idAssignatura = (String) aux.get("idAssig");
                JSONObject aules = (JSONObject) aux.get("Aules");

                String nom = (String) aules.get("nom");
                Long tipusAula = (Long) aules.get("tipusAula");
                Long aforament = (Long) aules.get("aforament");

                TipusAula tipo = null;
                if(Integer.valueOf(tipusAula.intValue()) == 1) tipo = TipusAula.TEORIA;
                else if(Integer.valueOf(tipusAula.intValue()) == 2) tipo = TipusAula.LABORATORI;
                else if(Integer.valueOf(tipusAula.intValue()) == 3) tipo = TipusAula.PROBLEMES;
                Aula aulaGrup = new Aula(nom,tipo, Integer.valueOf(aforament.intValue()));

                JSONObject horari = (JSONObject) aux.get("Horari");

                Long horaInici = (Long) horari.get("HoraInici");
                Long durada = (Long) horari.get("durada");
                Long diaSet = (Long) horari.get("DiaSetmana");
                DiaSetmana dia = intToDia(diaSet.intValue());

                Horari horaris = new Horari(horaInici.intValue(), durada.intValue(), dia);
                GrupAssignatura grup = new GrupAssignatura(numGrup.intValue(), numPlaces.intValue(), tipo1, idAssignatura, aulaGrup, horaris);
                grupAssig.add(grup);
                i++;

                // grup.print();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (new LlistaGrupAssigYfilename(grupAssig, nomFitxersAAP));
    }

    /**
     * passa de un integear a el tipus d'aula
     * @param x x
     * @return retorna el enum de diaSetmana
     */
    public static DiaSetmana intToDia(int x){
        DiaSetmana diaI = null;
        if(x == 1) diaI = DiaSetmana.DILLUNS;
        else if(x == 2) diaI = DiaSetmana.DIMARTS;
        else if(x == 3) diaI = DiaSetmana.DIMECRES;
        else if(x == 4) diaI = DiaSetmana.DIJOUS;
        else if(x == 5) diaI = DiaSetmana.DIVENDRES;
        else if(x == 6) diaI = DiaSetmana.DISSABTE;
        else if(x == 7) diaI = DiaSetmana.DIUMENGE;
        return diaI;
    }
}
