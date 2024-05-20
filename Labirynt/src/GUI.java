import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {
    JFileChooser fileChooser;
    JLabel tytul;
    JLabel komunikaty;
    JPanel menu;
    JPanel wczytaneDane;
    JPanel wczytanyLabirynt;
    JTextField nazwa;
    JButton znajdzSciezke;
    JButton nazwaPliku;
    JButton zapiszRozwiazanie;

    Labirynt labirynt = new Labirynt();

    GUI() {
        setTitle("Rozwiązywacz labiryntu");
        setSize(900, 1200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        Font syne = new Font("Arial", Font.BOLD, 35);

        try{
            syne = Font.createFont(Font.TRUETYPE_FONT, new File("Labirynt/Syne-ExtraBold.ttf")).deriveFont(35f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Labirynt/Syne-ExtraBold.ttf")));
        }catch (IOException | FontFormatException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        Font poppins = new Font("Arial", Font.PLAIN, 12);

        try{
            poppins = Font.createFont(Font.TRUETYPE_FONT, new File("Labirynt/Poppins-Medium.ttf")).deriveFont(13f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Labirynt/Poppins-Medium.ttf")));
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
        wczytaneDane.setBounds(0, 300, 1000, 300);
        wczytaneDane.setFont(poppins);
        wczytaneDane.setBackground(Color.white);
        wczytaneDane.setLayout(null);

        wczytanyLabirynt = new JPanel();
        //wczytanyLabirynt.setPreferredSize(new Dimension(2000, 2000));
        wczytanyLabirynt.setFont(poppins);
        wczytanyLabirynt.setBackground(Color.white);
        wczytanyLabirynt.setLayout(new BoxLayout(wczytanyLabirynt, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(wczytanyLabirynt);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(wczytanyLabirynt);

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
        //zapiszRozwiazanie.addActionListener(this);
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
        //scrollKomunikaty.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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
        add(znajdzSciezke);


        JPanel labiryntPanel = new WyswietlLabirynt(labirynt);
        //labiryntPanel.setBounds(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        labiryntPanel.setBackground(Color.white);
        labiryntPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        wczytanyLabirynt.add(labiryntPanel);

        panel.setBounds(0, 0, 1000, 500);
        add(panel);

        fileChooser = new JFileChooser();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nazwaPliku) {
            int returnValue = fileChooser.showOpenDialog(null);


            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                nazwa.setVisible(true);
                nazwa.setText("   " + selectedFile.getName());
                Wczytywacz Wczytywacz = new Wczytywacz();
                try {
                    new Wczytywacz.Odczyt(selectedFile.getAbsolutePath(), labirynt);
                    //Wczytywacz.rysujLabirynt(selectedFile.getAbsolutePath());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                wczytaneDane.add(Wczytywacz.dane);

                wczytanyLabirynt.setPreferredSize(new Dimension(Wczytywacz.kolumny *10 +5, Wczytywacz.wiersze*10 +5));
                System.out.println(Wczytywacz.kolumny);
                System.out.println(Wczytywacz.wiersze);
                wczytanyLabirynt.add(Wczytywacz.wczytanyLabirynt);
                znajdzSciezke.setVisible(true);
                revalidate();
                repaint();
            }
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}