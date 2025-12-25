import java.util.Random;
  import java.util.Date;


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
    public boolean getBoolean(){
        boolean rdValue=rd.nextBoolean();
        return rdValue;
    }
}
