package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

public class ExprUnary extends Expression{
    final Token operator;
    final Expression right;

    ExprUnary(Token operator, Expression right) {
        this.operator = operator;
        this.right = right;
    }
}
