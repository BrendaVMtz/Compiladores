/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica.ipn.abstracto;
import java.util.Arrays;
import practica.ipn.abstracto.TipoToken;
import practica.ipn.abstracto.Token;
import java.util.List;
import static practica.ipn.abstracto.TipoToken.FOR;
import static practica.ipn.abstracto.TipoToken.IDENTIFIER;
import static practica.ipn.abstracto.TipoToken.OR;
import static practica.ipn.abstracto.TipoToken.SEMICOLON;
import static practica.ipn.abstracto.TipoToken.VAR;

/**
 *
 * @author karel
 */
public class Parser {
    
    private final List<Token> tokens;
    private int i = 0;
    private Token preanalisis;
    public Parser(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
    }
    
    public boolean analisis(){
        List<Statement> lstStmt= null;
        System.out.print("a");
        Declaration(lstStmt);
        if(preanalisis.tipo == TipoToken.EOF){
            System.out.println("Consulta correcta");
            return  true;
        }else {
            System.out.println("Se encontraron errores");
        }
        return false;
    }
    
    private List<Statement> Declaration(List<Statement> lstStmt){
        switch(preanalisis.tipo){
            case FUN:
                match(TipoToken.FUN);
                lstStmt.add(fun_dec());
                lstStmt = Declaration(lstStmt);
            
            case VAR:
                match(TipoToken.VAR);
                lstStmt.add(var_dec());
                lstStmt = Declaration(lstStmt);
            case EOF:
                match(TipoToken.EOF);
            break;
            default:
                Statement();
            break;
        }
        return lstStmt;
    }
    
    private Statement fun_dec(){
        match(TipoToken.FUN);
        return Function();
    }
    
    private Statement var_dec(){
        Token tipo = tokens.get(i);
        match(TipoToken.IDENTIFIER);
        Expression exp = VAR_INT();
        match(TipoToken.SEMICOLON);
        return new StmtVar(tipo, exp);
    }
    
    private Expression VAR_INT(){
        switch(preanalisis.tipo){
            case EQUAL:
                match(TipoToken.EQUAL);
                Expression exp = expression();
            return exp;
        }
        return null;
    }
    private Statement Statement(){
        switch(preanalisis.tipo){
                    case FOR:
                        match(TipoToken.FOR);
                        match(TipoToken.LEFT_PAREN);
                        Statement st= For();
                    return st;
                    case IF:
                        match(TipoToken.IF);
                        Statement st2= If();
                    return st2;
                    case PRINT:
                        match(TipoToken.PRINT);
                        expression();
                        match(TipoToken.SEMICOLON);
                    break;
                    case RETURN:
                        match(TipoToken.RETURN);
                        Expression ex = expression();
                        match(TipoToken.EOF);
                    break;
                    case WHILE:
                        match(TipoToken.WHILE);
                        match(TipoToken.LEFT_PAREN);
                        expression();
                        match(TipoToken.RIGHT_PAREN);
                        Statement();
                    break;
                }
        return null;
    }
    
    private Statement For(){
        
        Statement initializer = For_1();
        Expression condition = For_2();
        Expression increment = expression();
        match(TipoToken.RIGHT_PAREN);
        Statement ex = Statement();

        if (increment != null) {
            ex = new StmtBlock(Arrays.asList(ex, new StmtExpression(increment)));
        }

        if (condition == null) {
            condition = new ExprLiteral(true);
        }

        ex = new StmtLoop(condition, ex);

        if (initializer != null) {
            ex = new StmtBlock(Arrays.asList(initializer, ex));
        }
        return ex;
    }
    
    private Statement For_1(){
        switch (preanalisis.tipo){
            case VAR:
                Statement st = var_dec();
            return st;
            case SEMICOLON:
                match(TipoToken.SEMICOLON);
                return null;
            default:
                Statement ex = expr_stmt();
            return ex;
        }
    }
    
    private Expression For_2(){
        switch (preanalisis.tipo){
            case SEMICOLON:
                match(TipoToken.SEMICOLON);
                return null;
            default:
                Expression ex = expression();
                match(TipoToken.SEMICOLON);
            return ex;
        }
    }
    
