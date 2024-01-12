package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

public class StmtExpression extends Statement {
    final Expression expression;

    StmtExpression(Expression expression) {
        this.expression = expression;
    }
}
