package com.prop.fib.horaris.data;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.domain.Classes.Aula;
import com.prop.fib.horaris.domain.Classes.PeriodeLectiu;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pere Verges Boncompte
 */


public class CtrlFitxerPeriodeLectiu {
    private static CtrlFitxerPeriodeLectiu singletonObject;
    private static final String PATH = "DATA/";

    public static CtrlFitxerPeriodeLectiu getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlFitxerPeriodeLectiu() {};
        return singletonObject;
    }

    /**
     * Class constructor
     */
    private CtrlFitxerPeriodeLectiu(){}


    /**
     * guarda un periode lectiu
     * @param filename Filename
     */
    public static void guardarPeriodeLectiu(List<PeriodeLectiu> periodeLectius, String filename){
        JSONObject json = new JSONObject();
        JSONArray PeriodeLectiuFinal = new JSONArray();
        PeriodeLectiu periodeLectiu;
        for(int i = 0; i < periodeLectius.size(); ++i){
            JSONObject periodeJson = new JSONObject();

            periodeLectiu = periodeLectius.get(i);
            periodeJson.put("nom", periodeLectiu.getNom());
            periodeJson.put("id", periodeLectiu.getId());
            periodeJson.put("horaInici", periodeLectiu.getHoraInici());
            periodeJson.put("horaFinal", periodeLectiu.getHoraFinal());
            int I = DiaToInt(periodeLectiu.getDataInici());
            int F = DiaToInt(periodeLectiu.getDataFinal());
            periodeJson.put("dataInici", I);
            periodeJson.put("dataFinal", F);

            PeriodeLectiuFinal.add(periodeJson);

        }

        json.put("PeriodesLectius", PeriodeLectiuFinal);

        try (FileWriter file = new FileWriter(PATH + filename)) {
            file.write(json.toJSONString());
            System.out.println("Periode guardada amb èxit...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filename Filename
     * @return Una llista de PeriodesLectius
     */
    public static List<PeriodeLectiu> getAll(String filename) throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        LinkedList<PeriodeLectiu> periodesLectius = new LinkedList<>();

        try {
            Object obj = parser.parse(new FileReader(
                    "DATA/" + filename));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray periodeLectius = (JSONArray) jsonObject.get("PeriodesLectius");
            int i = 0;
            while (i < periodeLectius.size()) {
                JSONObject aux = (JSONObject) periodeLectius.get(i);

                String nom = (String) aux.get("nom");
                String id = (String) aux.get("id");
                Long horaInici = (Long) aux.get("horaInici");
                Long horaFinal = (Long) aux.get("horaFinal");
                Long dataInici = (Long) aux.get("dataInici");
                Long dataFinal = (Long) aux.get("dataFinal");

                DiaSetmana diaI = intToDia(dataInici.intValue());
                DiaSetmana diaF = intToDia(dataFinal.intValue());

                PeriodeLectiu periode = new PeriodeLectiu(nom, id, horaInici.intValue(), horaFinal.intValue(), diaI, diaF);
                periodesLectius.add(periode);
                i++;
                //periode.print();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return periodesLectius;
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

    /**
     * passa de un integear a el tipus d'aula
     * @param d diasemtnaa
     * @return retorna el int del dia
     */
    public static int DiaToInt(DiaSetmana d){
        int diaI = 1;
        if(d == DiaSetmana.DILLUNS) diaI = 1;
        else if(d == DiaSetmana.DIMARTS) diaI = 2;
        else if(d == DiaSetmana.DIMECRES) diaI = 3;
        else if(d == DiaSetmana.DIJOUS) diaI = 4;
        else if(d == DiaSetmana.DIVENDRES) diaI = 5;
        else if(d == DiaSetmana.DISSABTE) diaI = 6;
        else if(d == DiaSetmana.DIUMENGE) diaI = 7;
        return diaI;
    }

}