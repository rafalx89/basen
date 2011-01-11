package Wejsciowki;
public class Rezerwacja {

	
	private int nrKasy;
	private String imieRezerwujacego;
	private String nazwiskoRezerwujacego;
	private String pesel;
	private String dataRezerwacji;
	private String godzinaRezerwacji;
	private String typ;
	
	public Rezerwacja(int nrKasy, String imieRezerwujacego,
			String nazwiskoRezerwujacego, String pesel, String dataRezerwacji,
			String godzinaRezerwacji,String typ, int czasPobytu, int iloscOsob, float cena) {
		super();
		this.nrKasy = nrKasy;
		this.imieRezerwujacego = imieRezerwujacego;
		this.nazwiskoRezerwujacego = nazwiskoRezerwujacego;
		this.pesel = pesel;
		this.dataRezerwacji = dataRezerwacji;
		this.godzinaRezerwacji = godzinaRezerwacji;
		this.typ=typ;
		this.czasPobytu = czasPobytu;
		this.iloscOsob = iloscOsob;
		this.cena = cena;
	}
	public String getDataRezerwacji() {
		return dataRezerwacji;
	}
	public void setDataRezerwacji(String dataRezerwacji) {
		this.dataRezerwacji = dataRezerwacji;
	}
	public String getGodzinaRezerwacji() {
		return godzinaRezerwacji;
	}
	public void setGodzinaRezerwacji(String godzinaRezerwacji) {
		this.godzinaRezerwacji = godzinaRezerwacji;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	private int czasPobytu;
	private int iloscOsob;
	private float cena;
	
	/*
	 * Usun¹³em adres bo po co nam adres rezerwuj¹cego?;p zostawi³em pesel
	 */
	
	
	
	public int getCzasPobytu() {
		return czasPobytu;
	}
	public void setCzasPobytu(int czasPobytu) {
		this.czasPobytu = czasPobytu;
	}
	public int getIloscOsob() {
		return iloscOsob;
	}
	public void setIloscOsob(int iloscOsob) {
		this.iloscOsob = iloscOsob;
	}
	public float getCena() {
		return cena;
	}
	public void setCena(float cena) {
		this.cena = cena;
	}
	
	public int getNrKasy() {
		return nrKasy;
	}
	public void setNrKasy(int nrKasy) {
		this.nrKasy = nrKasy;
	}
	public String getImieRezerwujacego() {
		return imieRezerwujacego;
	}
	public void setImieRezerwujacego(String imieRezerwujacego) {
		this.imieRezerwujacego = imieRezerwujacego;
	}
	public String getNazwiskoRezerwujacego() {
		return nazwiskoRezerwujacego;
	}
	public void setNazwiskoRezerwujacego(String nazwiskoRezerwujacego) {
		this.nazwiskoRezerwujacego = nazwiskoRezerwujacego;
	}
	
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	
	
	

	

}