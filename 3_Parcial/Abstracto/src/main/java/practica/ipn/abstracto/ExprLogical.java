package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

public class ExprLogical extends Expression{
    final Expression left;
    final Token operator;
    final Expression right;

    ExprLogical(Expression left, Token operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    

    @Override
    public Object resolver(TablaSimbolos tabla) {
        Object izq = left.resolver(tabla);
        Object der = right.resolver(tabla);
        
        // TODO: Hacer comparaciones para los demás tipos
        if ((Integer) izq instanceof Integer && (Integer) der instanceof Integer) {
            switch (operator.lexema) {
                case "==": return (Integer) izq == (Integer) der;
                case "!=": return (Integer) izq != (Integer) der;
                case "<": return (Integer) izq < (Integer) der;
                case ">": return (Integer) izq > (Integer) der;
                case "<=": return (Integer) izq <= (Integer) der;
                case ">=": return (Integer) izq >= (Integer) der;
                default: return null;
            }
        }
        
        return null;
    }
}

