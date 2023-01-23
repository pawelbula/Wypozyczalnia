package wypozycz.modele;

import wypozycz.modele.wyliczenia.Paliwo;
import wypozycz.modele.wyliczenia.Typ;

import java.util.ArrayList;
import java.util.List;

public class Samochod extends Dane {

	/** to jest numer wersji klasy na potrzeby serializacji */
	private static final long serialVersionUID = 1L;

	private String nazwa;
	private String tablicaRejestracyjna;
	private int iloscMiejsc;
	private Paliwo paliwo;
	private Typ typ;

	private Klient wypozyczyl;

	public boolean waliduj() {
		if (nazwa == null || nazwa.equalsIgnoreCase(""))
			return false;
		if (tablicaRejestracyjna == null || tablicaRejestracyjna.equalsIgnoreCase(""))
			return false;
		if (iloscMiejsc < 1)
			return false;
		if (paliwo == null)
			return false;
		if (typ == null)
			return false;
		return true;
	}

	public Samochod(long id, String nazwa, String tablicaRejestracyjna, int iloscMiejsc, Paliwo paliwo, Typ typ) {
		super(id);
		this.nazwa = nazwa;
		this.tablicaRejestracyjna = tablicaRejestracyjna;
		this.iloscMiejsc = iloscMiejsc;
		this.paliwo = paliwo;
		this.typ = typ;
	}

	public String getNazwa() {
		return nazwa;
	}

	public Klient getWypozyczyl() {
		return wypozyczyl;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public void setWypozyczyl(Klient wypozyczyl) {
		this.wypozyczyl = wypozyczyl;
	}

	public String getTablicaRejestracyjna() {
		return tablicaRejestracyjna;
	}

	public void setTablicaRejestracyjna(String tablicaRejestracyjna) {
		this.tablicaRejestracyjna = tablicaRejestracyjna;
	}

	public int getIloscMiejsc() {
		return iloscMiejsc;
	}

	public void setIloscMiejsc(int iloscMiejsc) {
		this.iloscMiejsc = iloscMiejsc;
	}

	public Paliwo getPaliwo() {
		return paliwo;
	}

	public void setPaliwo(Paliwo paliwo) {
		this.paliwo = paliwo;
	}

	public Typ getTyp() {
		return typ;
	}

	public void setTyp(Typ typ) {
		this.typ = typ;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nazwa);
		sb.append("(");
		sb.append(tablicaRejestracyjna);
		sb.append(")");
		return sb.toString();
	}

	public boolean isWypozyczony() {
		return wypozyczyl != null;
	}

	public static Klient szukaj(List<Samochod> samochody, String imie, String nazwisko) {
		for (Samochod samochod : samochody) {
			if (samochod.isWypozyczony()) {
				// to auto jest pozyczone wiec go nie ma
			} else {
				Klient wypozyczyl = samochod.getWypozyczyl();
				String imie2 = wypozyczyl.getImie();
				String nazwisko2 = wypozyczyl.getNazwisko();

				if (imie2 == null)
					imie2 = "";
				if (nazwisko2 == null)
					nazwisko2 = "";

				if (imie2.equals(imie) && nazwisko2.equals(nazwisko))
					return wypozyczyl;
			}
		}
		return null;
	}

	public static List<Samochod> wolneSamochody(List<Samochod> samochody) {
		List<Samochod> wolne = new ArrayList<Samochod>();
		for (Samochod samochod : samochody) {
			if (samochod.getWypozyczyl() == null) {
				wolne.add(samochod);
			}
		}
		return wolne;
	}

}