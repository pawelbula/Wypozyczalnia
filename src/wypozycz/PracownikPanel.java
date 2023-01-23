package wypozycz;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import wypozycz.modele.Samochod;
import wypozycz.modele.wyliczenia.Paliwo;
import wypozycz.modele.wyliczenia.Typ;

public class PracownikPanel extends JPanel {

	public PracownikPanel(Wypozyczalnia wypozyczalnia) {
		JLabel etykietaNazwaAuta = new JLabel("Nazwa auta: ");
		JTextField nazwaAuta = new JTextField("");
		
		JLabel etykietaRejestracjaAuta = new JLabel("Rejestracja auta: ");
		JTextField rejestracjaAuta = new JTextField("");
		
		JLabel etykietaMiejsca = new JLabel("Ilosc miejsc: ");
		JTextField miejsca = new JTextField("");
		
		JLabel etykietaPaliwo = new JLabel("Paliwo: ");
		JComboBox<Paliwo> paliwo = new JComboBox<Paliwo>();
		Paliwo[] paliwoTablica = Paliwo.values();
		paliwo.setModel(new DefaultComboBoxModel<>(paliwoTablica));
		if (paliwoTablica.length > 0)
			paliwo.setSelectedIndex(0);
		
		JLabel etykietaTypAuta = new JLabel("Typ: ");
		JComboBox<Typ> typAuta = new JComboBox<Typ>();
		Typ[] typyTablica = Typ.values();
		typAuta.setModel(new DefaultComboBoxModel<>(typyTablica));
		if (typyTablica.length > 0)
			typAuta.setSelectedIndex(0);
		
		nazwaAuta.setPreferredSize(new Dimension(66, 22));
		rejestracjaAuta.setPreferredSize(new Dimension(66, 22));
		miejsca.setPreferredSize(new Dimension(66, 22));
		paliwo.setPreferredSize(new Dimension(66, 22));
		typAuta.setPreferredSize(new Dimension(66, 22));
		
		JButton dodaj = new JButton("dodaj");
		
		dodaj.addActionListener(e -> {
			try {
				Paliwo wybranePaliwo = (Paliwo)paliwo.getSelectedItem();
				Typ wybranyTyp = (Typ)typAuta.getSelectedItem();
				String iloscMiejsc = miejsca.getText();
				int ilosc = Integer.parseInt(iloscMiejsc);
				if (ilosc < 1)
					JOptionPane.showMessageDialog(this, "Ilosc miejsc nie moze byc ujemna", "Komunikat", JOptionPane.WARNING_MESSAGE);
				String nazwa = nazwaAuta.getText();
				if (nazwa.length() == 0)
					JOptionPane.showMessageDialog(this, "Nie ma nazwy auta", "Komunikat", JOptionPane.WARNING_MESSAGE);
				
				String rejestracja = rejestracjaAuta.getText();
				if (rejestracja.length() == 0)
					JOptionPane.showMessageDialog(this, "Nie ma rejestracji", "Komunikat", JOptionPane.WARNING_MESSAGE);
				wypozyczalnia.dodajSamochod(nazwa, rejestracja, ilosc, wybranePaliwo, wybranyTyp);
				nazwaAuta.setText("");
				rejestracjaAuta.setText("");
				miejsca.setText("0");
				paliwo.setSelectedIndex(0);
				typAuta.setSelectedIndex(0);
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, "Niepoprawna ilosc miejsc", "Komunikat", JOptionPane.WARNING_MESSAGE);
			}
		});

		JPanel panelZEtykietaITekstem = new JPanel();
		panelZEtykietaITekstem.add(etykietaNazwaAuta);
		panelZEtykietaITekstem.add(nazwaAuta);

		JPanel panelZEtykietaIRejestracja = new JPanel();
		panelZEtykietaIRejestracja.add(etykietaRejestracjaAuta);
		panelZEtykietaIRejestracja.add(rejestracjaAuta);

		JPanel panelZEtykietaIMiejsca = new JPanel();
		panelZEtykietaIMiejsca.add(etykietaMiejsca);
		panelZEtykietaIMiejsca.add(miejsca);

		JPanel panelZEtykietaIPaliwo = new JPanel();
		panelZEtykietaIPaliwo.add(etykietaPaliwo);
		panelZEtykietaIPaliwo.add(paliwo);

		JPanel panelZEtykietaITypAuta = new JPanel();
		panelZEtykietaITypAuta.add(etykietaTypAuta);
		panelZEtykietaITypAuta.add(typAuta);

		JPanel przyciski = new JPanel();
		przyciski.add(dodaj);

		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout);

		add(panelZEtykietaITekstem);
		add(panelZEtykietaIRejestracja);
		add(panelZEtykietaIMiejsca);
		add(panelZEtykietaIPaliwo);
		add(panelZEtykietaITypAuta);
		add(przyciski);
	}

}
