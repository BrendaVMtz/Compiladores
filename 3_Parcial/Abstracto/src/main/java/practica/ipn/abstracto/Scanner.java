/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica.ipn.abstracto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    private static final Map<String, TipoToken> palabrasReservadas;

    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and",    TipoToken.AND);
        palabrasReservadas.put("else",   TipoToken.ELSE);
        palabrasReservadas.put("false",  TipoToken.FALSE);
        palabrasReservadas.put("for",    TipoToken.FOR);
        palabrasReservadas.put("fun",    TipoToken.FUN);
        palabrasReservadas.put("if",     TipoToken.IF);
        palabrasReservadas.put("null",   TipoToken.NULL);
        palabrasReservadas.put("or",     TipoToken.OR);
        palabrasReservadas.put("print",  TipoToken.PRINT);
        palabrasReservadas.put("return", TipoToken.RETURN);
        palabrasReservadas.put("true",   TipoToken.TRUE);
        palabrasReservadas.put("var",    TipoToken.VAR);
        palabrasReservadas.put("while",  TipoToken.WHILE);
    }

    private final String source;
    private Abstracto ER= new Abstracto();
    private final List<Token> tokens = new ArrayList<>();
    
    public Scanner(String source){
        this.source = source + " ";
    }

    public List<Token> scan() throws Exception {
        String lexema = "";
        
        int estado = 0;
        char c;
        int d =0;
        
        
        
        for(int i=0; i<source.length(); i++){
            c = source.charAt(i);

            switch (estado){
                case 0:
                    if(Character.isLetter(c)){
                        estado = 9;
                        lexema += c;
                    }
                    else if(Character.isDigit(c)){
                        estado = 11;
                        lexema += c;
                    }else if(c == '"'){
                        estado = 1;
                    }else if(c == '<'){
                        estado = 2;
                    }else if(c == '>'){
                        estado = 3;
                    }else if(c == '='){
                        estado = 4;
                    }else if(c == '!'){
                        estado = 5;
                    }else if(c == '/'){
                        estado = 6;
                    }

                    break;

                case 9:
                    if(Character.isLetter(c) || Character.isDigit(c)){
                        estado = 9;
                        lexema += c;
                    }
                    else{
                        // Vamos a crear el Token de identificador o palabra reservada
                        TipoToken tt = palabrasReservadas.get(lexema);

                        if(tt == null){
                            Token t = new Token(TipoToken.IDENTIFIER, lexema);
                            tokens.add(t);
                        }
                        else{
                            Token t = new Token(tt, lexema);
                            tokens.add(t);
                        }

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
/////////////////////////////////////////////////////////////////////                    
                case 11:
                    if(Character.isDigit(c)){
                        estado = 11;
                        lexema += c;
                    }
                    else if(c == '.'){
                        lexema +=c;
                        estado = 12;
                    }
                    else if(c == 'E'){
                        lexema +=c;
                        estado = 12;
                    }
                    else{
                        Token t = new Token(TipoToken.NUMBER, lexema, i+1);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                case 12:
                    if(Character.isDigit(c)){
                        estado = 12;
                        lexema += c;
                    }
                    else{
                        Token t = new Token(TipoToken.NUMBER, lexema, i+1);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                case 13:
                    if(Character.isDigit(c)){
                        estado = 13;
                        lexema += c;
                    }
                    else{
                        Token t = new Token(TipoToken.NUMBER, lexema, i+1);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
/////////////////////////////////////////////////////////////////////////////
                case 1:
                    if(Character.isLetter(c)){
                        estado = 1;
                        lexema +=c;
                    }else if(c == ' '){
                        System.out.println("Error : Salto de linea");
                        System.exit(0);
                    }
                    else if(c == '"'){
                        
                        Token t = new Token(TipoToken.STRING, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                break;
/////////////////////////////////////////////////////////////////                    
                case 2:
                    if(c == '='){
                        Token t = new Token(TipoToken.LESS_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }else{
                        Token t = new Token(TipoToken.LESS, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                break;
                
                case 3:
                    if(c == '='){
                        Token t = new Token(TipoToken.GREATER_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }else{
                        Token t = new Token(TipoToken.GREATER, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                break;
                
                case 4:
                    if(c == '='){
                        Token t = new Token(TipoToken.EQUAL_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }else{
                        Token t = new Token(TipoToken.EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                break;
                
                case 5:
                    if(c == '='){
                        Token t = new Token(TipoToken.BANG_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }else{
                        Token t = new Token(TipoToken.BANG, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                break;
                //////////////////////////////////////////////////////////////
                case 6:
                    if(c == '*'){
                        estado = 7;
                    }else if(c == '/'){
                        estado = 10;
                    }else{
                        Token t = new Token(TipoToken.SLASH, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                break;
                
                case 7:
                    if(c == '*'){
                        estado = 8;
                    }else{
                        estado = 7;
                        lexema+= c;
                    }
                break;
                
                case 8:
                    if(c == '*'){
                        estado = 8;
                    }else if(c =='/'){
                        System.out.print(""+lexema);
                    }
                    else{
                        estado = 7;
                        lexema+= c;
                    }
                break;
                
                case 10:
                    if(c == ' '){
                        System.out.print(""+lexema);
                    }else{
                        estado = 10;
                        lexema+=c;
                    }
/////////////////////////////////////////////////////////////////
                
                    
            }
        }


        return tokens;
    }

}
