import java.io.*;

public class OdczytBin {
    OdczytBin(String nazwaPliku, Labirynt graf) throws IOException {
        PlikBinarny plikBinarny = odczytNaglowka(nazwaPliku);
        if (plikBinarny != null) {
            odczytsekcjiKodujacej(nazwaPliku, plikBinarny, graf);

            if (plikBinarny.getSolutionOffset() != 0) {
                odczytSekcjiRozwiazania(nazwaPliku, plikBinarny, graf);
            }
        }
    }

    private PlikBinarny odczytNaglowka(String nazwaPliku) {
        PlikBinarny plikBinarny = new PlikBinarny();
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
                plikBinarny.setReserved(dataInputStream.readNBytes(12));
                plikBinarny.setCounter(Integer.reverseBytes(dataInputStream.readInt()));
                plikBinarny.setSolutionOffset(Integer.reverseBytes(dataInputStream.readInt()));
                plikBinarny.setSeparator(dataInputStream.readByte());
                plikBinarny.setWall(dataInputStream.readByte());
                plikBinarny.setPath(dataInputStream.readByte());
                dataInputStream.close();
            } catch (EOFException _) {
            }
        } catch (IOException ex) {
            return null;
        }
        return plikBinarny;
    }

    private void odczytsekcjiKodujacej(String nazwaPliku, PlikBinarny plikBinarny, Labirynt graf) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(nazwaPliku));
        dataInputStream.skipBytes(40);

        int kolumna = 0;
        int wiersz = 0;

        for (int i = 0; i < plikBinarny.getCounter(); i++) {
            byte separator = dataInputStream.readByte();
            byte value = dataInputStream.readByte();
            byte counter = dataInputStream.readByte();

            for (int j = 0; j < Byte.toUnsignedInt(counter) + 1; j++) {
                if (separator != plikBinarny.getSeparator()) {
                    return;
                }

                char symbol;
                if (wiersz == plikBinarny.getEntryY() - 1 && kolumna == plikBinarny.getEntryX() - 1) {
                    symbol = 'P';
                } else if (wiersz == plikBinarny.getExitY() - 1 && kolumna == plikBinarny.getExitX() - 1) {
                    symbol = 'K';
                } else {
                    if (value == plikBinarny.getWall()) {
                        symbol = 'X';
                    } else if (value == plikBinarny.getPath()) {
                        symbol = ' ';
                    } else {
                        continue;
                    }
                }

                Komorka komorka = new Komorka(wiersz, kolumna, symbol == 'X');
                graf.dodajKomorke(komorka);

                if (symbol == ' ') {
                    if (kolumna > 0) {
                        Komorka left = graf.pobierzKomorke(wiersz, kolumna - 1);
                        if (left != null && !left.pobierzSciane()) {
                            graf.dodajPolaczenie(komorka, left);
                        }
                    }
                    if (wiersz > 0) {
                        Komorka up = graf.pobierzKomorke(wiersz - 1, kolumna);
                        if (up != null && !up.pobierzSciane()) {
                            graf.dodajPolaczenie(komorka, up);
                        }
                    }
                }

                if (symbol == 'P') {
                    graf.ustawStart(komorka);
                } else if (symbol == 'K') {
                    graf.ustawKoniec(komorka);
                }

                kolumna++;
                if (kolumna >= plikBinarny.getColumns()) {
                    kolumna = 0;
                    wiersz++;
                }
            }
        }
        Wczytywacz.wiersze = wiersz;
        Wczytywacz.kolumny = plikBinarny.getColumns();
        dataInputStream.close();
    }

    private void odczytSekcjiRozwiazania(String nazwaPliku, PlikBinarny plikBinarny, Labirynt graf) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(nazwaPliku));
        dataInputStream.skipBytes(plikBinarny.getSolutionOffset() + 4);

        int wiersz = plikBinarny.getEntryY() - 1;
        int kolumna = plikBinarny.getEntryX() - 1;

        while (dataInputStream.available() > 0) {
            byte dir = dataInputStream.readByte();
            byte cou = dataInputStream.readByte();

            for (int j = 0; j < Byte.toUnsignedInt(cou) + 1; j++) {
                switch (dir) {
                    case 0x4E:
                        wiersz--;
                        break;
                    case 0x45:
                        kolumna++;
                        break;
                    case 0x53:
                        wiersz++;
                        break;
                    case 0x57:
                        kolumna--;
                        break;
                    default:
                        throw new IOException("Nieznany kierunek: " + dir);
                }
                Komorka komorka = graf.pobierzKomorke(wiersz, kolumna);
                if (komorka == null || komorka.pobierzSciane()) {
                    throw new IOException("Nieprawidłowa komórka w ścieżce rozwiązania: " + kolumna + ", " + wiersz);
                }
                komorka.ustawCzySciezka(true);
            }
        }
        dataInputStream.close();
    }
}
