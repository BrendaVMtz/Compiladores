package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

public class ExprGet extends Expression{
    final Expression object;
    final Token name;

    ExprGet(Expression object, Token name) {
        this.object = object;
        this.name = name;
    }
}
