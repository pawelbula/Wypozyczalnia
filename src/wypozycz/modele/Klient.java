package wypozycz.modele;

import java.io.Serializable;

public class Klient extends Dane implements Serializable {

	private String imie;
	private String nazwisko;

	public Klient(long id, String imie, String nazwisko) {
		super(id);
		this.imie = imie;
		this.nazwisko = nazwisko;
	}

	public Klient(long id) {
		super(id);
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

}
