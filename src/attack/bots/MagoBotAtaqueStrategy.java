package attack.bots;

import attack.AtaqueStrategy;
import characters.GuerreroDecorator;
import characters.MagoDecorator;
import characters.Personaje;

public class MagoBotAtaqueStrategy implements AtaqueStrategy{

    //Método de ataque
    @Override
    public void atacar(Personaje atacante, Personaje atacado){

        Integer accion;

        System.out.println("Que deberia hacer el mago?");
        System.out.println("1- Atacar\n2- Consumible\n\n...");

        accion = (int)(Math.random() * 2 + 1);

        //Pausa para decidir
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //Acciones del personaje en función de la opcion escogida
        switch(accion){
            case 1:
                System.out.println("El jugador " + atacante.getNombre() + " ataca a " + atacado.getNombre());
                atacarGuerrero(atacante, atacado);

                if(atacante instanceof MagoDecorator){
                    ((MagoDecorator) atacante).gastarMana();
                }
            case 2:
                if(atacante instanceof MagoDecorator){
                    ((MagoDecorator) atacante).tomarPocion();
                }
        }
    }

    public void atacarGuerrero(Personaje atacante, Personaje atacado){
        if(atacado instanceof GuerreroDecorator){
            if(((GuerreroDecorator)atacado).getArmadura() > 0){
                atacado.recibeDanyo(atacante.getFuerza());
            }else{
                ((GuerreroDecorator)atacado).recibeDanyoVida(atacante.getFuerza());
            }
        }else{
            atacado.recibeDanyo(atacante.getFuerza());
        }
    }
}
