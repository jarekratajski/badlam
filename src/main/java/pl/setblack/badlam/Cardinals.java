package pl.setblack.badlam;

public class Cardinals {
    public static final Lambda ZERO = (f)->x->(x);

    public static final Lambda SUCC = (n)->(f)->(x)->f.apply(n.apply(f).apply(x));

    public static final Lambda fromInteger(int x) {
        assert x >=0 ;
        return applySucc(x, ZERO);
    }

    public static final Lambda applySucc(int x, Lambda f) {
        if (x > 0) {
            return applySucc( x-1, SUCC.apply(f));
        }
        return f;
    }

    public static Integer toInteger(Lambda cardinal) {
        final Lambda someX = new Lambda() {
            @Override
            public Lambda apply(Lambda x) {
                throw new IllegalStateException();
            }
        };
        final SelfApply someF = new SelfApply(someX);
        cardinal.apply(someF).apply(someX);
        return someF.counter;
    }

    private static final class SelfApply implements Lambda {
        int counter = 0;
        private final Lambda tested;

        public SelfApply(Lambda tested) {
            this.tested = tested;
        }

        @Override
        public Lambda apply(Lambda x) {
            if (x == tested) {
                counter=1;
                return this;
            } else if ( x == this) {
                counter++;
                return this;
            }
            throw new IllegalStateException();
        }
    }
}
