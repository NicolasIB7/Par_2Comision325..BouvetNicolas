
package cine.model;


import java.io.Serializable;

public class Sala implements Serializable {
    private int numeroSala;
    private String pelicula;
    private Butaca[][] mapa; // filas x columnas

    public Sala(int numeroSala, String pelicula, int filas, int columnas) {
        this.numeroSala = numeroSala;
        this.pelicula = pelicula;
        this.mapa = new Butaca[filas][columnas];
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                mapa[f][c] = new Butaca(f + 1, c + 1); // 1-index
            }
        }
    }

    public int getNumeroSala() { return numeroSala; }
    public String getPelicula() { return pelicula; }
    public int getFilas() { return mapa.length; }
    public int getColumnas() { return mapa[0].length; }

    public Butaca getButaca(int fila, int numero) {
        // asumiendo 1..N
        return mapa[fila - 1][numero - 1];
    }

    public boolean estaLibre(int fila, int numero) {
        return !getButaca(fila, numero).isOcupada();
    }

    public int contarLibres() {
        int libres = 0;
        for (Butaca[] fila : mapa)
            for (Butaca b : fila)
                if (!b.isOcupada()) libres++;
        return libres;
    }

    @Override public String toString() {
        return "Sala " + numeroSala + " - " + pelicula + " (libres: " + contarLibres() + ")";
    }
}

