package pl.setblack.badlam.analysis;

import pl.setblack.badlam.Lambda;

public class SmartDisplay {
    private static final String allTerms = "abcdefghijklmnopqrstuvwxyz";
    public static final String PQ = generate("pq");
    public static final String FXY = generate("fxyz");
    public static final String FMN = generate("fmn");
    public static final String FG = generate("fg");
    private final DisplayContext displayContext;


    private static String generate(String startSymbols) {
        return generate(startSymbols, allTerms);
    }

    private static String generate(String startSymbols, String terms) {
        if ( ! startSymbols.isEmpty() ) {
            String first = startSymbols.substring(0,1);
            return first + generate(startSymbols.substring(1),
                    terms.replaceAll(first, ""));
        }
        return terms;

    }

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

    public SmartDisplay withoutBrackets() {
        final DisplayContext cloned = displayContext.copy();
        cloned.useBracket = false;
        return new SmartDisplay(cloned);
    }

    public SmartDisplay withSymbols(String symbols) {
        final DisplayContext cloned = displayContext.copy();
        cloned.possibleTerms = symbols;
        return new SmartDisplay(cloned);
    }
}
