/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Usuario
 */
public class PruebaClases {

    
    
    public static void main(String[] args) {
        Coche A4 = new Coche(4, 320);
        
        boolean randomValue;
        
        System.out.println("Modelo: "+A4.modelo);
        System.out.println("Velocidad máxima: "+A4.velocidadMaxima);
        System.out.println("Realizando repro...");
        A4.repro(1.25);
        System.out.println("Velocidad máxima: "+A4.velocidadMaxima);
        randomValue=A4.getBoolean();
        System.out.println("El resultado booleano es "+randomValue);
    }
}
