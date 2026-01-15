
import java.io.*;

public class ArchivosPlanos {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        lecEsc funciona =new lecEsc();
        funciona.lec();
    }
}
class lecEsc{
    
    public void lec (){
        int [] cuenta = new int[10];
        try{
            FileReader entrada = new FileReader("C:\\Users\\Usuario\\Desktop\\Programacion\\homework\\Programacion\\ficheros_planos\\ficheros\\numeros.txt");
            int c = 0;
            do{
                c = entrada.read();
                System.out.println(c);
                if (c!=-1){
                    if (c!=59){
                        cuenta[c-48]++;
                        System.out.println("Sumado: "+cuenta[c-48]);
                    }
                }
            }while(c!=-1);
            
            for (int i=0; i<cuenta.length; i++){
                if (cuenta[i]>0){
                    System.out.println("Total de "+i+": "+cuenta[i]);
                }
            }
        }catch(IOException err){
            System.out.println(err);
        }
    }
}
