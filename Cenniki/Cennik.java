package Cenniki;

public class Cennik {

	private String typ="normalny";
	private int czas=0,iloscOsob=0;
	
	
	
	public Cennik() {
		// TODO Auto-generated constructor stub
	}


	public float cena(){
		 float cena;
		if ((typ == "ulgowy")  ){
			return	cena= (float) ((0.16*0.5*czas)*iloscOsob);
			}
		
		if ((typ == "normalny")  ){
			return	cena= (float) ((0.16*czas)*iloscOsob);}
		
		if ((typ == "grupowy") ){
			return cena= (float) ((0.16*czas)/(iloscOsob/2));}
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


	public int getIloscOsob() {
		return iloscOsob;
	}


	public void setIloscOsob(int iloscOsob) {
		this.iloscOsob = iloscOsob;
	};
	
	
	
	
}
