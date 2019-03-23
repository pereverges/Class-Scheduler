package com.prop.fib.horaris.data;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.domain.Classes.Assignatura;
import com.prop.fib.horaris.domain.Classes.Aula;
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


public class CtrlFitxerAules {
    private static CtrlFitxerAules singletonObject;
    private static final String PATH = "DATA/";

    public static CtrlFitxerAules getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlFitxerAules() {};
        return singletonObject;
    }

    /**
     * Class constructor
     */
    private CtrlFitxerAules(){}


    /**
     * guarda una llista d'aules
     * @param filename Filename
     */
    public static void guardarAules(List<Aula> aulas, String filename){
        JSONObject json = new JSONObject();
        JSONArray AulaFinal = new JSONArray();
        Aula aula;
        for(int i = 0; i < aulas.size(); ++i){
            JSONObject aulaJson = new JSONObject();

            aula = aulas.get(i);
            aulaJson.put("nom", aula.getNom());
            TipusAula tipus = aula.getTipusAula();

            int aulaInt = 1;
            if(tipus == TipusAula.TEORIA) aulaInt = 1;
            else if(tipus == TipusAula.LABORATORI) aulaInt = 2;
            else if(tipus == TipusAula.PROBLEMES) aulaInt = 3;

            aulaJson.put("tipusAula", aulaInt);
            aulaJson.put("aforament", aula.getAforament());

            AulaFinal.add(aulaJson);

        }

        json.put("Aules", AulaFinal);

        try (FileWriter file = new FileWriter(PATH + filename)) {
            file.write(json.toJSONString());
            System.out.println("Aula guardada amb èxit...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filename Filename
     * @return Una List amb totes les aules
     */
    public static List<Aula> getAll(String filename) throws FileNotFoundException{
        JSONtryParser parser = new JSONParser();
        LinkedList<Aula> aulas = new LinkedList<>();

        try {
            Object obj = parser.parse(new FileReader(
                    "DATA/" + filename));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray aules = (JSONArray) jsonObject.get("Aules");
            int i = 0;
            while (i < aules.size()) {
                JSONObject aux = (JSONObject) aules.get(i);

                String nom = (String) aux.get("nom");
                Long tipusAula = (Long) aux.get("tipusAula");
                Long aforament = (Long) aux.get("aforament");

                TipusAula tipo = intToTipusAula(tipusAula.intValue());

                Aula aula = new Aula(nom, tipo, aforament.intValue());
                aulas.add(aula);
                //aula.print();
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return aulas;
    }


    /**
     * passa de un integear a el tipus d'aula
     * @param n n
     * @return retorna el enum de aula
     */
    public static TipusAula intToTipusAula(int n){
        TipusAula tipo = null;
        if(n == 1) tipo = TipusAula.TEORIA;
        else if(n == 2) tipo = TipusAula.LABORATORI;
        else if(n == 3) tipo = TipusAula.PROBLEMES;
        return tipo;
    }
}