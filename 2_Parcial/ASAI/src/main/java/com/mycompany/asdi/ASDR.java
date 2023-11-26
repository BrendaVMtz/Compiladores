/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asdi;

/**
 *
 * @author karel
 */


import java.util.List;
import java.util.Stack;

public class ASDR implements Parser{
    private int i = 0;
    boolean chec=false;
    private boolean hayErrores = false;
    private Token preanalisis;
    private final List<Token> tokens;
    
    Stack<String> pila = new Stack<>();
        public ASDR(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
    }

    @Override
    public boolean parse() {
        pila.push("$");
        pila.push("Q");

        comprobacion();

        if(preanalisis.tipo == TipoToken.EOF & pila.peek().equals("$")){
            System.out.println("Consulta correcta");
            
            return  true;
        }else {
            System.out.println("Se encontraron errores");
        }
        return false;
    }
    private void comprobacion(){
        String lexema="";
        lexema+=preanalisis.tipo;
        while(!pila.peek().equals("$")){
            chec=false;
            System.out.print(""+lexema);
            System.out.print(" "+pila.peek());
            System.out.print("\n");
            pilaI();
            if(pila.peek().equals(lexema)){
                i++;
                pila.pop();
                preanalisis=tokens.get(i);
                lexema="";
                lexema+=preanalisis.tipo;
                System.out.print("=\n");
                chec=true;
            }
            if(chec==false){
                break;
            }
        }
    }
    
    
    private void pilaI(){
        if(preanalisis.tipo==TipoToken.SELECT && "Q".equals(pila.peek())){
            pila.pop();
            pila.push("T");
            pila.push("FROM");
            pila.push("D");
            pila.push("SELECT");
            System.out.println("1\n");
            chec=true;
        }
        
        ////////////////////////////7
        if(preanalisis.tipo==TipoToken.DISTINCT && "D".equals(pila.peek())){
            pila.pop();
            pila.push("P");
            pila.push("DISTINCT");
            System.out.println("2\n");
            chec=true;
        }
        if(preanalisis.tipo==TipoToken.ASTERISCO && "D".equals(pila.peek())){
            pila.pop();
            pila.push("P");
            System.out.println("3\n");
            chec=true;
        }
        if(preanalisis.tipo==TipoToken.IDENTIFICADOR && "D".equals(pila.peek())){
            pila.pop();
            pila.push("P");
            System.out.println("3\n");
            chec=true;
        }
        /////////////////////////////
        if(preanalisis.tipo==TipoToken.ASTERISCO && "P".equals(pila.peek())){
            pila.pop();
            pila.push("ASTERISCO");
            System.out.println("4\n");
            chec=true;
        }
        if(preanalisis.tipo==TipoToken.IDENTIFICADOR && "P".equals(pila.peek())){
            pila.pop();
            pila.push("A");
            System.out.println("5\n");
            chec=true;
        }
        ////////////////////////////
        if(preanalisis.tipo==TipoToken.IDENTIFICADOR && "A".equals(pila.peek())){
            pila.pop();
            pila.push("A1");
            pila.push("A2");
            System.out.println("6\n");
            chec=true;
        }
        
        if(preanalisis.tipo==TipoToken.IDENTIFICADOR && "A1".equals(pila.peek())){
            pila.pop();
            pila.push("A2");
            pila.push("IDENTIFICADOR");
            System.out.println("7\n");
            chec=true;
        }
        if(preanalisis.tipo==TipoToken.COMA && "A1".equals(pila.peek())){
            pila.pop();
            pila.push("A");
            pila.push("COMA");
            System.out.println("8\n");
            chec=true;
        }
        if(preanalisis.tipo==TipoToken.FROM && "A1".equals(pila.peek())){
            pila.pop();
            System.out.println("9\n");
            chec=true;
        }
        
        if(preanalisis.tipo==TipoToken.IDENTIFICADOR && "A2".equals(pila.peek())){
            pila.pop();
            pila.push("A3");
            pila.push("IDENTIFICADOR");
            System.out.println("10\n");
            chec=true;
        }
        
        if(preanalisis.tipo==TipoToken.PUNTO && "A3".equals(pila.peek())){
            pila.pop();
            pila.push("IDENTIFICADOR");
            pila.push("PUNTO");
            System.out.println("11\n");
            chec=true;
        }
        if(preanalisis.tipo==TipoToken.COMA && "A3".equals(pila.peek())){
            pila.pop();
            System.out.println("12\n");
            chec=true;
        }
        if(preanalisis.tipo==TipoToken.FROM && "A3".equals(pila.peek())){
            pila.pop();
            System.out.println("12.1\n");
            chec=true;
        }
        /////////////////////////////
        if(preanalisis.tipo==TipoToken.IDENTIFICADOR && "T".equals(pila.peek())){
            pila.pop();
            pila.push("T1");
            pila.push("T2");
            System.out.println("13\n");
            chec=true;
        }
        
        if(preanalisis.tipo==TipoToken.COMA && "T1".equals(pila.peek())){
            pila.pop();
            pila.push("T");
            pila.push("COMA");
            System.out.println("14\n");
            chec=true;
        }
        if(preanalisis.tipo==TipoToken.EOF && "T1".equals(pila.peek())){
            pila.pop();
            System.out.println("15\n");
            chec=true;
        }
        
        if(preanalisis.tipo==TipoToken.IDENTIFICADOR && "T2".equals(pila.peek())){
            pila.pop();
            pila.push("T3");
            pila.push("IDENTIFICADOR");
            System.out.println("16\n");
            chec=true;
        }
        
        if(preanalisis.tipo==TipoToken.IDENTIFICADOR && "T3".equals(pila.peek())){
            pila.pop();
            pila.push("IDENTIFICADOR");
            System.out.println("17\n");
            chec=true;
        }
        if(preanalisis.tipo==TipoToken.COMA && "T3".equals(pila.peek())){
            pila.pop();
            System.out.println("18\n");
            chec=true;
        }
        if(preanalisis.tipo==TipoToken.EOF && "T3".equals(pila.peek())){
            pila.pop();
            System.out.println("19\n");
            chec=true;
        }

    }

    
    

}