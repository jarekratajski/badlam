package pl.setblack.badlam;

import org.junit.Test;
import pl.setblack.badlam.analysis.SmartDisplay;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static pl.setblack.badlam.Boolean.*;

public class BooleanTest {
    @Test
    public void shouldDisplayTrueLambda() {
        String val = SmartDisplay.displayLambda(Boolean.TRUELAMBDA);
        assertThat(val, equalTo("Lx.Ly.x"));
    }

    @Test
    public void shouldDisplayFalseLambda() {
        String val = SmartDisplay.displayLambda(Boolean.FALSELAMBDA);
        assertThat(val, equalTo("Lx.Ly.y"));
    }

    @Test
    public void shouldDisplayAndWithXYX() {
        String val = SmartDisplay.displayLambda(Boolean.AND);
        assertThat(val, equalTo("Lx.Ly.((x y) x)"));
    }

    @Test
    public void shouldDisplayAndWithXYXWithoutBrackets() {
        String val = SmartDisplay
                .get()
                .withoutBrackets()
                .display(Boolean.AND);
        assertThat(val, equalTo("Lx.Ly.x y x"));
    }

    @Test
     public void shouldDisplayAndWithPQPWithoutBrackets() {
        String val = SmartDisplay
                .get()
                .withoutBrackets()
                .withSymbols(SmartDisplay.PQ)
                .display(Boolean.AND);
        assertThat(val, equalTo("Lp.Lq.p q p"));
    }

    @Test
    public void shouldDisplayTrueAndFalse() {
        Lambda result = AND.apply(TRUELAMBDA).apply(FALSELAMBDA);
        String val = SmartDisplay
                .get()
                .withoutBrackets()
                .display(result);
        assertThat(val, equalTo("Lx.Ly.y"));
    }
    @Test
    public void shouldDisplayTrueOrFalse() {
        Lambda result = OR.apply(TRUELAMBDA).apply(FALSELAMBDA);
        String val = SmartDisplay
                .get()
                .withoutBrackets()
                .display(result);
        assertThat(val, equalTo("Lx.Ly.x"));
    }

    @Test
    public void toLambdaShouldReturnFalseForFalseLambda() {
        assertThat( toBoolean(FALSELAMBDA), equalTo(java.lang.Boolean.FALSE ));
    }

    @Test
    public void toLambdaShouldReturnTrueForTrueLambda() {
        assertThat( toBoolean(TRUELAMBDA), equalTo(java.lang.Boolean.TRUE ));
    }
    @Test(expected = IllegalStateException.class)
    public void anyLambdaShouldThrowException() {
        toBoolean((x) -> (y) -> (z) -> z);
    }
}