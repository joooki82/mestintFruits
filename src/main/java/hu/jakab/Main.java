package hu.jakab;

public class Main {
    public static void main(String[] args) {
        AbsztraktAllapot kezdoAllapot = new Allapot(13, 46, 59);
        Csucs startCsucs = new Csucs(kezdoAllapot);

        SzelessegiKereses bfs = new SzelessegiKereses(startCsucs);
        Csucs result = bfs.kereses();

        bfs.megoldasKiirasa(result);
    }
}
