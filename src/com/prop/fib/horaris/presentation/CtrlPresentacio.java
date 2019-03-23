package com.prop.fib.horaris.presentation;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.domain.Classes.GrupAssignatura;
import com.prop.fib.horaris.domain.Controllers.CtrlDominio;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Jinson Pardo
 */
public class CtrlPresentacio {


    private ViewLogin viewLogin;
    private CtrlPresentationVistaUser ctrlPresentationVistaUser;
    private CtrlDominio ctrlDominio;
    private ViewPrincipal viewPrincipal;

    public CtrlPresentacio() {
        ctrlDominio = new CtrlDominio();
        viewLogin = new ViewLogin(this);
        ctrlPresentationVistaUser = new CtrlPresentationVistaUser(this);
        viewPrincipal = new ViewPrincipal(this);
    }

    public void inicialitzarPresentacio() throws Exception {
        viewLogin.ferVisible();
    }

    public void obrirVistaPrincipal(String dadesUsuari) {
        viewPrincipal.ferVisble(dadesUsuari);
    }

    public boolean isLoginOk(String idUsuari, String contrasenya) {
        return ctrlDominio.isLoginOk(idUsuari, contrasenya);
    }

    public String imprimirDataUsuari() {
        return ctrlDominio.getDataUsuari();
    }

    public String getIdUsuari() {
        return ctrlDominio.getIdUsuari();
    }

    public void generarHorari(List<String> nomsFitxers) throws FileNotFoundException {
        this.ctrlDominio.generarHorari(nomsFitxers);
    }

    public TreeMap<Integer, ArrayList<String>> getTreeMapHorari(String filename) throws FileNotFoundException {
        return this.ctrlDominio.getTreeMapHorari(filename);
    }

    public void printHorari() {
        this.ctrlDominio.printHorari();
    }

    public void guardarHorari(String filename) {
        this.ctrlDominio.guardarHorari(filename);
    }

    public void cargarHorari(String filename) throws FileNotFoundException {
        this.ctrlDominio.cargarHorari(filename);
    }

    public String getHorariToString(){
        return this.ctrlDominio.getHorariToString();
    }


    public void modificar(String idAssignatura, int numGrup, int numPlaces, TipusAula tipusAula, String nomAulaAnterior, String nomAulaPosterior, int horaIniAnterior, int duradaAnterior, DiaSetmana diaAnterior, int horaIniPosterior, int duradaPosterior, DiaSetmana diaPosterior) {
        this.ctrlDominio.modificar(idAssignatura,numGrup,numPlaces,tipusAula,nomAulaAnterior,nomAulaPosterior,horaIniAnterior,duradaAnterior,diaAnterior,horaIniPosterior,duradaPosterior,diaPosterior);
    }

    public String[] getListaDirHorarisGenerats() {
        return ctrlDominio.getListaDirHorarisGenerats();
    }

    public void cargarVistaUsuari() {
        this.ctrlPresentationVistaUser.iniciar();
    }

    public int getNumDiesPeriodeLectiu() { return ctrlDominio.getNumDiesPeriodeLectiu(); }
}
