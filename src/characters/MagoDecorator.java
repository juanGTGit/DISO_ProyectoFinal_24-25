package characters;

/**
 * Decorador que añade funcionalidad de mago a un personaje.
*/

public class MagoDecorator extends PersonajeDecorator{

    private Integer mana;
    private Integer pociones;

    public MagoDecorator(Personaje decorado, Integer mana, Integer pociones){
        super(decorado);
        this.mana = mana;
        this.pociones = pociones;
    }

    public Integer getMana(){
        return this.mana;
    }

    public void setMana(Integer valor){
        this.mana = this.mana + valor;
    }

    public Integer getPociones(){
        return this.pociones;
    }

    public void tomarPocion(){
        if(this.pociones > 0){
            this.pociones = this.pociones - 1;
            setMana(40);
            System.out.println("Recuperas 40 de mana\n");
        }else{
            System.out.println("No tienes pociones\n");
        }
    }

    public void gastarMana(){
        setMana(-20);
        System.out.println("Has gastdo 20 de mana\n");
        if(this.mana <= 0){
            System.out.println("No tienes mana\n");
        }
        
    }

    public String toString() {
		return super.toString() + "\nMANA: "+ this.mana + "\nPOCIONES: "+this.pociones;
	}

    @Override
    public void recibeDanyo(Integer valor){
        this.setVida(-valor);
        if (valor < 0) {
            valor = 0; // Ensure life does not go below 0
        }
    }

}
