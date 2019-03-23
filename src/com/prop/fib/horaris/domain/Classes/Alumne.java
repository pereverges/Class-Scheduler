package com.prop.fib.horaris.domain.Classes;

import com.prop.fib.horaris.Enumerations.TipusUser;

/**
 * @author Jinson Pardo
 */
public class Alumne extends User {

    /**
     * Class constructor
     * @param nom Nom de l'usuari
     * @param id Identificador de l'usuari
     * @param password Contrasenya de l'usuari
     */
    public Alumne(String nom, String id, String password) {
        super(nom, id, TipusUser.ALUMNE, password);
    }
}
