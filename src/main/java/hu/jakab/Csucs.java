package hu.jakab;

import java.util.*;

/// <summary>
/// A Csucs tartalmaz egy állapotot, a Csucs mélységét, és a Csucs szülőjét.
/// Így egy Csucs egy egész utat reprezentál a start Csucsig.
/// </summary>
class Csucs {
    // A Csucs tartalmaz egy állapotot, a mélységét és a szülőjét
    AbsztraktAllapot allapot;
    int melyseg;
    Csucs szulo; // A szülőkön felfelé haladva a start Csucsig jutok.

    // Konstruktor:
    // A belső állapotot beállítja a start Csucsra.
    // A hívó felelőssége, hogy a kezdő állapottal hívja meg.
    // A start Csucs mélysége 0, szülője nincs.
    public Csucs(AbsztraktAllapot kezdőÁllapot) {
        allapot = kezdőÁllapot;
        melyseg = 0;
        szulo = null;
    }

    // Egy új gyermek Csucsot készít.
    // Erre még meg kell hívni egy alkalmazható operátor is, csak azután lesz kész.
    public Csucs(Csucs szulo) throws CloneNotSupportedException {
        allapot = (AbsztraktAllapot) szulo.allapot.clone();
        melyseg = szulo.melyseg + 1;
        this.szulo = szulo;
    }

    public Csucs getSzulo() {
        return szulo;
    }

    public int getMelyseg() {
        return melyseg;
    }

    public boolean terminalisCsucsE() {
        return allapot.celAllapotE();
    }

    public int operatorokSzama() {
        return allapot.operatorokSzama();
    }

    public boolean szuperOperator(int i) {
        return allapot.szuperOperator(i);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Csucs cs = (Csucs) obj;
        return allapot.equals(cs.allapot);
    }

    @Override
    public int hashCode() {
        return allapot.hashCode();
    }

    @Override
    public String toString() {
        return allapot.toString();
    }

    // Alkalmazza az összes alkalmazható operátort.
    // Visszaadja az így előálló új Csucsokat.
    public List<Csucs> kiterjesztes() throws CloneNotSupportedException {
        List<Csucs> ujCsucsok = new ArrayList<>();
        for (int i = 0; i < operatorokSzama(); i++) {
            // Új gyermek Csucsot készítek.
            Csucs ujCsucs = new Csucs(this);
            // Kiprobálom az i.dik alapoperátort. Alkalmazható?
            if (ujCsucs.szuperOperator(i)) {
                // Ha igen, hozzáadom az újakhoz.
                ujCsucsok.add(ujCsucs);
            }
        }
        return ujCsucsok;
    }
}
