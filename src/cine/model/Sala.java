package cine.model;

import java.io.Serializable;

public class Sala implements Serializable {

    private int numeroSala;
    private String pelicula;
    private double precioEntrada;
    private Butaca[][] mapa;

    public Sala(int numeroSala, String pelicula, double precioEntrada, int filas, int columnas) {
        this.numeroSala = numeroSala;
        this.pelicula = pelicula;
        this.precioEntrada = precioEntrada;
        this.mapa = new Butaca[filas][columnas];
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                mapa[f][c] = new Butaca(f + 1, c + 1);
            }
        }
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public String getPelicula() {
        return pelicula;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public int getFilas() {
        return mapa.length;
    }

    public int getColumnas() {
        return mapa[0].length;
    }

    public Butaca getButaca(int fila, int numero) {
        return mapa[fila - 1][numero - 1];
    }

    public boolean estaLibre(int fila, int numero) {
        return !getButaca(fila, numero).isOcupada();
    }

    public int contarLibres() {
        int libres = 0;
        for (Butaca[] fila : mapa) {
            for (Butaca b : fila) {
                if (!b.isOcupada()) {
                    libres++;
                }
            }
        }
        return libres;
    }

    @Override
    public String toString() {
        return "Sala " + numeroSala + " - " + pelicula + " (libres: " + contarLibres() + ")";
    }
}
