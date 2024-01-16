package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

public class ExprAssign extends Expression{
    final Token name;
    final Expression value;

    ExprAssign(Token name, Expression value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object resolver(TablaSimbolos tabla) {
        Object res = value.resolver(tabla);
        
        if (tabla.existeIdentificador(name.lexema)) {
            tabla.asignar(name.lexema, res);
        }
        
        return res;
    }
}
