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
    private int j = 0;
    private int k = 0;
    boolean chec=false;
    private boolean hayErrores = false;
    private boolean acept = false;
    private Token preanalisis;
    private final List<Token> tokens;
    int estado =0;
    String[] gramatica = new String[10];
    
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
        if(preanalisis.tipo == TipoToken.EOF){
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
            System.out.print(""+preanalisis.tipo+" "+pila.peek()+" "+gramatica[j]+"\n");
            k++;
            
            pilaI();
            for(int l=0;l<10; l++){
            System.out.print(" "+gramatica[l]);
            }
            System.out.print("\n");
        }
        
    }
    
    
    private void pilaI(){
        switch(estado){
            case 0:
                if(preanalisis.tipo==TipoToken.SELECT){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("2");
                    i++;
                    
                    preanalisis=tokens.get(i);
                    
                }
                break;
            case 1:
                if(preanalisis.tipo==TipoToken.EOF){
                    acept=true;
                }
            break;
            case 2:
                j++;
                if(preanalisis.tipo==TipoToken.DISTINCT){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("8");
                    i++;
                    preanalisis=tokens.get(i);
                }else if(preanalisis.tipo==TipoToken.ASTERISCO){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("11");
                    i++;
                    preanalisis=tokens.get(i);
                }else if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("14");
                    i++;
                    preanalisis=tokens.get(i);
                }
                
            break;
            case 3:
                    j++;
                if(preanalisis.tipo==TipoToken.FROM){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("4");
                    i++;
                    
                    preanalisis=tokens.get(i);
                    
                }
            break;
            case 4:
                j++;
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("21");
                    i++;
                    preanalisis=tokens.get(i);
                }
            break;
            case 5:
                if(preanalisis.tipo==TipoToken.PUNTO){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("6");
                    i++;
                    j++;
                    preanalisis=tokens.get(i);
                }
            break;
            case 6:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("21");
                    i++;
                    j++;
                    preanalisis=tokens.get(i);
                }
            break;
            case 7:
                pila.pop();
                got();
            break;
            case 8:
                if(preanalisis.tipo==TipoToken.ASTERISCO){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("11");
                    preanalisis=tokens.get(i);
                }else if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("14");
                    preanalisis=tokens.get(i);
                }
            break;
            case 9:
                pila.pop();
                got();
            break;
            case 10:
                pila.pop();
                got();
            break;
            case 11:
                pila.pop();
                got();
            break;
            case 12:
                if(preanalisis.tipo==TipoToken.COMA){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("18");
                    preanalisis=tokens.get(i);
                }else{
                    pila.pop();
                    got();
                }
                
            break;
            case 13:
                pila.pop();
                got();
            break;
            case 14:
                if(preanalisis.tipo==TipoToken.PUNTO){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("16");
                    preanalisis=tokens.get(i);
                }else{
                    pila.pop();
                    got();
                }
                
            break;
            case 15:
                pila.pop();
                got();
            break;
            case 16:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("17");
                    preanalisis=tokens.get(i);
                }else{
                    pila.pop();
                    got();
                }
            break;
            case 17:
                pila.pop();
                got();
            break;
            case 18:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("14");
                    preanalisis=tokens.get(i);
                }
            break;
            case 19:
                pila.pop();
                got();
            break;
            case 20:
                pila.pop();
                got();
            break;
            case 21:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("23");
                    preanalisis=tokens.get(i);
                }else{
                    pila.pop();
                    got();
                }
            break;
            case 22:
                pila.pop();
                got();
            break;
            case 23:
                pila.pop();
                got();
            break;
        }

    }
    private void got(){

        if("IDENTIFICADOR".equals(gramatica[j]) && "PUNTO".equals(gramatica[j-1])){
            gramatica[j]="";
            gramatica[j-1]="A2";
        }else if("T".equals(gramatica[j]) && "FROM".equals(gramatica[j-1]) && "D".equals(gramatica[j-2]) && "SELECT".equals(gramatica[j-3])){
            gramatica[j]="Q";
        }else if("IDENTIFICADOR".equals(gramatica[j])){
            gramatica[j]="T2";
        }else if("T2".equals(gramatica[j]) && "IDENTIFICADOR".equals(gramatica[j-1])){
            gramatica[j]="";
            gramatica[j-1]="T1";
        }else if("T1".equals(gramatica[j]) && "COMA".equals(gramatica[j-1]) && "T".equals(gramatica[j-2])){
            gramatica[j]="";
            gramatica[j-1]="";
            gramatica[j-2]="T";
        }else if("T1".equals(gramatica[j])){
            gramatica[j]="T";
        }else if("A2".equals(gramatica[j]) && "IDENTIFICADOR".equals(gramatica[j-1])){
            gramatica[j]="";
            gramatica[j-1]="A1";
        }else if("A1".equals(gramatica[j]) && "COMA".equals(gramatica[j-1]) && "A".equals(gramatica[j-2])){
            gramatica[j]="";
            gramatica[j-1]="";
            gramatica[j-2]="A";
        }else if("A1".equals(gramatica[j])){
            gramatica[j]="A";
        }else if("A".equals(gramatica[j])){
            gramatica[j]="P";
        }else if("ASTERISCO".equals(gramatica[j])){
            gramatica[j]="P";
        }else if("P".equals(gramatica[j]) && "DISTINCT".equals(gramatica[j-1])){
            gramatica[j]="";
            gramatica[j-1]="D";
        }else if("P".equals(gramatica[j])){
            gramatica[j]="D";
        }
        
        if("0".equals(pila.peek()) && "Q".equals(gramatica[j])){
            pila.push("1");
        }else if("2".equals(pila.peek()) && "P".equals(gramatica[j])){
            pila.push("10");
        }else if("2".equals(pila.peek()) && "D".equals(gramatica[j])){
            pila.push("3");
        }else if("2".equals(pila.peek()) && "A".equals(gramatica[j])){
            pila.push("12");
        }else if("2".equals(pila.peek()) && "A1".equals(gramatica[j])){
            pila.push("13");
        }else if("2".equals(pila.peek()) && "A2".equals(gramatica[j])){
            pila.push("14");
        }else if("4".equals(pila.peek()) && "T".equals(gramatica[j])){
            pila.push("5");
        }else if("4".equals(pila.peek()) && "T1".equals(gramatica[j])){
            pila.push("28");
        }else if("6".equals(pila.peek()) && "T1".equals(gramatica[j])){
            pila.push("7");
        }else if("8".equals(pila.peek()) && "P".equals(gramatica[j])){
            pila.push("9");
        }else if("8".equals(pila.peek()) && "A".equals(gramatica[j])){
            pila.push("12");
        }else if("8".equals(pila.peek()) && "A1".equals(gramatica[j])){
            pila.push("13");
        }else if("14".equals(pila.peek()) && "A2".equals(gramatica[j])){
            pila.push("15");
        }else if("18".equals(pila.peek()) && "A1".equals(gramatica[j])){
            pila.push("19");
        }else if("21".equals(pila.peek()) && "T2".equals(gramatica[j])){
            pila.push("22");
        }
        
        
    }
}