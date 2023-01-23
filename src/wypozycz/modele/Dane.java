package wypozycz.modele;

import java.io.Serializable;

public abstract class Dane implements Serializable {

	private long id;

	public Dane(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
