package state;

import characters.Personaje;
import observer.VidaPersonaje ;

public interface EstadoVida {
    void verificarEstado(Personaje personaje, VidaPersonaje  controlador);
}
