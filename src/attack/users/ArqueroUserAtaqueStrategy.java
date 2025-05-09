package attack.users;

import java.util.Scanner;

import attack.AtaqueStrategy;
import characters.ArqueroDecorator;
import characters.Personaje;

public class ArqueroUserAtaqueStrategy implements AtaqueStrategy{

    Scanner entrada = new Scanner(System.in);

    //Método de ataque
    @Override
    public void atacar(Personaje atacante, Personaje atacado){

        Integer accion, tipoAtaque;

        do{
            System.out.println("Que deberia hacer el arquero?");
            System.out.println("1- Atacar\n2- Consumible\n--> " );
            accion = entrada.nextInt();

            //Acciones del personaje en funcion de la opcion que se escoge y su tipo de ataque
            switch(accion){
                case 1:
                    do{
                        System.out.println("Como deberia atacar el arquero?");
                        System.out.println("1- Arco\n2- Daga");
                        System.out.print("---> ");
                        tipoAtaque = entrada.nextInt();

                        switch(tipoAtaque){
                            case 1:
                                System.out.println("El jugador " + atacante.getNombre() + " ataca a " + atacado.getNombre() + " con arco");
                                atacado.recibeDanyo(atacante.getFuerza());

                                if(atacante instanceof ArqueroDecorator){
                                    ((ArqueroDecorator)atacante).dispararFlecha();
                                }
                                break;
                            case 2:
                                System.out.println("El jugador " + atacante.getNombre() + " ataca a " + atacado.getNombre() + " con daga");
                                atacado.recibeDanyo(atacante.getFuerza());
                                break;
                            default:
                                System.out.println("Opcion no valida");
                        }
                    }while(tipoAtaque != 1 || tipoAtaque != 2);
                    break;
                case 2:
                    if(atacado instanceof ArqueroDecorator){
                        ((ArqueroDecorator) atacante).curarVenda();
                    }
                    break;
                default:
                    System.out.println("Opcion no valida");
                    

            }

        }while(accion != 1 || accion != 2);
        
    }

}
