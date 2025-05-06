package attack;

import characters.Personaje;

public interface AtaqueStrategy {
    
    public void atacar(Personaje atacante, Personaje atacado);

}
