package dk.sdu.cbse.common.utility;

public class UnorderedPair<T> {
    private final T k;
    private final T v;

    public UnorderedPair(T k, T v) {
        this.k = k;
        this.v = v;
    }

    public T getK() {
        return k;
    }

    public T getV() {
        return v;
    }

    public Object[] getValues() {
        return new Object[]{k, v};
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UnorderedPair<?> that) {
            return ((k == that.k & v == that.v) |
                    (k == that.v & v == that.k));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return k.hashCode() + v.hashCode();
    }
}
