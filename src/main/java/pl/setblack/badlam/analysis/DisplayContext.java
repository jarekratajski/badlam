package pl.setblack.badlam.analysis;

import pl.setblack.badlam.Lambda;

import java.util.*;
import java.util.function.Supplier;

import pl.setblack.badass.Politician;


public class DisplayContext implements Cloneable {
    private Map<String, CheatLambda> usedTerms = new HashMap<>();
    private Map<Lambda,String> seenLambdas = new HashMap<>();
    Map<Class<? extends Lambda>, Lambda> seenLambdaClasses = new HashMap<>();
    String context = "";
    Map<String,Lambda> unknownLambdas = new HashMap<>();


    String possibleTerms = "xyzabcdefghijklmnopqrstuvw";
    String lambdaSymbol ="L";
    boolean useBracket = true;
    int autoExpandLevel = 10;

    String presentLambda(Lambda l) {
        String val=  presentLambdaInside("", l).get();
        for ( final String  unknown : unknownLambdas.keySet()) {
            Lambda newLambda = unknownLambdas.get(unknown);
            String newVal = seenLambdas.get(newLambda);
            if ( newVal!=null) {
                val = val.replace(unknown, newVal);
            }

        }
        System.out.println("Result is:"+ val);
        return val;
    }

    DisplayContext subContext() {
        final DisplayContext cloned = this.copy();
       /* cloned.usedTerms = new HashMap<>();
        cloned.usedTerms.putAll( this.usedTerms);*/
        cloned.context = "";
        return cloned;
    }

    Supplier<String> presentLambdaInside(String init, Lambda l) {
        if (!wasSeen(l)) {
            markSeen(l);
            Optional<CheatLambda> lambdaOpt = nextPossibleTermLambda();
            if (lambdaOpt.isPresent()) {
                Lambda result = l.apply(lambdaOpt.get());

                if (result instanceof CheatLambda) {
                    final String wasContext = context;
                    context = "";

                    return see(l,wasContext + init + result.present());
                } else {
                    return see(l, presentLambdaInside(init, result).get());
                }
            } else {
                return see(l, "(...)");
            }
        } else {
            String seenVal = seenLambdas.get(l);
            if (seenVal == null) {
                return ()->seenVal;
            } else {


                return ()-> {

                    return seenLambdas.get(l);
                };
            }

        }
    }

    private Supplier<String> see( Lambda l, String val) {
        this.seenLambdas.put(l,val);
        return ()->val;
    }

    private boolean wasSeen(Lambda l) {
        return seenLambdas.containsKey(l);
    }

    private void markSeen(Lambda l) {

        this.seenLambdas.put(l,null);
    }


    Optional<CheatLambda> nextPossibleTermLambda() {
        return nextPossibleTerm().map(term -> {
            context += lambdaSymbol + term + ".";
            return usedTerms.get(term);
        });


    }

    Optional<String> nextPossibleTerm() {
        return Arrays.asList(new Integer[]{0, 1, 2, 3}).stream().map(x -> nextPossibleTerm(x)).filter(x -> x.isPresent()).map(x->x.get()).findFirst();

    }

    Optional<String> nextPossibleTerm(int k) {
        String suffix = k > 0 ? String.valueOf(k) : "";
        for (int l = 0; l < possibleTerms.length(); l++) {
            String t = String.valueOf(possibleTerms.charAt(l)) + suffix;
            if (!usedTerms.containsKey(t)) {
                usedTerms.put(t, new CheatLambda(t, this));
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }

    DisplayContext copy() {
        return Politician.beatAroundTheBush(
                () -> (DisplayContext)super.clone()
        );
    }
}
