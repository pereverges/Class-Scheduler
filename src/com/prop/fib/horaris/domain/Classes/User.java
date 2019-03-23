package com.prop.fib.horaris.domain.Classes;

import com.prop.fib.horaris.Enumerations.TipusUser;

/**
 * @author Jinson Pardo
 */
public class User {

    private String nom;
    private String id;
    private TipusUser tipusUser;
    private String password;

    /**
     * Class constructor
     * @param nom Nom de l'usuari
     * @param id Identificador de l'usuari
     * @param tipusUser Tipus d'usuari
     * @param password Contrasenya de l'usuari
     */
    public User(String nom, String id, TipusUser tipusUser, String password){
        this.nom = nom;
        this.id = id;
        this.tipusUser = tipusUser;
        this.password = password;
    }

    /**
     * @return Nom de l'usuari
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return Identificador de l'usuari
     */
    public String getId() {
        return id;
    }

    /**
     * @return Tipus d'usuari
     */
    public TipusUser getTipusUser() {
        return tipusUser;
    }

    /**
     * @return Contrasenya de l'usuari
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param nom Nom de l'usuari
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param id Identificador de l'usuari
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param tipusUser Tipus d'usuari
     */
    public void setTipusUser(TipusUser tipusUser) {
        this.tipusUser = tipusUser;
    }

    /**
     * @param password Contrasenya de l'usuari
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return getTipusUser() + ": " + getNom();
    }
}
