package wypozycz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import wypozycz.modele.Klient;
import wypozycz.modele.Samochod;
import wypozycz.modele.wyliczenia.Paliwo;
import wypozycz.modele.wyliczenia.Typ;

public class Wypozyczalnia implements Serializable {

	private List<Samochod> wszystkieSamochody = new ArrayList<>();
	private List<Klient> rejestrKlientow = new ArrayList<Klient>();
	
	public Wypozyczalnia() {
		inicjuj();
	}
	private void inicjuj() {
		String[] names = {"Fiat", "Porshce", "Polonez", "Kia", "Pożyczak"};
		String[] rejestracja = {"sk2233c", "sg66069", "wx23561", "s1franek", "so0000l"};
		for (int i = 0; i < names.length; ++i) {
			this.wszystkieSamochody.add(new Samochod(i, names[i], rejestracja[i], 5, Paliwo.PB95, Typ.sedan));
		}
	}
	
	public List<Samochod> getWszystkieSamochody() {
		List<Samochod> posortowane = sortuj(wszystkieSamochody);
		return posortowane;
	}
	
	private List<Samochod> sortuj(List<Samochod> samochody) {
		Collections.sort(samochody, new Comparator<Samochod>() {
			@Override
			public int compare(Samochod o1, Samochod o2) {
				return o1.getNazwa().compareToIgnoreCase(o2.getNazwa());
			}
		});
		return samochody;
	}
	public void dodajSamochod(String nazwa, String rejestracja, int ilosc, Paliwo wybranePaliwo, Typ wybranyTyp) {
		Samochod samochod = new Samochod(-1, nazwa, rejestracja, ilosc, wybranePaliwo, wybranyTyp);
		dodajSamochod(samochod);
	}
	
	public void dodajSamochod(Samochod samochod) {
		if (samochod == null)
			throw new NullPointerException("Nie zainicjalizowano samochodu");
		if (samochod.getId() == -1)
			samochod.setId(getNextNewIdForSamochod());
		wszystkieSamochody.add(samochod);
	}
	public void dodajKlient(Klient klient) {
		if (klient == null)
			throw new NullPointerException("Nie zainicjalizowano klienta");
		if (klient.getImie() == null || klient.getImie().trim().length() == 0)
			throw new NullPointerException("Nie podano imienia klienta");
		if (klient.getNazwisko() == null || klient.getNazwisko().trim().length() == 0)
			throw new NullPointerException("Nie podano nazwiska klienta");
		if (klient.getId() == -1)
			klient.setId(getNextNewIdForKlient());
		
		Klient szukanyKlient = null;
		for (Klient przeszukiwanyKlient : rejestrKlientow) {
			if (przeszukiwanyKlient.getImie().equalsIgnoreCase(klient.getImie()) && przeszukiwanyKlient.getNazwisko().equalsIgnoreCase(klient.getNazwisko()) ) {
				//klient istnieje już w rejestrze
				szukanyKlient = przeszukiwanyKlient;
				break;
			}
		}
		if (szukanyKlient != null) {
			klient.setId(szukanyKlient.getId());
		} else {
			klient.setId(getNextNewIdForKlient());
			rejestrKlientow.add(klient);
		}
	}

	private long getNextNewIdForSamochod() {
		long max = -2;
		for (int i = 0; i < wszystkieSamochody.size(); i++) {
			Samochod samochod = wszystkieSamochody.get(i);
			if (samochod.getId() > max)
				max = samochod.getId();
		}
		return max + 1;
	}
	private long getNextNewIdForKlient() {
		long max = -2;
		for (int i = 0; i < rejestrKlientow.size(); i++) {
			Klient klient = rejestrKlientow.get(i);
			if (klient.getId() > max)
				max = klient.getId();
		}
		return max + 1;
	}
	
	public List<Klient> getRejestrKlientow() {
		return rejestrKlientow;
	}
	public void addKlient(Klient klient) {
		rejestrKlientow.add(klient);
	}
}