    private Statement If(){
        Expression ex = expression();
        match(TipoToken.RIGHT_PAREN);
        Statement st = Statement();
        Statement els = Else();
        StmtIf if_stm = new StmtIf(ex, st, els);
        return if_stm;
    }
    
    private Statement Else(){
        if(preanalisis.tipo == TipoToken.ELSE){
            match(TipoToken.ELSE);
            Statement st = Statement();
            return st;
        }
        return null;
    }
    
    private Statement expr_stmt(){
        Expression ex = expression();
        match(TipoToken.SEMICOLON);
        StmtExpression st = new StmtExpression(ex);
        return st;
    }
    
    private Statement Function(){
        match(TipoToken.IDENTIFIER);
        Token tipo = previous();
        match(TipoToken.LEFT_PAREN);
        List<Token> lstParameters = Parameters();
        match(TipoToken.RIGHT_PAREN);
        StmtBlock stmt = Block();
        return new StmtFunction(tipo, lstParameters, stmt);
    }
    
    private StmtBlock Block(){
        match(TipoToken.LEFT_BRACE);
        List<Statement> Lst = null;
        Lst =(List<Statement>) Declaration(Lst);
        match(TipoToken.RIGHT_BRACE);
        return new StmtBlock(Lst);
    }
    
    private List<Token> Parameters(){
        switch(preanalisis.tipo){
            case IDENTIFIER:
                Token id = tokens.get(i);
                match(TipoToken.IDENTIFIER);
                List <Token> lst = (List<Token>) id;
                lst = Parameters_2(lst);
                Parameters();
            break;
            default:
                break;
        }        
        return null;
    }
    
    private List<Token> Parameters_2(List<Token> lst){
        if(preanalisis.tipo == TipoToken.COMMA){
            match(TipoToken.COMMA);
            Token tipo = tokens.get(i);
            match(TipoToken.IDENTIFIER);
            lst.add(tipo);
            lst = Parameters_2(lst);
            return lst;
        }
        return lst;
    }
    
    private Expression expression(){
        Expression exp = assignment();
        return exp;
    }
    private Expression assignment(){
        Expression exp = logic_or();
        exp = assignment_op(exp);
        return exp;
    }
    
    private Expression logic_or(){
        Expression exp = logic_and();
        exp = logic_or2(exp);
        return exp;
    }
    
    private Expression logic_or2(Expression expr){
        switch(preanalisis.tipo){
            case OR:
                match(TipoToken.OR);
                Expression ex = logic_or();
                return ex;
            default:
                return expr;
        }
    }
    
    private Expression logic_and(){
        Expression exp = Equaly();
        exp = logic_and2(exp);
        return exp;
    }
    
    private Expression logic_and2(Expression expr){
        switch(preanalisis.tipo){
            case AND:
                match(TipoToken.AND);
                Expression ex = logic_and();
                return ex;
            default:
                return expr;
        }
    }
    
    private Expression Equaly(){
        Expression exp = comparison();
        exp = Equaly2(exp);
        return exp;
    }
    
    private Expression Equaly2(Expression ex){
        switch(preanalisis.tipo){
            case BANG_EQUAL:
                Token tipo = tokens.get(i);
                match(TipoToken.BANG_EQUAL);
                Expression exp = comparison();
                Expression exB = new ExprBinary(ex, tipo, exp);
            return Equaly2(exB);
            case EQUAL_EQUAL:    
                Token tipo2 = tokens.get(i);
                match(TipoToken.BANG_EQUAL);
                Expression exp2 = comparison();
                Expression exB2 = new ExprBinary(ex, tipo2, exp2);
            return Equaly2(exB2);
        }
        return ex;
    }
    
    private Expression comparison(){
        Expression exp = Term();
        return exp;
    }
    
    private Expression Term(){
        Expression exp = Factor();
        exp = Term2(exp);
        return exp;
    }
    
