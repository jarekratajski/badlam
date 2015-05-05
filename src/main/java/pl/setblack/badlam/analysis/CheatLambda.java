package pl.setblack.badlam.analysis;


import pl.setblack.badlam.Lambda;

public class CheatLambda implements Lambda {
    final DisplayContext context;
    final String variableName;

    public CheatLambda(String variableName, DisplayContext ctx) {
        this.variableName = variableName;
        this.context = ctx;
    }

    private String wrapInBrackets(String expression) {
        if (context.useBracket) {
            return "(" + expression + ")";
        }
        return expression;
    }

    @Override
    public Lambda apply(Lambda x) {
        if (x instanceof CheatLambda) {
            return new EvaluatedLambda(wrapInBrackets(
                    presentContent()
                            + " "
                            + ((CheatLambda) x).presentContent()
            ), variableName, context);

        } else {
            if (!context.seenLambdaClasses.containsKey(x.getClass())) {
                context.seenLambdaClasses.put(x.getClass(), x);
                final DisplayContext subContext = context.subContext();
                return new EvaluatedLambda(wrapInBrackets(presentContent() + " " + subContext.presentLambda(x)), variableName, subContext);
            } else {
                final DisplayContext subContext = context.subContext();
                return new LaziedLambda(
                        (lambda) -> {
                            String resultRight = subContext.presentLambda(lambda);
                            if (resultRight==null) {
                                if ( lambda instanceof LaziedLambda ) {
                                    if ( lambda.variableName.equals(this.variableName)) {
                                        //this is surely bad - but works for simple cases
                                        return wrapInBrackets(presentContent() + " " + presentContent());
                                    }

                                }
                                final String key = ("["+lambda.getClass().toString()+"]");
                                subContext.unknownLambdas.put(key,lambda);
                                return wrapInBrackets(presentContent() + " " + key);
                            } else {
                                return wrapInBrackets(presentContent() + " " + resultRight);
                            }
                        },
                        variableName, subContext);
                //return new EvaluatedLamLbda(wrapInBrackets(presentContent() + " ?" ), variableName, context);
            }
        }
    }

    @Override
    public String present() {
        return presentContent();
    }


    public String presentContent() {
        return variableName;
    }

    @Override
    public String toString() {
        return "CheatLambda{" +
                //"context=" + context +
                ", variableName='" + variableName + '\'' +
                '}';
    }
}
