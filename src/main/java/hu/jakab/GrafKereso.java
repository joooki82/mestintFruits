package hu.jakab;

import java.util.*;

/// <summary>
/// Minden gráfkereső algoritmus őse.
/// A gráfkeresőknek csak a Keresés metódust kell megvalósítaniuk.
/// Ez visszaad egy terminális csúcsot, ha talált megoldást, egyébként null értékkel tér vissza.
/// A terminális csúcsból a szülő referenciákon felfelé haladva áll elő a megoldás.
/// </summary>
abstract class GrafKereso {
    private Csucs startCsucs; // A start csúcs csúcs.

    // Minden gráfkereső a start csúcsból kezd el keresni.
    public GrafKereso(Csucs startCsucs) {
        this.startCsucs = startCsucs;
    }

    // Jobb, ha a start csúcs privát, de a gyermek osztályok lekérhetik.
    protected Csucs getStartCsucs() {
        return startCsucs;
    }

    /// Ha van megoldás, azaz van olyan út az állapottér gráfban,
    /// ami a start csúcsból egy terminális csúcsba vezet,
    /// akkor visszaad egy megoldást, egyébként null.
    /// A megoldást egy terminális csúcsként adja vissza.
    /// Ezen csúcs szülő referenciáin felfelé haladva adódik a megoldás fordított sorrendben.
    public abstract Csucs kereses();

    /// <summary>
    /// Kiíratja a megoldást egy terminális csúcs alapján.
    /// Feltételezi, hogy a terminális csúcs szülő referenciáján felfelé haladva eljutunk a start csúcshoz.
    /// A csúcsok sorrendjét megfordítja, hogy helyesen tudja kiírni a megoldást.
    /// Ha a csúcs null, akkor kiírja, hogy nincs megoldás.
    /// </summary>
    /// <param name="egyTerminalisCsucs">
    /// A megoldást képviselő terminális csúcs vagy null.
    /// </param>
    public void megoldasKiirasa(Csucs egyTerminalisCsucs) {
        if (egyTerminalisCsucs == null) {
            System.out.println("Nincs megoldás");
            return;
        }
        // Meg kell fordítani a csúcsok sorrendjét.
        Stack<Csucs> megoldas = new Stack<>();
        Csucs aktCsucs = egyTerminalisCsucs;
        while (aktCsucs != null) {
            megoldas.push(aktCsucs);
            aktCsucs = aktCsucs.getSzulo();
        }
        // Megfordítottuk, lehet kiírni.
        for (Csucs akt : megoldas) {
            System.out.println(akt);
        }
    }
}
