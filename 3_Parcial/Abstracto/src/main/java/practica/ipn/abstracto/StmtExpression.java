package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

public class StmtExpression extends Statement {
    final Expression expression;

    StmtExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void ejecutar(TablaSimbolos tabla) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        expression.resolver(tabla);
    }
}
