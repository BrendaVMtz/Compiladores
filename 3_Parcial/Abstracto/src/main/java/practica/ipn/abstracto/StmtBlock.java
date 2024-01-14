package practica.ipn.abstracto;

import javax.swing.plaf.nimbus.State;
import java.util.List;

public class StmtBlock extends Statement{
    final List<Statement> statements;

    StmtBlock(List<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public void ejecutar(TablaSimbolos tabla) {
        for (Statement statement : statements) {
            statement.ejecutar(tabla);
        }
    }
}
