import java.util.Random;
public class Ejemplos_arrays {

    public static void main(String[] args) {
        int []dados=new int[30];
        int []notas=new int[30];
        //Valores random con Math
        for (int i=0; i<dados.length; i++){
            dados[i]= (int)(Math.random()*6)+1;
            System.out.println("Dados "+(i+1)+": "+dados[i]);
        }
        //Valores random con Random
        Random aleatorio =new Random();
        for (int i=0; i<notas.length;i++){
            notas[i]=aleatorio.nextInt(6)+1;
            //System.out.println("Notas "+(i+1)+": "+notas[i]);
        }
        //Ordenar con algoritmo de la burbuja
        for(int i=0; i < notas.length-1; i++){
            int aux = 0;
         // En cada iteración llegamos hasta n-1-i ya que hemos ordenado i enteros
         //en las i iteraciones pasadas.
            for(int j=0; j < (notas.length-1-i); j++){ 
                //Comparamos e intercambiamos si se cumple la condición
                if(notas[j] > notas[j+1]){ 
                        aux=notas[j];                
                        notas[j]=notas[j+1];        
                        notas[j+1]=aux;
                }   
            }
        }
        for (int i=0; i<notas.length;i++){
            System.out.println("Notas "+(i+1)+": "+notas[i]);
        }
        System.out.println("Hello World!");
    }
}
