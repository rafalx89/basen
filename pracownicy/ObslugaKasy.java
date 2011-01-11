package pracownicy;

import main.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.acl.LastOwnerException;
import java.text.*;

import java.util.*;

import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.*;

import javax.xml.crypto.Data;

import Wejsciowki.Bilet;
import Wejsciowki.Karnet;
import Wejsciowki.Rezerwacja;

import Cenniki.Cennik;
import Cenniki.CennikKarnetu;

public class ObslugaKasy extends Obsluga {

	public ObslugaKasy(String imie, String nazwisko, String adres,
			String pesel, String plec, int wiek, int czasPracy,
			String stanowisko, String zmiana, int przydzielonaKasa) {
		super(imie, nazwisko, adres, pesel, plec, wiek, czasPracy, stanowisko,
				zmiana);
		this.przydzielonaKasa = przydzielonaKasa;
	}

	private int przydzielonaKasa;

	// zmieni³em typ zmiennej przydzielonaKasa na int

	/**
	 * Metoda tworz¹ca obiekt klasy rezerwacja i wykorzystuj¹ca obiekt klasy
	 * BazaDanych do zapisu rezerwacji w bazie mysql
	 * 
	 * @return
	 */

	public void utworzRezerwacje(final int nrKasy) {

		final JTextField cena = new JTextField(4);
		cena.setEditable(false);

		final JFrame Rez = new JFrame("Rezerwacja");
		Rez.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Rez.setBounds(100, 100, 270, 400);
		Rez.setResizable(false);

		Rez.setLayout(new FlowLayout(FlowLayout.LEFT));

		Rez.show();

		JLabel etykietaImie = new JLabel("Podaj imiê: ");
		JLabel etykietaNazwisko = new JLabel("Podaj nazwisko: ");
		JLabel etykietaPesel = new JLabel("Podaj pesel: ");
		JLabel etykietaData = new JLabel("Podaj datê rezerwacji: ");
		JLabel etykietaGodzina = new JLabel("Podaj godzinê rezerwacji: ");

		Rez.add(etykietaImie);

		final JTextField imie = new JTextField(10);
		Rez.add(imie);

		Rez.add(etykietaNazwisko);
		final JTextField nazwisko = new JTextField(10);
		Rez.add(nazwisko);

		Rez.add(etykietaPesel);
		final JFormattedTextField pesel = new JFormattedTextField(NumberFormat
				.getIntegerInstance());
		pesel.setColumns(11);
		pesel.setValue(new Integer(0));
		Rez.add(pesel);

		Rez.add(etykietaData);

		final JFormattedTextField data = new JFormattedTextField(DateFormat
				.getDateInstance());
		Rez.add(data);
		data.setColumns(10);
		data.setValue(20110101);
		Rez.add(etykietaGodzina);

		final JFormattedTextField godzina = new JFormattedTextField(DateFormat
				.getTimeInstance());
		godzina.setColumns(10);
		godzina.setValue(240000);
		Rez.add(godzina);

		JLabel etykieta1 = new JLabel("Typ biletu:");
		Rez.add(etykieta1);

		final JComboBox lista = new JComboBox();
		lista.setEditable(false);
		lista.addItem("normalny");
		lista.addItem("ulgowy");
		lista.addItem("grupowy");
		Rez.add(lista);

		final Cennik cenaRezerwacji = new Cennik();

		lista.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cenaRezerwacji.setTyp((String) (lista.getSelectedItem()));
			}
		});

		JLabel etykieta = new JLabel("WprowadŸ iloœæ osób:");

		final JFormattedTextField ilosc = new JFormattedTextField(NumberFormat
				.getIntegerInstance());
		ilosc.setColumns(2);
		ilosc.setValue(new Integer(0));

		Rez.add(etykieta);
		Rez.add(ilosc);

		ilosc.setEditable(true);

		JLabel etykieta2 = new JLabel("Przewidywany czas pobytu(w minutach):");
		Rez.add(etykieta2);

		final JFormattedTextField czasPobytu = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		czasPobytu.setColumns(2);
		czasPobytu.setValue(new Integer(0));

		Rez.add(czasPobytu);

		JLabel etykietaCena = new JLabel("Cena:");
		Rez.add(etykietaCena);
		Rez.add(cena);

		JLabel etykietaZl = new JLabel("z³");
		Rez.add(etykietaZl);

		JButton sprawdzCene = new JButton("SprawdŸ cene");
		sprawdzCene.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cenaRezerwacji.setIloscOsob(Integer.parseInt(ilosc.getText()));
				cenaRezerwacji
						.setCzas(Integer.parseInt((czasPobytu.getText())));

				cena.setText(Float.toString(cenaRezerwacji.cena()));

			}
		});
		Rez.add(sprawdzCene);

		JButton zatwierdz = new JButton("Utwórz");
		Rez.add(zatwierdz);

		zatwierdz.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				BazaDanych bd = new BazaDanych();
				cenaRezerwacji.setIloscOsob(Integer.parseInt(ilosc.getText()));
				cenaRezerwacji
						.setCzas(Integer.parseInt((czasPobytu.getText())));
				cena.setText(Float.toString(cenaRezerwacji.cena()));

				System.out.println(bd.sprawdzWolneMiejsca() + " "
						+ cenaRezerwacji.getIloscOsob());
				// trzeab by zrobiæ jakoœ sprawdzanie miejsc w przysz³oœæ;p nie
				// wiem jak to inaczej nazwaæ

				if (cenaRezerwacji.getCzas() != 0
						&& cenaRezerwacji.getIloscOsob() != 0) {

					Rezerwacja r = new Rezerwacja(nrKasy, imie.getText(),
							nazwisko.getText(), pesel.getText(),
							data.getText(), godzina.getText(), cenaRezerwacji
									.getTyp(), cenaRezerwacji.getCzas(),
							cenaRezerwacji.getIloscOsob(), cenaRezerwacji
									.cena());
					bd.zapiszRezerwacje(r);
					JOptionPane.showMessageDialog(null, "ZAPISANO REZERWACJE!");

				}

				else {
					JOptionPane.showMessageDialog(null,
							"Z³e parametry! SprawdŸ wprowadzone dane!");

				}

				;

			}
		});

		JButton anuluj = new JButton("Zakoñcz");
		anuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Rez.dispose();
			}
		});
		Rez.add(anuluj);

	}

	/*
	 * Metoda tworz¹ca bilet i zapisuj¹ca go do bazy za pomoc¹ obiektu z klasy
	 * Bazadanych
	 */

	public void utworzBilet(final int nrKasy) {

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		DateFormat timeFormat = new SimpleDateFormat("HHmmss");
		Date date = new Date();

		final int dataWystawienia = Integer.parseInt(dateFormat.format(date));
		final int godzinaWystawienia = Integer
				.parseInt(timeFormat.format(date));

		final JTextField cena = new JTextField(4);
		cena.setEditable(false);

		final JFrame Rbilet = new JFrame("bilet");
		Rbilet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Rbilet.setBounds(100, 100, 300, 400);
		Rbilet.setResizable(false);

		Rbilet.setLayout(new FlowLayout(FlowLayout.LEADING));

		Rbilet.show();

		JLabel etykietaCena = new JLabel("Cena:");
		Rbilet.add(etykietaCena);
		Rbilet.add(cena);

		JLabel etykietaZl = new JLabel("z³");
		Rbilet.add(etykietaZl);

		JLabel etykieta1 = new JLabel("Typ biletu:");
		Rbilet.add(etykieta1);

		final JComboBox lista = new JComboBox();
		lista.setEditable(false);
		lista.addItem("normalny");
		lista.addItem("ulgowy");
		lista.addItem("grupowy");
		Rbilet.add(lista);

		final Cennik cenaBiletu = new Cennik();

		lista.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cenaBiletu.setTyp((String) (lista.getSelectedItem()));
			}
		});

		JLabel etykieta = new JLabel("WprowadŸ iloœæ osób:");

		final JFormattedTextField ilosc = new JFormattedTextField(NumberFormat
				.getIntegerInstance());
		ilosc.setColumns(2);
		ilosc.setValue(new Integer(0));

		Rbilet.add(ilosc, FlowLayout.LEFT);
		Rbilet.add(etykieta, FlowLayout.LEFT);
		ilosc.setEditable(true);

		JLabel etykieta2 = new JLabel("Przewidywany czas pobytu(w minutach):");
		Rbilet.add(etykieta2);

		final JFormattedTextField czasPobytu = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		czasPobytu.setColumns(2);
		czasPobytu.setValue(new Integer(0));

		Rbilet.add(czasPobytu);

		JButton sprawdzCene = new JButton("SprawdŸ cene");
		sprawdzCene.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cenaBiletu.setIloscOsob(Integer.parseInt(ilosc.getText()));
				cenaBiletu.setCzas(Integer.parseInt((czasPobytu.getText())));

				System.out
						.println(cenaBiletu.getCzas() + " "
								+ cenaBiletu.getIloscOsob() + " "
								+ cenaBiletu.getTyp());

				cena.setText(Float.toString(cenaBiletu.cena()));

			}
		});
		Rbilet.add(sprawdzCene);

		JButton zatwierdz = new JButton("Utwórz");
		Rbilet.add(zatwierdz);

		zatwierdz.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				BazaDanych bd = new BazaDanych();
				cenaBiletu.setIloscOsob(Integer.parseInt(ilosc.getText()));
				cenaBiletu.setCzas(Integer.parseInt((czasPobytu.getText())));
				cena.setText(Float.toString(cenaBiletu.cena()));

				System.out.println(bd.sprawdzWolneMiejsca() + " "
						+ cenaBiletu.getIloscOsob());
				if (bd.sprawdzWolneMiejsca() < cenaBiletu.getIloscOsob())
					JOptionPane.showMessageDialog(null, "BRAK MIEJSC!");
				else {

					if (cenaBiletu.getCzas() != 0
							&& cenaBiletu.getIloscOsob() != 0) {

						Bilet b = new Bilet(dataWystawienia,
								godzinaWystawienia, cenaBiletu.getCzas(),
								cenaBiletu.cena(), cenaBiletu.getTyp(), nrKasy,
								cenaBiletu.getIloscOsob());
						bd.dodajBilet(b);
						JOptionPane.showMessageDialog(null, "ZAPISANO BILET!");

					}

					else {
						JOptionPane.showMessageDialog(null,
								"Z³e parametry! SprawdŸ wprowadzone dane!");

					}
				}
				;

			}
		});

		JButton anuluj = new JButton("Zakoñcz");
		anuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Rbilet.dispose();
			}
		});
		Rbilet.add(anuluj);

	}

	/*
	 * Metoda tworz¹ca karnet
	 */
	public void utworzKarnet(final int nrKasy) {

		String DATE_FORMAT = "yyyyMMdd";
		final SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		final Calendar c1 = Calendar.getInstance();
		// System.out.println(sdf.format(c1.getTime()));

		final int terminWaznosci = 0;

		final JTextField cena = new JTextField(4);
		cena.setEditable(false);

		final JFrame Rkarnet = new JFrame("Karnet");
		Rkarnet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Rkarnet.setBounds(100, 100, 300, 400);
		Rkarnet.setResizable(false);

		Rkarnet.setLayout(new FlowLayout(FlowLayout.LEADING));

		Rkarnet.show();

		JLabel etykietaCena = new JLabel("Cena:");
		Rkarnet.add(etykietaCena);
		Rkarnet.add(cena);

		JLabel etykietaZl = new JLabel("z³");
		Rkarnet.add(etykietaZl);

		JLabel etykieta1 = new JLabel("Typ biletu:");
		Rkarnet.add(etykieta1);

		final JComboBox lista = new JComboBox();
		lista.setEditable(false);
		lista.addItem("normalny");
		lista.addItem("ulgowy");
		Rkarnet.add(lista);

		final CennikKarnetu cenaKarnetu = new CennikKarnetu();

		lista.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cenaKarnetu.setTyp((String) (lista.getSelectedItem()));
			}
		});

		JLabel etykieta2 = new JLabel("Podaj czas wa¿noœci karnetu( dni ):");
		Rkarnet.add(etykieta2);

		final JFormattedTextField czasWaznosci = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		czasWaznosci.setColumns(2);
		czasWaznosci.setValue(new Integer(0));

		Rkarnet.add(czasWaznosci);

		JButton sprawdzCene = new JButton("SprawdŸ cene");
		sprawdzCene.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				cenaKarnetu.setCzas(Integer.parseInt(czasWaznosci.getText()));
				cenaKarnetu.setTyp((String) (lista.getSelectedItem()));

				System.out.println(cenaKarnetu.getCzas() + " "

				+ cenaKarnetu.getTyp());

				cena.setText(Float.toString(cenaKarnetu.cena()));

			}
		});
		Rkarnet.add(sprawdzCene);

		JButton zatwierdz = new JButton("Utwórz");
		Rkarnet.add(zatwierdz);

		zatwierdz.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				BazaDanych bd = new BazaDanych();

				cena.setText(Float.toString(cenaKarnetu.cena()));

				if (bd.sprawdzWolneKarnety() == 0)
					JOptionPane.showMessageDialog(null, "BRAK MIEJSC!");
				else {

					if (cenaKarnetu.getCzas() != 0) {

						c1.add(Calendar.DAY_OF_MONTH, Integer
								.parseInt(czasWaznosci.getText()));

						Karnet k = new Karnet(Integer.parseInt(sdf.format(c1
								.getTime())), cenaKarnetu.getTyp(), cenaKarnetu
								.cena(), nrKasy);

						bd.dodajKarnet(k);
						JOptionPane.showMessageDialog(null, "ZAPISANO KARNET!");

					}

					else {
						JOptionPane.showMessageDialog(null,
								"Z³e parametry! SprawdŸ wprowadzone dane!");

					}
				}
				;

			}
		});

		JButton anuluj = new JButton("Zakoñcz");
		anuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Rkarnet.dispose();
			}
		});
		Rkarnet.add(anuluj);
	}

}