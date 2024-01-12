package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

class ExprVariable extends Expression {
    final Token name;

    ExprVariable(Token name) {
        this.name = name;
    }
}