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

import pracownicy.ObslugaBasenu;

import main.PanelZarzadu.OknoPracownika;

public class OknoObslugaBasenu extends JFrame {
	OknoObslugaBasenu() {
		setBounds(100, 100, 250, 125);
		PanelObslugiBasenu panelObslugiBasenu = new PanelObslugiBasenu(this);
		setResizable(false);
		Container pow = getContentPane();
		pow.add(panelObslugiBasenu);
	}

}

class PanelObslugiBasenu extends JPanel {

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

	private OknoObslugaBasenu oob;

	public PanelObslugiBasenu(final OknoObslugaBasenu oob) {
		this.oob = oob;
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
											+ "' and stanowisko like 'obsluga basenu'");

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
					oob.setVisible(false);
					okno.setVisible(true);

				}
			}
		});
		add(ok);

		JButton anuluj = new JButton("Anuluj");
		anuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				oob.dispose();
			}
		});

		add(anuluj);

	}

	class OknoPracownika extends JFrame implements WindowListener {

		public OknoPracownika(final String imie, final String nazwisko) {
			JButton napiszRaport = new JButton("Napisz Raport");

			addWindowListener(this);
			setTitle("Obsluga basenu: " + imie + " " + nazwisko);
			setBounds(100, 100, 350, 100);
			Container powZawartosci = getContentPane();
			// Zarzadca zarzadca = new Zarzadca();

			final BazaDanych b = new BazaDanych();

			JPanel panelPracownika = new JPanel();
			panelPracownika.add(napiszRaport);
			powZawartosci.add(panelPracownika);

			napiszRaport.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					new ObslugaBasenu(imie, nazwisko).napiszRaport();

				}

			});
			setResizable(false);

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
