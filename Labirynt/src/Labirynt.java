public class Labirynt {

    private char [][] labirynt;

    public Labirynt() {
        labirynt = new char[2050][2050];
    }

    public void wstawWartosc(int wiersz, int kolumna, char wartosc) {
        if(wiersz >= 0 && wiersz < labirynt.length && kolumna >= 0 && kolumna < labirynt[0].length){
            labirynt[wiersz][kolumna] = wartosc;
        }
        else {
            System.out.println("Błąd: Nieprawidłowe indeksy");
        }
    }
    public char zwrocWartosc(int wiersz, int kolumna) {
        if(wiersz >= 0 && wiersz < labirynt.length && kolumna >= 0 && kolumna < labirynt[0].length){
            return labirynt[wiersz][kolumna];
        }
        else {
            System.out.println("Błąd: Nieprawidłowe indeksy");
            return '\0';
        }
    }
}
