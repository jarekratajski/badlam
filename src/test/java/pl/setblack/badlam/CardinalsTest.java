package pl.setblack.badlam;

import org.junit.Test;
import pl.setblack.badlam.analysis.SmartDisplay;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by Kanapka on 5/12/2015.
 */
public class CardinalsTest {
    @Test
    public void shouldDisplayZero() {
        String val = SmartDisplay.get().withSymbols(SmartDisplay.FXY).display(Cardinals.ZERO);
        assertThat(val, equalTo("Lf.Lx.x"));
    }

    @Test
    public void shouldDisplayOne() {
        Lambda possiblyOne = Cardinals.SUCC.apply(Cardinals.ZERO);
        String val = SmartDisplay.get().withSymbols(SmartDisplay.FXY).display(possiblyOne);
        assertThat(val, equalTo("Lf.Lx.(f x)"));
    }

    @Test
    public void shouldDisplay4() {
        Lambda possibly4 = Cardinals.fromInteger(4);
        String val = SmartDisplay.get().withSymbols(SmartDisplay.FXY).display(possibly4);
        assertThat(val, equalTo("Lf.Lx.(f (f (f (f x))))"));
    }

    @Test
    public void shouldAdd4To5() {
        Lambda result = Cardinals.PLUS.apply(Cardinals.fromInteger(4)).apply(Cardinals.fromInteger(5));

        assertThat(Cardinals.toInteger(result), equalTo(9));
    }

    @Test
    public void shouldMultiply3By7() {
        Lambda result = Cardinals.MULT.apply(Cardinals.fromInteger(3)).apply(Cardinals.fromInteger(7));

        assertThat(Cardinals.toInteger(result), equalTo(21));
    }

    @Test
    public void shouldConverTo0() {
        assertThat(Cardinals.toInteger(Cardinals.ZERO), equalTo(0));
    }

    @Test
    public void shouldConverTo1() {
        Lambda possiblyOne = Cardinals.SUCC.apply(Cardinals.ZERO);
        assertThat(Cardinals.toInteger(possiblyOne), equalTo(1));
    }

    @Test
    public void shouldConverTo2() {
        Lambda possiblyTwo = Cardinals.SUCC.apply(Cardinals.SUCC.apply(Cardinals.ZERO));
        assertThat(Cardinals.toInteger(possiblyTwo), equalTo(2));
    }

    @Test
    public void shouldConverTo5() {
        Lambda possibly4 = Cardinals.fromInteger(4);
        Lambda maybe5 = Cardinals.SUCC.apply(possibly4);
        assertThat(Cardinals.toInteger(maybe5), equalTo(5));
    }

    /** test that is (a*b)==0 then (a==0) or (b==0)*/
    @Test
    public void theoremTest() {
        Lambda left = a->b->Cardinals.IS_ZERO.apply(Cardinals.MULT.apply(a).apply(b));
        Lambda right = a->b->Boolean.OR.apply(Cardinals.IS_ZERO.apply(a)).apply(Cardinals.IS_ZERO.apply(b));
        Lambda theorem = a->b->
                Boolean.OR.apply(Boolean.NOT.apply(left.apply(a).apply(b)))
                .apply(right.apply(a).apply(b));
        Lambda result = theorem.apply(Cardinals.fromInteger(5)).apply(Cardinals.fromInteger(1));
        assertThat(Boolean.toBoolean(result), equalTo(java.lang.Boolean.TRUE));
    }



}