package Cenniki;

public class CennikKarnetu {



	private String typ="normalny";
	private int czas=0;
	
	
	
	public CennikKarnetu() {
		// TODO Auto-generated constructor stub
	}


	public float cena(){
		 float cena;
		if ((typ == "ulgowy")  ){
			return	cena= (float) ((17*0.5*czas));
			}
		
		if ((typ == "normalny")  ){
			return	cena= (float) ((17*czas));}
		
		
	return 0;
	};
	
		
		
		
		
		
	


	public String getTyp() {
		return typ;
	}


	public void setTyp(String typ) {
		this.typ = typ;
	}


	public int getCzas() {
		return czas;
	}


	public void setCzas(int czas) {
		this.czas = czas;
	}


	
	
	
}
