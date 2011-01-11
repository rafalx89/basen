package pracownicy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import main.BazaDanych;
import main.Raport;

public class Zarzadca {

	private String imie;
	private String nazwisko;
	private String adres;
	private String pesel;
	private String plec;
	private int wiek;
	private String stanowisko;
	private int czasPracy;
	private String zmiana;
	private String haslo;

	public Zarzadca(String imie, String nazwisko, String adres, String pesel,
			String plec, int wiek, int czasPracy, String stanowisko,
			String zmiana, String haslo) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.adres = adres;
		this.pesel = pesel;
		this.plec = plec;
		this.wiek = wiek;
		this.stanowisko = stanowisko;
		this.czasPracy = czasPracy;
		this.zmiana = zmiana;
		this.haslo = haslo;

	}

	public Zarzadca() {

	}

	public void usunPracownika() {
		final JFrame usunPr = new JFrame("Zwolnij pracownika.");

		usunPr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		usunPr.setBounds(100, 100, 600, 400);
		usunPr.setLayout(new GridLayout(1, 1));
		final BazaDanych bd = new BazaDanych();
		final ArrayList<Pracownik> pracownicy = bd.pobierzPracownikow();

		JLabel nr_pracownika = new JLabel("Nr pracownika");
		JLabel imie = new JLabel("Imie");
		JLabel nazwisko = new JLabel("Nazwisko");
		JLabel pesel = new JLabel("Pesel");
		JLabel pusty = new JLabel("");

		Container tabelka = new Container();
		tabelka.setLayout(new GridLayout(pracownicy.size() + 1, 5, 5, 5));

		tabelka.add(nr_pracownika);
		tabelka.add(imie);
		tabelka.add(nazwisko);
		tabelka.add(pesel);
		tabelka.add(pusty);

		for (int i = 0; i < pracownicy.size(); i++) {
			final int j = i;
			JLabel nr = new JLabel("" + pracownicy.get(i).getNrPracownika());
			JLabel imie1 = new JLabel(pracownicy.get(i).getImie());
			JLabel nazwisko1 = new JLabel(pracownicy.get(i).getNazwisko());
			JLabel pesel1 = new JLabel(pracownicy.get(i).getPesel());
			JButton usun = new JButton("Usun");

			usun.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					boolean spr = bd.usunPracownika(pracownicy.get(j)
							.getNrPracownika());
					if (spr == true) {
						JOptionPane.showMessageDialog(null,
								"Zwolniono Pracownika");
						usunPr.dispose();
						usunPracownika();
					} else {
						JOptionPane.showMessageDialog(null, "B³¹d");

					}

				}

			});
			Border thickBorder = new LineBorder(Color.BLUE, 1);
			nr.setBorder(thickBorder);
			imie1.setBorder(thickBorder);
			nazwisko1.setBorder(thickBorder);
			pesel1.setBorder(thickBorder);

			tabelka.add(nr);
			tabelka.add(imie1);
			tabelka.add(nazwisko1);
			tabelka.add(pesel1);
			tabelka.add(usun);

		}
		Container cn = new Container();
		cn.setLayout(new FlowLayout());
		cn.add(tabelka);
		JScrollPane pl = new JScrollPane(cn);
		// JScrollBar br = new JScrollBar(1, 50, 1, 1, 100);
		// pl.setVerticalScrollBar(br);

		usunPr.add(pl);
		usunPr.setResizable(false);

		usunPr.show();

	}

	/**
	 * Metoda do dodawania nowych pracowników
	 * 
	 * @throws ParseException
	 */
	public void dodajPracownika() throws ParseException {

		/*
		 * Tworzenie instancji okna
		 */
		final JFrame dodajPr = new JFrame("Dodaj pracownika");
		dodajPr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dodajPr.setBounds(100, 100, 300, 400);
		// dodajPr.setLayout(new FlowLayout(FlowLayout.LEFT));
		dodajPr.setLayout(new GridLayout(11, 1));

		Container c1 = new Container();
		c1.setLayout(new FlowLayout(FlowLayout.LEFT));
		Container c2 = new Container();
		c2.setLayout(new FlowLayout(FlowLayout.LEFT));
		Container c3 = new Container();
		c3.setLayout(new FlowLayout(FlowLayout.LEFT));
		Container c4 = new Container();
		c4.setLayout(new FlowLayout(FlowLayout.LEFT));
		Container c5 = new Container();
		c5.setLayout(new FlowLayout(FlowLayout.LEFT));
		Container c6 = new Container();
		c6.setLayout(new FlowLayout(FlowLayout.LEFT));
		Container c7 = new Container();
		c7.setLayout(new FlowLayout(FlowLayout.LEFT));
		Container c8 = new Container();
		c8.setLayout(new FlowLayout(FlowLayout.LEFT));
		Container c9 = new Container();
		c9.setLayout(new FlowLayout(FlowLayout.LEFT));
		Container c10 = new Container();
		c10.setLayout(new FlowLayout(FlowLayout.LEFT));
		Container c11 = new Container();
		c11.setLayout(new FlowLayout(FlowLayout.LEFT));

		/*
		 * Tworzenie komponentow
		 */
		// Tworzenie komponentów z polami tekstowymi i wyboru
		final JTextField t_imie = new JTextField(10);
		final JTextField t_nazwisko = new JTextField(10);
		final JTextField t_adres = new JTextField(10);
		final JComboBox t_wiek = new JComboBox();
		final JComboBox t_stanowisko = new JComboBox();
		final JComboBox t_plec = new JComboBox();
		final JFormattedTextField t_pesel;
		final JComboBox t_czasPracy = new JComboBox();
		final JComboBox t_zmiana = new JComboBox();
		final JPasswordField t_haslo = new JPasswordField(10);

		// Tworzenie przycisków do zatwierdzania i anulowania
		JButton zatwierdz = new JButton("Zatwierdz");
		JButton anuluj = new JButton("Anuluj");

		// Tworzenie etykiet do opisu pol tekstowych
		JLabel etykietaImie = new JLabel("Podaj imiê: ");
		JLabel etykietaNazwisko = new JLabel("Podaj nazwisko: ");
		JLabel etykietaPesel = new JLabel("Podaj pesel: ");
		JLabel etykietaWiek = new JLabel("Podaj wiek: ");
		JLabel etykietaAdres = new JLabel("Podaj adres: ");
		JLabel etykietaPlec = new JLabel("Wybierz plec: ");
		JLabel etykietaZmiana = new JLabel("Wybierz zmiane: ");
		JLabel etykietaStanowisko = new JLabel("Wybierz stanowisko: ");
		JLabel etykietaCzasPracy = new JLabel("Podaj czas pracy: ");
		JLabel etykietaHaslo = new JLabel("Podaj haslo: ");

		/*
		 * opcje dla odpowiednich komponentow
		 */

		// stanowsko
		t_stanowisko.setEditable(false);
		t_stanowisko.addItem("zarzadca");
		t_stanowisko.addItem("kasjer");
		t_stanowisko.addItem("obsluga basenu");
		// plec
		t_plec.setEditable(false);
		t_plec.addItem("M");
		t_plec.addItem("K");
		// zmiana
		t_zmiana.setEditable(false);
		t_zmiana.addItem("pierwsza");
		t_zmiana.addItem("druga");
		t_zmiana.addItem("nocna");

		// czasPracy
		t_czasPracy.setEditable(false);
		for (int i = 1; i < 24; i++) {
			t_czasPracy.addItem("" + i + "");
		}
		// wiek
		t_wiek.setEditable(false);
		for (int i = 15; i < 75; i++) {
			t_wiek.addItem("" + i + "");
		}

		// ///////
		MaskFormatter formatter1;

		formatter1 = new MaskFormatter("###########");
		formatter1.setValidCharacters("0123456789");
		t_pesel = new JFormattedTextField(formatter1);
		t_pesel.setMinimumSize(new Dimension(150, 20));
		t_pesel.setPreferredSize(new Dimension(150, 20));
		t_pesel.setMaximumSize(new Dimension(150, 20));

		/*
		 * Dodawanie komponentow do okna dodajPr
		 */
		c1.add(etykietaImie);
		c1.add(t_imie);
		c2.add(etykietaNazwisko);
		c2.add(t_nazwisko);
		c3.add(etykietaPesel);
		c3.add(t_pesel);
		c4.add(etykietaWiek);
		c4.add(t_wiek);
		c5.add(etykietaAdres);
		c5.add(t_adres);
		c6.add(etykietaPlec);
		c6.add(t_plec);
		c7.add(etykietaStanowisko);
		c7.add(t_stanowisko);
		c8.add(etykietaZmiana);
		c8.add(t_zmiana);
		c9.add(etykietaCzasPracy);
		c9.add(t_czasPracy);
		c10.add(etykietaHaslo);
		c10.add(t_haslo);
		c11.add(zatwierdz);
		c11.add(anuluj);

		dodajPr.add(c1);
		dodajPr.add(c2);
		dodajPr.add(c3);
		dodajPr.add(c4);
		dodajPr.add(c5);
		dodajPr.add(c6);
		dodajPr.add(c7);
		dodajPr.add(c8);
		dodajPr.add(c9);
		dodajPr.add(c10);
		dodajPr.add(c11);
		dodajPr.setResizable(false);
		/*
		 * ActionListenery dla poszczególnych komponentów
		 */
		// ActionListener dla przycisku anuluj
		anuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dodajPr.dispose();
			}
		});

		// ActionListener dla przycisku zatwierdz
		zatwierdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// atrybuty nowego pracownika
				imie = t_imie.getText();
				nazwisko = t_nazwisko.getText();
				adres = t_adres.getText();
				plec = (String) t_plec.getSelectedItem();
				wiek = Integer.parseInt((String) t_wiek.getSelectedItem());
				pesel = t_pesel.getText();

				czasPracy = Integer.parseInt((String) t_czasPracy
						.getSelectedItem());
				stanowisko = (String) t_stanowisko.getSelectedItem();
				zmiana = (String) t_zmiana.getSelectedItem();
				haslo = t_haslo.getText();
				BazaDanych baza_danych = new BazaDanych();
				Zarzadca z = new Zarzadca(imie, nazwisko, adres, pesel, plec,
						wiek, czasPracy, stanowisko, zmiana, haslo);
				boolean spr = baza_danych.zapiszPracownika(z);

				if (spr == true) {
					JOptionPane.showMessageDialog(null, "ZAPISANO PRACOWNIKA.");
					dodajPr.dispose();
				} else
					JOptionPane.showMessageDialog(null,
							"Napotkano jakieœ b³êdy, spróbuj jeszcze raz.");
			}
		});

		/*
		 * Wyswietlenie okna
		 */
		dodajPr.show();

	}

	/**
	 * 
	 * @return
	 */
	final JFrame czytajRaporty = new JFrame("Czytaj Raporty");

	public void odbierzRaport() {
		ArrayList<Raport> lista_raportow = null;
		Raport r = new Raport();
		try {
			lista_raportow = r.czytajRaport();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// czytajRaporty.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		czytajRaporty.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		czytajRaporty.setBounds(100, 100, 600, 400);
		czytajRaporty.setLayout(new GridLayout(1, 1));

		JLabel autor = new JLabel("Autor");
		JLabel temat = new JLabel("Temat");
		JLabel czysty = new JLabel("");

		Container tabelka = new Container();
		tabelka.setLayout(new GridLayout(lista_raportow.size() + 1, 3, 5, 5));
		tabelka.add(temat);
		tabelka.add(autor);
		tabelka.add(czysty);

		for (int i = 0; i < lista_raportow.size(); i++) {
			final int j = i;
			final Raport tmp = lista_raportow.get(j);
			JLabel autor1 = new JLabel(lista_raportow.get(i).getAutor());
			JLabel temat1 = new JLabel(lista_raportow.get(i).getTematRaportu());
			JButton pokaz = new JButton("Pokaz");
			pokaz.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					pokazWybranyRaport(tmp);

				}

			});
			Border thickBorder = new LineBorder(Color.BLUE, 1);
			autor1.setBorder(thickBorder);
			temat1.setBorder(thickBorder);

			tabelka.add(temat1);
			tabelka.add(autor1);
			tabelka.add(pokaz);

		}
		Container cn = new Container();
		cn.setLayout(new FlowLayout());
		cn.add(tabelka);
		JScrollPane pl = new JScrollPane(cn);
		// JScrollBar br = new JScrollBar(1, 50, 1, 1, 100);
		// pl.setVerticalScrollBar(br);

		czytajRaporty.add(pl);
		czytajRaporty.setResizable(false);
		czytajRaporty.show();

	}

	private void pokazWybranyRaport(Raport r) {

		final Raport raport = r;

		final JFrame czytajWybrany = new JFrame("Czytaj Wybrany Raport");
		// czytajRaporty.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		czytajWybrany.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		czytajWybrany.setBounds(100, 100, 400, 400);
		czytajWybrany.setLayout(new BorderLayout());

		JLabel autor = new JLabel("Autor:");
		JLabel temat = new JLabel("Temat:");
		JLabel data = new JLabel("Dnia:");
		JLabel autor1 = new JLabel(r.getAutor());
		JLabel temat1 = new JLabel(r.getTematRaportu());
		JLabel data1 = new JLabel(r.getDataRaportu());
		final JTextArea uw = new JTextArea(raport.getUwagi());
		JScrollPane uwagi = new JScrollPane(uw);
		JButton anuluj = new JButton("anuluj");
		JButton zapisz_zmiany = new JButton("zapisz zmiany");
		JTextArea tekst = new JTextArea(r.getTrescRaportu());
		tekst.setEditable(false);
		JScrollPane tekst_raportu = new JScrollPane(tekst);

		Container wstep = new Container();
		Container czytane = new Container();
		Container koniec = new Container();

		wstep.setLayout(new GridLayout(3, 2, 2, 2));
		wstep.add(autor);
		wstep.add(autor1);
		wstep.add(temat);
		wstep.add(temat1);
		wstep.add(data);
		wstep.add(data1);

		czytane.setLayout(new GridLayout(2, 1));
		Container ctekst_raportu = new Container();
		ctekst_raportu.setLayout(new BorderLayout());
		ctekst_raportu.add(new JLabel("Tresc:"), "North");
		ctekst_raportu.add(tekst_raportu, "Center");
		czytane.add(ctekst_raportu);

		Container cuwagi = new Container();
		cuwagi.setLayout(new BorderLayout());
		cuwagi.add(new JLabel("Dodaj uwagi:"), "North");
		cuwagi.add(uwagi, "Center");
		czytane.add(cuwagi);

		koniec.setLayout(new FlowLayout(FlowLayout.LEFT));
		final JCheckBox cb = new JCheckBox("Oznacz jako przeczytany");
		koniec.add(cb);
		koniec.add(zapisz_zmiany);
		koniec.add(anuluj);

		czytajWybrany.setLayout(new BorderLayout());
		czytajWybrany.add(wstep, "North");
		czytajWybrany.add(czytane, "Center");
		czytajWybrany.add(koniec, "South");

		anuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				czytajWybrany.dispose();

			}

		});
		zapisz_zmiany.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cb.isSelected()) {
					new Raport().zapisz_zmiany(raport.getNrRaportu(), uw
							.getText(), 1);
					JOptionPane.showMessageDialog(null, "Zapisano");
					czytajWybrany.dispose();
					czytajRaporty.dispose();
				} else {
					new Raport().zapisz_zmiany(raport.getNrRaportu(), uw
							.getText(), 0);
					JOptionPane.showMessageDialog(null, "Zapisano");
					czytajWybrany.dispose();
					czytajRaporty.dispose();

				}

			}

		});

		czytajWybrany.show();

	}

	/**
	 * 
	 * @return
	 */
	private void zlecCzyszczenieBasenu() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	private void zmienGrafikPracy() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	private void analizujRaport() {
		throw new UnsupportedOperationException();
	}

	public String getImie() {
		return this.imie;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public String getAdres() {
		return this.adres;
	}

	public String getPesel() {
		return this.pesel;
	}

	public String getPlec() {
		return this.plec;
	}

	public int getWiek() {
		return this.wiek;
	}

	public int getCzasPracy() {
		return this.czasPracy;
	}

	public String getStanowisko() {
		return this.stanowisko;
	}

	public String getZmiana() {
		return this.zmiana;
	}

	public String getHaslo() {
		return this.haslo;
	}
}