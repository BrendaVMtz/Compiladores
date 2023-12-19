/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica.ipn.abstracto;
import practica.ipn.abstracto.TipoToken;
import practica.ipn.abstracto.Token;
import java.util.List;
import static practica.ipn.abstracto.TipoToken.IDENTIFIER;

/**
 *
 * @author karel
 */
public class Parser {
    private final List<String> generic = null;
    private final List<Expression> generic2 = null;
    private final List<Token> tokens;
    private int i = 0;
    private Token preanalisis;
    public Parser(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
    }
    
    public boolean analisis(){
        Declaration();
        if(preanalisis.tipo == TipoToken.EOF){
            System.out.println("Consulta correcta");
            return  true;
        }else {
            System.out.println("Se encontraron errores");
        }
        return false;
    }
    
    void Declaration(){
        switch(preanalisis.tipo){
            case FUN:
                match(TipoToken.FUN);
                Function();
            break;
            case VAR:
                match(TipoToken.VAR);
                match(TipoToken.IDENTIFIER);
                VAR_INT();
            break;
            case EOF:
            break;
        }
    }
    
    void Function(){
        match(TipoToken.IDENTIFIER);
        match(TipoToken.LEFT_PAREN);
        List<String> lstParameters = Parameters();
        match(TipoToken.RIGHT_PAREN);
        Block();
    }
    
    private List<String> Parameters(){
        switch(preanalisis.tipo){
            case IDENTIFIER:
                generic.add(""+preanalisis.lexema);
                match(TipoToken.IDENTIFIER);
                Parameters();
            break;
            case COMMA:
                generic.add(""+preanalisis.lexema);
                match(TipoToken.COMMA);
                generic.add(""+preanalisis.lexema);
                match(TipoToken.IDENTIFIER);
                Parameters();
            break;
            case RIGHT_PAREN:
            break;
        }
        
        return generic;
    }
    
    void term(){
        factor();
        term2();
    }


    private Expression factor(){
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
                return new ExprLiteral(numero.getLiteral());
            case STRING:
                match(TipoToken.STRING);
                Token cadena = previous();
                return new ExprLiteral(cadena.getLiteral());
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
        As();
        return generic2;
    }
    private Token previous() {
        return this.tokens.get(i - 1);
    }
}
