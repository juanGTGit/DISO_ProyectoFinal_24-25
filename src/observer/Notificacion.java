package observer;

import characters.Personaje;

public class Notificacion implements VidaObserver{
    @Override
    public void actualizar(Personaje personaje, String mensaje) {
        System.out.printf("[ALERTA] %s: %s\n", personaje.getNombre(), mensaje);
    }
}
