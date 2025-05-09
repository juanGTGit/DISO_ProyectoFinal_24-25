package attack.users;

import java.util.Scanner;

import attack.AtaqueStrategy;
import characters.MagoDecorator;
import characters.Personaje;

public class MagoUserAtaqueStrategy implements AtaqueStrategy{

    Scanner entrada = new Scanner(System.in);

    //Método de ataque
    @Override
    public void atacar(Personaje atacante, Personaje atacado){

        Integer accion;

        do{
            System.out.println("Que deberia hacer el mago?");
            System.out.println("1- Atacar\n2- Consumible");
            System.out.print("---> ");
            accion = entrada.nextInt();

            //Acciones del personaje en función de la opción escogida
            switch(accion){
                case 1:
                    System.out.println("El jugador " + atacante.getNombre() + " ataca a " + atacado.getNombre());

                    atacado.recibeDanyo(atacante.getFuerza());

                    if(atacante instanceof MagoDecorator){
                        ((MagoDecorator) atacante).gastarMana();
                    }
                    break;
                case 2:
                    if(atacante instanceof MagoDecorator){
                        ((MagoDecorator) atacante).tomarPocion();
                    }
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while(accion != 1 || accion != 2);
        
    }

}
