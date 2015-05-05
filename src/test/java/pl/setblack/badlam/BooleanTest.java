package pl.setblack.badlam;

import org.junit.Before;
import org.junit.Test;
import pl.setblack.badlam.analysis.SmartDisplay;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static pl.setblack.badlam.Boolean.*;

public class BooleanTest {
    @Test
    public void shouldDisplayTrueLambda() {
        String val = SmartDisplay.displayLambda(Boolean.trueLambda);
        assertThat(val, equalTo("Lx.Ly.x"));
    }

    @Test
    public void shouldDisplayFalseLambda() {
        String val = SmartDisplay.displayLambda(Boolean.falseLambda);
        assertThat(val, equalTo("Lx.Ly.y"));
    }

    @Test
    public void shouldDisplayAndWithXYX() {
        String val = SmartDisplay.displayLambda(Boolean.and);
        assertThat(val, equalTo("Lx.Ly.((x y) x)"));
    }

    @Test
    public void shouldDisplayAndWithXYXWithoutBrackets() {
        String val = SmartDisplay
                .get()
                .withoutBrackets()
                .display(Boolean.and);
        assertThat(val, equalTo("Lx.Ly.x y x"));
    }

    @Test
     public void shouldDisplayAndWithPQPWithoutBrackets() {
        String val = SmartDisplay
                .get()
                .withoutBrackets()
                .withSymbols(SmartDisplay.PQ)
                .display(Boolean.and);
        assertThat(val, equalTo("Lp.Lq.p q p"));
    }

    @Test
    public void shouldDisplayTrueAndFalse() {
        Lambda result = and.apply(trueLambda).apply(falseLambda);
        String val = SmartDisplay
                .get()
                .withoutBrackets()
                .display(result);
        assertThat(val, equalTo("Lx.Ly.y"));
    }
    @Test
    public void shouldDisplayTrueOrFalse() {
        Lambda result = OR.apply(trueLambda).apply(falseLambda);
        String val = SmartDisplay
                .get()
                .withoutBrackets()
                .display(result);
        assertThat(val, equalTo("Lx.Ly.x"));
    }
}