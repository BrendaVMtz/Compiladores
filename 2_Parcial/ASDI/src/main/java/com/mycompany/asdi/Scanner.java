/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asdi;

/**
 *
 * @author karel
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    
    private final String source;
    private int k = 5;
    private int j = 1;
    String[] pila= new String[k];
     
    private final List<Token> tokens = new ArrayList<>();

    private static final Map<String, TipoToken> palabrasReservadas;
    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("select", TipoToken.SELECT);
        palabrasReservadas.put("from", TipoToken.FROM);
        palabrasReservadas.put("distinct", TipoToken.DISTINCT);
        
        
    }

    Scanner(String source){
        this.source = source + " ";
    }
    

    List<Token> scanTokens(){
        int estado = 0;
        char caracter = 0;
        pila[0]="$";
        pila[1]="Q";
        String lexema = "";
        int inicioLexema = 0;
                                    
        for(int i=0; i<source.length(); i++){
            caracter = source.charAt(i);

            switch (estado){
                case 0:
                    if(caracter == '*'){
                        
                        tokens.add(new Token(TipoToken.ASTERISCO, "*", i + 1));
                        pilaI("*");
                    }
                    else if(caracter == ','){
                        tokens.add(new Token(TipoToken.COMA, ",", i + 1));
                        pilaI(",");
                    }
                    else if(caracter == '.'){
                        tokens.add(new Token(TipoToken.PUNTO, ".", i + 1));
                        pilaI(".");
                    }
                    else if(Character.isAlphabetic(caracter)){
                        estado = 1;
                        lexema = lexema + caracter;
                        inicioLexema = i;
                    }
                    break;

                case 1:
                    if(Character.isAlphabetic(caracter) || Character.isDigit(caracter) ){
                        lexema = lexema + caracter;
                    }
                    else{
                        TipoToken tt = palabrasReservadas.get(lexema);
                        pilaI(lexema);
                        
                        if(tt == null){
                            tokens.add(new Token(TipoToken.IDENTIFICADOR, lexema, inicioLexema + 1));
                            pilaI("IDENTIFICADOR");
                        }
                        else{
                            tokens.add(new Token(tt, lexema, inicioLexema + 1));
                        }

                        estado = 0;
                        i--;
                        lexema = "";
                        inicioLexema = 0;
                    }
                    break;
            }
        }
        tokens.add(new Token(TipoToken.EOF, "", source.length()));
        
        return tokens;
    }
    
    private void pilaI(String lexema){
              
        if("select".equals(lexema) && "Q".equals(pila[j])){
            pila[j]="T";
            j++;
            pila[j]="FROM";
            j++;
            pila[j]="D";
            j++;
            pila[j]="SELECT";
            System.out.print("1"+Arrays.toString(pila));
        }
        
        ////////////////////////////7
        if("D".equals(pila[j]) && "DISTINCT".equals(lexema)){
            pila[j]="P";
            j++;
            pila[j]="DISTINCT";
            System.out.print("2"+Arrays.toString(pila));
        }
        if("D".equals(pila[j]) && "*".equals(lexema)){
            pila[j]="P";
            System.out.print("3"+Arrays.toString(pila));
        }
        if("D".equals(pila[j]) && "IDENTIFICADOR".equals(lexema)){
            pila[j]="P";
            System.out.print("4"+Arrays.toString(pila));
        }
        /////////////////////////////
        if("P".equals(pila[j]) && "*".equals(lexema)){
            pila[j]="*";
            System.out.print("5"+Arrays.toString(pila));
        }
        if("P".equals(pila[j]) && "IDENTIFICADOR".equals(lexema)){
            pila[j]="A";
            System.out.print("6"+Arrays.toString(pila));
        }
        ////////////////////////////
        if("A".equals(pila[j]) && "IDENTIFICADOR".equals(lexema)){
            pila[j]="A1";
            j++;
            pila[j]=",";
            j++;
            pila[j]="A";
        }
        if("A1".equals(pila[j]) && "IDENTIFICADOR".equals(lexema)){
            pila[j]="A2";
            j++;
            pila[j]="IDENTIFICADOR";
        }
        if("A2".equals(pila[j]) && ".".equals(lexema)){
            pila[j]="";
            j--;
        }
        if("A2".equals(pila[j]) && ",".equals(lexema)){
            pila[j]="";
            j--;
        }
        if("A2".equals(pila[j]) && ".".equals(lexema)){
            pila[j]="IDENTIFICADOR";
            j++;
            pila[j]=".";
            System.out.print("7"+Arrays.toString(pila));
        }
        /////////////////////////////
        if("T".equals(pila[j]) && "IDENTIFICADOR".equals(lexema)){
            pila[j]="T";
            j++;
            pila[j]=",";
            j++;
            pila[j]="T1";
        }
        if("T1".equals(pila[j]) && "IDENTIFICADOR".equals(lexema)){
            pila[j]="T2";
            j++;
            pila[j]="IDENTIFICADOR";
        }
        ////////////////////////////
        if("SELECT".equals(pila[j])){
            pila[j]="";
            j--;
        }
        if("FROM".equals(pila[j])){
            pila[j]="";
            j--;
        }
        if("*".equals(pila[j])){
            pila[j]="";
            j--;
            System.out.print("8"+Arrays.toString(pila));
        }
        if("IDENTIFICADOR".equals(pila[j])){
            pila[j]="";
            j--;
        }
        System.out.print("9"+Arrays.toString(pila));
        
    }

}
