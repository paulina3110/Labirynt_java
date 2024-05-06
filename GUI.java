import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    JLabel tytul;
    JLabel plik_nazwa;
    JTextField nazwa;
    JLabel plik_rodzaj;
    JComboBox<String> wybor_rodzaju;
    JLabel wysokosc_lab;
    JTextField wysokosc;
    JLabel szerokosc_lab;
    JTextField szerokosc;
    JButton wyswietl;
    JButton znajdz_sciezke;
    JButton zmien_pkt;

    GUI() {
        setTitle("Labirynt");
        setSize(900, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        tytul = new JLabel("Rozwiązywacz labiryntu", SwingConstants.CENTER);
        tytul.setFont(new Font("Arial", Font.BOLD, 36));
        tytul.setBounds(200, 50, 500, 60);
        add(tytul);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        plik_nazwa = new JLabel("Podaj nazwę pliku z labiryntem:");
        plik_nazwa.setBounds(100, 150, 300, 30);
        panel.add(plik_nazwa);

        nazwa = new JTextField();
        nazwa.setBounds(400, 150, 300, 30);
        panel.add(nazwa);

        plik_rodzaj = new JLabel("Wybierz rodzaj pliku:");
        plik_rodzaj.setBounds(100, 200, 300, 30);
        panel.add(plik_rodzaj);

        String[] rodzaje = {"  Plik tekstowy", "  Plik binarny"};
        wybor_rodzaju = new JComboBox<>(rodzaje);
        wybor_rodzaju.setBounds(400, 200, 300, 30);
        wybor_rodzaju.setBackground(Color.WHITE);
        panel.add(wybor_rodzaju);

        szerokosc_lab = new JLabel("Szerokość labiryntu:");
        szerokosc_lab.setBounds(100, 250, 150, 30);
        panel.add(szerokosc_lab);

        szerokosc = new JTextField();
        szerokosc.setBounds(400, 250, 300, 30);
        panel.add(szerokosc);

        wysokosc_lab = new JLabel("Wysokość labiryntu:");
        wysokosc_lab.setBounds(100, 300, 150, 30);
        panel.add(wysokosc_lab);

        wysokosc = new JTextField();
        wysokosc.setBounds(400, 300, 300, 30);
        panel.add(wysokosc);

        wyswietl = new JButton("Wyświetl labirynt");
        wyswietl.setBounds(100, 380, 200, 50);
        wyswietl.setFocusable(false);
        wyswietl.setHorizontalTextPosition(JButton.CENTER);
        wyswietl.setVerticalTextPosition(JButton.CENTER);
        wyswietl.setBackground(new Color(0xAFD6D1));
        wyswietl.setBorder(BorderFactory.createEmptyBorder());
        panel.add(wyswietl);


        ImageIcon icon = new ImageIcon("labirynt.jpg");
        JLabel obrazek = new JLabel();
        obrazek.setBounds(100, 400, 1000, 400);
        obrazek.setIcon(icon);
        panel.add(obrazek);

        znajdz_sciezke = new JButton("Znajdź najkrótszą ścieżkę");
        znajdz_sciezke.setBounds(100, 780, 200, 50);
        znajdz_sciezke.setFocusable(false);
        znajdz_sciezke.setHorizontalTextPosition(JButton.CENTER);
        znajdz_sciezke.setVerticalTextPosition(JButton.CENTER);
        znajdz_sciezke.setBackground(new Color(0xAFD6D1));
        znajdz_sciezke.setBorder(BorderFactory.createEmptyBorder());
        panel.add(znajdz_sciezke);

        zmien_pkt = new JButton("Zmień punkt startowy/końcowy");
        zmien_pkt.setBounds(320, 780, 220, 50);
        zmien_pkt.setFocusable(false);
        zmien_pkt.setHorizontalTextPosition(JButton.CENTER);
        zmien_pkt.setVerticalTextPosition(JButton.CENTER);
        zmien_pkt.setBackground(new Color(0xAFD6D1));
        zmien_pkt.setBorder(BorderFactory.createEmptyBorder());
        panel.add(zmien_pkt);

        panel.setBounds(0, 0, 1000, 1000);
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}