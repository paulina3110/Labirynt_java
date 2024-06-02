import java.util.*;

public class Rozwiazywacz {
    public static List<Komorka> znajdzNajkrotszaSciezke(Labirynt labirynt) {
        Komorka start = labirynt.pobierzStart();
        Komorka koniec = labirynt.pobierzKoniec();
        Map<Komorka, Komorka> poprzednicy = new HashMap<>();
        Queue<Komorka> kolejka = new LinkedList<>();

        poprzednicy.put(start, null);
        kolejka.add(start);

        while (!kolejka.isEmpty()) {
            Komorka obecna = kolejka.poll();

            if (obecna.equals(koniec)) {
                return odtworzSciezke(poprzednicy, koniec);
            }

            for (Komorka sasiad : labirynt.pobierzGraf().get(obecna)) {
                if (!poprzednicy.containsKey(sasiad)) {
                    poprzednicy.put(sasiad, obecna);
                    kolejka.add(sasiad);
                }
            }
        }

        return Collections.emptyList();
    }

    private static List<Komorka> odtworzSciezke(Map<Komorka, Komorka> poprzednicy, Komorka koniec) {
        List<Komorka> sciezka = new ArrayList<>();
        for (Komorka komorka = koniec; komorka != null; komorka = poprzednicy.get(komorka)) {
            sciezka.add(komorka);
        }
        Collections.reverse(sciezka);

        for (Komorka komorka : sciezka) {
            komorka.ustawCzySciezka(true);
        }

        return sciezka;
    }
}

