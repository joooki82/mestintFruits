package hu.jakab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SzelessegiKereses extends GrafKereso {
    Queue<Csucs> nyilt; // Nyílt csúcsok halmaza.
    List<Csucs> zart; // Zárt csúcsok halmaza.
    boolean korFigyeles; // Ha hamis, végtelen ciklusba eshet.

    public SzelessegiKereses(Csucs startCsucs, boolean korFigyeles) {
        super(startCsucs);
        nyilt = new LinkedList<>();
        nyilt.add(startCsucs); // kezdetben csak a start csúcs nyílt
        zart = new ArrayList<>(); // kezdetben a zárt csúcsok halmaza üres
        this.korFigyeles = korFigyeles;
    }

    // A körfigyelés alapértelmezett értéke igaz.
    public SzelessegiKereses(Csucs startCsucs) {
        this(startCsucs, true);
    }

    // A megoldás keresés itt indul.
    public Csucs kereses() {
        // Ha nem kell körfigyelés, akkor sokkal gyorsabb az algoritmus.
        if (korFigyeles) return terminalisCsucsKereses();
        return terminalisCsucsKeresesGyorsan();
    }

    private Csucs terminalisCsucsKereses() {
        // Amíg a nyílt csúcsok halmaza nem üres.
        while (!nyilt.isEmpty()) {
            // Ez a legkisebb mélységű nyílt csúcs.
            Csucs C = nyilt.poll();
            // Ezt kiterjesztem.
            List<Csucs> ujCsucsok;
            try {
                ujCsucsok = C.kiterjesztes();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            for (Csucs D : ujCsucsok) {
                // Ha megtaláltam a terminális csúcsot, akkor kész vagyok.
                if (D.terminalisCsucsE()) return D;
                // Csak azokat az új csúcsokat veszem fel a nyíltak közé,
                // amik nem szerepeltek még sem a zárt, sem a nyílt csúcsok halmazában.
                // A Contains a Csucs osztályban megírt Equals metódust hívja.
                if (!zart.contains(D) && !nyilt.contains(D)) nyilt.add(D);
            }
            // A kiterjesztett csúcsot átminősítem zárttá.
            zart.add(C);
        }
        return null;
    }

    // Ezt csak akkor szabad használni, ha biztos, hogy az állapottér gráfban nincs kör!
    // Különben valószínűleg végtelen ciklust okoz.
    private Csucs terminalisCsucsKeresesGyorsan() {
        while (!nyilt.isEmpty()) {
            Csucs C = nyilt.poll();
            List<Csucs> ujCsucsok;
            try {
                ujCsucsok = C.kiterjesztes();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            for (Csucs D : ujCsucsok) {
                if (D.terminalisCsucsE()) return D;
                // Ha nincs kör, akkor felesleges megnézni, hogy D volt-e már nyíltak vagy a zártak közt.
                nyilt.add(D);
            }
            // Ha nincs kör, akkor felesleges C-t zárttá minősíteni.
        }
        return null;
    }
}
