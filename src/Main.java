//INICIO DE PARTIDA: Proyecto Final DISO 24-25
//Juan Battaglio Quintana, Juan Guasp Timoner
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        int opcion;

        System.out.println();
        System.out.println("--- BIENVENIDO A POOBR --- ");
        System.out.println();
        System.out.println("Elija una opcion de las siguientes: ");
        System.out.println();

        do {
            System.out.println("1-Empezar Partida Nueva");
            System.out.println("2-Guarda la partdia");
            System.out.println("3-Salir");
            System.out.print("---> ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    p = new Partida();
                    System.out.println();
                    System.out.println("-------COMIENZA LA PARTIDA!!!-------");
                    System.out.println();
                    p.simular();
                    break;
                case 2:

                    try{
                        p.guardarPartida();
                    }catch(Exception ex){
                        System.out.println("No se ha podido guardar nada en el archivo...");
                    }

                    break;
                case 3:
                    break;
                default:
                    throw new IllegalStateException("Valor no válido: " + opcion);
            }

        }while(opcion != 3);

        System.out.println(".....Saliendo del juego.....");
        entrada.close();
        System.exit(0);
    }
}