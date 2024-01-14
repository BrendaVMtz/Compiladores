package practica.ipn.abstracto;


public class StmtIf extends Statement {
    final Expression condition;
    final Statement thenBranch;
    final Statement elseBranch;

    StmtIf(Expression condition, Statement thenBranch, Statement elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public void ejecutar(TablaSimbolos tabla) {
        Object conditionResult = condition.resolver(tabla);
        
        if ((Boolean) conditionResult instanceof Boolean) {
            if ((Boolean) condition.resolver(tabla)) {
            thenBranch.ejecutar(tabla);
            }
            else {
                elseBranch.ejecutar(tabla);
            }
        }
    }
}
