package pracownicy;

public class Pracownik {

	private int nrPracownika;
	private String imie;

	public int getNrPracownika() {
		return nrPracownika;
	}

	public void setNrPracownika(int nrPracownika) {
		this.nrPracownika = nrPracownika;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	private String nazwisko;
	private String adres;
	private String pesel;
	private String plec;
	private int wiek;
	private int czasPracy;
	private String stanowisko;
	private String zmiana;

	public Pracownik() {

	}

	public Pracownik(int nrPracownika, String imie, String nazwisko,
			String pesel) {
		this.nrPracownika = nrPracownika;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
	}

}
