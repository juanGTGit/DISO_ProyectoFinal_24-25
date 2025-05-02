package characters.bots;

import characters.Personaje;

public interface PersonajeBotFactory {

    /*
     * INTERFAZ FACTORY PARA LOS ENEMIGOS
     * - Uso del patrón Abstract Factory
    */

    public Personaje crearGuerrero();
    public Personaje crearMago();
    public Personaje crearArquero();

}
