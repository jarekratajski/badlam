package pl.setblack.badlam.analysis;


import pl.setblack.badlam.Lambda;

public class EvaluatedLambda extends CheatLambda {

    final String evaluated;

    public EvaluatedLambda(String evaluated, String var, DisplayContext ctx) {
        super(var,ctx);
        this.evaluated = evaluated;
    }

    @Override
    public Lambda apply(Lambda x) {
        return super.apply(x);
    }

    @Override
    public String presentContent() {
        return evaluated;
    }
}
