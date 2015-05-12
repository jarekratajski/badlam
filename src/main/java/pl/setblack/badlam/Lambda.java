package pl.setblack.badlam;

@FunctionalInterface
public interface Lambda {
    Lambda apply( Lambda x);

    default String present() {
        return "\u03BBx.?" ;
    }
}
