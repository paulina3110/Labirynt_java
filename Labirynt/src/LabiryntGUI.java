import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class LabiryntGUI extends JFrame implements ActionListener {
    JFileChooser fileChooser;
    JLabel tytul;
    JLabel komunikaty;
    JPanel menu;
    JPanel wczytaneDane;
    JPanel pudelkoNaLabirynt;
    JTextField nazwa;
    JButton znajdzSciezke;
    JButton nazwaPliku;
    JButton zapiszRozwiazanie;
    JPanel dane;
    static JPanel wczytanyLabirynt;
    JLabel tytulSekcji;
    JLabel wspolrzedneP;
    JLabel wspolrzedneK;

    JButton zmienP;
    JButton zmienK;

    Labirynt graf = new Labirynt();

    private static int startWiersz;
    private static int startKolumna;
    private static int koniecWiersz;
    private static int koniecKolumna;

    public LabiryntGUI() {
        setTitle("Rozwiązywacz labiryntu");
        setSize(900, 1200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        Font syne = new Font("Arial", Font.BOLD, 35);

        try {
            syne = Font.createFont(Font.TRUETYPE_FONT, new File("Labirynt/Syne-ExtraBold.ttf")).deriveFont(35f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Labirynt/Syne-ExtraBold.ttf")));
        } catch (IOException | FontFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        Font poppins = new Font("Arial", Font.PLAIN, 12);

        try {
            poppins = Font.createFont(Font.TRUETYPE_FONT, new File("Labirynt/Poppins-Medium.ttf")).deriveFont(13f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Labirynt/Poppins-Medium.ttf")));
        } catch (IOException | FontFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        menu = new JPanel();
        menu.setBounds(0, 0, 1000, 70);
        menu.setFont(poppins);
        menu.setBackground(new Color(0xE1E1E1));
        menu.setLayout(null);

        wczytaneDane = new JPanel();
        wczytaneDane.setBounds(0, 300, 1000, 300);
        wczytaneDane.setFont(poppins);
        wczytaneDane.setBackground(Color.white);
        wczytaneDane.setLayout(null);

        pudelkoNaLabirynt = new JPanel();
        pudelkoNaLabirynt.setFont(poppins);
        pudelkoNaLabirynt.setBackground(Color.white);
        pudelkoNaLabirynt.setLayout(new BoxLayout(pudelkoNaLabirynt, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(pudelkoNaLabirynt);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(pudelkoNaLabirynt);
        scrollPane.setBounds(100, 600, 683, 450);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        add(scrollPane);

        nazwaPliku = new JButton("Wybierz plik z labiryntem");
        nazwaPliku.setBounds(40, 15, 220, 40);
        nazwaPliku.setFocusable(false);
        nazwaPliku.setHorizontalTextPosition(JButton.CENTER);
        nazwaPliku.setVerticalTextPosition(JButton.CENTER);
        nazwaPliku.setBackground(new Color(0xE1E1E1));
        nazwaPliku.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        nazwaPliku.setFont(poppins);
        nazwaPliku.addActionListener(this);
        menu.add(nazwaPliku);

        zapiszRozwiazanie = new JButton("Zapisz rozwiązanie");
        zapiszRozwiazanie.setBounds(610, 15, 220, 40);
        zapiszRozwiazanie.setFocusable(false);
        zapiszRozwiazanie.setHorizontalTextPosition(JButton.CENTER);
        zapiszRozwiazanie.setVerticalTextPosition(JButton.CENTER);
        zapiszRozwiazanie.setBackground(new Color(0xE1E1E1));
        zapiszRozwiazanie.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        zapiszRozwiazanie.setFont(poppins);
        zapiszRozwiazanie.setEnabled(false);
        zapiszRozwiazanie.addActionListener(this);
        menu.add(zapiszRozwiazanie);

        nazwa = new JTextField();
        nazwa.setBounds(290, 15, 300, 40);
        nazwa.setFont(poppins);
        nazwa.setBackground(new Color(0xE1E1E1));
        nazwa.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        nazwa.setLayout(null);
        nazwa.setMargin(new Insets(5, 10, 5, 10));
        nazwa.setVisible(false);

        add(wczytaneDane);
        menu.add(nazwa);
        add(menu);

        tytul = new JLabel("Rozwiązywacz labiryntu", SwingConstants.CENTER);
        tytul.setFont(syne);
        tytul.setBounds(-60, 200, 1000, 60);
        add(tytul);

        komunikaty = new JLabel();
        komunikaty.setFont(poppins);
        komunikaty.setPreferredSize(new Dimension(650, 200));
        komunikaty.setBackground(Color.white);

        JScrollPane scrollKomunikaty = new JScrollPane(komunikaty);
        scrollKomunikaty.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollKomunikaty.setViewportView(komunikaty);
        scrollKomunikaty.setBounds(100, 100, 680, 60);
        scrollKomunikaty.setBackground(Color.white);
        scrollKomunikaty.setBorder(BorderFactory.createLineBorder(Color.black));
        add(scrollKomunikaty);

        znajdzSciezke = new JButton("Znajdź najkrótszą ścieżkę");
        znajdzSciezke.setBounds(100, 480, 200, 50);
        znajdzSciezke.setFocusable(false);
        znajdzSciezke.setHorizontalTextPosition(JButton.CENTER);
        znajdzSciezke.setVerticalTextPosition(JButton.CENTER);
        znajdzSciezke.setBackground(new Color(0xAFD6D1));
        znajdzSciezke.setBorder(BorderFactory.createEmptyBorder());
        znajdzSciezke.setFont(poppins);
        znajdzSciezke.setVisible(false);
        znajdzSciezke.addActionListener(this);
        add(znajdzSciezke);

        JPanel labiryntPanel = new WyswietlLabirynt(graf);
        labiryntPanel.setBackground(Color.white);
        labiryntPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE + 5));
        pudelkoNaLabirynt.add(labiryntPanel);

        panel.setBounds(0, 0, 1000, 500);
        add(panel);

        fileChooser = new JFileChooser();
        setVisible(true);

        dane = new JPanel();
        dane.setBounds(0, 0, 1000, 1000);
        dane.setFont(poppins);
        dane.setBackground(Color.white);
        dane.setLayout(null);


        tytulSekcji = new JLabel("Wczytane parametry labiryntu:");
        tytulSekcji.setBounds(100, 0, 300, 30);
        tytulSekcji.setFont(poppins.deriveFont(17f));
        dane.add(tytulSekcji);

        zmienP = new JButton("Zmień");
        zmienP.setBounds(420, 50, 70, 30);
        zmienP.setFocusable(false);
        zmienP.setHorizontalTextPosition(JButton.CENTER);
        zmienP.setVerticalTextPosition(JButton.CENTER);
        zmienP.setBackground(new Color(0xAFD6D1));
        zmienP.setBorder(BorderFactory.createEmptyBorder());
        zmienP.setFont(poppins);
        dane.add(zmienP);

        zmienK = new JButton("Zmień");
        zmienK.setBounds(420, 100, 70, 30);
        zmienK.setFocusable(false);
        zmienK.setHorizontalTextPosition(JButton.CENTER);
        zmienK.setVerticalTextPosition(JButton.CENTER);
        zmienK.setBackground(new Color(0xAFD6D1));
        zmienK.setBorder(BorderFactory.createEmptyBorder());
        zmienK.setFont(poppins);
        dane.add(zmienK);

        wspolrzedneP = new JLabel("Współrzędne punktu startowego:");
        wspolrzedneP.setBounds(100, 50, 300, 30);
        wspolrzedneP.setFont(poppins);

        wspolrzedneK = new JLabel("Współrzędne punktu końcowego:");
        wspolrzedneK.setBounds(100, 100, 300, 30);
        wspolrzedneK.setFont(poppins);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nazwaPliku) {
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                nazwa.setVisible(true);
                nazwa.setText("   " + selectedFile.getName());
                znajdzSciezke.setVisible(true);

                Wczytywacz wczytywacz = new Wczytywacz();
                wczytywacz.wczytajPlik(selectedFile.getAbsolutePath(), graf);

                startWiersz = graf.pobierzStart().pobierzWiersz();
                startKolumna = graf.pobierzStart().pobierzKolumna();
                koniecWiersz = graf.pobierzKoniec().pobierzWiersz();
                koniecKolumna = graf.pobierzKoniec().pobierzKolumna();

                wspolrzedneP.setText("Współrzędne punktu startowego: (" + startKolumna + ", " + startWiersz + ")");
                wspolrzedneK.setText("Współrzędne punktu końcowego: (" + koniecKolumna + ", " + koniecWiersz + ")");

                dane.add(wspolrzedneP);
                dane.add(wspolrzedneK);
                wczytaneDane.add(dane);

                pudelkoNaLabirynt.setPreferredSize(new Dimension(graf.pobierzLiczbeKolumn() * 10 + 5, graf.pobierzLiczbeWierszy() * 10 + 5));
                pudelkoNaLabirynt.add(Wczytywacz.wczytanyLabirynt);
                revalidate();
                repaint();
            }
        } else if (e.getSource() == znajdzSciezke) {
            List<Komorka> sciezka = Rozwiazywacz.znajdzNajkrotszaSciezke(graf);
            if (!sciezka.isEmpty()) {
                pudelkoNaLabirynt.removeAll();
                JPanel labiryntPanel = new WyswietlLabirynt(graf);
                labiryntPanel.setBackground(Color.white);
                labiryntPanel.setPreferredSize(new Dimension(Wczytywacz.kolumny * 10 + 5, Wczytywacz.wiersze * 10 + 5));
                pudelkoNaLabirynt.add(labiryntPanel);
                zapiszRozwiazanie.setEnabled(true);
                revalidate();
                repaint();
            }
        } else if (e.getSource() == zapiszRozwiazanie){
            ZapisLabiryntu zapisLabiryntu = new ZapisLabiryntu();

            String _nazwaPliku = nazwa.getText();
            _nazwaPliku = _nazwaPliku.replace(" ", "");
            _nazwaPliku = _nazwaPliku.replaceAll("\\.(?=[^\\.]+$).*", "");

            zapisLabiryntu.zapiszRozwiazanieJakoObraz(graf, "rozwiazanie_" + _nazwaPliku + ".png");
            zapisLabiryntu.zapiszRozwiazanieDoPlikuTekstowego(graf, "rozwiazanie_" + _nazwaPliku + ".txt");
        }
    }
}
