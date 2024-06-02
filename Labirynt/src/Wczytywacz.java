import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Wczytywacz {

    //static JLabel wspolrzedneP;
    //static JLabel wspolrzedneK;
    public static int wiersze;
    public static int kolumny;
    static JPanel wczytanyLabirynt;
    public static int startWiersz;
    public static int startKolumna;
    public static int koniecWiersz;
    public static int koniecKolumna;

    Wczytywacz() {
        wczytanyLabirynt = new JPanel();
        wczytanyLabirynt.setBackground(Color.white);
        wczytanyLabirynt.setLayout(null);

    }

    public static class Odczyt {
        Odczyt(String nazwaPliku, Labirynt graf) throws FileNotFoundException {
            File plik = new File(nazwaPliku);
            Scanner in = new Scanner(plik);
            int row = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    char symbol = line.charAt(i);
                    Komorka komorka = new Komorka(row, i, symbol == 'X');
                    graf.dodajKomorke(komorka);

                    if (symbol == 'P') {
                        graf.ustawStart(komorka);
                        startKolumna = i;
                        startWiersz = row;
                        //Wczytywacz.wspolrzedneP.setText("Współrzędne punktu startowego: (" + i + ", " + row + ")");
                    } else if (symbol == 'K') {
                        graf.ustawKoniec(komorka);
                        koniecKolumna = i;
                        koniecWiersz = row;
                        //Wczytywacz.wspolrzedneK.setText("Współrzędne punktu końcowego: (" + i + ", " + row + ")");
                    }

                    if (!komorka.pobierzSciane()) {
                        if (i > 0) {
                            Komorka left = graf.pobierzKomorke(row, i - 1);
                            if (left != null && !left.pobierzSciane()) {
                                graf.dodajPolaczenie(komorka, left);
                            }
                        }
                        if (row > 0) {
                            Komorka up = graf.pobierzKomorke(row - 1, i);
                            if (up != null && !up.pobierzSciane()) {
                                graf.dodajPolaczenie(komorka, up);
                            }
                        }
                    }
                }
                row++;
            }
            Wczytywacz.wiersze = row;
            Wczytywacz.kolumny = graf.pobierzLiczbeKolumn();
        }
    }
}