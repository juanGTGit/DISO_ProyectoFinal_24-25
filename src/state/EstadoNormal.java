package state;

import characters.Personaje;
import observer.VidaPersonaje;

public class EstadoNormal implements EstadoVida {  // <-- Implementación aquí
    @Override
    public void verificarEstado(Personaje personaje, VidaPersonaje controlador) {
        if (personaje.getVida() <= personaje.getVida() * 0.2) {
            controlador.notificarObservers("¡Estado CRÍTICO!");
            personaje.setEstado(new EstadoHerido());
        }
    }
}