package pl.setblack.badlam.analysis;

import pl.setblack.badlam.Lambda;

public class SmartDisplay {
    private final DisplayContext displayContext;

    public SmartDisplay(final DisplayContext displayContext) {
        this.displayContext = displayContext;
    }

    public static String displayLambda(Lambda l) {
        return get().display(l);
    }

    public static SmartDisplay get() {
        return new SmartDisplay(new DisplayContext());
    }

    public String display(Lambda l) {
        return displayContext.presentLambda(l);
    }

    public SmartDisplay withLambdaSymbol(final String symbol ) {
        final DisplayContext cloned = displayContext.copy();
        cloned.lambdaSymbol = symbol;
        return new SmartDisplay(cloned);
    }
}
