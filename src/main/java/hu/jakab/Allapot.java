package hu.jakab;

import java.util.Objects;

public class Allapot extends AbsztraktAllapot {
    int apples, pears, peaches;

    public Allapot(int apples, int pears, int peaches) {
        this.apples = apples;
        this.pears = pears;
        this.peaches = peaches;
    }

    @Override
    public boolean allapotE() {
        // A valid intermediate state can have any combination of fruits.
        // The method should simply confirm that no counts are negative.
        return apples >= 0 && pears >= 0 && peaches >= 0;
    }

    @Override
    public boolean celAllapotE() {
        return (apples == 0 && pears == 0) || (apples == 0 && peaches == 0) || (pears == 0 && peaches == 0);
    }

    @Override
    public int operatorokSzama() {
        return 3;
    }

    @Override
    public boolean szuperOperator(int i) {
        switch (i) {
            case 0:
                return op1();
            case 1:
                return op2();
            case 2:
                return op3();
            default:
                return false;
        }
    }

    private boolean op1() {
        if (!preOp1()) return false;
        // Clone current state to revert if necessary
        Allapot oldState = (Allapot) this.clone();
        // State transition: exchange apples and pears for peaches
        apples--;
        pears--;
        peaches += 2;
        // Postcondition check
        if (allapotE()) return true;
        // Revert state transition
        this.apples = oldState.apples;
        this.pears = oldState.pears;
        this.peaches = oldState.peaches;
        return false;
    }

    private boolean preOp1() {
        return apples > 0 && pears > 0;
    }

    private boolean op2() {
        if (!preOp2()) return false;
        // Clone current state to revert if necessary
        Allapot oldState = (Allapot) this.clone();
        // State transition: exchange apples and peaches for pears
        apples--;
        peaches--;
        pears += 2;
        // Postcondition check
        if (allapotE()) return true;
        // Revert state transition
        this.apples = oldState.apples;
        this.peaches = oldState.peaches;
        this.pears = oldState.pears;
        return false;
    }

    private boolean preOp2() {
        return apples > 0 && peaches > 0;
    }

    private boolean op3() {
        if (!preOp3()) return false;
        // Clone current state to revert if necessary
        Allapot oldState = (Allapot) this.clone();
        // State transition: exchange pears and peaches for apples
        pears--;
        peaches--;
        apples += 2;
        // Postcondition check
        if (allapotE()) return true;
        // Revert state transition
        this.pears = oldState.pears;
        this.peaches = oldState.peaches;
        this.apples = oldState.apples;
        return false;
    }

    private boolean preOp3() {
        return pears > 0 && peaches > 0;
    }

    @Override
    public Object clone() {
        return new Allapot(apples, pears, peaches);
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

    @Override
    public String toString() {
        return "Allapot [apples=" + apples + ", pears=" + pears + ", peaches=" + peaches + "]";
    }
}
