package controller;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import attack.users.*;
import attack.bots.*;
import characters.*;
import characters.bots.DifficultBotFactory;
import characters.bots.NormalBotFactory;
import settings.*;

//TODO HACER COMENTARIOS EN STATE Y VIDA

public class GameController {

    //Guardado de personajes, atributos y fábricas
    private ArrayList<Personaje> arrayPersonajes = new ArrayList<Personaje>();
    Scanner entrada = new Scanner(System.in);

    DifficultBotFactory difficultBotFactory = new DifficultBotFactory();
    NormalBotFactory normalBotFactory = new NormalBotFactory();
    
    //Metodo inicial llamado en el main
    public void configurarJuego(){

        int totalJugadores = 0, totalJugadoresMaquina = 0, nivelDificultad = 0;

        int opcion = 0;
        System.out.println("\n---BIENVENIDO AL JUEGO---\n");
        System.out.println("Elija una opcion de las siguientes:\n");

        //Menu de seleccion de partida
        do{
            System.out.println("1. Empezar Partida Nueva");
            System.out.println("2. Guarda la Partida");
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

        //Llamadas a métodos de la partida
        creacionPersonajesUser(totalJugadores);
        if(totalJugadoresMaquina > 0){
            creacionPersonajesBots(totalJugadoresMaquina, nivelDificultad);
        }
        //Ataques y acciones
        simulacion();
        //Vuelta al metodo de menu
        configurarJuego();

    }

    //Creacion de usuarios
    private void creacionPersonajesUser(int totalJugadores){

        System.out.println("\nBienvenido a la seleccion de personajes!!!");

        for(int i = 0; i < totalJugadores; i++){

            System.out.println("Escoge el tipo de personaje:");
            System.out.print("Guerrero/Mago/Arquero\n---> ");
            String tipo = entrada.nextLine().toLowerCase();

            Personaje nuevoPersonaje;

            //Decoradores en funcion de la clase
            switch (tipo) {
                case "guerrero":
                    nuevoPersonaje = new GuerreroDecorator(new PersonajeBase("J" + (i+1), 100, 20, TipoJugador.USER), 30);
                    break;
                case "mago":
                    nuevoPersonaje = new MagoDecorator(new PersonajeBase("J" + (i+1), 80, 15, TipoJugador.USER), 100, 3);
                    break;
                case "arquero":
                    nuevoPersonaje = new ArqueroDecorator(new PersonajeBase("J" + (i+1), 90, 25, TipoJugador.USER), 5, 3);
                    break;
                default:
                    System.out.println("Tipo de personaje no válido. Se creará un Guerrero por defecto.");
                    nuevoPersonaje = new GuerreroDecorator(new PersonajeBase("J" + (i+1), 100, 20, TipoJugador.USER), 30);
            }
            
            //Añadido al array de personajes

            arrayPersonajes.add(nuevoPersonaje);
        }
    }

    //Creacion de bots
    private void creacionPersonajesBots(int totalJugadoresMaquina, int nivelDificultad){
        Personaje nuevoPersonajeBot = null;

        for(int i = 0; i < totalJugadoresMaquina; i++){

            Integer personajeRandom = (int)(Math.random() * 3 + 1);

            //Decoradores en funcion de dificultad
            if(nivelDificultad == 1 /*NORMAL*/){
                switch (personajeRandom) {
                    //Los métodos de la factory ya implementan un personaje base para ser decorado en funicon de la clase
                    case 1:
                    nuevoPersonajeBot = new GuerreroDecorator(normalBotFactory.crearGuerrero(), 30);
                    break;
                    case 2:
                    nuevoPersonajeBot = new MagoDecorator(normalBotFactory.crearMago(), 100, 3);
                    break;
                    case 3:
                    nuevoPersonajeBot = new ArqueroDecorator(normalBotFactory.crearArquero(), 5, 3);
                    break;
                }

            }else /*DIFÍCIL*/{
                switch (personajeRandom) {
                    case 1:
                    nuevoPersonajeBot = new GuerreroDecorator(difficultBotFactory.crearGuerrero(), 30);
                    break;
                    case 2:
                    nuevoPersonajeBot = new MagoDecorator(difficultBotFactory.crearMago(), 100, 5);
                    break;
                    case 3:
                    nuevoPersonajeBot = new ArqueroDecorator(difficultBotFactory.crearArquero(), 7, 5);
                    break;
                }

            }
            //Añadido al array
            arrayPersonajes.add(nuevoPersonajeBot);
        }
    }
    
    //Método para decidir el turno
    private int randomTurno() {
		return  (int)(Math.random() * this.arrayPersonajes.size() + 0);
	}

	//Simulación. Método principal de la partida
    public void simulacion()
    {
        System.out.println("\n-------\n");
        

        //Estrategias user
        GuerreroUserAtaqueStrategy guerreroUserAtaqueStrategy = new GuerreroUserAtaqueStrategy();
        MagoUserAtaqueStrategy magoUserAtaqueStrategy = new MagoUserAtaqueStrategy();
        ArqueroUserAtaqueStrategy arqueroUserAtaqueStrategy = new ArqueroUserAtaqueStrategy();

        //Estrategias bots
        ArqueroBotAtaqueStrategy arqueroBotAtaqueStrategy = new ArqueroBotAtaqueStrategy();
        MagoBotAtaqueStrategy magoBotAtaqueStrategy = new MagoBotAtaqueStrategy();
        GuerreroBotAtaqueStrategy guerreroBotAtaqueStrategy = new GuerreroBotAtaqueStrategy();

        do{
            /*
             * Avisar
             */
            Integer jTurnoAtacante = randomTurno();
            Integer jTurnoAtacado;
            System.out.println(this.arrayPersonajes.get(jTurnoAtacante));
            
            /** SELECCION DEL TURNO
             * - En función del turno elegido al azar, le toca a un jugador o a otro
             * - Se escoge la estrategia correspondiente en caso de user o bot
             * - Después, se cruza con el tipo de personaje
            */

            do{
                jTurnoAtacado = randomTurno();
            }while(jTurnoAtacado == jTurnoAtacante);

            if(this.arrayPersonajes.get(jTurnoAtacante).getTipoJugador() == TipoJugador.USER){

                if(this.arrayPersonajes.get(jTurnoAtacante) instanceof GuerreroDecorator){
                    guerreroUserAtaqueStrategy.atacar(this.arrayPersonajes.get(jTurnoAtacante), this.arrayPersonajes.get(jTurnoAtacado));
                }else if(this.arrayPersonajes.get(jTurnoAtacante) instanceof MagoDecorator){
                    magoUserAtaqueStrategy.atacar(this.arrayPersonajes.get(jTurnoAtacante), this.arrayPersonajes.get(jTurnoAtacado));
                }else if(this.arrayPersonajes.get(jTurnoAtacante) instanceof ArqueroDecorator){
                    arqueroUserAtaqueStrategy.atacar(this.arrayPersonajes.get(jTurnoAtacante), this.arrayPersonajes.get(jTurnoAtacado));
                }

            }else if(this.arrayPersonajes.get(jTurnoAtacante).getTipoJugador() == TipoJugador.BOT){

                if(this.arrayPersonajes.get(jTurnoAtacante) instanceof GuerreroDecorator){
                    guerreroBotAtaqueStrategy.atacar(this.arrayPersonajes.get(jTurnoAtacante), this.arrayPersonajes.get(jTurnoAtacado));
                }else if(this.arrayPersonajes.get(jTurnoAtacante) instanceof MagoDecorator){
                    magoBotAtaqueStrategy.atacar(this.arrayPersonajes.get(jTurnoAtacante), this.arrayPersonajes.get(jTurnoAtacado));
                }else if(this.arrayPersonajes.get(jTurnoAtacante) instanceof ArqueroDecorator){
                    arqueroBotAtaqueStrategy.atacar(this.arrayPersonajes.get(jTurnoAtacante), this.arrayPersonajes.get(jTurnoAtacado));
                }
                
            }

            /** TODO Consultar estado de los personajes
             * Implementar el State Pattern en función de la vida del personaje
             * (NOTIFICACIONES)
             * Si está al 50%, poner que está a mitad de vida
             * Si está al 20%, poner que está a poca vida
             * Si está a 0, cambiar el estado y eliminar al personaje del ArrayList
             * Implementar Observer para poder implementar el State
            */



            //Pausa entre turnos
            try {
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(this.arrayPersonajes.get(jTurnoAtacado).getVida() <= 0){
                System.out.println(this.arrayPersonajes.get(jTurnoAtacado).getNombre() + " esta muerto\n");
                this.arrayPersonajes.remove((int)jTurnoAtacado);

                //Adjuste del indice del atacante
                if (jTurnoAtacado < jTurnoAtacante) {
                    jTurnoAtacante--;
                }
            }else if(this.arrayPersonajes.get(jTurnoAtacante).getVida() <= 0){
                System.out.println(this.arrayPersonajes.get(jTurnoAtacante).getNombre() + " esta muerto\n");
                this.arrayPersonajes.remove((int)jTurnoAtacado);
            }

        }while(this.arrayPersonajes.size() != 1);



        if(this.arrayPersonajes.size() == 1){
            System.out.println("\n---------\n");
            System.out.println("El ganador es: " + this.arrayPersonajes.get(0).getNombre());
            System.out.println("\n---------\n" + this.arrayPersonajes.get(0));
        }
    }

    /** TODO Cambiar la condición de guardado de partida
     * Si no se ha jugado ninguna partida que no se pueda guardar nada
     * y haga un print de un aviso (no excepcion)
    */

    //Método de guardado
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