package registrodocumentos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 
 */
public class Documento {
    private String tipo;
    protected String numero;
    protected String cliente;
    protected double monto;

    public Documento(String tipo, String numero, String cliente, double monto) {
        this.tipo = tipo;
        this.numero = numero;
        this.cliente = cliente;
        this.monto = monto;
    }

    public String mostrar() {
        return "[" + tipo + "] Número: " + numero + " | Cliente: " + cliente + " | Monto: " + monto;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public double getMonto() {
        return monto;
    }
}
