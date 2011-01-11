package main;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import pracownicy.ObslugaKasy;

public class Kasa extends JFrame {

	public Kasa() {

		setBounds(100, 100, 250, 125);
		PanelKasy panelKasy = new PanelKasy(this);
		setResizable(false);
		Container pow = getContentPane();
		pow.add(panelKasy);

	}

}

class PanelKasy extends JPanel {

	private static String userName = "root"; // nazwa u¿ytkownika, który posiada
	// prawa dostêpu do bazy
	private static String password = ""; // has³o dostêpu do bazy
	private static String dbName = "basen"; // nazwa bazy danych
	private static String host = "localhost";

	private JFormattedTextField pesel = null;

	private JPasswordField haslo = null;

	private int flaga = 0;

	private String imie, nazwisko, adres, stanowisko, zmiana, plec, peselek;
	private int nrPracownika, wiek, czasPracy, przydzielonaKasa,
			iloscWolnychMiejsc = 0;

	private Kasa k;

	PanelKasy(final Kasa k) {
		this.k = k;
		setLayout(new GridLayout(3, 3));
		JLabel etykieta_imie = new JLabel("Podaj pesel: ");
		add(etykieta_imie);
		MaskFormatter formatter;

		try {
			formatter = new MaskFormatter("###########");
			formatter.setValidCharacters("0123456789");
			pesel = new JFormattedTextField(formatter);
			add(pesel);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel etykieta_haslo = new JLabel("Podaj haslo: ");

		add(etykieta_haslo);
		haslo = new JPasswordField();
		add(haslo);

		JButton ok = new JButton("OK");

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String haselko = haslo.getText();

				String peselek = pesel.getText();

				Connection conn = null;
				try {
					String url = "jdbc:mysql://" + host + "/" + dbName;
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn = DriverManager.getConnection(url, userName, password);
					System.out.println("Connect with database");
				} catch (Exception e) {
					System.err.println("ERROR!");
				} finally {
					if (conn != null) {
						try {

							Statement s = conn.createStatement();

							s
									.executeQuery("select * from pracownicy where pesel = "
											+ peselek
											+ " and haslo like '"
											+ haselko
											+ "' and stanowisko like 'kasjer'");

							flaga = 1;

							ResultSet rs = s.getResultSet();
							rs.next();

							if ((rs.getRow()) == 0) {
								flaga = 0;
							} else {
								// przesuniêcie kursora na pierwszy
								// rekord

								nrPracownika = rs.getInt("nrPracownika");
								imie = rs.getString("imie");
								nazwisko = rs.getString("nazwisko");
								adres = rs.getString("adres");
								plec = rs.getString("plec");
								wiek = rs.getInt("wiek");
								czasPracy = rs.getInt("czasPracy");
								stanowisko = rs.getString("stanowisko");
								zmiana = rs.getString("zmiana");

								System.out.print(imie + " " + nazwisko + " ");
							}
							rs.close();

						}

						catch (Exception e1) {
							e1.getMessage();
						}

					}

				}

				if (flaga == 0) {
					JOptionPane.showMessageDialog(null,
							"B³êdny login lub haslo.");
				}

				if (flaga == 1) {

					OknoPracownika okno = new OknoPracownika(imie, nazwisko);
					okno.setVisible(true);
					k.dispose();

				}
			}
		});
		add(ok);

		JButton anuluj = new JButton("Anuluj");
		anuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				k.dispose();
			}
		});
		add(anuluj);

	}

	class OknoPracownika extends JFrame implements WindowListener {
		int flaga1 = 0;// doda³em

		// implementacjê
		// Winodow
		// Listner'a
		// po to aby
		// po
		// zamknieciu
		// okna pracownika zosta³a zwolniona kasa, zamkniêcie okna zwalnia kasê,
		// ustawia status na off w bazie przy kasie przydzielonej pracownikowi
		OknoPracownika(String imie, String nazwisko) {

			addWindowListener(this);
			setTitle("Kasjer: " + imie + " " + nazwisko);
			setBounds(100, 100, 300, 250);
			Container powZawartosci = getContentPane();

			String zmienna = JOptionPane.showInputDialog(null,
					"Podaj numer kasy: ");

			try {
				przydzielonaKasa = Integer.parseInt(zmienna);
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "NIE PODA£EŒ LICZBY!");

				flaga1 = 1;
				System.exit(0);
			}

			final BazaDanych b = new BazaDanych();
			if (b.statusKasy(przydzielonaKasa) == true) {
				System.out.print("OK!");

				final ObslugaKasy osoba = new ObslugaKasy(imie, nazwisko,
						adres, peselek, plec, wiek, czasPracy, stanowisko,
						zmiana, przydzielonaKasa);

				JPanel panelKasjera = new JPanel();
				powZawartosci.add(panelKasjera);

				JButton bilet = new JButton("Utwórz bilet");
				panelKasjera.add(bilet);
				bilet.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						osoba.utworzBilet(przydzielonaKasa);

					}
				});

				JButton karnet = new JButton("Utwórz karnet");
				panelKasjera.add(karnet);
				karnet.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						osoba.utworzKarnet(przydzielonaKasa);

					}
				});

				JButton wolneMiejsca = new JButton(
						"SprawdŸ liczbê wolnych miejsca");
				panelKasjera.add(wolneMiejsca);
				wolneMiejsca.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {

						BazaDanych bd = new BazaDanych();

						String info = Integer
								.toString(bd.sprawdzWolneMiejsca());
						JOptionPane.showMessageDialog(null, info);

					}
				});

				JButton rezerwacja = new JButton("Utwórz rezerwacjê");
				panelKasjera.add(rezerwacja);
				rezerwacja.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						osoba.utworzRezerwacje(przydzielonaKasa);

					}
				});

				setResizable(false);
			} else {
				JOptionPane.showMessageDialog(null,
						"Kasa zajêta b¹dŸ taka nie istnieje!");
				flaga1 = 1;
				k.setVisible(false);
				System.exit(0);

			}

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			if (flaga1 == 0) {
				BazaDanych bazaDanych = new BazaDanych();
				bazaDanych.opuszczenieKasy(przydzielonaKasa);
				JOptionPane.showMessageDialog(null, "¯EGNAMY!");
			}

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			if (flaga1 == 0)
				JOptionPane.showMessageDialog(null, "Witamy w pracy!");

		}

	}

}