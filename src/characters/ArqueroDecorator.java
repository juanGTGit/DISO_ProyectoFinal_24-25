package characters;

/**
 * Decorador que añade funcionalidad de mago a un personaje.
*/

public class ArqueroDecorator extends PersonajeDecorator{

    private Integer flechas;
    private Integer vendas;
    private Integer daga;

    public ArqueroDecorator(Personaje decorado, Integer vendas){
        super(decorado);
        this.flechas = 20;
        this.vendas = vendas;
        this.daga = 10;
    }

    public int getFlechas() {
		return this.flechas;
	}
	public int getVendas() {
		return this.vendas;
	}
	
	public int getDaga() {
		return this.daga;
	}

    public void dispararFlecha() {
		if (this.flechas > 0) {
			this.flechas = this.flechas -1;
			System.out.println("Has gastado una flecha");
		}else {
			System.out.println("No quedan flechas");
		}
	}

    public void curarVenda() {
		if (this.vendas > 0) {
			this.vendas = this.vendas -1;
			setVida(40);
			System.out.println("Recuperas 40 de vida");
		}else {
			System.out.println("No quedan vendas");
		}
		
	}

    public String toString() {
		return super.toString() + "\nDAGA: "+this.daga + "\nFLECHAS: "+ this.flechas+ "\nVENDAS: "+this.vendas ;
	}

	@Override
	public void recibeDanyo(Integer valor) {
		this.setVida(-valor);
	}
	
}
