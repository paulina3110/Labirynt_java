import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JPanel;

public class Wczytywacz{
    JPanel dane;
    JPanel wczytanyLabirynt;
    JLabel tytulSekcji;
    static JLabel wspolrzedneP;
    static JLabel wspolrzedneK;
    JButton zmienP;
    JButton zmienK;
    static int wysokosc;
    static int szerokosc;
    public static int wiersze;
    public static int kolumny;

    Wczytywacz(){
        //Font poppins = null;

        Font poppins = new Font("Arial", Font.PLAIN, 12);
        try{
            poppins = Font.createFont(Font.TRUETYPE_FONT, new File("Labirynt/Poppins-Medium.ttf")).deriveFont(13f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Labirynt/Poppins-Medium.ttf")));
        }catch (IOException | FontFormatException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        dane = new JPanel();
        dane.setBounds(0, 0, 1000, 1000);
        dane.setFont(poppins);
        dane.setBackground(Color.white);
        dane.setLayout(null);

        wczytanyLabirynt = new JPanel();
        wczytanyLabirynt.setBounds(0, 0, 1000, 1000);
        wczytanyLabirynt.setBackground(Color.white);
        wczytanyLabirynt.setLayout(null);

        tytulSekcji = new JLabel("Wczytane parametry labiryntu:");
        tytulSekcji.setBounds(100, 0, 300, 30);
        tytulSekcji.setFont(poppins.deriveFont(17f));
        dane.add(tytulSekcji);

        wspolrzedneP = new JLabel("Współrzędne punktu startowego:");
        wspolrzedneP.setBounds(100, 50, 300, 30);
        wspolrzedneP.setFont(poppins);
        dane.add(wspolrzedneP);

        wspolrzedneK = new JLabel("Współrzędne punktu końcowego:");
        wspolrzedneK.setBounds(100, 100, 300, 30);
        wspolrzedneK.setFont(poppins);
        dane.add(wspolrzedneK);

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
    }


    /*public void rysujLabirynt(String nazwaPliku) {
        JPanel labiryntPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    File plik = new File(nazwaPliku);
                    Scanner in = new Scanner(plik);

                    int row = 0;
                    while (in.hasNextLine()) {
                        String line = in.nextLine();
                        for (int i = 0; i < line.length(); i++) {
                            wysokosc = line.length();
                            char symbol = line.charAt(i);
                            if (symbol == ' ') {
                                g.setColor(Color.WHITE);
                            } else if (symbol == 'x' || symbol == 'X') {
                                g.setColor(Color.BLACK);
                            } else if (symbol == 'K' || symbol == 'P') g.setColor(new Color(0xAFD6D1));
                            g.fillRect(i * 20, row * 20, 20, 20);
                        }
                        row++;
                    }
                    szerokosc = row;

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        labiryntPanel.setBounds(0, 0, 2000, 2000);
        labiryntPanel.setBackground(Color.white);
        wczytanyLabirynt.add(labiryntPanel);
    }
*/

    public static class Odczyt {
        Odczyt(String nazwaPliku, Labirynt labirynt) throws FileNotFoundException {
            File plik = new File(nazwaPliku);
            Scanner in = new Scanner(plik);
            int row = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                kolumny = line.length();
                for (int i = 0; i < line.length(); i++) {
                    char symbol = line.charAt(i);
                    labirynt.wstawWartosc(row, i, symbol);
                    if (symbol == 'P') {
                        wspolrzedneP.setText("Współrzędne punktu startowego:  (" + i + ", " + row + ")");
                    } else if (symbol == 'K') {
                        wspolrzedneK.setText("Współrzędne punktu końcowego:  (" + i + ", " + row + ")");
                    }
                }
                row++;
            }
            wiersze = row;
        }
    }



}
