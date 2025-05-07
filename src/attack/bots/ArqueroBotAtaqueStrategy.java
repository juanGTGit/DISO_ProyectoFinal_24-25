package attack.bots;

import attack.AtaqueStrategy;
import characters.ArqueroDecorator;
import characters.Personaje;

public class ArqueroBotAtaqueStrategy implements AtaqueStrategy{

    //Método de ataque
    @Override
    public void atacar(Personaje atacante, Personaje atacado){

        Integer accion, tipoAtaque;

        System.out.println("Que deberia hacer el arquero?");
        System.out.println("1- Atacar\n2- Consumible\n\n...");

        //Pausa para decidir
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        accion = (int)(Math.random() * 2 + 1);

        //Acciones del personaje en funcion de la opcion que se escoge y su tipo de ataque
        switch(accion){
            case 1:
                System.out.println("Como deberia atacar el arquero?");
                System.out.println("1- Arco\n2- Daga\n\n...");

                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                tipoAtaque = (int)(Math.random() * 2 + 1);

                switch(tipoAtaque){
                    case 1:
                        System.out.println("El jugador " + atacante.getNombre() + " ataca a " + atacado.getNombre() + " con arco");
                        atacado.recibeDanyo(atacante.getFuerza());

                        if(atacante instanceof ArqueroDecorator){
                            ((ArqueroDecorator)atacante).dispararFlecha();
                        }
                    case 2:
                        System.out.println("El jugador " + atacante.getNombre() + " ataca a " + atacado.getNombre() + " con daga");
                        atacado.recibeDanyo(atacante.getFuerza());
                    default:
                        System.out.println("Opcion no valida");
                }
            case 2:
                if(atacado instanceof ArqueroDecorator){
                    ((ArqueroDecorator) atacante).curarVenda();
                }
            default:
                System.out.println("Opcion no valida");    
        }




    }
}
