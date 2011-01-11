package Wejsciowki;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Karnet {

	private int terminWaznosci;
	private String typ;
	private float cena;
	private int nrKasy;
	private int iloscOsob;
	
	//doda³em pole numer kasy
	//zmiana long w nrKarnet na int
	

	public Karnet( int terminWaznosci, String typ, float cena,
			int nrKasy) {
		super();
		
	
	
		this.terminWaznosci = terminWaznosci;
		this.typ = typ;
		this.cena = cena;
		this.nrKasy = nrKasy;
		
	}
	
	
	
	

	
	



	public int getNrKasy() {
		return nrKasy;
	}







	public void setNrKasy(int nrKasy) {
		this.nrKasy = nrKasy;
	}


	
	public int getTerminWaznosci() {
		return terminWaznosci;
	}
	
	public void setTerminWaznosci(int terminWaznosci) {
		this.terminWaznosci = terminWaznosci;
	}
	
	public String getTyp() {
		return typ;
	}
	
	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	public float getCena() {
		return cena;
	}
	
	public void setCena(float cena) {
		this.cena = cena;
	}

}