
/**
 *
 * @author Miguel Álvarez
 */
import java.util.Arrays;
public class EjercicioMorse {
    public static void main(String[] args) {
        operacionesMorse om = new operacionesMorse();
        System.out.println("Hello World!");
        
        //PRIMER SENTIDO
        om.primerSentido();
        System.out.println("Cadena de numeros: "+Arrays.toString(om.cadena));
        System.out.println("Cadena de numeros: "+Arrays.toString(om.morse));

        //SEGUNDO SENTIDO
        om.segundoSentido();
        System.out.println("Cadena de caracteres: "+Arrays.toString(om.charArray));
        System.out.println("Cadena Morse: "+Arrays.toString(om.intArray));
        System.out.println("Total de caracteres validos: "+om.numCoin);
    }
    
}
