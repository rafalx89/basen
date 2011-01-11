package Wejsciowki;
public class Bilet {

	
	private int  dataWystawienia;
	private int godzinaWystawienia;
	private int czasPobytu;
	private float cena;
	private String typ;
	private int nrKasy;
	private int iloscOsob;
	
	//doda³em pole numer kasy
	//zmiana long w nrBiletu na int
	
	
	public Bilet( int dataWystawienia, int godzinaWystawienia, int czasPobytu,
			float cena, String typ, int nrKasy,int iloscOsob) {
		super();
	
		this.czasPobytu=czasPobytu;
		this.dataWystawienia = dataWystawienia;
		this.godzinaWystawienia = godzinaWystawienia;
		this.cena = cena;
		this.typ = typ;
		this.nrKasy = nrKasy;
		this.iloscOsob=iloscOsob;
	}
	
	
	
	public int getIloscOsob() {
		return iloscOsob;
	}



	public void setIloscOsob(int iloscOsob) {
		this.iloscOsob = iloscOsob;
	}



	public int getNrKasy() {
		return nrKasy;
	}



	public void setNrKasy(int nrKasy) {
		this.nrKasy = nrKasy;
	}



	public int getCzasPobytu() {
		return czasPobytu;
	}

	public void setCzasPobytu(int czasPobytu) {
		this.czasPobytu = czasPobytu;
	}
	public int getDataWystawienia() {
		return dataWystawienia;
	}
	public void setDataWystawienia(int dataWystawienia) {
		this.dataWystawienia = dataWystawienia;
	}
	public int getGodzinaWystawienia() {
		return godzinaWystawienia;
	}
	public void setGodzinaWystawienia(int godzinaWystawienia) {
		this.godzinaWystawienia = godzinaWystawienia;
	}
	public float getCena() {
		return cena;
	}
	public void setCena(float cena) {
		this.cena = cena;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}

}