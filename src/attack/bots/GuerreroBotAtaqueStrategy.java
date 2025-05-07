package attack.bots;

import attack.AtaqueStrategy;
import characters.Personaje;

public class GuerreroBotAtaqueStrategy implements AtaqueStrategy{

    //Método de ataque
    @Override
    public void atacar(Personaje atacante, Personaje atacado){

        System.out.println("Que deberia hacer el guerrero?");
        System.out.println("1- Atacar\n\n...");

        //Pausa para decidir
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Realización del ataque
        System.out.println("El jugador " + atacante.getNombre() + " ataca a " + atacado.getNombre());

        atacado.recibeDanyo(atacante.getFuerza());

    }

}
