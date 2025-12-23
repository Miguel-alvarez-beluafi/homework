/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class Coche {
    public int ruedas=4;
    public double velocidadMaxima=300;
    public String modelo="Audi A6";
    
    public Coche (int ruedas, double velocidadMaxima){
        this.ruedas=ruedas;
        this.velocidadMaxima=velocidadMaxima;
    }
    
    public void repro( double ganancia){
        velocidadMaxima=velocidadMaxima*ganancia;
    }
}
