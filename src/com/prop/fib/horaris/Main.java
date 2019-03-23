package com.prop.fib.horaris;

import com.prop.fib.horaris.Enumerations.DiaSetmana;
import com.prop.fib.horaris.Enumerations.TipusAula;
import com.prop.fib.horaris.domain.Classes.*;
import com.prop.fib.horaris.domain.Controllers.CtrlDominio;
import com.prop.fib.horaris.domain.Test.JocsDeProvaFinal;
import com.prop.fib.horaris.presentation.CtrlPresentacio;

import java.util.ArrayList;
import java.util.List;


public class Main {

    private static CtrlPresentacio ctrlPresentacio;
    private static CtrlDominio ctrlDominio;

    public static void main(String[] args) throws Exception {

        JocsDeProvaFinal j = new JocsDeProvaFinal();
        j.jocs();



    }

}
