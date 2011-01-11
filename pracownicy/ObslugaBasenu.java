package pracownicy;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

import main.BazaDanych;

public class ObslugaBasenu extends Obsluga {
	public ObslugaBasenu(String imie, String nazwisko) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
	}

	public ObslugaBasenu(String imie, String nazwisko, String adres,
			String pesel, String plec, int wiek, int czasPracy,
			String stanowisko, String zmiana) {

		super(imie, nazwisko, adres, pesel, plec, wiek, czasPracy, stanowisko,
				zmiana);

	}

	private String imie;
	private String nazwisko;
	private String adres;
	private String pesel;
	private String plec;
	private int wiek;
	private int czasPracy;
	private String stanowisko;
	private String zmiana;
	private String haslo;

	public void napiszRaport() {
		final JFrame dodajRp = new JFrame("Dodaj raport");
		dodajRp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dodajRp.setBounds(100, 100, 300, 400);

		JButton zatwierdz = new JButton("Zatwierdz");
		JButton anuluj = new JButton("Anuluj");

		JLabel temat = new JLabel("Temat: ");
		final JTextField ftemat = new JTextField();

		JLabel wypelniajaca = new JLabel("Osoba wypelniajaca: ");
		JLabel osobaWypelniajaca = new JLabel(this.getImie() + " "
				+ this.getNazwisko());

		JLabel tresc = new JLabel("Tresc:");
		final JTextArea txt = new JTextArea();
		JScrollPane text = new JScrollPane(txt);

		Container gora = new Container();
		gora.setLayout(new GridLayout(2, 2, 2, 2));
		gora.add(wypelniajaca);
		gora.add(osobaWypelniajaca);
		gora.add(temat);
		gora.add(ftemat);

		Container srodek = new Container();
		srodek.setLayout(new BorderLayout());
		srodek.add(tresc, "North");
		srodek.add(text, "Center");
		Container dol = new Container();
		dol.setLayout(new FlowLayout());
		dol.add(zatwierdz);
		dol.add(anuluj);

		dodajRp.setLayout(new BorderLayout());
		dodajRp.add(gora, "North");
		dodajRp.add(srodek, "Center");
		dodajRp.add(dol, "South");
		dodajRp.show();

		anuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dodajRp.dispose();

			}

		});

		zatwierdz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date dt = new Date();
				int rok = 1900 + dt.getYear();
				int miesiac = dt.getMonth();
				String miesiac1 = "" + miesiac;
				if (miesiac < 10) {
					miesiac1 = "0" + miesiac;
				}
				int dzien = dt.getDate();
				String dzien1 = "" + dzien;
				if (dzien < 10) {
					dzien1 = "0" + dzien;
				}

				String data = "" + rok + "-" + miesiac1 + "-" + dzien1;
				String autor = getImie() + " " + getNazwisko();
				String tytul = ftemat.getText();
				String text = txt.getText();

				BazaDanych bd = new BazaDanych();
				if (text.equals("") || tytul.equals("")) {
					JOptionPane.showMessageDialog(null,
							"Wype³nij wszystkie pola.");
				} else {
					boolean spr = bd.wyslijRaport(tytul, text, autor, data);
					if (spr == true) {
						JOptionPane.showMessageDialog(null, "Zapisano raport.");
						dodajRp.dispose();

					} else {
						JOptionPane.showMessageDialog(null,
								"Napotkano jakieœ b³êdy, spróbuj jeszcze raz.");

					}
				}

			}

		});

	}

	public String getImie() {
		return this.imie;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	/**
	 * 
	 * @return
	 */
	public void sprawdzStanBasenu() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void uruchomNatryski() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void wyporzyczSprzet() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void wlaczOswietlenie() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void wylaczOswietlenie() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void sprzatajBasen() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void wlaczOgrzewanie() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void wylaczOgrzewanie() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public void opieka() {
		throw new UnsupportedOperationException();
	}

}