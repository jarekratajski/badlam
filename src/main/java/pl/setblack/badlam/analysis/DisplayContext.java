package pl.setblack.badlam.analysis;

import pl.setblack.badlam.Lambda;

import java.util.*;
import pl.setblack.badass.Politician;


public class DisplayContext implements Cloneable {
    private final Map<String, CheatLambda> usedTerms = new HashMap<>();
    private HashSet<Lambda> seenLambdas = new HashSet<>();
    private String context = "";

    String possibleTerms = "xyzabcdefghijklmnopqrstuvw";
    String lambdaSymbol ="L";
    boolean useBracket = true;


    String presentLambda(Lambda l) {
        return presentLambdaInside("", l);
    }

    String presentLambdaInside(String init, Lambda l) {
        if (!wasSeen(l)) {
            markSeen(l);
            Optional<CheatLambda> lambdaOpt = nextPossibleTermLambda();
            if (lambdaOpt.isPresent()) {
                Lambda result = l.apply(lambdaOpt.get());
                if (result instanceof CheatLambda) {
                    final String wasContext = context;
                    context = "";
                    return wasContext + init + result.present();
                } else {
                    return presentLambdaInside(init, result);
                }
            } else {
                return "...";
            }
        } else {
            return "(...)";
        }
    }

    private boolean wasSeen(Lambda l) {
        return seenLambdas.contains(l);
    }

    private void markSeen(Lambda l) {
        //System.out.println("added"+l.hashCode());
        this.seenLambdas.add(l);
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
