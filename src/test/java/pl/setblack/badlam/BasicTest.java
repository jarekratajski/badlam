package pl.setblack.badlam;

import org.junit.Test;
import pl.setblack.badlam.analysis.SmartDisplay;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class BasicTest {
    @Test
    public void shouldDisplayIdentityLambda() {
        String val = SmartDisplay.displayLambda(Basic::identity);
        assertThat(val, equalTo("Lx.x"));
    }

    @Test
    public void shouldDisplayChosenLambdaSymbol() {
        String val = SmartDisplay
                .get()
                .withLambdaSymbol("U")
                .display(Basic::identity);
        assertThat(val, equalTo("Ux.x"));
    }
}