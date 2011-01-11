package main;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import pracownicy.Zarzadca;

import main.PanelKasy.OknoPracownika;

public class Zarzad extends JFrame {

	public Zarzad() {

		setBounds(100, 100, 250, 125);
		PanelZarzadu panelZarzadu = new PanelZarzadu(this);
		setResizable(false);
		Container pow = getContentPane();
		pow.add(panelZarzadu);

	}

}

class PanelZarzadu extends JPanel {

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

	private Zarzad z;

	PanelZarzadu(final Zarzad z) {

		this.z = z;
		setLayout(new GridLayout(3, 3));
		JLabel etykieta_imie = new JLabel("Podaj pesel: ");
		JLabel etykieta_haslo = new JLabel("Podaj haslo: ");

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
											+ "' and stanowisko like 'zarzad'");

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
					z.setVisible(false);
					okno.setVisible(true);

				}
			}
		});
		add(ok);

		JButton anuluj = new JButton("Anuluj");
		anuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				z.dispose();
			}
		});

		add(anuluj);

	}

	class OknoPracownika extends JFrame implements WindowListener {

		OknoPracownika(String imie, String nazwisko) {

			addWindowListener(this);
			setTitle("Zarzadca: " + imie + " " + nazwisko);
			setBounds(100, 100, 300, 200);
			Container powZawartosci = getContentPane();
			// Zarzadca zarzadca = new Zarzadca();

			final BazaDanych b = new BazaDanych();

			JPanel panelZarzadcy = new JPanel();

			// Przyciski w panelu zarzadcy
			JButton zatrudnijPracownika = new JButton("Zatrudnij pracownika");
			JButton zwolnijPracownika = new JButton("Zwolnij pracownika");
			JButton czytajRaporty = new JButton("Czytaj Raporty");
			// JButton zlecZadania = new JButton("Zlec zadania");

			zatrudnijPracownika.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						new Zarzadca().dodajPracownika();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			});

			czytajRaporty.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					new Zarzadca().odbierzRaport();

				}

			});
			zwolnijPracownika.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					new Zarzadca().usunPracownika();

				}

			});

			panelZarzadcy.add(zatrudnijPracownika);
			panelZarzadcy.add(zwolnijPracownika);
			panelZarzadcy.add(czytajRaporty);
			// panelZarzadcy.add(zlecZadania);
			powZawartosci.add(panelZarzadcy);
			setResizable(false);
			show();

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
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(null, "¯EGNAMY!");
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
			JOptionPane.showMessageDialog(null, "Witamy w pracy!");

		}

	}

}
