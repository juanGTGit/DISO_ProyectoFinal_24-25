package controller;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import characters.*;
import characters.bots.DifficultBotFactory;
import characters.bots.NormalBotFactory;
import settings.*;
/* CLASE GAME CONTROLLER
 * - Encargada de realizar todo el programa
 * - Gran parte del código y la implementaciones de los patrones ocurren aquí
*/

/** TODO Crear clase en paquete controller con los métodos alternos
 * - Creación de personajes
 * - Algo más (ya veremos)
 */

public class GameController {

    private ArrayList<Personaje> arrayPersonajes = new ArrayList<Personaje>();
    Scanner entrada = new Scanner(System.in);

    DifficultBotFactory difficultBotFactory = new DifficultBotFactory();
    NormalBotFactory normalBotFactory = new NormalBotFactory();
    
    public void configurarJuego(){

        
        int totalJugadores = 0, totalJugadoresMaquina = 0, nivelDificultad = 0;

        int opcion = 0;
        System.out.println("\n---BIENVENIDO AL JUEGO---\n");
        System.out.println("Elija una opcion de las siguientes:\n");

        do{
            System.out.println("1. Empezar Partida Nueva");
            System.out.println("2. Guarda la Partida");
            /** TODO Cambiar la condición de guardado de partida
             * Si no se ha jugado ninguna partida que no se pueda guardar nada
             * y haga un print de un aviso (no excepcion)
             */
            System.out.println("3. Salir");
            System.out.print("---> ");
            opcion = entrada.nextInt();

            switch(opcion){
                case 1:
                    System.out.println("\n-------COMIENZA LA PARTIDA!!!-------\n");
                    System.out.println("\n-------\n");
                    System.out.println("Escoge el nivel de dificultad:");
                    System.out.print("1- Normal 2-Dificil\n---> ");
                    nivelDificultad = entrada.nextInt();
                    do {
                        do {
                            System.out.println("\n-------\n");
                            System.out.println("Cuantos jugadores quieres?");
                            System.out.print("---> ");
                            totalJugadores = entrada.nextInt();
                            if (totalJugadores < 1 || totalJugadores > 5) {
                                System.out.println("Dato no aceptable, ha de ser entre 1 y 5");
                            }
                        } while (totalJugadores < 1 || totalJugadores > 5);
            
                        do {
                            System.out.println("Cuantos bots quieres?");
                            System.out.print("---> ");
                            totalJugadoresMaquina = entrada.nextInt();
                            if (totalJugadoresMaquina < 0 || totalJugadoresMaquina > 5) {
                                System.out.println("Dato no aceptable, ha de ser entre 0 y 5");
                            }
                        } while (totalJugadoresMaquina < 0 || totalJugadoresMaquina > 5);
            
                        // Validación adicional: al menos un usuario y un bot, o dos usuarios
                        if ((totalJugadores + totalJugadoresMaquina) < 2 || totalJugadores == 0) {
                            System.out.println("Debe haber al menos un jugador usuario y un bot, o al menos dos jugadores usuario.");
                        }
                    } while ((totalJugadores + totalJugadoresMaquina) < 2 || totalJugadores == 0);

                    iniciarJuego(totalJugadores, totalJugadoresMaquina, nivelDificultad);
                    break;
                case 2:
                    try{
                        guardarPartida();
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
                    System.out.println("\nSolo admite valores del 1 al 3\n");
            }

        }while(opcion != 3);

    }

    //Comienzo del juego
    public void iniciarJuego(int totalJugadores, int totalJugadoresMaquina, int nivelDificultad){
        /**
         * TODO Comenzar la base de la clase partida (método simular)
         * Crear personajes usuarios (HECHO)
         * Crear personajes enemigos
         * Simuacaion de partida (Ataques enemigos con Strategy)
         * Consulta de la salud (State en caso de que quede poca vida o nula)
         * Creación de personajes en funcion de dificultad
        */
        creacionPersonajesUser(totalJugadores);
        creacionPersonajesBots(totalJugadoresMaquina, nivelDificultad);
    }

    private void creacionPersonajesUser(int totalJugadores){

        System.out.println("\nBienvenido a la seleccion de personajes!!!");
        for(int i = 0; i < totalJugadores; i++){

            System.out.print("Nombre del personaje " + (i+1) + ": ");
            String nombre = entrada.nextLine();

            System.out.println("Escoge el tipo de personaje:");
            System.out.print("Guerrero/Mago/Arquero\n---> ");
            String tipo = entrada.nextLine().toLowerCase();

            Personaje nuevoPersonaje;
            switch (tipo) {
                case "guerrero":
                    nuevoPersonaje = new GuerreroDecorator(new PersonajeBase(nombre, 100, 20, TipoJugador.USER), 30);
                    break;
                case "mago":
                    nuevoPersonaje = new MagoDecorator(new PersonajeBase(nombre, 80, 15, TipoJugador.USER), 100, 3);
                    break;
                case "arquero":
                    nuevoPersonaje = new ArqueroDecorator(new PersonajeBase(nombre, 90, 25, TipoJugador.USER), 5, 3);
                    break;
                default:
                    System.out.println("Tipo de personaje no válido. Se creará un Guerrero por defecto.");
                    nuevoPersonaje = new GuerreroDecorator(new PersonajeBase(nombre, 100, 20, TipoJugador.USER), 30);
            }
            
            arrayPersonajes.add(nuevoPersonaje);
        }
    }

    private void creacionPersonajesBots(int totalJugadoresMaquina, int nivelDificultad){
        Integer personajeRandom = (int)(Math.random() * 3 + 1);
        Personaje nuevoPersonajeBot = null;

        for(int i = 0; i < totalJugadoresMaquina; i++){
            //TODO Arreglar problema de asignaciond e nombre de la máquina
            if(nivelDificultad == 1 /*NORMAL*/){
                switch (personajeRandom) {
                    case 1:
                        nuevoPersonajeBot = new GuerreroDecorator(normalBotFactory.crearGuerrero(), 30);
                    case 2:
                        nuevoPersonajeBot = new MagoDecorator(normalBotFactory.crearMago(), 100, 3);
                    case 3:
                        nuevoPersonajeBot = new ArqueroDecorator(normalBotFactory.crearArquero(), 5, 3);
                }

            }else /*DIFÍCIL*/{
                switch (personajeRandom) {
                    case 1:
                        nuevoPersonajeBot = new GuerreroDecorator(difficultBotFactory.crearGuerrero(), 30);
                    case 2:
                        nuevoPersonajeBot = new MagoDecorator(difficultBotFactory.crearMago(), 100, 5);
                    case 3:
                        nuevoPersonajeBot = new ArqueroDecorator(difficultBotFactory.crearArquero(), 7, 5);
                }

            }
            arrayPersonajes.add(nuevoPersonajeBot);
        }
    }

    public void guardarPartida(){
        FileOutputStream  fichero = null;
	    PrintWriter escritura = null;
		try {
			fichero = new FileOutputStream("Guardado.txt", true);
			escritura = new PrintWriter (fichero,true);
			
			LocalDate fecha = LocalDate.now(); // Create a date object
			
			escritura.write("Fecha: "+ fecha +"\n");
			escritura.write("GANADOR:\n");
			escritura.write(this.arrayPersonajes.get(0).toString()+"\n\n");
			
			escritura.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(null != fichero) {
					fichero.close();
				}
			}catch(Exception ex2){
				ex2.printStackTrace();
			}
		}
    }

}