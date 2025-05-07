package main;

/**
INICIO DE PARTIDA: Proyecto Final DISO 24-25
*@author: Juan Battaglio Quintana
*@author: Juan Guasp Timoner
*/

import controller.*;

public class Main {
    public static void main(String[] args) {

        /*MAIN DEL JUEGO
         * -Uso de patrón Facade (Se ininicializa el objeto y
         * se llama a un método inicial que delega todo el proceso de la partida)
        */

        GameController p = new GameController();
        p.configurarJuego();
    }

}