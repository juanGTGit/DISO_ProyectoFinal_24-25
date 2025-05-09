package attack.users;

import java.util.Scanner;

import attack.AtaqueStrategy;
import characters.GuerreroDecorator;
import characters.Personaje;

public class GuerreroUserAtaqueStrategy implements AtaqueStrategy{

    Scanner entrada = new Scanner(System.in);

    //Método de atauqe
    @Override
    public void atacar(Personaje atacante, Personaje atacado){

        Integer accion;

        do{
            System.out.println("Que deberia hacer el guerrero?");
            System.out.println("1- Atacar");
            System.out.print("---> ");
            accion = entrada.nextInt();
            
            //Realización del ataque
            switch (accion) {
                case 1:
                    System.out.println("El jugador " + atacante.getNombre() + " ataca a " + atacado.getNombre());

                    if(atacado instanceof GuerreroDecorator){
                        if(((GuerreroDecorator)atacado).getArmadura() > 0){
                            atacado.recibeDanyo(atacante.getFuerza());
                        }else{
                            ((GuerreroDecorator)atacado).recibeDanyoVida(atacante.getFuerza());
                        }
                    }else{
                        atacado.recibeDanyo(atacante.getFuerza());
                    }
                    
                    break;
            }
            
        }while(accion != 1);
        
        
    }
}
