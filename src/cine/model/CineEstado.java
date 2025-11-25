package cine.model;

import java.io.Serializable;
import java.util.List;

public class CineEstado implements Serializable {

    private List<Cliente> clientes;
    private List<Sala> salas;
    private List<Entrada> entradas;

    public CineEstado(List<Cliente> clientes,
            List<Sala> salas,
            List<Entrada> entradas) {
        this.clientes = clientes;
        this.salas = salas;
        this.entradas = entradas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }
}
