
package com.mycompany.calculadora;

import java.util.Scanner;
import java.util.Arrays;//No sé por qué dice que no uso esta clasi cuando sí la uso.

public class Calculadora {

    public static void main(String[] args) {
        String mensajeFinal="Default";
        
        //Pedir la operación
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce la operación");
        String operacion=scan.nextLine();
        //Controlar que no hayan simbolos incorrectos
        boolean simbolosCorrectos=soloNumeros(operacion);
        if (simbolosCorrectos!= true){
            mensajeFinal ="Hay caracteres icompatibles u operadores desordenados.";
        }else{ System.out.println("Caracteres correctos");}
        operacion=operacion.replace('\'', '.');
        operacion=operacion.replace(',', '.');
        System.out.println("Operacion corregida: "+operacion);
        
        //Este bucle repite todo el proceso hasta que no queden más operaciones que hacer
        boolean hayPar=true;
        while (hayPar&&simbolosCorrectos){
            System.out.println("Operacion: "+operacion);
            operacion = resolverParentesis(operacion, simbolosCorrectos);
            if (operacion.indexOf('(')==-1){
                hayPar=false;
                operacion=operacionesCompletas(operacion);
                mensajeFinal="Resultado: "+operacion;
            }
        }
        
        //Se muestra el resultado final
        System.out.println(mensajeFinal);
    }
//COMPROBACION DE CARACTERES VÁLIDOS
    //Esta función controla que los carácteres sean válidos
    public static boolean soloNumeros(String operacion){
        boolean correcto=true;
        String charCorrectos ="0123456789/*-+%(){}[].,'";
        char [] correctChars= charCorrectos.toCharArray();//Convertir el String en char[] no se cuanto sentido tiene pero quería hacerlo para practicar.
        
        for (int i=0; i<operacion.length(); i++){
            char nextChar='u';
            char antChar='p';
            if(i>0){ antChar= operacion.charAt(i-1);}
            char currentChar= operacion.charAt(i);
            if (i<(operacion.length()-1)) {nextChar=operacion.charAt(i+1);}
            for (int i2=0; i2<correctChars.length; i2++){
                if (currentChar == correctChars[i2]){
                    i2=correctChars.length;
                    correcto=true;
                }else{ correcto=false;}
            }
            
            if(correcto) correcto=opRest(antChar, currentChar, nextChar);
            if (correcto==false){
                i=operacion.length();
                System.out.println("Simbolos incorrectos");
            }
            //else{System.out.println("Simbolos correctos");}
        }
        
        return correcto; 
    }
    //Esta función controla que los caracteres no estén apelotonados a menos que sea un '-' de un valor negativo.
    public static boolean opRest (char antChar, char candidato, char nextChar){
        boolean [][] isOpRest ={
            {false, false},
            {false, false},
            {false, false}
        };
        boolean correct=true;
        String operadores = "/*-+%";
        char [] conjunto={antChar, candidato, nextChar};
        
        for (int actCon=0; actCon<conjunto.length; actCon++){
            char candChar=conjunto[actCon];
            for (int i=0; i<operadores.length(); i++){
                char actChar= operadores.charAt(i);
                
                if (actChar==candChar){
                    isOpRest[actCon][0]=true;
                    i=operadores.length();
                    
                }
                if(candChar=='-'){
                    isOpRest[actCon][1]=true;
                }
                
            }
        }
        if ((isOpRest[1][0]&&(isOpRest[2][0]&&isOpRest[2][1]==false))||(isOpRest[0][0]&&isOpRest[1][0] && isOpRest[2][0])||(antChar=='p'&&(isOpRest[1][0]&&isOpRest[1][1]==false))||(nextChar=='u'&&isOpRest[1][0])){
            correct=false;
        }
        //System.out.println("simbolo: "+Arrays.toString(conjunto));
        //System.out.println("valores: "+Arrays.deepToString(isOpRest));
        //System.out.println("Es correcto? "+correct);
        return correct;
    }
//-----Resolver parentesis
    public static String resolverParentesis(String operacion, boolean simbolosCorrectos){
        String [] currentSim = {"paréntesis", "corchetes","llaves"};
        int control=4;
        String nuevo =operacion;
        boolean ordenSim= comprobarPar(operacion);//ordenCorrecto(operacion);
        
        String mensajeFinal="";
        if (ordenSim==false){mensajeFinal="Símbolos desordenados";}
        if (ordenSim&&simbolosCorrectos){
            //System.out.println("Simbolos ordenados");
        //-----Este bucle controla que hayan paréntesis, corchetes y llaves y, de ser asi, selecciona el contenido del primer paréntesis para resolverlo.    
            for (int controlSim=2; controlSim>=0; controlSim--){
                char [] simbolos ={'(',')','[',']','{','}'};
                int parA1 = operacion.indexOf(simbolos[control]);
                int parC1= operacion.indexOf(simbolos[control+1]);
                int parA2= operacion.indexOf(simbolos[control], parA1+1);
                int parC2 = operacion.indexOf(simbolos[control+1], parC1+1);
                boolean tenemos = true;

                //System.out.println("parA1 "+parA1+" parC1 "+parC1+" parA2 "+parA2+" parC2 "+parC2);
                if (parA1==-1 && parC1==-1){
                    //System.out.println("No hay "+currentSim[controlSim]);
                    tenemos = false;
                    control-=2;
                }
                if (tenemos&&ordenSim){
                    nuevo = operacion.substring(parA1+1, parC1);
                    //System.out.println("Dentro de "+currentSim[controlSim]+" hay "+nuevo);
                    control-=2;
                }
            }
        //-----Esto resuelve las operaciones y las sutituye en el String original
            String resultadoNuevo= operacionesCompletas(nuevo);
            operacion = operacion.replace(nuevo, resultadoNuevo);
        //-----Eliminar paréntesis si los hay
            if (operacion.indexOf('(')!=-1){
                int parIdx=operacion.indexOf('(');
                boolean isNum=false;
                if (parIdx>0){
                    char charAnt = operacion.charAt(parIdx-1);
                    String numString = "0123456789";
                    
                    for (int i=0; i<numString.length(); i++){
                        char actChar = numString.charAt(i);
                        if (actChar==charAnt){
                            i=numString.length();
                            isNum=true;
                        }
                    }
                }
                if (isNum){
                    operacion=operacion.replace("("+resultadoNuevo+")","*"+resultadoNuevo);
                }else{
                    operacion=operacion.replace("("+resultadoNuevo+")", resultadoNuevo);
                }
            }
        //-----Susituir corchetes y llaves    
            if (operacion.indexOf(']')!=-1 && ((operacion.indexOf(')')==-1||(operacion.indexOf(')')> operacion.indexOf(']'))))){
                int inicio =operacion.indexOf('[');
                int fin = operacion.indexOf(']');
                String viejo = operacion.substring(inicio+1, fin);
                //System.out.println("viejo: "+viejo);
                operacion=operacion.replace("["+viejo+"]","("+viejo+")");
            }
            if (operacion.indexOf('}')!=-1 && ((operacion.indexOf(']')==-1||(operacion.indexOf(']')> operacion.indexOf('}'))))){
                int inicio =operacion.indexOf('{');
                int fin = operacion.indexOf('}');
                String viejo = operacion.substring(inicio+1, fin);
                operacion=operacion.replace("{"+viejo+"}","["+viejo+"]");
            }
            
            mensajeFinal=operacion;
            
        }
        return mensajeFinal;
    }
//-----Resolver operaciones
    public static String operacionesCompletas(String operacion){
        String resultado="Todavía no se sumar, jefe, pero la cuenta que me has mandado es esta: "+operacion;
        int posOp=0;
        int posAnt=0;
        int posNext=0;
        boolean isOp=true;
        char operador=' ';
        String opString="*/%+-";
        
        while(isOp){
            System.out.println("Voy a resolver esta operacion: "+operacion);
        //-----Buscar el operador
            boolean isPriority=false;
            //Este primer bucle busca los operadores con prioridad (-/%) para solucionarlos de izquierda a derecha.
            for(int actIdx=0; actIdx<operacion.length(); actIdx++){
                char actChar=operacion.charAt(actIdx);
                isOp=false;
                for(int i=0; i<3; i++){
                    char actOp=opString.charAt(i);
                    if (actChar==actOp){
                        posOp=actIdx;
                        operador=actOp;
                        isOp=true;
                        isPriority=true;
                    }
                    
                    if (isOp) actIdx=operacion.length();
                }
            }
            //Este otro busca los operadores de menor prioridad (-+)
            if (isPriority==false){
                for(int actIdx=3; actIdx<opString.length(); actIdx++){
                    char actOp=opString.charAt(actIdx);
                    isOp=false;
                    for(int i=0; i<operacion.length(); i++){
                        char actChar=operacion.charAt(i);
                        if (actChar==actOp){
                            posOp=i;
                            operador=actOp;
                            isOp=true;
                        }
                        if (operador=='-'&&posOp==0){ //Comprueba que el '-' no sea un numero negativo al inicio de una frase
                            isOp=false;
                        }
                        if (isOp) actIdx=opString.length();
                    }
                }
            }
            if(isOp==true){
                //System.out.println("Operador "+operador);
            //-----RESOLVER LAS OPERACIONES
            //-----Buscar el valor 'a'
                for(int actIdx=posOp-1; actIdx>=0; actIdx--){
                    char actChar=operacion.charAt(actIdx);
                    posAnt=actIdx;
                    //-----Buscar el operador anterior
                    for(int i=0; i<opString.length(); i++){
                        char actOp=opString.charAt(i);
                        if (actChar==actOp){
                            if (actChar!='-'){
                                posAnt=actIdx+1;
                                //System.out.println("Posicion de inicio: "+posAnt);
                            }
                            //Aqui voy a comprobar que el '-' no sea un número negativo
                            if (actChar=='-' && actIdx>0){ 
                                int charAnt=operacion.charAt(actIdx-1);
                                posAnt=actIdx+1;
                                for (int a=0; a<opString.length(); a++){
                                    char opProvisional= opString.charAt(a);
                                    if (opProvisional==charAnt){
                                        posAnt=actIdx;
                                        a=opString.length();
                                    }
                                };
                            }
                            actIdx=-1;
                        }
                    }

                //System.out.println("Operador anterior: "+actChar);
                }
            //-----Finalmente selecciono el valor y lo convierto en double
                String vAString= operacion.substring(posAnt, posOp);
                //System.out.println("Valor A: "+vAString);
                double valorA = Double.parseDouble(vAString);
            //-----Buscar el valor 'b'
                for(int actIdx=posOp+1; actIdx<operacion.length(); actIdx++){
                    char actChar=operacion.charAt(actIdx);
                    posNext=actIdx;
                    //-----Buscar el operador posterior
                    for(int i=0; i<opString.length(); i++){
                        char actOp=opString.charAt(i);
                        //Con el siguiente condicional me aseguro de que el siguiente operador no sea un '-' de un numero negativo, que es la unica opción que queda con las comprobaciones anteriores.
                        if (actChar==actOp&&actIdx!=posOp+1){
                            i=opString.length();
                            actIdx=operacion.length();
                            posNext--;

                        }
                    }
                    //System.out.println("Operador posterior: "+actChar);
                }
            //-----Finalmente selecciono el valor y lo convierto en double
                String vBString= operacion.substring(posOp+1, posNext+1);
                //System.out.println("Valor B: "+vBString);
                double valorB = Double.parseDouble(vBString);

                /*
                System.out.println("Valor A: "+Double.toString(valorA));
                System.out.println("Operador: "+operador);
                System.out.println("Valor B: "+Double.toString(valorB));
                */

            //-----Resolver operaciones
                switch (operador){
                    case '+':
                        resultado = Double.toString(valorA+valorB);
                        break;
                    case '-':
                        resultado = Double.toString(valorA-valorB);
                        break;
                    case '*':
                        resultado = Double.toString(valorA*valorB);
                        break;
                    case '/':
                        resultado = Double.toString(valorA/valorB);
                        break;
                    case '%':
                        resultado = Double.toString(valorA%valorB);
                        break;
                }
                System.out.println("Resultado: "+resultado);
                //System.out.println("Vieja operacion= "+operacion.substring(posAnt, posNext+1));
                operacion=operacion.replace(operacion.substring(posAnt, posNext+1), resultado);
                //System.out.println("Nueva operacion= "+operacion);
            }else{resultado=operacion;}
        }
        return resultado;
    }
//-----COMPROBAR PARÉNTESIS CORCHETES Y LLAVES
    public static boolean comprobarPar(String operacion){
        boolean correcto=true;
    //-----Comprobar paréntesis
        correcto=comprobarSencillo(operacion, '(', ')');
        /*if(correcto){
            System.out.println("Los paréntesis estan bien en el nuevo sistema");
        }
        else{
            System.out.println("Los paréntesis estan mal en el nuevo sistema");
        }*/
        
    //-----Comprobar Corchetes
        if (correcto) correcto=comprobarCompuesto(operacion,'[',']','(',')');
        /*if(correcto){
            System.out.println("Los corchetes estan bien en el nuevo sistema");
        }
        else{
            System.out.println("Los corchetes estan mal en el nuevo sistema");
        }*/
        
    //-----Comprobar Llaves
        if (correcto) correcto=comprobarCompuesto(operacion, '{','}','[',']');
        /*if(correcto){
            System.out.println("Las llaves estan bien en el nuevo sistema");
        }
        else{
            System.out.println("Las llaves estan mal en el nuevo sistema");
        }*/
        
    //-----Comprobar signos a continuación
        for (int i=0; i<operacion.length(); i++){
            char actChar= operacion.charAt(i);
            String cierres = "}])";
            for (int a=0; a<cierres.length(); a++){
                char actCierre= cierres.charAt(a);
                if (actCierre==actChar&&i!=operacion.length()-1){
                    char nxtChar=operacion.charAt(i+1);
                    String wrongChars="0123456789.',";
                    for (int b=0; b<wrongChars.length(); b++){
                        char actWC=wrongChars.charAt(b);
                        if (nxtChar==actWC){
                            correcto=false;
                            b=wrongChars.length();
                            a=cierres.length();
                            i=operacion.length();
                        }
                    }
                }
            }
        }
        return correcto;
    }
    public static boolean comprobarSencillo(String operacion, char apa, char cre){
        boolean correcto=true;
        char [] sim={apa, cre};
        int numApa=0;
        int numCre=0;
        for (int i=0; i<operacion.length(); i++){
            char actChar=operacion.charAt(i);
            if (actChar==sim[0]){
                numApa++;
            }
            if (actChar==sim[1]){
                numCre++;
            }
        }
        if (numApa!=numCre){correcto=false;}else{
            int actApa=operacion.indexOf(sim[0]);
            int actCre= operacion.indexOf(sim[1]);
            for (int a=0; a<numApa; a++){
                if (actApa>actCre){
                    correcto=false;
                    a=numApa+1;
                }else if(actApa==(actCre-1)){
                    correcto=false;
                    a=numApa+1;
                }
                else{
                    int actApa2=operacion.indexOf(sim[0], actApa+1);
                    if (actApa2<actCre&&actApa2!=-1){
                        correcto=false;
                        a=numApa+1;
                    }else{
                        actApa=actApa2;
                        actCre=operacion.indexOf(sim[1],actCre+1);
                    }
                }
            }
                    
        }
        return correcto;
    }
    public static boolean comprobarCompuesto(String operacion, char apa, char cre, char sub1, char sub2){
        boolean correcto=true;
        char [] sim={apa, cre, sub1, sub2};
        int numApa=0;
        int numCre=0;
        for (int i=0; i<operacion.length(); i++){
            char actChar=operacion.charAt(i);
            if (actChar==sim[0]){
                numApa++;
            }
            if (actChar==sim[1]){
                numCre++;
            }
        }
        if (numApa!=numCre){correcto=false;}else{
            int actApa=operacion.indexOf(sim[0]);
            int actCre= operacion.indexOf(sim[1]);
            for (int a=0; a<numApa; a++){
                if (actApa>actCre){
                    correcto=false;
                    a=numApa+1;
                }else{
                    int actApa2=operacion.indexOf(sim[0], actApa+1);
                    if (actApa2<actCre&&actApa2!=-1){
                        correcto=false;
                        a=numApa+1;
                    }else{
                        correcto =operacion.indexOf(sim[2])!=-1;
                        correcto = comprobarSencillo(operacion.substring(actApa+1, actCre),sim[2], sim[3]);
                        if (correcto==true){
                            actApa=actApa2;
                            actCre=operacion.indexOf(sim[1],actCre+1);
                        }
                    }
                }
            }
                    
        }
        return correcto;
    }
}