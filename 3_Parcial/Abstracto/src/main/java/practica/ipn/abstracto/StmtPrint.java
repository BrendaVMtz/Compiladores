package practica.ipn.abstracto;


public class StmtPrint extends Statement {
    final Expression expression;

    StmtPrint(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void ejecutar(TablaSimbolos tabla) {
        //Object res = expression.resolver(tabla);
        System.out.println(expression.resolver(tabla));
    }
}
