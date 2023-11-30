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
        pila.push("1");
        comprobacion();
        if(preanalisis.tipo == TipoToken.EOF && acept){
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
            chec=false;
            pilaI();
            for(int l=0;l<10; l++){
            System.out.print(" "+gramatica[l]);
            }
            if(!chec){
                break;
            }
            
            System.out.print("\n");
        }
        
    }
    
    
    private void pilaI(){
        switch(estado){
            case 1:
                if(preanalisis.tipo==TipoToken.SELECT){
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("4");
                    i++;
                    preanalisis=tokens.get(i);
                    chec=true;
                }
                break;
            case 2:
                if(preanalisis.tipo==TipoToken.EOF){
                    acept=true;
                }
            break;
            case 4:
                if(preanalisis.tipo==TipoToken.DISTINCT){
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("15");
                    i++;
                    preanalisis=tokens.get(i);
                    chec=true;
                }else if(preanalisis.tipo==TipoToken.ASTERISCO){
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("17");
                    i++;
                    preanalisis=tokens.get(i);
                    chec=true;
                }else if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("21");
                    i++;
                    preanalisis=tokens.get(i);
                    chec=true;
                }
            break;
            case 5:
                if(preanalisis.tipo==TipoToken.FROM){
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("6");
                    i++;
                    preanalisis=tokens.get(i);
                    chec=true;
                }
            break;
            case 6:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("10");
                    i++;
                    preanalisis=tokens.get(i);
                    chec=true;
                }else{
                    pila.pop();
                    got();
                }
            break;
            case 7:
                if(preanalisis.tipo==TipoToken.COMA){
                    i++;
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("8");
                    preanalisis=tokens.get(i);
                    chec=true;
                }else{
                    pila.pop();
                    got();
                }
            break;
            case 8:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("10");
                    i++;
                    preanalisis=tokens.get(i);
                    chec=true;
                }
            break;
            case 9:
                pila.pop();
                got();
            break;
            case 10:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("12");
                    preanalisis=tokens.get(i);
                    chec=true;
                }if(preanalisis.tipo==TipoToken.EOF){
                    j++;
                    gramatica[j]="T2";
                    pila.pop();
                    got();
                }
            break;
            case 11:
                pila.pop();
                got();
            break;
            case 12:
                pila.pop();
                got();
            break;
            case 13:
                pila.pop();
                got();
            break;
            case 14:
                pila.pop();
                got();
            break;
            case 15:
                if(preanalisis.tipo==TipoToken.ASTERISCO){
                    i++;
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("17");
                    preanalisis=tokens.get(i);
                    chec=true;
                }else if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    i++;
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("21");
                    preanalisis=tokens.get(i);
                    chec=true;
                }else{
                    pila.pop();
                    got();
                }
                
            break;
            case 16:
                pila.pop();
                got();
            break;
            case 17:
                pila.pop();
                got();
            break;
            case 18:
                if(preanalisis.tipo==TipoToken.COMA){
                    i++;
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("19");
                    preanalisis=tokens.get(i);
                    chec=true;
                }else{
                    pila.pop();
                    got();
                }
                
            break;
            case 19:
                if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                    i++;
                    j++;
                    gramatica[j]=""+preanalisis.tipo;
                    pila.push("21");
                    preanalisis=tokens.get(i);
                    chec=true;
                }else{
                    pila.pop();
                    got();
                }
                
            break;
            case 20:
                pila.pop();
                got();
            break;
            case 21:
                if(preanalisis.tipo==TipoToken.PUNTO){
                    j++;
                    i++;
                    gramatica[j]=""+preanalisis.tipo;
                    preanalisis=tokens.get(i);
                    System.out.print(" "+preanalisis.tipo);
                    if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                        j++;
                        i++;
                        gramatica[j]=""+preanalisis.tipo;
                        pila.push("23");
                        preanalisis=tokens.get(i);
                        chec=true;
                    }
                }else{
                    pila.pop();
                    j++;
                    
                    gramatica[j]="A2";
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
            case 25:
                pila.pop();
                got();
            break;
            case 26:
                pila.pop();
                got();
            break;
        }

    }
    private void got(){

        
        if("IDENTIFICADOR".equals(gramatica[j]) && "PUNTO".equals(gramatica[j-1])){
            gramatica[j]="";
            gramatica[j-1]="A2";
            j--;
            chec=true;
        }else if("T".equals(gramatica[j]) && "FROM".equals(gramatica[j-1]) && "D".equals(gramatica[j-2]) && "SELECT".equals(gramatica[j-3])){
            gramatica[j]="Q";
            chec=true;
        }else if("IDENTIFICADOR".equals(gramatica[j])){
            gramatica[j]="T2";
            chec=true;
        }else if("T2".equals(gramatica[j]) && "IDENTIFICADOR".equals(gramatica[j-1])){
            gramatica[j]="";
            gramatica[j-1]="T1";
            j--;
            chec=true;
        }else if("T1".equals(gramatica[j]) && "COMA".equals(gramatica[j-1]) && "T".equals(gramatica[j-2])){
            gramatica[j]="";
            gramatica[j-1]="";
            gramatica[j-2]="T";
            chec=true;
        }else if("T1".equals(gramatica[j])){
            gramatica[j]="T";
            chec=true;
        }else if("A2".equals(gramatica[j]) && "IDENTIFICADOR".equals(gramatica[j-1])){
            gramatica[j]="";
            j--;
            gramatica[j]="A1";
            chec=true;
        }else if("A1".equals(gramatica[j]) && "COMA".equals(gramatica[j-1]) && "A".equals(gramatica[j-2])){
            gramatica[j]="";
            gramatica[j-1]="";
            gramatica[j-2]="A";
            j--;
            j--;
            chec=true;
        }else if("A1".equals(gramatica[j])){
            gramatica[j]="A";
            chec=true;
        }else if("A".equals(gramatica[j])){
            gramatica[j]="P";
            chec=true;
        }else if("ASTERISCO".equals(gramatica[j])){
            gramatica[j]="P";
            chec=true;
        }else if("P".equals(gramatica[j]) && "DISTINCT".equals(gramatica[j-1])){
            gramatica[j]="";
            gramatica[j-1]="D";
            j--;
            chec=true;
        }else if("P".equals(gramatica[j])){
            gramatica[j]="D";
            chec=true;
        }else if("EOF".equals(gramatica[j])){
            gramatica[j]="T2";
            chec=true;
        }
        tablago();
          
    }
    void tablago(){
        if(!pila.isEmpty()){
            if("1".equals(pila.peek()) && "Q".equals(gramatica[j])){
                pila.push("2");
                chec=true;
            }else if("4".equals(pila.peek()) && "P".equals(gramatica[j])){
                pila.push("26");
                chec=true;
            }else if("4".equals(pila.peek()) && "D".equals(gramatica[j])){
                pila.push("5");
                chec=true;
            }else if("4".equals(pila.peek()) && "A".equals(gramatica[j])){
                pila.push("18");
                chec=true;
            }else if("4".equals(pila.peek()) && "A1".equals(gramatica[j])){
                pila.push("25");
                chec=true;
            }else if("6".equals(pila.peek()) && "T".equals(gramatica[j])){
                pila.push("7");
                chec=true;
            }else if("6".equals(pila.peek()) && "T1".equals(gramatica[j])){
                pila.push("14");
                chec=true;
            }else if("8".equals(pila.peek()) && "T1".equals(gramatica[j])){
                pila.push("9");
                chec=true;
            }else if("10".equals(pila.peek()) && "T2".equals(gramatica[j])){
                pila.push("11");///
                chec=true;
            }else if("8".equals(pila.peek()) && "P".equals(gramatica[j])){
                pila.push("9");
                chec=true;
            }else if("8".equals(pila.peek()) && "A".equals(gramatica[j])){
                pila.push("12");
                chec=true;
            }else if("8".equals(pila.peek()) && "A1".equals(gramatica[j])){
                pila.push("13");
                chec=true;
            }else if("14".equals(pila.peek()) && "A2".equals(gramatica[j])){
                pila.push("15");
                chec=true;
            }else if("18".equals(pila.peek()) && "A1".equals(gramatica[j])){
                pila.push("19");
                chec=true;
            }else if("21".equals(pila.peek()) && "A2".equals(gramatica[j])){
                pila.push("22");
                chec=true;
            }else{
                pila.pop();
                tablago();
            } 
        }else{
            return;
        }
        
         
    }
}