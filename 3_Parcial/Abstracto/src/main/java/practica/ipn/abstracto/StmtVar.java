package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

public class StmtVar extends Statement {
    final Token name;
    final Expression initializer;

    StmtVar(Token name, Expression initializer) {
        this.name = name;
        this.initializer = initializer;
    }

    @Override
    public void ejecutar(TablaSimbolos tabla) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (!tabla.existeIdentificador(name.lexema)) {
            Object res = initializer.resolver(tabla);
            tabla.asignar(name.lexema, res);
        }
    }
}
