package com.prop.fib.horaris.data;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.domain.Classes.Assignatura;
import com.prop.fib.horaris.domain.Classes.Aula;
import com.prop.fib.horaris.domain.Classes.GrupAssignatura;
import com.prop.fib.horaris.domain.Classes.Horari;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pere Verges Boncompte
 */

public class CtrlFitxerAssig {
    private static CtrlFitxerAssig singletonObject;
    private static final String PATH = "DATA/";

    public static CtrlFitxerAssig getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlFitxerAssig() {};
        return singletonObject;
    }

    /**
     * Class constructor
     */
    private CtrlFitxerAssig(){}

    /**
     * guarda una llista d'assignatures
     * @param filename Filename
     */
    public static void guardarAssignatura(List<Assignatura> assignaturas, String filename){
        JSONObject json = new JSONObject();
        JSONArray AssigFinal = new JSONArray();
        Assignatura assignatura;
        for(int i = 0; i < assignaturas.size(); ++i){
            JSONObject assigJson = new JSONObject();

            assignatura = assignaturas.get(i);
            assigJson.put("Codi", assignatura.getId());
            assigJson.put("Nom", assignatura.getNom());
            assigJson.put("NumCredits", assignatura.getNumCredits());
            assigJson.put("NumPlaces", assignatura.getNumPlaces());
            assigJson.put("Quadrimestre", assignatura.getQuadrimestre());
            assigJson.put("NumGrups", assignatura.getNumGrups());
            assigJson.put("numSubgrupsPerGrup", assignatura.getNumSubgrupsPerGrup());
            assigJson.put("numSessionsSetmanals", assignatura.getNumSessionsSetmanals());
            assigJson.put("horesSetmanals", assignatura.getHoresSetmanals());
            assigJson.put("Fase", assignatura.getFase());

            JSONArray correquisits = new JSONArray();
            List<String> correq = assignatura.getCorrequisits();
            for(int j = 0; j < correq.size(); j++){
                JSONObject corrActual = new JSONObject();
                corrActual.put("Codi", correq.get(j));
                correquisits.add(corrActual);
            }
            assigJson.put("Correquisits", correquisits);

            JSONArray Icorrequisits = new JSONArray();
            List<String> Icorreq = assignatura.getInverseCorrequisits();
            for(int j = 0; j < Icorreq.size(); j++){
                JSONObject IcorrActual = new JSONObject();
                IcorrActual.put("Codi",Icorreq.get(j));
                Icorrequisits.add(IcorrActual);
            }
            assigJson.put("InverseCorrequisits", Icorrequisits);


            AssigFinal.add(assigJson);

        }

        json.put("Assignatures", AssigFinal);

        try (FileWriter file = new FileWriter(PATH + filename)) {
            file.write(json.toJSONString());
            System.out.println("Assignatures guardades amb èxit...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param filename Filename
     * @return Una List amb totes les assignatures
     */
    public static List<Assignatura> getAll(String filename) throws FileNotFoundException{
        JSONParser parser = new JSONParser();
        LinkedList<Assignatura> assignaturas = new LinkedList<>();

        try {
            Object obj = parser.parse(new FileReader(
                    "DATA/" + filename));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray assignatures = (JSONArray) jsonObject.get("Assignatures");
            int i = 0;
            while (i < assignatures.size()) {
                JSONObject aux = (JSONObject) assignatures.get(i);

                String id = (String) aux.get("Codi");
                String nom = (String) aux.get("Nom");
                Long numCredits = (Long) aux.get("NumCredits");
                Long numPlaces = (Long) aux.get("NumPlaces");
                Long quadrimestre = (Long) aux.get("Quadrimestre");
                Long numGrups = (Long) aux.get("NumGrups");
                Long numSubgrupsPerGrup = (Long) aux.get("numSubgrupsPerGrup");
                Long numSessionsSetmanals = (Long) aux.get("numSessionsSetmanals");
                Long horesSetmanals = (Long) aux.get("horesSetmanals");
                List<String> correqs = new ArrayList<>();
                JSONArray correquisits = (JSONArray) aux.get("Correquisits");
                int j = 0;
                while(j < correquisits.size()){
                    JSONObject corr = (JSONObject) correquisits.get(j);
                    String correq = (String) corr.get("Codi");
                    correqs.add(correq);
                    ++j;
                }
                List<String> invcorreq = new ArrayList<>();
                JSONArray InverseCorrequisits = (JSONArray) aux.get("InverseCorrequisits");
                int l = 0;
                while(l < InverseCorrequisits.size()){
                    JSONObject invcorr = (JSONObject) InverseCorrequisits.get(l);
                    String invcorre = (String) invcorr.get("Codi");
                    invcorreq.add(invcorre);
                    ++l;
                }

                String fase = (String) aux.get("Fase");

                Assignatura assig = new Assignatura(id, nom,
                        Integer.valueOf(numCredits.intValue()),
                        Integer.valueOf(numPlaces.intValue()),
                        Integer.valueOf(quadrimestre.intValue()),
                        Integer.valueOf(numGrups.intValue()),
                        Integer.valueOf(numSubgrupsPerGrup.intValue()),
                        Integer.valueOf(numSessionsSetmanals.intValue()),
                        Integer.valueOf(horesSetmanals.intValue()),
                        correqs,
                        invcorreq,
                        fase);

                assignaturas.add(assig);
                //assig.print();
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return assignaturas;
    }
}
