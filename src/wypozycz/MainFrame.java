package wypozycz;

import wypozycz.modele.Klient;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

	private JPanel mainPane;
	private JButton samochodButton, pracownikButton, klientButton, zapisButton, odczytButton;
	private List<Klient> klientList = new ArrayList<>();
	private JTextArea textArea = new JTextArea(); // ?
	private int id;
	private Wypozyczalnia wypozyczalnia;

	public MainFrame(Wypozyczalnia wypozyczalnia) {
		this.wypozyczalnia = wypozyczalnia;
		this.mainPane = new JPanel(new FlowLayout());
		this.setSize(400, 250);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		try {
			readIn();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.samochodButton = new JButton("Samochod");
		this.samochodButton.addActionListener((e) -> samochodPane());

		this.pracownikButton = new JButton("Pracownik");
		this.pracownikButton.addActionListener((e) -> pracownikPane());

		this.klientButton = new JButton("Klient");
		this.klientButton.addActionListener((e -> klientPane()));

		this.zapisButton = new JButton("Zapis");
		this.zapisButton.addActionListener((e -> zapis()));

		this.odczytButton = new JButton("Odczyt");
		this.odczytButton.addActionListener((e -> odczyt()));

		this.mainPane.add(samochodButton);
		this.mainPane.add(pracownikButton);
		this.mainPane.add(klientButton);
		this.mainPane.add(zapisButton);
		this.mainPane.add(odczytButton);

		this.add(mainPane);
	}

	private void readIn() throws IOException {
	}

	private void klientPane() {
		JDialog jDialog = new JDialog(this, true);
		jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		KlientPanel contentPane = new KlientPanel(wypozyczalnia);
		contentPane.setCallback(new Runnable() { //Event Handling (swing) na potrzeby zmiany okienka
			@Override
			public void run() {
				jDialog.setVisible(false);
			}
		});
		jDialog.setContentPane(contentPane);
		jDialog.pack();
		jDialog.setVisible(true);
	}

	private void pracownikPane() {
		JDialog jDialog = new JDialog(this, true);
		jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jDialog.setContentPane(new PracownikPanel(wypozyczalnia));
		jDialog.pack();
		jDialog.setVisible(true);
	}

	private void samochodPane() {
		JDialog jDialog = new JDialog(this, true);
		jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jDialog.setContentPane(new SamochodPanel(wypozyczalnia));
		jDialog.pack();
		jDialog.setVisible(true);
	}

	private void zapis() {
		JFileChooser fileChooser = new JFileChooser();
		if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(MainFrame.this)) {
			try {
				FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile());
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(wypozyczalnia);
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(MainFrame.this, "Zapis nie powiódł się.", "Zapis", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void odczyt() {
		JFileChooser fileChooser = new JFileChooser();
		if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(MainFrame.this)) {
			try {
				FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile());
				ObjectInputStream ois = new ObjectInputStream(fis);
				wypozyczalnia = (Wypozyczalnia) ois.readObject();
				ois.close();
				fis.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(MainFrame.this, "Odczyt nie powiódł się.", "Zapis", JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}