# badlam
=======
Lambda analysis package

This small research project may be used to play with
untyped lambda calculus in Java.
Main goal is to create develop unintrusive display utility
for Lambdas (SmartDisplay)).
==
There is strong guarantee that this software does not work.
(because if undecidability).
==
Nevertheless, for some simple Lambdas it might be working.
Please, refer to unit test for usage patterns.


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

Presentation at Voxxed Days Zurich (March 2016)
==
https://www.youtube.com/watch?v=Dun8ewSeX6c
