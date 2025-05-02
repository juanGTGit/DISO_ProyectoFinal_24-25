package characters.bots;

import characters.Personaje;
import characters.PersonajeBase;
import settings.TipoJugador;

public class DifficultBotFactory implements PersonajeBotFactory{

    /*
     * INTERFAZ FACTORY PARA LOS ENEMIGOS
     * - Gestión según dificultad
     * - Uso del patrón Factory
    */
    
    private static int contador = 1;

    public Personaje crearGuerrero()
    {
        return new PersonajeBase("M" + contador++, 90, 10, TipoJugador.BOT);
    }

    public Personaje crearMago() {
        return new PersonajeBase("M" + contador++, 100, 30, TipoJugador.BOT);
    }

    public Personaje crearArquero() {
        return new PersonajeBase("M" + contador++, 80, 20, TipoJugador.BOT);
    }

}
