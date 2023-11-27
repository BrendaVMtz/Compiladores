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
    private boolean acept = false;
    private Token preanalisis;
    private final List<Token> tokens;
    int estado =0;
    
    Stack<String> pila = new Stack<>();
    Stack simbolo = new Stack<>();
        public ASDR(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
    }

    @Override
    public boolean parse() {
        pila.push("0");
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
        
        while(acept!=true){
            estado=Integer.parseInt(pila.peek());
            pilaI();
        }
        
    }
    
    
    private void pilaI(){
        switch(estado){
            case 0:
                if(preanalisis.tipo==TipoToken.SELECT){
                    simbolo.push(preanalisis.tipo);
                    pila.push("2");
                    preanalisis=tokens.get(i);
                    
                }
                break;
            case 1:
                if(preanalisis.tipo==TipoToken.EOF){
                    acept=true;
                }
            break;
            case 2:
                if(preanalisis.tipo==TipoToken.DISTINCT){
                    simbolo.push(preanalisis.tipo);
                    pila.push("8");
                    preanalisis=tokens.get(i);
                }else if(preanalisis.tipo==TipoToken.ASTERISCO){
                    simbolo.push(preanalisis.tipo);
                    pila.push("11");
                    preanalisis=tokens.get(i);
                }else if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    simbolo.push(preanalisis.tipo);
                    pila.push("14");
                    preanalisis=tokens.get(i);
                }else if("A".equals(simbolo.peek())){
                    simbolo.push(preanalisis.tipo);
                    pila.push("14");
                    preanalisis=tokens.get(i);
                }
            break;
            case 3:
                if(preanalisis.tipo==TipoToken.FROM){
                    simbolo.push(preanalisis.tipo);
                    pila.push("4");
                    preanalisis=tokens.get(i);
                }
            break;
            case 4:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    simbolo.push(preanalisis.tipo);
                    pila.push("14");
                    preanalisis=tokens.get(i);
                }
            break;
            case 5:
                if(preanalisis.tipo==TipoToken.PUNTO){
                    simbolo.push(preanalisis.tipo);
                    pila.push("6");
                    preanalisis=tokens.get(i);
                }
            break;
            case 6:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    simbolo.push(preanalisis.tipo);
                    pila.push("21");
                    preanalisis=tokens.get(i);
                }
            break;
            case 7:
                pila.pop();
                got(7);
            break;
            case 8:
                if(preanalisis.tipo==TipoToken.ASTERISCO){
                    simbolo.push(preanalisis.tipo);
                    pila.push("11");
                    preanalisis=tokens.get(i);
                }else if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    simbolo.push(preanalisis.tipo);
                    pila.push("14");
                    preanalisis=tokens.get(i);
                }
            break;
            case 9:
                pila.pop();
                got(2);
            break;
            case 10:
                pila.pop();
                got(3);
            break;
            case 11:
                pila.pop();
                got(4);
            break;
            case 12:
                if(preanalisis.tipo==TipoToken.COMA){
                    simbolo.push(preanalisis.tipo);
                    pila.push("18");
                    preanalisis=tokens.get(i);
                }else{
                    pila.pop();
                    got(5);
                }
                
            break;
            case 13:
                pila.pop();
                got(7);
            break;
            case 14:
                if(preanalisis.tipo==TipoToken.PUNTO){
                    simbolo.push(preanalisis.tipo);
                    pila.push("16");
                    preanalisis=tokens.get(i);
                }else{
                    pila.pop();
                    got(10);
                }
                
            break;
            case 15:
                pila.pop();
                got(8);
            break;
            case 16:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    simbolo.push(preanalisis.tipo);
                    pila.push("17");
                    preanalisis=tokens.get(i);
                }else{
                    pila.pop();
                    got(10);
                }
            break;
            case 17:
                pila.pop();
                got(9);
            break;
            case 18:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    simbolo.push(preanalisis.tipo);
                    pila.push("14");
                    preanalisis=tokens.get(i);
                }
            break;
            case 19:
                pila.pop();
                got(6);
            break;
            case 20:
                pila.pop();
                got(12);
            break;
            case 21:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    simbolo.push(preanalisis.tipo);
                    pila.push("23");
                    preanalisis=tokens.get(i);
                }else{
                    pila.pop();
                    got(10);
                }
            break;
            case 22:
                pila.pop();
                got(13);
            break;
            case 23:
                pila.pop();
                got(14);
            break;
        }

    }
    private void got(int i){
        
        
        if("0".equals(pila.peek()))
    }
}