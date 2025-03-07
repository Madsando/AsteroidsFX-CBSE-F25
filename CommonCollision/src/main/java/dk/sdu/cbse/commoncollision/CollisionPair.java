package dk.sdu.cbse.commoncollision;

public class CollisionPair<T> {
    private final T k;
    private final T v;

    public CollisionPair(T k, T v) {
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
        if (o instanceof CollisionPair that) {
            return ((k == that.k & v == that.v) ||
                    (k == that.v & v == that.k));
        } else {
            return false;
        }
    }
}
