package characters;

/** CLASE ABSTRACTA. COMPONENTE DECORADOR
 * - Se decora un personaje y se le extiende su funcionalidad
 * - Se implemente el método abstracto de la clase padre
 * - Implementa el patrón Decorator
*/

public class PersonajeDecorator extends Personaje{
    protected Personaje decorado;

    public PersonajeDecorator(Personaje decorado) {
        super(decorado.getNombre(), decorado.getVida(), decorado.getFuerza(), decorado.getTipoJugador());
        this.decorado = decorado;
    }

    public void recibeDanyo(Integer vida){
        this.setVida(-vida);
    }

}
