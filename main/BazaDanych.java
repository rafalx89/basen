package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import pracownicy.Pracownik;
import pracownicy.Zarzadca;

import Wejsciowki.Bilet;
import Wejsciowki.Karnet;
import Wejsciowki.Rezerwacja;

public class BazaDanych {

	/**
	 * 
	 * @return
	 */

	private static String userName = "root"; // nazwa u¿ytkownika, który posiada
	// prawa dostêpu do bazy
	private static String password = ""; // has³o dostêpu do bazy
	private static String dbName = "basen"; // nazwa bazy danych
	private static String host = "localhost";

	// doda³em metodê aby sprawdzaæ czy s¹ wolne karnety, na karnety bêdzie
	// przeznaczona okreœlona pula miejsc

	public int sprawdzWolneKarnety() {
		Connection conn = null;
		int wolneBilety = 0;
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

					s.executeQuery("select * from miejsca");

					ResultSet rs = s.getResultSet();

					rs.next();

					wolneBilety = rs.getInt("wolneKarnety");

					rs.close();

				}

				catch (Exception e1) {
					e1.getMessage();
				}

			}

		}
		return wolneBilety;
	}

	/*
	 * Metoda ³¹czy siê z baz¹ danych, odczytuje iloœæ wolnych miejsc i je¿eli
	 * jest ona wiêksza od 0 to zwraca wartoœæ true, je¿eli nie to zwraca
	 * wartoœæ false
	 */

	public int sprawdzWolneMiejsca() {
		Connection conn = null;
		int wolneBilety = 0;
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

					s.executeQuery("select * from miejsca");

					ResultSet rs = s.getResultSet();

					rs.next();

					wolneBilety = rs.getInt("wolneMiejsca");

					rs.close();

				}

				catch (Exception e1) {
					e1.getMessage();
				}

			}

		}
		return wolneBilety;
	}

	public boolean zapiszPracownika(Zarzadca z) {
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

					// System.out.print(r.getPesel());
					String zapytanie = ("INSERT INTO pracownicy (imie, nazwisko, adres, pesel, plec, wiek, czasPracy, stanowisko, zmiana, haslo)"
							+ " VALUES"
							+ "('"
							+ z.getImie()
							+ "','"
							+ z.getNazwisko()
							+ "','"
							+ z.getAdres()
							+ "','"
							+ z.getPesel()
							+ "','"
							+ z.getPlec()
							+ "',"
							+ z.getWiek()
							+ ","
							+ z.getCzasPracy()
							+ ",'"
							+ z.getStanowisko()
							+ "','"
							+ z.getZmiana()
							+ "','"
							+ z.getHaslo() + "')");
					s.executeUpdate(zapytanie);

					return true;

				} catch (Exception e1) {
					System.err.println("B£¥D, powtórz proces od nowa!");
					// e1.printStackTrace();
				}

			}
		}

		return false;

	}

	public boolean edytujRaport(int nrRaportu, String uwagi, int przeczytany) {
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

					// System.out.print(r.getPesel());
					String zapytanie = ("UPDATE raport SET uwagiZarzadcy='"
							+ uwagi + "', przeczytany=" + przeczytany
							+ " where nrRaportu = " + nrRaportu + "");
					s.executeUpdate(zapytanie);

					return true;

				} catch (Exception e1) {
					System.err.println("B£¥D, powtórz proces od nowa!");
					// e1.printStackTrace();
				}

			}
		}

		return false;

	}

	/**
	 * 
	 * @return
	 */
	public boolean zapiszRezerwacje(Rezerwacja r) {
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

					System.out.print(r.getPesel());
					String zapytanie = ("INSERT INTO rezerwacja (nrKasy,imieRezerwujacego,nazwiskoRezerwujacego,pesel,dataRezerwacji, godzinaRezerwacji, typ, czasPobytu, iloscOsob, cena)"
							+ " VALUES"
							+ "("
							+ r.getNrKasy()
							+ ",'"
							+ r.getImieRezerwujacego()
							+ "','"
							+ r.getNazwiskoRezerwujacego()
							+ "',"
							+ r.getPesel()
							+ ",'"
							+ r.getDataRezerwacji()
							+ "','"
							+ r.getGodzinaRezerwacji()
							+ "','"
							+ r.getTyp()
							+ "',"
							+ r.getCzasPobytu()
							+ ","
							+ r.getIloscOsob() + "," + r.getCena() + ")");
					s.executeUpdate(zapytanie);

					return true;

				} catch (Exception e1) {
					System.err.println("B£¥D, powtórz proces od nowa!");
					// e1.printStackTrace();
				}

			}
		}

		return false;

	}

	public ArrayList<Pracownik> pobierzPracownikow() {
		ArrayList<Pracownik> pracownicy = new ArrayList<Pracownik>();

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

					s.executeQuery("select * from pracownicy");

					ResultSet rs = s.getResultSet();
					while (rs.next()) {

						int nr = rs.getInt("nrPracownika");
						String imie = rs.getString("imie");
						String nazwisko = rs.getString("nazwisko");
						String pesel = rs.getString("pesel");
						Pracownik p = new Pracownik(nr, imie, nazwisko, pesel);
						pracownicy.add(p);
					}

					rs.close();

				}

				catch (Exception e1) {
					e1.getMessage();
				}

			}

		}

		return pracownicy;

	}

	public boolean usunPracownika(int nrPracownika) {

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
							.executeUpdate("delete from pracownicy where nrPracownika = "
									+ nrPracownika + "");
					return true;

				}

				catch (Exception e1) {
					e1.getMessage();
				}

			}

		}

		return false;

	}

	/**
	 * 
	 * @return
	 */
	public boolean dodajKarnet(Karnet k) {
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
					Statement s2 = conn.createStatement();
					Statement s3 = conn.createStatement();

					int count = 0, count2 = 0, nowaIlosc = 0;

					System.out.println(k.getNrKasy() + " "
							+ k.getTerminWaznosci() + " " + k.getTyp() + " "
							+ k.getCena());
					String zapytanie = ("INSERT INTO karnet (nrKasy,terminWaznosci, typ, cena)"
							+ " VALUES"
							+ "("
							+ k.getNrKasy()
							+ ","
							+ k.getTerminWaznosci()
							+ ",'"
							+ k.getTyp()
							+ "',"
							+ k.getCena() + ")");

					count = s.executeUpdate(zapytanie);
					System.out.print("tutaj");

					String zapytanie2 = ("Select wolneKarnety from miejsca");
					System.out.print("tutaj2");
					s2.executeQuery(zapytanie2);
					System.out.print("tutaj3");

					ResultSet r = s2.getResultSet();
					r.next();

					int miejsca = r.getInt("wolneKarnety");
					nowaIlosc = miejsca - 1;

					String zapytanie3 = ("UPDATE miejsca SET wolneKarnety= "
							+ nowaIlosc + "");
					System.out.print(nowaIlosc);
					count2 = s3.executeUpdate(zapytanie3);
					if (count > 0 && count2 > 0) {
						System.out.println("Zapisano karnet!");
						conn.close();
						return true;

					} else {
						System.err.println("B£¥D!");
						conn.close();
						return false;
					}

				} catch (Exception e1) {
					System.err.println("B£¥D, powtórz proces od nowa!");
					// e1.printStackTrace();
				}

			}
		}

		return false;

	}

	/**
	 * 
	 * @return
	 */
	public boolean dodajBilet(Bilet b) {
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
					Statement s2 = conn.createStatement();
					Statement s3 = conn.createStatement();

					int count = 0, count2 = 0, nowaIlosc = 0;

					String zapytanie = ("INSERT INTO bilet (nrKasy,dataWystawienia, godzinaWystawienia, typ, cena, czasPobytu)"
							+ " VALUES"
							+ "("
							+ b.getNrKasy()
							+ ","
							+ b.getDataWystawienia()
							+ ","
							+ b.getGodzinaWystawienia()
							+ ",'"
							+ b.getTyp()
							+ "'," + b.getCena() + "," + b.getCzasPobytu() + ")");
					count = s.executeUpdate(zapytanie);

					String zapytanie2 = ("Select wolneMiejsca from miejsca");
					s2.executeQuery(zapytanie2);

					ResultSet r = s2.getResultSet();
					r.next();

					int miejsca = r.getInt("wolneMiejsca");
					nowaIlosc = miejsca - b.getIloscOsob();

					String zapytanie3 = ("UPDATE miejsca SET wolneMiejsca= "
							+ nowaIlosc + "");

					count2 = s3.executeUpdate(zapytanie3);
					if (count > 0 && count2 > 0) {

						conn.close();
						return true;

					} else {
						System.err.println("B£¥D!");
						conn.close();
						return false;
					}

				} catch (Exception e1) {
					System.err.println("B£¥D, powtórz proces od nowa!");
					// e1.printStackTrace();
				}

			}
		}

		return false;

	}

	/**
	 * 
	 * @return
	 */

	public boolean statusKasy(int numer) {

		Connection conn = null;
		int nr = 0;
		String status;
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

					s.executeQuery("select status from kasy where nrKasy ="
							+ numer + "");

					ResultSet rs = s.getResultSet();

					rs.next();

					status = rs.getString("status");
					rs.close();

					if (status.equals("off")) {

						Statement s2 = conn.createStatement();

						s2
								.executeUpdate("update kasy set status = 'on' where nrKasy ="
										+ numer + "");

						return true;
					} else
						return false;

				}

				catch (Exception e1) {
					e1.getMessage();
				}

			}

		}
		return false;

	}

	/*
	 * Metoda wywyo³ywana gdy pracownik koñczy pracê ( zamyka okno:P)
	 */

	public boolean opuszczenieKasy(int numer) {

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

					Statement s2 = conn.createStatement();

					s2
							.executeUpdate("update kasy set status = 'off' where nrKasy ="
									+ numer + "");

					return true;
				} catch (Exception e1) {
					e1.getMessage();
				}

			}

		}
		return false;

	}

	public void dodajRachunek() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return
	 */
	public ResultSet odbierzRaport() {
		Connection conn = null;
		ResultSet raporty = null;
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
							.executeQuery("select * from raport where przeczytany = 0 group by nrRaportu DESC");

					ResultSet rs = s.getResultSet();
					raporty = rs;
					// rs.next();
					// rs.close();

				}

				catch (Exception e1) {
					e1.getMessage();
				}

			}

		}
		return raporty;
	}

	/**
	 * 
	 * @return
	 */
	public boolean wyslijRaport(String tytul, String zawartoscRaportu,
			String osobaWypelniajaca, String dataRaportu) {
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
					String zapytanie = ("INSERT INTO raport (tytul, zawartoscRaportu, osobaWypelniajaca, dataRaportu, uwagiZarzadcy) VALUES ('"
							+ tytul
							+ "', '"
							+ zawartoscRaportu
							+ "', '"
							+ osobaWypelniajaca + "', '" + dataRaportu + "', '' )");
					s.executeUpdate(zapytanie);

					return true;

				} catch (Exception e1) {
					System.err.println("B£¥D, powtórz proces od nowa!");
					// e1.printStackTrace();
				}

			}
		}

		return false;

	}
}