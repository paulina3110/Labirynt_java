import java.awt.*;
import javax.swing.JPanel;

public class WyswietlLabirynt extends JPanel{
    public WyswietlLabirynt(Labirynt wczytanyLabirynt) {
        setPreferredSize(new Dimension(1000, 1000));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Labirynt labirynt = new Labirynt();
        int wiersze = wczytywacz.wiersze;
        int kolumny = wczytywacz.kolumny;
        for (int i = 0; i < kolumny; i++) {
            for (int j = 0; j < wiersze; j++) {
                char xij = labirynt.pobierzWartosc(i, j);
                if (xij == 'X') g2d.setColor(Color.BLACK);
                else if (xij == 'K' || xij == 'P') g2d.setColor(Color.GREEN);
                else if (xij == ' ') g2d.setColor(Color.WHITE);
                g2d.fillRect(j * 10, i * 10, 10, 10);
            }
        }
    }
}
