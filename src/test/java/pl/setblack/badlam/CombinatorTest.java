package pl.setblack.badlam;

import org.junit.Test;
import pl.setblack.badlam.analysis.DisplayContext;
import pl.setblack.badlam.analysis.SmartDisplay;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;


public class CombinatorTest {

    @Test
    public void shouldDisplayAutocall() {
       System.out.println(SmartDisplay.get().display(Combinator.AUTOCALL));

    }

    @Test
    public void shouldDisplayYCombinator() {
        System.out.println(SmartDisplay.get().withSymbols(SmartDisplay.FXY).display(
                Combinator.Y));
    }
}