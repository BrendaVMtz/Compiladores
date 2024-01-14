package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

public class ExprUnary extends Expression{
    final Token operator;
    final Expression right;

    ExprUnary(Token operator, Expression right) {
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Object resolver(TablaSimbolos tabla) {
        Object der = right.resolver(tabla);
        
        if ((Integer) der instanceof Integer) {
            switch (operator.lexema) {
                case "+": return der;
                case "-": return (-1) * (Integer) der;
                default: return null;
            }
        }
        
        return null;
    }
}
