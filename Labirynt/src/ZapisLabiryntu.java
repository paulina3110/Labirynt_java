import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileWriter;

public class ZapisLabiryntu {

    public static void zapiszRozwiazanieJakoObraz(Labirynt graf, String nazwaPliku) {
        int wiersze = graf.pobierzLiczbeWierszy();
        int kolumny = graf.pobierzLiczbeKolumn();
        int rozmiarKomorki = 10; // Rozmiar komórki w pikselach

        BufferedImage obraz = new BufferedImage(kolumny * rozmiarKomorki, wiersze * rozmiarKomorki, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = obraz.createGraphics();

        for (int i = 0; i < wiersze; i++) {
            for (int j = 0; j < kolumny; j++) {
                Komorka komorka = graf.pobierzKomorke(i, j);
                if (graf.pobierzGraf().containsKey(komorka)) {
                    if (komorka.pobierzSciane()) {
                        g2d.setColor(Color.BLACK);
                    } else {
                        g2d.setColor(Color.WHITE);
                    }
                    if (komorka.equals(graf.pobierzStart())) {
                        g2d.setColor(new Color(0x6AA46F));
                    } else if (komorka.equals(graf.pobierzKoniec())) {
                        g2d.setColor(new Color(0xB47DD6));
                    } else if (komorka.pobierzCzySciezka()) {
                        g2d.setColor(new Color(0xFCF29A));
                    }
                    g2d.fillRect(j * rozmiarKomorki, i * rozmiarKomorki, rozmiarKomorki, rozmiarKomorki);
                }
            }
        }

        g2d.dispose();

        try {
            ImageIO.write(obraz, "png", new File(nazwaPliku));
            System.out.println("Obraz został zapisany do pliku " + nazwaPliku);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void zapiszRozwiazanieDoPlikuTekstowego(Labirynt graf, String nazwaPliku) {
        int wiersze = graf.pobierzLiczbeWierszy();
        int kolumny = graf.pobierzLiczbeKolumn();

        try {
            FileWriter writer = new FileWriter(nazwaPliku);

            for (int i = 0; i < wiersze; i++) {
                for (int j = 0; j < kolumny; j++) {
                    Komorka komorka = graf.pobierzKomorke(i, j);
                    if (graf.pobierzGraf().containsKey(komorka)) {
                        if (komorka.equals(graf.pobierzStart())) {
                            writer.write("P");
                        } else if (komorka.equals(graf.pobierzKoniec())) {
                            writer.write("K");
                        } else if (komorka.pobierzCzySciezka()) {
                            writer.write("*");
                        } else if (komorka.pobierzSciane()){
                            writer.write("X");
                        } else{
                            writer.write(" ");
                        }

                    }
                }
                writer.write("\n");
            }

            writer.close();


            System.out.println("Rozwiązanie zostało zapisane do pliku " + nazwaPliku);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
