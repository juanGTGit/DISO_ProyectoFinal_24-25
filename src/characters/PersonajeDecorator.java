package characters;

public class PersonajeDecorator extends Personaje{
    protected Personaje decorado;

    public PersonajeDecorator(Personaje decorado) {
        super(decorado.getNombre(), decorado.getVida(), decorado.getFuerza(), decorado.getTipoJugador());
        this.decorado = decorado;
    }

    public void recibeDanyo(Integer vida){
        this.setVida(-vida);
    }

    public String toString() {
		return "NOMBRE: "+ getNombre() + "\nVIDA: "+ getVida() + "\nFUERZA: "+ getFuerza();
	}


}
