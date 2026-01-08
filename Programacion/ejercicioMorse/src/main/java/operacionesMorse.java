
/**
 *
 * @author Usuario
 */
import java.util.Random;
import java.util.Arrays;
        
public class operacionesMorse {
    Random creador = new Random();
    
    int cadena[]=new int [10];
    String morse[]= new String [10];
    
    char charArray[]=new char[30];
    String intArray[]=new String[6];
    int numCoin= 0;
    
    public void primerSentido(){
        
        System.out.println(cadena.length);
        for(int i=0; i<10; i++){
            int actInt= creador.nextInt(10);
            String actString ="";
            cadena[i]=actInt;
            //System.out.println("Int actual: "+actInt);
            switch (actInt){
                case 0:
                    actString="-----";
                    break;
                case 1:
                    actString=".----";
                    break;
                case 2:
                    actString="..---";
                    break;
                case 3:
                    actString="...--";
                    break;
                case 4:
                    actString="....-";
                    break;
                case 5:
                    actString=".....";
                    break;
                case 6:
                    actString="-....";
                    break;
                case 7:
                    actString="--...";
                    break;
                case 8:
                    actString="---..";
                    break;
                case 9:
                    actString="----.";
                    break;
            }
            //System.out.println("Morse: "+actString);
            morse[i]=actString;
        }
        //System.out.println(cadena);
    }
    public void segundoSentido(){
        //Creación de la cadena
        for (int i=0; i<30; i++){
            int actInt=creador.nextInt(2);
            char actChar=' ';
            if(actInt==0){ actChar='.';}else{actChar='-';}
            charArray[i]=actChar;
            
        }
        //Interpretación de la cadena
        int controlPos=0;
        for (int i=5; i<31; i+=5){
            int inicio = i-5;
            int finArray = i-1;
            char[] actArr=Arrays.copyOfRange(charArray, inicio, finArray+1);
            //int actArray []=new int[5];
            String actMors= Arrays.toString(actArr);
            actMors=actMors.replace("[", "");
            actMors=actMors.replace("]", "");
            actMors=actMors.replace(",", "");
            actMors=actMors.replace(" ", "");
            System.out.println("Control numero "+(controlPos+1)+": "+actMors);
            switch (actMors){
                case "-----":
                    intArray[controlPos]="0";
                    numCoin++;
                    break;
                case ".----":
                    intArray[controlPos]="1";
                    numCoin++;
                    break;
                case "..---":
                    intArray[controlPos]="2";
                    numCoin++;
                    break;
                case "...--":
                    intArray[controlPos]="3";
                    numCoin++;
                    break;
                case "....-":
                    intArray[controlPos]="4";
                    numCoin++;
                    break;
                case ".....":
                    intArray[controlPos]="5";
                    numCoin++;
                    break;
                case "-....":
                    intArray[controlPos]="6";
                    numCoin++;
                    break;
                case "--...":
                    intArray[controlPos]="7";
                    numCoin++;
                    break;
                case "---..":
                    intArray[controlPos]="8";
                    numCoin++;
                    break;
                case "----.":
                    intArray[controlPos]="9";
                    numCoin++;
                    break;  
                default:
                    intArray[controlPos]="none";
            }
            controlPos++;
        }
    }
}
