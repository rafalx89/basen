package main;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;


//nic
public class KlasaGlowna extends JFrame {

	KlasaGlowna() {

		setBounds(100, 100, 350, 100);
		setLayout(new FlowLayout());

		// dodanie przycisku kasjer
		JButton kasjer = new JButton("Kasjer");
		JButton zarzad = new JButton("Zarzadca");
		JButton obsluga_basenu = new JButton("Obsluga basenu");
		add(kasjer);
		add(zarzad);
		add(obsluga_basenu);

		kasjer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				Kasa k = new Kasa();
				k.show();

				k.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			}
		});

		zarzad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Zarzad z = new Zarzad();
				z.show();

				z.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		obsluga_basenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OknoObslugaBasenu ok = new OknoObslugaBasenu();
				ok.show();

				ok.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

	}

	public static void main(String[] args) {

		KlasaGlowna program = new KlasaGlowna();
		program.show();
		program.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
