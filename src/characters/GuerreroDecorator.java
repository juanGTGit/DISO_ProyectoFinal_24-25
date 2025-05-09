package characters;

/**
 * Decorador que añade funcionalidad de mago a un personaje.
*/

public class GuerreroDecorator extends PersonajeDecorator{
    private Integer armadura;

    public GuerreroDecorator(Personaje decorado, Integer armadura) {
      super(decorado);
      this.armadura = 30;
    }

    public String toString(){
      return super.toString() + "\nARMADURA: " +this.armadura;
    }

    public Integer getArmadura() {
		  return this.armadura;
	  }

    public void setArmadura(Integer danyo) {
		  this.armadura = this.armadura + danyo; 
	  }

    @Override
    public void recibeDanyo(Integer valor){
      this.setArmadura(-valor);
      if(valor < 0){
        valor = 0;
      }
    }

    public void recibeDanyoVida(Integer valorVida){

      this.setVida(-valorVida);
      if(valorVida < 0){
        valorVida = 0;
      }

    }

}
