package hu.jakab;


public abstract class AbsztraktAllapot implements Cloneable {

    // Determines if the internal state is a specific state.
    // If true, returns true, otherwise false.
    public abstract boolean allapotE();

    // Determines if the internal state is a goal state.
    // If true, returns true, otherwise false.
    public abstract boolean celAllapotE();

    // Returns the number of basic operators.
    public abstract int operatorokSzama();

    // Accesses all operators through the super operator.
    // Returns true if the i-th basic operator can be applied to the internal state.
    // Should be called from a for loop starting from 0 up to the number of basic operators, e.g.:
    // for (int i = 0; i < allapot.operatorokSzama(); i++) {
    //     AbsztraktAllapot klon = (AbsztraktAllapot) allapot.clone();
    //     if (klon.szuperOperator(i)) {
    //         System.out.println("Operator " + i + " can be applied to state " + allapot);
    //     }
    // }
    public abstract boolean szuperOperator(int i);

    // Clones the state. This is needed because some operator effects need to be undone.
    // The simplest way is to clone the state, apply the operator,
    // if there is an issue, revert to the original state.
    // If there are no issues, continue the search from the cloned state.
    // This uses shallow cloning. If shallow cloning is sufficient, this method does not need to be overridden in the child class.
    // If deep cloning is needed, this method must be overridden.
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Only override if using a memorable backtrack or depth search.
    // Otherwise, this default implementation is sufficient.
    // Technically, this is a hook method that can be overridden without violating the OCP.
    @Override
    public boolean equals(Object a) {
        return false;
    }

    // If two instances are equal, their hash codes must also be equal.
    // Therefore, if the equals method is overridden, this should also be overridden.
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
