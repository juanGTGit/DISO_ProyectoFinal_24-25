package characters.bots;

import characters.Personaje;

public interface PersonajeBotFactory {

    public Personaje crearGuerrero();
    public Personaje crearMago();
    public Personaje crearArquero();

}
