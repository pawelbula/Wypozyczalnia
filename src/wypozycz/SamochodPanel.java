package wypozycz;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import wypozycz.modele.Klient;
import wypozycz.modele.Samochod;

public class SamochodPanel extends JPanel {

	public SamochodPanel(Wypozyczalnia wypozyczalnia) {

		List<Samochod> wszystkieSamochody = wypozyczalnia.getWszystkieSamochody();
		Samochod[] samochody = wszystkieSamochody.toArray(new Samochod[wszystkieSamochody.size()]);
		
		JComboBox<Samochod> jComboBox = new JComboBox<Samochod>();
		jComboBox.setModel(new DefaultComboBoxModel<>(samochody));
		if (samochody.length > 0)
			jComboBox.setSelectedIndex(0);
		
		
		JButton sprawdz = new JButton("sprawdz");
		sprawdz.addActionListener(e -> {
			Samochod selectedItem = (Samochod)jComboBox.getSelectedItem();
			sprawdz(selectedItem);
		});

		add(jComboBox);
		add(sprawdz);
	}

	private void sprawdz(Samochod selectedItem) {
		boolean wypozyczony = selectedItem.isWypozyczony();
		if (wypozyczony) {
			
			Klient wypozyczyl = selectedItem.getWypozyczyl();
			StringBuilder sb = new StringBuilder();
			sb.append("Auto zostało wypożyczone przez: ");
			sb.append(wypozyczyl.getImie());
			sb.append(" ");
			sb.append(wypozyczyl.getNazwisko());
			sb.append(".");
			JOptionPane.showMessageDialog(this, sb.toString());
		} else {
			JOptionPane.showMessageDialog(this, "Auto można wypozyczyc.");
		}
	}

}
