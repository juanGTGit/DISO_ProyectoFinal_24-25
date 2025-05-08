package observer;

import characters.Personaje;
import state.EstadoVida;
import java.util.ArrayList;
import java.util.List;

public class VidaPersonaje {
    
    private List<VidaObserver> observers = new ArrayList<>();
    private Personaje personaje;

    public VidaPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public void agregarObserver(VidaObserver observer) {
        observers.add(observer);
    }

    public void verificarEstado() {
        EstadoVida estadoActual = personaje.getEstado();
        estadoActual.verificarEstado(personaje, this);
    }

    public void notificarObservers(String mensaje) {
        for (VidaObserver observer : observers) {
            observer.actualizar(personaje, mensaje);
        }
    }
}
