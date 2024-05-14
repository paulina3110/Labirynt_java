public class Labirynt {

    public char[][] labirynt;

    public Labirynt() {

        labirynt = new char[2050][2050];
    }

    public void ustawWartosc(int wiersz, int kolumna, char wartosc) {
        if (wiersz >= 0 && wiersz < labirynt.length && kolumna >= 0 && kolumna < labirynt[0].length) {
            labirynt[wiersz][kolumna] = wartosc;
        } else {
            System.out.println("Błąd: Nieprawidłowe indeksy");
        }
    }

    public char pobierzWartosc(int wiersz, int kolumna) {
        if (wiersz >= 0 && wiersz < labirynt.length && kolumna >= 0 && kolumna < labirynt[0].length) {
            return labirynt[wiersz][kolumna];
        } else {
            System.out.println("Błąd: Nieprawidłowe indeksy");
            return '\0';
        }
    }
}
