package pl.setblack.badlam;

public class Boolean {
    public static final Lambda trueLambda =
            (x)->(y)->x;

    public static final Lambda falseLambda =
            (x)->(y)->y;

    public static final Lambda and =
            (p)->(q)->p.apply(q).apply(p);

}