    private Expression Term2(Expression exp){
        Expression ex;
        switch(preanalisis.tipo){
            case MINUS:
                match(TipoToken.MINUS);
                ex = Factor();
                ex = Term2(ex);
            return ex;
            case PLUS:
                match(TipoToken.PLUS);
                ex = Factor();
                ex = Term2(ex);
            return ex;
            default:
                return exp;
        }
    }
    
    
    private Expression assignment_op(Expression exp){
        switch(preanalisis.tipo){
            case EQUAL:
                Token tipo = tokens.get(i);
                match(TipoToken.EQUAL);
                Expression aux = expression();
                ExprAssign ex = new ExprAssign(tipo, aux);
                return ex;
            default:
                return exp;
        }        
    }
    
    private Expression term(){
        Expression exp = Factor();
        exp = Term2(exp);
        return exp;
    }
    

    private Expression Factor(){
        Expression expr = unary();
        expr = factor2(expr);
        return expr;
    }

    private Expression factor2(Expression expr){
        switch (preanalisis.tipo){
            case SLASH:
                match(TipoToken.SLASH);
                Token operador = previous();
                Expression expr2 = unary();
                ExprBinary expb = new ExprBinary(expr, operador, expr2);
                return factor2(expb);
            case STAR:
                match(TipoToken.STAR);
                operador = previous();
                expr2 = unary();
                expb = new ExprBinary(expr, operador, expr2);
                return factor2(expb);
        }
        return expr;
    }

    private Expression unary(){
        switch (preanalisis.tipo){
            case BANG:
                match(TipoToken.BANG);
                Token operador = previous();
                Expression expr = unary();
                return new ExprUnary(operador, expr);
            case MINUS:
                match(TipoToken.MINUS);
                operador = previous();
                expr = unary();
                return new ExprUnary(operador, expr);
            default:
                return call();
        }
    }

    private Expression call(){
        Expression expr = primary();
        expr = call2(expr);
        return expr;
    }

    private Expression call2(Expression expr){
        switch (preanalisis.tipo){
            case LEFT_PAREN:
                match(TipoToken.LEFT_PAREN);
                List<Expression> lstArguments = argumentsOptional();
                match(TipoToken.RIGHT_PAREN);
                ExprCallFunction ecf = new ExprCallFunction(expr, lstArguments);
                return call2(ecf);
        }
        return expr;
    }

    private Expression primary(){
        switch (preanalisis.tipo){
            case TRUE:
                match(TipoToken.TRUE);
                return new ExprLiteral(true);
            case FALSE:
                match(TipoToken.FALSE);
                return new ExprLiteral(false);
            case NULL:
                match(TipoToken.NULL);
                return new ExprLiteral(null);
            case NUMBER:
                match(TipoToken.NUMBER);
                Token numero = previous();
                return new ExprLiteral(numero.lexema);
            case STRING:
                match(TipoToken.STRING);
                Token cadena = previous();
                return new ExprLiteral(cadena.lexema);
            case IDENTIFIER:
                match(TipoToken.IDENTIFIER);
                Token id = previous();
                return new ExprVariable(id);
            case LEFT_PAREN:
                match(TipoToken.LEFT_PAREN);
                Expression expr = expression();
                // Tiene que ser cachado aquello que retorna
                match(TipoToken.RIGHT_PAREN);
                return new ExprGrouping(expr);
        }
        return null;
    }


    private void match(TipoToken tt){
        if(preanalisis.tipo ==  tt){
            i++;
            preanalisis = tokens.get(i);
        }
        else{
            String message = "Error en la línea " +
                    preanalisis.posicion +
                    ". Se esperaba " + preanalisis.tipo +
                    " pero se encontró " + tt;
        }
    }

    private List<Expression> argumentsOptional(){
        List<Expression> ex = (List<Expression>) expression();
        ex = arguments(ex);
        return ex;
        
        
    }
    
    private List<Expression> arguments (List<Expression> ar){
        if(preanalisis.tipo==TipoToken.COMMA){
            match(TipoToken.COMMA);
            ar.add(expression());
            arguments(ar);
            return ar;
        }
        return ar;
    }
    
    private Token previous() {
        return this.tokens.get(i - 1);
    }
}
