package com.prop.fib.horaris.presentation;

/**
 * @author Jinson Pardo
 */
public class VistaUser {

    private inout io = new inout();
    private static int espais = 0;

    private  void header(String cabecera) throws Exception {
        limpiarConsola(30);
        borders('*', cabecera.length());
        io.writespace(espais);
        io.write(cabecera);
        io.writespace(espais); io.writeln();
        borders('*', cabecera.length());
    }

    public void borders(char caracter, int num) throws Exception {
        io.writesame(caracter, num + 2 * espais); io.writeln();
    }

    private void limpiarConsola(int num) throws Exception {
        for (int i=0; i<num; ++i) io.writeln();
    }


    public void mostrarMenu(String usuari) throws Exception {
        espais = 10;
        header(usuari);
        io.writeln("1) Generar horari");
        io.writeln("2) Consultar horaris guardats");
        io.writeln("3) Provar drivers");
        io.writeln("4) Modificar horari");
        io.writeln("0) Tancar sessió");
        borders('*', usuari.length());
        io.write("Tria una opció: ");
    }

    public void mostraMenu2(String cabecera) throws Exception {
        espais = 15;
        header(cabecera);
        io.writeln("1) Escenari amb dades normals");
        io.writeln("2) Escenari amb dades grans");
        io.writeln("3) Escenari amb dades normals d'assignatures i poques aules");
        io.writeln("4) Escenari amb dades d'assignatures del mateix nivel i poques aules");
        io.writeln("5) Escenari amb dades d'assignatures amb correquisits i poques aules");
        io.writeln("6) Escenari amb dades d'assignatures grup i subgrup no ser el mateix dia i poques aules");
        io.writeln("7) Cargar fitxer amb les teves dades");
        io.writeln("0) Sortir");
        borders('*', cabecera.length());
        io.write("Tria una opció: "); io.writeln();
    }


    public int getOpcio() throws Exception {
        return io.readint();
    }

    public String preguntaGuardar() throws Exception {
        limpiarConsola(1);
        io.write("Desitja guarda l'horari? (S/N): ");
        return io.readword();
    }

    public String nomHorari() throws Exception {
        limpiarConsola(1);
        io.write("Nombre del horario: ");
        return io.readword();
    }

    public void pause() throws Exception {
        limpiarConsola(1);
        io.write("Presiona enter per a continuar..."); io.readln();
        io.read();
    }

    public void missatgeError(String missatge) throws Exception {
        limpiarConsola(1);
        espais = 10;
        io.writespace(espais);
        io.write(missatge);
        io.writespace(espais); io.writeln();
        borders('x', missatge.length());
    }

    public void imprimirCabecera(String cabecera) throws Exception {
        limpiarConsola(30);
        espais = 15;
        header(cabecera);
    }

    public void imprimirlistafitxers (String[] fitxers) throws Exception {
        for (int i=0; i<fitxers.length; ++i)
            io.writeln((i+1) +") " + fitxers[i]);
        io.writeln("0) Sortir");
    }

}
