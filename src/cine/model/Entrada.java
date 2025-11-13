
package cine.model;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Entrada implements Serializable {
    private Cliente cliente;
    private Sala sala;
    private int fila;
    private int numero;
    private LocalDateTime fechaHora;

    public Entrada(Cliente cliente, Sala sala, int fila, int numero, LocalDateTime fechaHora) {
        this.cliente = cliente;
        this.sala = sala;
        this.fila = fila;
        this.numero = numero;
        this.fechaHora = fechaHora;
    }

    public Cliente getCliente() { return cliente; }
    public Sala getSala() { return sala; }
    public int getFila() { return fila; }
    public int getNumero() { return numero; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override public String toString() {
        return cliente + " | " + sala.getPelicula() + " | Sala " + sala.getNumeroSala() +
               " | F" + fila + " B" + numero + " | " + fechaHora;
    }
}

