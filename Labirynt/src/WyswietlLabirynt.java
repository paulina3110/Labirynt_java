import java.awt.*;
import javax.swing.JPanel;

public class WyswietlLabirynt extends JPanel{

    private Labirynt labirynt;

    public WyswietlLabirynt(Labirynt labirynt) {
        this.labirynt = labirynt;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int wiersze = Wczytywacz.wiersze;
        int kolumny = Wczytywacz.kolumny;
        for (int i = 0; i < wiersze; i++) {
            for (int j = 0; j < kolumny; j++) {
                char xij = labirynt.zwrocWartosc(i, j);
                if (xij == 'X' || xij == 'x') g2d.setColor(Color.BLACK);
                else if (xij == 'K' || xij == 'P') g2d.setColor(new Color(0xAFD6D1));
                else if (xij == ' ') g2d.setColor(Color.WHITE);
                g2d.fillRect(j * 10, i * 10, 10, 10);
            }
        }
    }
}


