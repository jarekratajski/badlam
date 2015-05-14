package pl.setblack.badlam;

public class Boolean {
    public static final Lambda TRUELAMBDA =
            (x)->(y)->x;

    public static final Lambda FALSELAMBDA =
            (x)->(y)->y;

    public static final Lambda AND =
            (p)->(q)->p.apply(q).apply(p);
    public static final Lambda OR =
            (p)->(q)->p.apply(p).apply(q);

    public static final Lambda NOT =
            (p)->(a)->(b)->p.apply(b).apply(a);


    public static boolean toBoolean( final Lambda lambda ) {
        final Lambda test1 = new Lambda() {
            @Override
            public Lambda apply(Lambda x) {
                throw new IllegalStateException();
            }
        };

        final Lambda test2 = new Lambda() {
            @Override
            public Lambda apply(Lambda x) {
                throw new IllegalStateException();
            }
        };

        Lambda result = lambda.apply(test1).apply(test2);
        if ( result == test1) {
            return true;
        }
        if ( result == test2) {
            return false;
        }
        throw new IllegalStateException();
    }
}
