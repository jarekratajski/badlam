package pl.setblack.badlam.analysis;

import pl.setblack.badlam.Lambda;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Kanapka on 5/5/2015.
 */
public class LaziedLambda extends CheatLambda {

    final Function<CheatLambda, String> displaySupplier;

    public LaziedLambda(Function<CheatLambda, String> displaySupplier, String variableName, DisplayContext ctx ) {
        super(variableName, ctx);
        this.displaySupplier = displaySupplier;
    }

    @Override
    public Lambda apply(Lambda x) {
        return super.apply(x);
    }

    @Override
    public String presentContent() {
        return this.displaySupplier.apply(this);
    }
}
