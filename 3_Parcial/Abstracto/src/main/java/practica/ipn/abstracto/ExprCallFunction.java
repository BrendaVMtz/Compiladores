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
}
