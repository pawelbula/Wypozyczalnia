package wypozycz;

import wypozycz.modele.Klient;
import wypozycz.modele.Samochod;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class KlientPanel extends JPanel {

	public KlientPanel(Wypozyczalnia wypozyczalnia) {
		JLabel jLabel = new JLabel("Podaj imię i nazwisko");
		JTextField imie = new JTextField("wpisz tu imie");
		JTextField nazwisko = new JTextField("wpisz tu nazwisko");
		
		imie.setPreferredSize(new Dimension(66,22));
		nazwisko.setPreferredSize(new Dimension(66,22));

		List<Samochod> wolneSamochody = Samochod.wolneSamochody(wypozyczalnia.getWszystkieSamochody());
		Samochod[] samochody = wolneSamochody.toArray(new Samochod[wolneSamochody.size()]);
		
		JComboBox<Samochod> jComboBox = new JComboBox<Samochod>();
		jComboBox.setModel(new DefaultComboBoxModel<>(samochody));
		if (samochody.length > 0)
			jComboBox.setSelectedIndex(0);
		
		
		JButton wypozycz = new JButton("wypożycz");
		wypozycz.addActionListener(e -> {
			Samochod selectedItem = (Samochod)jComboBox.getSelectedItem();
			Klient klient = new Klient(-1, imie.getText(), nazwisko.getText());
			wypozycz(wypozyczalnia, klient, selectedItem);
		});
		
		JPanel przyciskICombo = new JPanel();
		przyciskICombo.add(jComboBox);
		przyciskICombo.add(wypozycz);

		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout);
		
		add(jLabel);
		add(imie);
		add(nazwisko);
		add(przyciskICombo);
	}

	private void wypozycz(Wypozyczalnia wypozyczalnia, Klient klient, Samochod selectedItem) {
		List<Samochod> wszystkieSamochody = wypozyczalnia.getWszystkieSamochody();
		for (Samochod samochod : wszystkieSamochody) {
			if (samochod.getId() == selectedItem.getId()) {
				wypozyczalnia.addKlient(klient);//dodaje sprytnie klienta, wyszukujac czy taki juz istnieje
				samochod.setWypozyczyl(klient);
				JOptionPane.showMessageDialog(this, "wypożyczono: " + samochod);
				akcjaNaZamkniecie(); //akcja wielowątkowości?
				return;
			}
		}
		JOptionPane.showMessageDialog(this, "to nie powinno się wykonać", "", JOptionPane.WARNING_MESSAGE);
	}

	private Runnable callback;
	public void setCallback(Runnable callback) { //event handling
		this.callback = callback;
	}
	private void akcjaNaZamkniecie() {
		if (callback != null)
			callback.run();
	}

}
