package com.prop.fib.horaris.domain.Classes;

import com.prop.fib.horaris.Enumerations.TipusUser;

/**
 * @author Jinson Pardo
 */
public class Admin extends User {

    /**
     * Class constructor
     * @param nom Nom de l'usuari
     * @param id Identificador de l'usuari
     * @param password Contrasenya de l'usuari
     */
    public Admin(String id, String nom, String password) {
        super(id, nom, TipusUser.ADMIN, password);
    }
}
