import java.util.Random;
import java.util.Date;
import java.io.IOException;


public class Coche {
    public int ruedas=4;
    public double velocidadMaxima=300;
    public String modelo="Audi A6";
    
    Random rd = new Random();
    Date date=new Date();
    
    public Coche (int ruedas, double velocidadMaxima){
        this.ruedas=ruedas;
        this.velocidadMaxima=velocidadMaxima;
    }
    
    public void repro( double ganancia){
        velocidadMaxima=velocidadMaxima*ganancia;
    }
    public String comprobarModelo (String nombre) throws IOException {
        if (nombre==null){
            throw new IllegalArgumentException("Fallo en comprobarModelo: cadena nula");
        }else{
         return nombre;
        }
    }
    public boolean getBoolean(){
        boolean rdValue=rd.nextBoolean();
        return rdValue;
    }
}
