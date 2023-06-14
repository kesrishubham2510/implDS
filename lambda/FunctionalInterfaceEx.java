package lambda;

/*
   * An interface with exactly one abstract method is called a functional interface
*/

@FunctionalInterface
interface functionWithLambdaExpression{
    String merge(String a, String b);
}

public class FunctionalInterfaceEx {
    
    public static void main(String[] args) {
        functionWithLambdaExpression f = (a,b)->a+" "+b;
    }
}
