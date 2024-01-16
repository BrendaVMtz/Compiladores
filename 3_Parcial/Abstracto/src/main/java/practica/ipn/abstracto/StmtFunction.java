package practica.ipn.abstracto;

import practica.ipn.abstracto.Token;

import java.util.List;

public class StmtFunction extends Statement {
    final Token name;
    final List<Token> params;
    final StmtBlock body;

    StmtFunction(Token name, List<Token> params, StmtBlock body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }

    @Override
    public void ejecutar(TablaSimbolos tabla) {
        // TODO: Tienes que insertar lo que te devuelva la función, resolver el body y los params (Idea)
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
