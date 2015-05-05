package pl.setblack.badlam;

/**
 * Created by Kanapka on 5/4/2015.
 */
public class Combinator {
    public static final Lambda AUTOCALL= x->x.apply(x);
    public static final Lambda Y = f-> (AUTOCALL.apply(y->f.apply(v->y.apply(y).apply(v))));
    //original \lambda f.(\lambda x.f\ (x\ x))\ (\lambda x.f\ (x\ x))
    public static final Lambda FIXED_POINT_COMBINATOR =
            (Lambda f)-> {
                Lambda inner = x->f.apply( x.apply(x));
                return inner.apply(inner);
            };

}
