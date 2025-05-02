package characters;

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

        if(this.armadura>=valor) {
			setArmadura(-valor);
		}else if(this.armadura == 0) {
			if(this.getVida()<=valor) {
				this.setVida(0);
			}else {
				this.setVida(-valor);
			}
			
		}else {
			Integer aux = valor-this.armadura;
			this.armadura = 0;
			this.setVida(-aux);
		}

    }


}
