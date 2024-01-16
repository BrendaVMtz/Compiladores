package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

public class ExprBinary extends Expression{
    final Expression left;
    final Token operator;
    final Expression right;

    ExprBinary(Expression left, Token operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Object resolver(TablaSimbolos tabla) {
        Object izq = left.resolver(tabla);
        Object der = right.resolver(tabla);
        
        // TODO: Añadir más tipos
        if (izq instanceof Integer && der instanceof Integer) {
            switch (operator.lexema) {
                case "+":
                    return (Integer) izq + (Integer) der;
                case "-":
                    return (Integer) izq - (Integer) der;
                case "*":
                    return (Integer) izq * (Integer) der;
                case "/":
                    return (Integer) izq / (Integer) der;
                default: return null;
            }
        }
        
        return null;
    }

}
