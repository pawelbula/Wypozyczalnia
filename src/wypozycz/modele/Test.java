package wypozycz.modele;
import java.io.Serializable;

public class Test implements Serializable {

    private String description;
    private int idSamochod;

    public Test(String description, int idSamochod) {
        this.description = description;
        this.idSamochod = idSamochod;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public int getIdSamochod() {
        return this.idSamochod;
    }
}
