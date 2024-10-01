package business.data;

import java.util.ArrayList;
import java.util.List;

public class SimpleListBoolBinaryAdder implements ListBoolBinaryAdder {
    private List<Boolean> ListA;
    private List<Boolean> ListB;
    @Override
    public List<Boolean> add(List<Boolean> a, List<Boolean> b) {
        this.ListA = new ArrayList<>(a);
        this.ListB = new ArrayList<>(b);

        makeListsSameNoBits();

        return rippleAddAAndB();
    }

    @Override
    public List<Boolean> addOne(List<Boolean> a) {
        this.ListA = new ArrayList<>(a);
        List<Boolean> one = List.of(true);
        this.ListB = new ArrayList<>(one);

        makeListsSameNoBits();

        return rippleAddAAndB();
    }

    private void makeListsSameNoBits() {
        while (ListA.size() > ListB.size()) {
            ListB.addFirst(false);
        }
        while (ListB.size() > ListA.size()) {
            ListA.addFirst(false);
        }

    }

    private List<Boolean> rippleAddAAndB() {
        boolean carry = false;
        List<Boolean> result = new ArrayList<>(List.of());

        for (int i = ListA.size()-1; i >= 0; i--) {
            boolean a = ListA.get(i);
            boolean b = ListB.get(i);

            result.addFirst(sum(a,b,carry));
            carry = carryOut(a,b,carry);
        }

        return result;
    }

    private boolean sum(boolean a, boolean b, boolean carryIn) {
        return xor(xor(a, b),carryIn);
    }

    private boolean carryOut(boolean a, boolean b, boolean carryIn) {
        return or(and(carryIn, xor(a,b)),and(a,b));
    }

    private Boolean xor(boolean a, boolean b) {
        return a ^ b;
    }

    private boolean and(boolean a, boolean b) {
        return a & b;
    }

    private boolean or(boolean a, boolean b) {
        return a | b;
    }
}
