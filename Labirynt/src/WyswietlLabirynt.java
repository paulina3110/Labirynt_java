import java.awt.*;
import javax.swing.JPanel;

public class WyswietlLabirynt extends JPanel {

    private Labirynt graf;

    public WyswietlLabirynt(Labirynt graf) {
        this.graf = graf;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int wiersze = graf.pobierzLiczbeWierszy();
        int kolumny = graf.pobierzLiczbeKolumn();


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
                    } else if(komorka.pobierzCzySciezka()) {
                        g2d.setColor(new Color(0xFCF29A));
                    }
                    g2d.fillRect(j * 10, i * 10, 10, 10);
                }
            }
        }
    }
}
