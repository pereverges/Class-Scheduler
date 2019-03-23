package com.prop.fib.horaris.data;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.domain.Classes.PeriodeLectiu;
import com.prop.fib.horaris.domain.Classes.Restriccions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pere Verges Boncompte
 */


public class CtrlFitxerRestriccions {
    private static CtrlFitxerRestriccions singletonObject;
    private static final String PATH = "DATA/";

    public static CtrlFitxerRestriccions getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlFitxerRestriccions() {
            };
        return singletonObject;
    }

    /**
     * Class constructor
     */
    private CtrlFitxerRestriccions() {
    }


    /**
     * guarda una llista de restriccions
     * @param filename Filename
     */
    public static void guardarRestriccions(List<Restriccions> restriccions, String filename) {
        JSONObject json = new JSONObject();
        JSONArray RestriccionsHora = new JSONArray();
        JSONArray RestriccionsSubnivell = new JSONArray();
        List<String> restriccionsAuxList;
        String restriccionsAuxNom;
        String restriccionsAuxId;
        for (int i = 0; i < restriccions.size(); ++i) {
            JSONObject restriccioJson = new JSONObject();

            restriccionsAuxList = restriccions.get(i).getIds();
            restriccionsAuxNom = restriccions.get(i).getNom();
            restriccionsAuxId = restriccions.get(i).getId();
            if(restriccionsAuxNom == "Subnivell") {
                JSONArray restric = new JSONArray();
                for (int j = 0; j < restriccionsAuxList.size(); j++) {
                    JSONObject resActual = new JSONObject();
                    resActual.put("Codi", restriccionsAuxList.get(j));
                    restric.add(resActual);
                }
                restriccioJson.put("id", restriccionsAuxId);
                restriccioJson.put(restriccionsAuxNom, restric);

                RestriccionsSubnivell.add(restriccioJson);
            }
            else if(restriccionsAuxNom == "IntervalHorari") {
                JSONArray restric = new JSONArray();
                for (int j = 0; j < restriccionsAuxList.size(); j++) {
                    JSONObject resActual = new JSONObject();
                    resActual.put("Interval", restriccionsAuxList.get(j));
                    restric.add(resActual);
                }
                restriccioJson.put("id", restriccionsAuxId);
                restriccioJson.put(restriccionsAuxNom, restric);

                RestriccionsHora.add(restriccioJson);
            }

        }

        json.put("RestriccionsSubnivell", RestriccionsSubnivell);
        json.put("RestriccionsHora", RestriccionsHora);

        try (FileWriter file = new FileWriter(PATH + filename)) {
            file.write(json.toJSONString());
            System.out.println("Restriccions guardades amb èxit...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filename Filename
     * @return Una llista de restriccions
     */
    public static List<Restriccions> getAll(String filename) throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        LinkedList<Restriccions> Restriccions = new LinkedList<>();

        try {
            Object obj = parser.parse(new FileReader(
                    "DATA/" + filename));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray restriccions = (JSONArray) jsonObject.get("RestriccionsSubnivell");
            int i = 0;
            while (i < restriccions.size()) {
                JSONObject aux = (JSONObject) restriccions.get(i);
                String id = (String) aux.get("id");
                JSONArray restriccionsSubnivell = (JSONArray) aux.get("Subnivell");
                List<String> restriccionsSub = new ArrayList<>();
                int j = 0;
                while (j < restriccionsSubnivell.size()) {
                    JSONObject restrSub = (JSONObject) restriccionsSubnivell.get(j);
                    String assig = (String) restrSub.get("Codi");
                    restriccionsSub.add(assig);
                    ++j;
                }
                Restriccions.add(new Restriccions(id,"Subnivell", restriccionsSub));
                i++;
            }
            JSONArray restriccionsInterval = (JSONArray) jsonObject.get("RestriccionsHora");
            int m = 0;
            while (m < restriccionsInterval.size()) {
                JSONObject aux = (JSONObject) restriccionsInterval.get(m);
                String id = (String) aux.get("id");
                JSONArray restriccioHoraria = (JSONArray) aux.get("IntervalHorari");
                List<String> restriccionsHora = new ArrayList<>();
                int k = 0;
                while (k < restriccioHoraria.size()) {
                    JSONObject restriccioH = (JSONObject) restriccioHoraria.get(k);
                    String hora = (String) restriccioH.get("Interval");
                    restriccionsHora.add(hora);
                    ++k;
                }
                Restriccions.add(new Restriccions(id,"IntervalHorari", restriccionsHora));
                m++;
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return Restriccions;
    }
}

