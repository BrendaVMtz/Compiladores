package practica.ipn.abstracto;

public class StmtLoop extends Statement {
    final Expression condition;
    final Statement body;

    StmtLoop(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void ejecutar(TablaSimbolos tabla) {
        Object conditionResult = condition.resolver(tabla);
        
        if ((Boolean) conditionResult instanceof Boolean) {
            while ((Boolean) conditionResult) {
                body.ejecutar(tabla);
                conditionResult = condition.resolver(tabla);
            }
        }
    }
}
