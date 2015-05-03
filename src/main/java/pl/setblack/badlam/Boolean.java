package pl.setblack.badlam;

public class Boolean {
    public static Lambda trueLambda(Lambda x) {
        return (y)->x;
    }

    public static Lambda falseLambda(Lambda x) {
        return (y)->y;
    }
}
