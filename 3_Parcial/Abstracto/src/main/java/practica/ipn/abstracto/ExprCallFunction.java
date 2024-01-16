package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

import java.util.List;
import practica.ipn.abstracto.Expression;

public class ExprCallFunction extends Expression{
    final Expression callee;
    // final Token paren;
    final List<Expression> arguments;

    ExprCallFunction(Expression callee, /*Token paren,*/ List<Expression> arguments) {
        this.callee = callee;
        // this.paren = paren;
        this.arguments = arguments;
    }

    @Override
    public Object resolver(TablaSimbolos tabla) {
        // TODO: Esta es una idea
        for (Expression argument : arguments) {
            argument.resolver(tabla);
        }
        return callee.resolver(tabla);
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
