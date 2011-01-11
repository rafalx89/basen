package main;

public class Rachunek {

	private int nrRachunku;
	private int nrKasy;
	private float kwota;
	private String nabywca;
	private String usluga;
	
	//dodoa³em pole nrKasy
	//zmiana long na int w nrRachunku
	
	
	public Rachunek(int nrRachunku, int nrKasy, float kwota, String nabywca,
			String usluga) {
		super();
		this.nrRachunku = nrRachunku;
		this.nrKasy = nrKasy;
		this.kwota = kwota;
		this.nabywca = nabywca;
		this.usluga = usluga;
	}

	
	
	public int getNrRachunku() {
		return nrRachunku;
	}

	public void setNrRachunku(int nrRachunku) {
		this.nrRachunku = nrRachunku;
	}

	public int getNrKasy() {
		return nrKasy;
	}

	public void setNrKasy(int nrKasy) {
		this.nrKasy = nrKasy;
	}

	public float getKwota() {
		return kwota;
	}

	public void setKwota(float kwota) {
		this.kwota = kwota;
	}

	public String getNabywca() {
		return nabywca;
	}

	public void setNabywca(String nabywca) {
		this.nabywca = nabywca;
	}

	public String getUsluga() {
		return usluga;
	}

	public void setUsluga(String usluga) {
		this.usluga = usluga;
	}

	
	
	
	
}