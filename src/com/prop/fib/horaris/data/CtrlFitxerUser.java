package com.prop.fib.horaris.data;

import com.prop.fib.horaris.Enumerations.TipusUser;
import com.prop.fib.horaris.domain.Classes.Admin;
import com.prop.fib.horaris.domain.Classes.Alumne;
import com.prop.fib.horaris.domain.Classes.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.TreeMap;

/**
 * @author Pere Verges Boncompte
 */

public class CtrlFitxerUser {
    private static CtrlFitxerUser singletonObject;

    public static CtrlFitxerUser getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlFitxerUser() {};
        return singletonObject;
    }

    /**
     * Class constructor
     */
    private CtrlFitxerUser(){}

    /**
     * guarda un fitxer d'usuaris
     * @param filename Filename
     */
    public TreeMap<String, User> getAll(String filename) {
        TreeMap<String, User> usuaris  = new TreeMap<String, User>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(
                    "DATA/" + filename));
            User user = null;
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray useres = (JSONArray) jsonObject.get("Users");
            int i = 0;
            while (i < useres.size()) {
                JSONObject aux = (JSONObject) useres.get(i);
                String nom = (String) aux.get("nom");
                String id = (String) aux.get("id");
                Long tipusUser = (Long) aux.get("tipusUser");
                String password = (String) aux.get("password");

                if(Integer.valueOf(tipusUser.intValue()) == 1) {
                    user = new Alumne(nom, id, password);
                }
                else if(Integer.valueOf(tipusUser.intValue()) == 2) {
                    user = new Admin(nom, id, password);
                }
                usuaris.put(id, user);
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuaris;
    }
}
