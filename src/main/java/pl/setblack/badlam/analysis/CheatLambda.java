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
        System.out.println("PRESENT:" + presentContent() + " =>   "+context.context +" @ " +x.toString() );
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
                            (lambda)->{
                                System.out.println("x="+x.getClass());
                                System.out.println("lambda="+lambda.getClass());
                                System.out.println("this=" + this.getClass());
                                if ( this.variableName.equals(lambda.variableName)) {
                                    return wrapInBrackets(presentContent() + " " + presentContent());
                                } else {
                                    return wrapInBrackets(presentContent() + " " + subContext.presentLambda(lambda));
                                }
                            },
                                        variableName, subContext);
                //return new EvaluatedLamLbda(wrapInBrackets(presentContent() + " ?" ), variableName, context);
            }
            //subContext.presentLambda( x);
            /*if ( context.autoExpandLevel > 0 ) {
                context.autoExpandLevel--;
                return new EvaluatedLambda(this.context.presentLambdaInside(presentContent(), x), variableName, context);
            } else {
                final DisplayContext subContext =context.subContext();

                return new EvaluatedLambda(context.presentLambdaInside(presentContent(), x), variableName, subContext);

               // return new EvaluatedLambda(  x.present() , variableName, context.subContext());
            }*/

            //

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
