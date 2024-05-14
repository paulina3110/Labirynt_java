import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.FileChooser;

public class GUI extends JFrame implements ActionListener {

    JLabel tytul;
    JPanel menu;
    JPanel wczytaneDane;
    JTextField nazwa;
    JButton wyswietl;
    JButton znajdzSciezke;
    JButton zmienPkt;
    JButton nazwaPliku;
    Labirynt labirynt;
    JPanel panelLabiryntu;

    GUI() {
        setTitle("Rozwiązywacz labiryntu");
        setSize(900, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        Font syne = null;

        try{
            syne = Font.createFont(Font.TRUETYPE_FONT, new File("Syne-ExtraBold.ttf")).deriveFont(35f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Syne-ExtraBold.ttf")));
        }catch (IOException | FontFormatException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        Font poppins = null;

        try{
            poppins = Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Medium.ttf")).deriveFont(13f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Medium.ttf")));
        }catch (IOException | FontFormatException e){
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
        wczytaneDane.setBounds(0, 200, 1000, 1000);
        wczytaneDane.setFont(poppins);
        wczytaneDane.setBackground(Color.white);
        wczytaneDane.setLayout(null);

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

        nazwa = new JTextField();
        nazwa.setBounds(300, 15, 300, 40);
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
        tytul.setBounds(-60, 100, 1000, 60);
        add(tytul);

        znajdzSciezke = new JButton("Znajdź najkrótszą ścieżkę");
        znajdzSciezke.setBounds(100, 380, 200, 50);
        znajdzSciezke.setFocusable(false);
        znajdzSciezke.setHorizontalTextPosition(JButton.CENTER);
        znajdzSciezke.setVerticalTextPosition(JButton.CENTER);
        znajdzSciezke.setBackground(new Color(0xAFD6D1));
        znajdzSciezke.setBorder(BorderFactory.createEmptyBorder());
        znajdzSciezke.setFont(poppins);
        znajdzSciezke.setVisible(false);
        panel.add(znajdzSciezke);

        panel.setBounds(0, 0, 1000, 500);
        add(panel);

        panelLabiryntu = new JPanel();

        panelLabiryntu.setBounds(50, 550, 500, 500);
        panelLabiryntu.setFont(poppins);
        panelLabiryntu.setLayout(null);
        panelLabiryntu.setVisible(false);
        panelLabiryntu.setBackground(Color.WHITE);

        panel.add(panelLabiryntu);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nazwaPliku) {
            nazwa.setVisible(true);
            wyborPliku fileChooserThread = new wyborPliku(panelLabiryntu);
            fileChooserThread.start();
        }
    }

    public static void main(String[] args) {
        new GUI();
    }

    class wyborPliku extends Thread {
        private JPanel panelLabiryntu;

        wyborPliku(JPanel panelLabiryntu) {
            this.panelLabiryntu = panelLabiryntu;
        }

        @Override
        public void run() {
            JFXPanel panel = new JFXPanel();
            Platform.runLater(() -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Wybierz plik");
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    nazwa.setText("   "  +selectedFile.getName());
                    wczytywacz Wczytywacz = new wczytywacz();
                    try {
                        new wczytywacz.Odczyt(selectedFile.getAbsolutePath());
                        Wczytywacz.rysujLabirynt(selectedFile.getAbsolutePath());
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    wczytaneDane.add(Wczytywacz.dane);
                    znajdzSciezke.setVisible(true);
                    panelLabiryntu.add(new WyswietlLabirynt(wczytywacz.wczytanyLabirynt));
                    panelLabiryntu.setVisible(true);
                    add(panelLabiryntu);
                    panelLabiryntu.revalidate();
                    panelLabiryntu.repaint();
                }
            });
        }
    }
}