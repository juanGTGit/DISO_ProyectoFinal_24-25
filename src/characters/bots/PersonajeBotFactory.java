package characters.bots;

import characters.Personaje;

public interface PersonajeBotFactory {

    /*
     * INTERFAZ FACTORY PARA LOS ENEMIGOS
     * - Uso del patrón Abstract Factory (Se crean familias de objetos relacionados
     * sin especificar sus clases concretas)
    */

    public Personaje crearGuerrero();
    public Personaje crearMago();
    public Personaje crearArquero();

}
