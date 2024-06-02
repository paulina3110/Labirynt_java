import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Wczytywacz {

    public static int wiersze;
    public static int kolumny;
    static JPanel wczytanyLabirynt;


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

                    } else if (symbol == 'K') {
                        graf.ustawKoniec(komorka);

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