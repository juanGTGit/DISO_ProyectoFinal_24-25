package attack.users;

import java.util.Scanner;

import attack.AtaqueStrategy;
import characters.Personaje;

public class GuerreroUserAtaqueStrategy implements AtaqueStrategy{

    Scanner entrada = new Scanner(System.in);

    @Override
    public void atacar(Personaje atacante, Personaje atacado){

        Integer accion;

        do{
            System.out.println("Que deberia hacer el guerrero?");
            System.out.println("1- Atacar\n---> ");
            accion = entrada.nextInt();
            
            switch (accion) {
                case 1:
                    System.out.println("El jugador " + atacante.getNombre() + " ataca a " + atacado.getNombre());

                    atacado.recibeDanyo(atacante.getFuerza());
                    break;
            
                default:
                    System.out.println("Opcion no valida");
            }
            
        }while(accion != 1);
        
        entrada.close();
    }
}
