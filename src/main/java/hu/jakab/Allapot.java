package hu.jakab;

import java.util.Objects;

class Allapot extends AbsztraktAllapot {
    int apples, pears, peaches;

    public Allapot(int apples, int pears, int peaches) {
        this.apples = apples;
        this.pears = pears;
        this.peaches = peaches;
    }

    @Override
    public boolean allapotE() {
        return (apples == 13 && pears == 46 && peaches == 59);
    }

    @Override
    public boolean celAllapotE() {
        return (apples == 0 && pears == 0) || (apples == 0 && peaches == 0) || (pears == 0 && peaches == 0);
    }

    @Override
    public int operatorokSzama() {
        return 3; // Three types of exchanges
    }

    @Override
    public boolean szuperOperator(int i) {
        switch (i) {
            case 0:
                if (apples > 0 && pears > 0) {
                    apples--;
                    pears--;
                    peaches += 2;
                    return true;
                }
                break;
            case 1:
                if (apples > 0 && peaches > 0) {
                    apples--;
                    peaches--;
                    pears += 2;
                    return true;
                }
                break;
            case 2:
                if (pears > 0 && peaches > 0) {
                    pears--;
                    peaches--;
                    apples += 2;
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Allapot(apples, pears, peaches);
    }

    @Override
    public String toString() {
        return "Allapot [apples=" + apples + ", pears=" + pears + ", peaches=" + peaches + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Allapot allapot = (Allapot) obj;
        return apples == allapot.apples && pears == allapot.pears && peaches == allapot.peaches;
    }

    @Override
    public int hashCode() {
        return Objects.hash(apples, pears, peaches);
    }
}
