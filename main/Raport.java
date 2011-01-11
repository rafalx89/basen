package main;

import java.awt.Container;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Raport {

	BazaDanych Zapisuje;
	private int nrRaportu;
	private String autor;
	private String dataRaportu;
	private String tematRaportu;
	private String trescRaportu;
	private String uwagiZarzadcy;
	private int aktywny;

	public Raport() {

	}

	public Raport(int nrRaportu, String autor, String dataRaportu,
			String tematRaportu, String trescRaportu, String uwagi) {
		this.uwagiZarzadcy = uwagi;
		this.nrRaportu = nrRaportu;
		this.autor = autor;
		this.dataRaportu = dataRaportu;
		this.tematRaportu = tematRaportu;
		this.trescRaportu = trescRaportu;
	}

	private ArrayList<Raport> lista_raportow = new ArrayList<Raport>();

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Raport> czytajRaport() throws SQLException {
		BazaDanych bd = new BazaDanych();
		ResultSet raporty = bd.odbierzRaport();
		while (raporty.next()) {
			int nr = raporty.getInt("nrRaportu");
			String tytul = raporty.getString("tytul");
			String trescRaportu = raporty.getString("zawartoscRaportu");
			String autor = raporty.getString("osobaWypelniajaca");
			String dataRaportu = raporty.getString("dataRaportu");
			String uwagi = raporty.getString("uwagiZarzadcy");
			Raport r = new Raport(nr, autor, dataRaportu, tytul, trescRaportu,
					uwagi);
			lista_raportow.add(r);
		}
		raporty.close();
		return lista_raportow;

	}

	public void zapisz_zmiany(int nrR, String uwagi, int przeczytany) {
		new BazaDanych().edytujRaport(nrR, uwagi, przeczytany);

	}

	public String getUwagi() {
		return uwagiZarzadcy;
	}

	public int getNrRaportu() {
		return nrRaportu;
	}

	public String getAutor() {
		return this.autor;
	}

	public String getDataRaportu() {
		return this.dataRaportu;
	}

	public String getTematRaportu() {
		return this.tematRaportu;
	}

	public String getTrescRaportu() {
		return this.trescRaportu;
	}

	public void setUwagi(String uwagi) {
		this.uwagiZarzadcy = uwagi;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setDataRaportu(String data) {

		this.dataRaportu = data;
	}

}