package characters.bots;

import characters.Personaje;
import characters.PersonajeBase;

import settings.TipoJugador;

public class NormalBotFactory implements PersonajeBotFactory{

    /*
     * INTERFAZ FACTORY PARA LOS ENEMIGOS
     * - Gestión según dificultad
     * - Uso del patrón Factory (Se crea un objeto según un tipo solicitado,
     * sin exponer la lógica de instanciación)
    */
    
    private static int contador = 0;

    public Personaje crearGuerrero()
    {
        return new PersonajeBase("M" + contador++, 80, 10, TipoJugador.BOT);
    }

    public Personaje crearMago() {
        return new PersonajeBase("M" + contador++, 60, 30, TipoJugador.BOT);
    }

    public Personaje crearArquero() {
        return new PersonajeBase("M" + contador++, 70, 20, TipoJugador.BOT);
    }

}
