package characters;

/**
 * Decorador que añade funcionalidad de mago a un personaje.
*/

public class ArqueroDecorator extends PersonajeDecorator{

    private Integer flechas;
    private Integer vendas;
    private Integer daga;

    public ArqueroDecorator(Personaje decorado, Integer flechas, Integer vendas){
        super(decorado);
        this.flechas = 10;
        this.vendas = vendas;
        this.daga = 10;
    }

    public Integer getFlechas() {
		return this.flechas;
	}
	public Integer getVendas() {
		return this.vendas;
	}
	
	public Integer getDaga() {
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
			setVida(30);
			System.out.println("Recuperas 30 de vida");
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
		if (valor < 0) {
			valor = 0; // Ensure life does not go below 0
		}
	}
	
}
