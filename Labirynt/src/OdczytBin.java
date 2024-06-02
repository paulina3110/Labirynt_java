import java.io.*;

public class OdczytBin{

    PlikBinarny plikBinarny = new PlikBinarny();

    public void odczytNaglowka(String nazwaPliku){
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(new File(nazwaPliku)));

            try {
                plikBinarny.setFileId(Integer.reverseBytes(dataInputStream.readInt()));
                plikBinarny.setEscape(dataInputStream.readByte());
                plikBinarny.setColumns(Short.reverseBytes(dataInputStream.readShort()));
                plikBinarny.setLines(Short.reverseBytes(dataInputStream.readShort()));
                plikBinarny.setEntryX(Short.reverseBytes(dataInputStream.readShort()));
                plikBinarny.setEntryY(Short.reverseBytes(dataInputStream.readShort()));
                plikBinarny.setExitX(Short.reverseBytes(dataInputStream.readShort()));
                plikBinarny.setExitY(Short.reverseBytes(dataInputStream.readShort()));
                plikBinarny.setRes(dataInputStream.readNBytes(12));
                plikBinarny.setCounter(Integer.reverseBytes(dataInputStream.readInt()));
                plikBinarny.setSolutionOffset(Integer.reverseBytes(dataInputStream.readInt()));
                plikBinarny.setSeparator(dataInputStream.readByte());
                plikBinarny.setWall(dataInputStream.readByte());
                plikBinarny.setPath(dataInputStream.readByte());
                dataInputStream.close();

            } catch (EOFException _) {
            }
        } catch (IOException _) {
        }

    }

    public void odczytSekcjiKodujacej(String nazwaPliku, Labirynt graf) {
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(nazwaPliku));
            dataInputStream.skipBytes(40); // Skip header

            int row = 0;
            int col = 0;
            int counter = plikBinarny.getCounter();

            for (int i = 0; i < counter; i++) {
                byte separator = dataInputStream.readByte();
                byte value = dataInputStream.readByte();
                byte count = dataInputStream.readByte();

                for (int j = 0; j < (count == 0 ? 1 : count); j++) {
                    char symbol;
                    if (value == plikBinarny.getWall()) {
                        symbol = 'X';
                    } else if (value == plikBinarny.getPath()) {
                        symbol = ' ';
                    } else {
                        continue;
                    }

                    Komorka komorka = new Komorka(row, col, symbol == 'X');
                    graf.dodajKomorke(komorka);

                    if (symbol == ' ') {
                        if (col > 0) {
                            Komorka left = graf.pobierzKomorke(row, col - 1);
                            if (left != null && !left.pobierzSciane()) {
                                graf.dodajPolaczenie(komorka, left);
                            }
                        }
                        if (row > 0) {
                            Komorka up = graf.pobierzKomorke(row - 1, col);
                            if (up != null && !up.pobierzSciane()) {
                                graf.dodajPolaczenie(komorka, up);
                            }
                        }
                    }

                    col++;
                    if (col >= plikBinarny.getColumns()) {
                        col = 0;
                        row++;
                    }
                }
            }

            dataInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void odczytSekcjiRozwiazania(String nazwaPliku, Labirynt graf) {
        if (plikBinarny.getSolutionOffset() == 0) {
            return;
        }

        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(nazwaPliku));
            dataInputStream.skipBytes(plikBinarny.getSolutionOffset());

            int currentX = plikBinarny.getEntryX();
            int currentY = plikBinarny.getEntryY();

            try {
                while (true) {
                    int direction = Integer.reverseBytes(dataInputStream.readInt());
                    byte steps = dataInputStream.readByte();
                    int stepCount = steps == 0 ? 1 : steps;

                    for (int i = 0; i < stepCount; i++) {
                        switch (direction) {
                            case 'N':
                                currentY--;
                                break;
                            case 'E':
                                currentX++;
                                break;
                            case 'S':
                                currentY++;
                                break;
                            case 'W':
                                currentX--;
                                break;
                            default:
                                throw new IOException("Nieznany kierunek: " + direction);
                        }

                        Komorka komorka = graf.pobierzKomorke(currentY - 1, currentX - 1);
                        if (komorka != null && !komorka.pobierzSciane()) {
                            graf.dodajKomorke(komorka);
                        } else {
                            throw new IOException("Nieprawidłowa komórka w ścieżce rozwiązania: " + currentX + ", " + currentY);
                        }
                    }
                }
            } catch (EOFException _) {
            }

            dataInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    OdczytBin(String nazwaPliku, Labirynt graf) throws FileNotFoundException {
        odczytNaglowka(nazwaPliku);
        odczytSekcjiKodujacej(nazwaPliku, graf);
        odczytSekcjiRozwiazania(nazwaPliku, graf);

    }
}