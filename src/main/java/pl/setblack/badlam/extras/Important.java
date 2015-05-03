package pl.setblack.badlam.extras;

import pl.setblack.badlam.Lambda;
import pl.setblack.badlam.analysis.SmartDisplay;

/**
 * Created by Kanapka on 5/3/2015.
 */
public class Important {
    //x y ( x x)
    private static Lambda intercourse( Lambda x ) {
        return (y)-> x.apply(y).apply(x.apply(x));
    }

    public static void main (final String ... args) {
        System.out.println(SmartDisplay.displayLambda(Important::intercourse));
    }

}
