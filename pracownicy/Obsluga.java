package pracownicy;

import main.*;

public class Obsluga {

	protected String imie;
	protected String nazwisko;
	protected String adres;
	protected String pesel;
	protected String plec;
	protected int wiek;
	protected int czasPracy;
	protected String stanowisko;
	protected String zmiana;

	public Obsluga() {

	}

	public Obsluga(String imie, String nazwisko, String adres, String pesel,
			String plec, int wiek, int czasPracy, String stanowisko,
			String zmiana) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.adres = adres;
		this.pesel = pesel;
		this.plec = plec;
		this.wiek = wiek;
		this.czasPracy = czasPracy;
		this.stanowisko = stanowisko;
		this.zmiana = zmiana;
	}

	/**
	 * 
	 * @return
	 */
	protected Raport sporzadzRaport() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	protected void przekazRaport() {
		throw new UnsupportedOperationException();
	}

}