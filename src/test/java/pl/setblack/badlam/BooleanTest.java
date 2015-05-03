package pl.setblack.badlam;

import org.junit.Test;
import pl.setblack.badlam.analysis.SmartDisplay;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BooleanTest {
    @Test
    public void shouldDisplayTrueLambda() {
        String val = SmartDisplay.displayLambda(Boolean::trueLambda);
        assertThat(val, equalTo("Lx.Ly.x"));
    }

    @Test
    public void shouldDisplayFalseLambda() {
        String val = SmartDisplay.displayLambda(Boolean::falseLambda);
        assertThat(val, equalTo("Lx.Ly.y"));
    }
}