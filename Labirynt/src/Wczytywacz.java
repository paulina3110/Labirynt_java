import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Wczytywacz {

    public static int wiersze;
    public static int kolumny;
    static JPanel wczytanyLabirynt;


    Wczytywacz() {
        wczytanyLabirynt = new JPanel();
        wczytanyLabirynt.setBackground(Color.white);
        wczytanyLabirynt.setLayout(null);

    }

    public void wczytajPlik(String nazwaPliku, Labirynt graf) {
        if (sprawdzCzyBinarny(nazwaPliku)) {
            try {
                new OdczytBin(nazwaPliku, graf);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                new OdczytTxt(nazwaPliku, graf);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean sprawdzCzyBinarny(String nazwaPliku) {

        try (FileInputStream fis = new FileInputStream(nazwaPliku)) {
            int size = fis.available();
            if (size > 40) {
                byte[] buffer = new byte[40];
                fis.read(buffer);
                int fileId = ((buffer[3] & 0xFF) << 24) | ((buffer[2] & 0xFF) << 16) |
                        ((buffer[1] & 0xFF) << 8) | (buffer[0] & 0xFF);
                return fileId == 0x52524243;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}