package com.prop.fib.horaris.domain.Test;

import com.prop.fib.horaris.domain.Classes.Aula;
import com.prop.fib.horaris.presentation.inout;

public class CtrlDrivers {

    private static DriverAdmin driverAdmin;
    private static DriverAlumne driverAlumne;
    private static DriverAssignatura driverAssignatura;
    private static DriverAula driverAula;
    private static DriverCtrlDominio driverCtrlDominio;
    private static DriverGrupAssignatura driverGrupAssignatura;
    private static DriverHorari driverHorari;
    private static DriverPeriodeLectiu driverPeriodeLectiu;
    private static DriverUser driverUser;
    private static DriverRestriccions driverRestriccions;
    private inout sc = new inout();

    public void Executa() throws Exception{


        mostrarMenu();
        int op;
        op = sc.readint();

        while (op != 0) {
            switch (op) {
                case 1:
                    driverAdmin = new DriverAdmin();
                    driverAdmin.Executa();
                    break;
                case 2:
                    driverAlumne = new DriverAlumne();
                    driverAlumne.Executa();
                    break;
                case 3:
                    driverAssignatura = new DriverAssignatura();
                    driverAssignatura.Executa();
                    break;
                case 4:
                    driverAula = new DriverAula();
                    driverAula.Executa();
                    break;
                case 5:
                    driverCtrlDominio = new DriverCtrlDominio();
                    driverCtrlDominio.Executa();
                    break;
                case 6:
                    driverGrupAssignatura = new DriverGrupAssignatura();
                    driverGrupAssignatura.Executa();
                    break;
                case 7:
                    driverHorari = new DriverHorari();
                    driverHorari.Executa();
                    break;
                case 8:
                    driverPeriodeLectiu = new DriverPeriodeLectiu();
                    driverPeriodeLectiu.Executa();
                    break;
                case 9:
                    driverUser = new DriverUser();
                    driverUser.Executa();
                    break;
                case 10:
                    driverRestriccions = new DriverRestriccions();
                    driverRestriccions.Executa();
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

            System.out.println("Escull un altra opcio");
            mostrarMenu();
            op = sc.readint();
        }
    }

    private void  mostrarMenu() throws Exception{
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                    CTRL DRIVERS                                  -\n");
        sc.writeln("-           [opcio   Operacio(Atributs)                            -\n");
        sc.writeln("--------------------------------------------------------------------\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("-   0  Sortir Drivers                                              -\n");
        sc.writeln("-   1  DriverAdmin                                                 -\n");
        sc.writeln("-   2  driverAlumne                                                -\n");
        sc.writeln("-   3  driverAssignatura                                           -\n");
        sc.writeln("-   4  driverAula                                                  -\n");
        sc.writeln("-   5  driverCtrlDominio                                           -\n");
        sc.writeln("-   6  driverGrupAssignatura                                       -\n");
        sc.writeln("-   7  driverHorari                                                -\n");
        sc.writeln("-   8  driverPeriodeLectiu                                         -\n");
        sc.writeln("-   9  driverUser                                                  -\n");
        sc.writeln("-   10 driverRestriccions                                          -\n");
        sc.writeln("-                                                                  -\n");
        sc.writeln("--------------------------------------------------------------------\n");
    }
}
