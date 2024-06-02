import java.util.Objects;

public class Komorka {
    private int wiersz;
    private int kolumna;
    private boolean czySciana;
    private boolean czySciezka;

    public Komorka(int wiersz, int kolumna, boolean czySciana) {
        this.wiersz = wiersz;
        this.kolumna = kolumna;
        this.czySciana = czySciana;
        this.czySciezka = false;
    }

    public int pobierzWiersz() {
        return wiersz;
    }

    public int pobierzKolumna() {
        return kolumna;
    }

    public boolean pobierzSciane() {
        return czySciana;
    }

    public boolean pobierzCzySciezka() {
        return czySciezka;
    }

    public void ustawCzySciezka(boolean czySciezka) {
        this.czySciezka = czySciezka;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Komorka komorka = (Komorka) o;
        return wiersz == komorka.wiersz && kolumna == komorka.kolumna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wiersz, kolumna);
    }
}

