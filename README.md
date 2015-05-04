# badlam
=======
Lambda analysis package

This small research project may be used to play with
untyped lambda calculus in Java.
Main goal is to create develop unintrusive display utility
for Lambdas (SmartDisplay)).

#Sample code
    Lambda trueLambda =
                (x)->(y)->x;
    Lambda falseLambda =
                (x)->(y)->y;
    Lambda and =
                (p)->(q)->p.apply(q).apply(p);
    Lambda result = and.apply(trueLambda).apply(falseLambda);
    System.out.println(
        SmartDisplay.displayLambda(result) );


