package com.mycompany.compiladoresscanner;

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
    private final List<Token> tokens = new ArrayList<>();
    
    public Scanner(String source){
        this.source = source + "";
    }

    public List<Token> scan() throws Exception {
        String lexema = "";
        String lexema2 = "";
        int estado = 0;
        char c;
        
        
        
        
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
                        lexema+=c;
                    }else if(c == '<'){
                        estado = 2;
                        lexema+=c;
                    }else if(c == '>'){
                        estado = 3;
                        lexema+=c;
                    }else if(c == '='){
                        estado = 4;
                        lexema+=c;
                    }else if(c == '!'){
                        estado = 5;
                        lexema+=c;
                    }else if(c == '/'){
                        estado = 6;
                    }else if (c == '+'){
                        lexema+= c;
                        Token t = new Token(TipoToken.PLUS, lexema);
                        tokens.add(t);
                        estado=0;
                        lexema="";
                    }else if (c == '-'){
                        lexema+= c;
                        Token t = new Token(TipoToken.MINUS, lexema);
                        tokens.add(t);
                        estado=0;
                        lexema="";
                    }else if (c == '*'){
                        lexema+= c;
                        Token t = new Token(TipoToken.STAR, lexema);
                        tokens.add(t);
                        estado=0;
                        lexema="";
                    }else if (c == '{'){
                        lexema+= c;
                        Token t = new Token(TipoToken.LEFT_BRACE, lexema);
                        tokens.add(t);
                        estado=0;
                        lexema="";
                    }else if (c == '}'){
                        lexema+= c;
                        Token t = new Token(TipoToken.RIGHT_BRACE, lexema);
                        tokens.add(t);
                        estado=0;
                        lexema="";
                    }else if (c == '('){
                        lexema+= c;
                        Token t = new Token(TipoToken.LEFT_PAREN, lexema);
                        tokens.add(t);
                        estado=0;
                        lexema="";
                    }else if (c == ')'){
                        lexema+= c;
                        Token t = new Token(TipoToken.RIGHT_PAREN, lexema);
                        tokens.add(t);
                        estado=0;
                        lexema="";
                    }else if (c == ','){
                        lexema+= c;
                        Token t = new Token(TipoToken.COMMA, lexema);
                        tokens.add(t);
                        estado=0;
                        lexema="";
                    }else if (c == '.'){
                        lexema+= c;
                        Token t = new Token(TipoToken.DOT, lexema);
                        tokens.add(t);
                        estado=0;
                        lexema="";
                    }else if (c == ';'){
                        lexema+= c;
                        Token t = new Token(TipoToken.SEMICOLON, lexema);
                        tokens.add(t);
                        estado=0;
                        lexema="";
                    }else if (c == '['){
                        System.out.println("Error");
                        return null;
                    }else if (c == ']'){
                        System.out.println("Error");
                        return null;
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
                        Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
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
                        Token t = new Token(TipoToken.NUMBER, lexema, Float.valueOf(lexema));
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
                        Token t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
/////////////////////////////////////////////////////////////////////////////
                case 1:
                    if(c == '\r'){
                        System.out.println("Error : Salto de linea");
                        return null;
                    }
                    else if(c == '"'){
                        lexema+=c;
                        Token t = new Token(TipoToken.STRING, lexema, lexema2);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        lexema2 = "";
                        
                    }else{
                        estado = 1;
                        lexema2 +=c;
                    }
                break;
/////////////////////////////////////////////////////////////////                    
                case 2:
                    if(c == '='){
                        lexema+=c;
                        Token t = new Token(TipoToken.LESS_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
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
                        lexema+=c;
                        Token t = new Token(TipoToken.GREATER_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
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
                        Token t = new Token(TipoToken.SLASH, "");
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
                    }
                break;
                
                case 8:
                    if(c == '*'){
                        estado = 8;
                    }else if(c =='/'){
                        estado = 0;
                    }
                    else{
                        estado = 7;
                    }
                break;
                
                case 10:
                    if(c == '\r'){
                        estado=0;
                    }else{
                        estado = 10;
                    }
/////////////////////////////////////////////////////////////////
                
                    
            }
        }


        return tokens;
    }

    private void print(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void printf(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

