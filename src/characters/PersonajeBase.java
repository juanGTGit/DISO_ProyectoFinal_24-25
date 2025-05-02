package characters;

import settings.TipoJugador;

/** CLASE CONCRETA
 * - Definición de un personaje básico.
*/

public class PersonajeBase extends Personaje{

    public PersonajeBase(String nombre, int vida, int fuerza, TipoJugador tipoJugador) {
        super(nombre, vida, fuerza, tipoJugador);
    }

    @Override
    public void recibeDanyo(Integer vida){
        this.setVida(-vida);
    }

}
