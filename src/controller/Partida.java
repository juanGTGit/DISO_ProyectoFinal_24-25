package controller;

import java.util.Scanner;

/* CLASE PARTIDA
 * - Encaragada de realizar todo el programa
 * - Gran parte del código y la implementciones de los patrones ocurren aquí
*/

public class Partida {

    public void configurarJuego(){

        Scanner entrada = new Scanner(System.in);
        int totalJugadores = 0, totalJugadoresMaquina = 0;

        int opcion = 0;
        System.out.println("\n---BIENVENIDO AL JUEGO---\n");
        System.out.println("Elija una opcion de las siguientes:\n");

        do{
            System.out.println("1. Empezar Partida Nueva");
            System.out.println("2. Guarda la Partida");
            System.out.println("3. Salir");
            System.out.println("---> ");
            opcion = entrada.nextInt();

            switch(opcion){
                case 1:
                    System.out.println("\n-------COMIENZA LA PARTIDA!!!-------\n");
                    do{
                        System.out.println("\n-------\n");
                        System.out.println("Cuantos jugadores quieres?");
                        System.out.println("---> ");
                        totalJugadores = entrada.nextInt();
                        if(totalJugadores<1 || totalJugadores>5) {
                            System.out.println("Dato no aceptable, ha de ser entre 1 y 5");
                        }
                    }while(totalJugadores < 1 || totalJugadores > 5);

                    do{
                        System.out.println("Cuantos bots quieres?");
                        System.out.println("---> ");
                        totalJugadoresMaquina = entrada.nextInt();
                        if(totalJugadoresMaquina < 0 || totalJugadoresMaquina > 5) {
                            System.out.println("Dato no aceptable, ha de ser entre 0 y 5");
                        }
                    }while(totalJugadoresMaquina < 0 || totalJugadoresMaquina > 5);
                    iniciarJuego(totalJugadores, totalJugadoresMaquina);
                    break;
                case 2:
                    try{
                        //p.guardarPartida();
                    }catch(Exception ex){
                        System.out.println("No se ha podido guardar nada en el archivo...");
                    }
                    break;
                case 3:
                    entrada.close();

                    System.out.println("\n.....Saliendo del juego.....");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Solo admite valores del 1 al 3");
            }

        }while(opcion != 3);

    }

    public void iniciarJuego(int totalJugadores, int totalJugadoresMaquina){
        //TODO Comenzar la base de la clase partida
    }

}
