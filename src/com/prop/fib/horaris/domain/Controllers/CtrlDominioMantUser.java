package com.prop.fib.horaris.domain.Controllers;

import com.prop.fib.horaris.data.CtrlFitxerUser;
import com.prop.fib.horaris.domain.Classes.User;

import java.util.Map;
import java.util.TreeMap;


/**
 * @author Jinson Pardo
 */
public class CtrlDominioMantUser {

    private TreeMap<String, User> usuaris;
    private User usuariActiu;
    private CtrlFitxerUser ctrlFitxerUser;

    public CtrlDominioMantUser() {
        ctrlFitxerUser = CtrlFitxerUser.getInstance();
    }

    public void carregarUsuaris(String filename) {
        usuaris = ctrlFitxerUser.getAll(filename);
    }

    /**
     * @param idUsuari id de l'usuari
     * @return Retorna l'usuari amb id idUsiuari
     */
    private User getUsuari(String idUsuari) {
        return usuaris.get(idUsuari);
    }

    public boolean isLoginOk(String idUsuari, String contrasenya) {
        usuariActiu = getUsuari(idUsuari);
        if (usuariActiu != null)
            return usuariActiu.getId().equals(idUsuari) && usuariActiu.getPassword().equals(contrasenya);
        return false;
    }

    public boolean existeixUs(String id) {
        return usuaris.containsKey(id);
    }

    public void imprimirUsuaris() {
        for(Map.Entry<String,User> entry : usuaris.entrySet()) {
            String key = entry.getKey();
            User value = entry.getValue();
            System.out.println(key + " => " + value);
        }
    }

    public String getDataUsuari() {
        return usuariActiu.toString();
    }

    public String getIdUsuari() {
        return usuariActiu.getId();
    }
}
