import java.util.*;

public class Labirynt {
    private Map<Komorka, List<Komorka>> graf;
    private Komorka start;
    private Komorka koniec;

    public Labirynt() {
        this.graf = new HashMap<>();
    }

    public void dodajKomorke(Komorka komorka) {
        if (!graf.containsKey(komorka)) {
            graf.put(komorka, new ArrayList<>());
        }
    }

    public void dodajPolaczenie(Komorka k1, Komorka k2) {
        if (graf.containsKey(k1) && graf.containsKey(k2)) {
            graf.get(k1).add(k2);
            graf.get(k2).add(k1);
        }
    }

    public void ustawStart(Komorka start) {
        this.start = start;
    }

    public void ustawKoniec(Komorka koniec) {
        this.koniec = koniec;
    }

    public Komorka pobierzStart() {
        return start;
    }

    public Komorka pobierzKoniec() {
        return koniec;
    }

    public int pobierzLiczbeWierszy() {
        int maxWiersz = 0;
        for (Komorka k : graf.keySet()) {
            int wiersz = k.pobierzWiersz();
            if (wiersz > maxWiersz) {
                maxWiersz = wiersz;
            }
        }
        return maxWiersz + 1;
    }

    public int pobierzLiczbeKolumn() {
        int maxKolumna = 0;
        for (Komorka k : graf.keySet()) {
            int kolumna = k.pobierzKolumna();
            if (kolumna > maxKolumna) {
                maxKolumna = kolumna;
            }
        }
        return maxKolumna + 1;
    }

    public Map<Komorka, List<Komorka>> pobierzGraf() {
        return graf;
    }

    public Komorka pobierzKomorke(int row, int col) {
        for (Komorka komorka : graf.keySet()) {
            if (komorka.pobierzWiersz() == row && komorka.pobierzKolumna() == col) {
                return komorka;
            }
        }
        return null;
    }
}
