package practica.ipn.abstracto;


class ExprLiteral extends Expression {
    final Object value;

    ExprLiteral(Object value) {
        this.value = value;
    }

    @Override
    public Object resolver(TablaSimbolos tabla) {
        return value;
    }
    
    @Override
    public String toString() {
        return value.toString();
    }
}
