package characters;

import settings.TipoJugador;
/* CLASE ABSTRACTA
 * - Definición de atributos y métodos comunes para los personajes
 * - Representación de un personaje común
*/

public abstract class Personaje{

    private String nombre;
    private Integer vida;
    private Integer fuerza;
    private TipoJugador tipo;

    public Personaje(String nombre, Integer vida, Integer fuerza, TipoJugador tipo){
        this.nombre = nombre;
        this.vida = vida;
        this.fuerza = fuerza;
        this.tipo = tipo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public Integer getVida(){
        return this.vida;
    }

    public void setVida(Integer vida){
        this.vida = this.vida + vida;
    }

    public Integer getFuerza(){
        return this.fuerza;
    }

    public TipoJugador getTipoJugador(){
        return this.tipo;
    }

    public String toString() {
		return "NOMBRE: "+ getNombre() + "\nVIDA: "+ getVida() + "\nFUERZA: "+ getFuerza();
    }

    public abstract void recibeDanyo(Integer valor);

}
