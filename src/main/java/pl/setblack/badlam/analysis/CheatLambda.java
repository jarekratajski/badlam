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
        //System.out.println("PRESENT:" + presentContent());
        if (x instanceof CheatLambda) {
            return new EvaluatedLambda(wrapInBrackets(
                    presentContent()
                            + " "
                            + ((CheatLambda) x).presentContent()
            ), variableName, context);

        } else {
            //return new EvaluatedLambda(variableName + "(" + x.present() + ")", variableName);
            return new EvaluatedLambda(this.context.presentLambdaInside(presentContent(), x), variableName, context);
        }
    }

    @Override
    public String present() {
        return presentContent();
    }


    public String presentContent() {
        return variableName;
    }

}
