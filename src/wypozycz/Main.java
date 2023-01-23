package wypozycz;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Wypozyczalnia wypozyczalnia = new Wypozyczalnia();
			MainFrame okno = new MainFrame(wypozyczalnia);
		});
	}
}
