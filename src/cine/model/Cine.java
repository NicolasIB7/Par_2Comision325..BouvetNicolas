
package cine.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Cine implements Serializable {
    private List<Sala> salas = new ArrayList<>();
    private List<Entrada> entradas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public List<Sala> getSalas() { return salas; }
    public List<Entrada> getEntradas() { return entradas; }
    public List<Cliente> getClientes() { return clientes; }

    // --- Clientes ---
    public boolean registrar(Cliente c) {
        boolean existe = clientes.stream().anyMatch(x -> x.getEmail().equalsIgnoreCase(c.getEmail()));
        if (existe) return false;
        return clientes.add(c);
    }

    public Optional<Cliente> login(String email, String pass) {
        return clientes.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email) && Objects.equals(c.getPassword(), pass))
                .findFirst();
    }

    // --- Negocio: comprar entrada ---
    public boolean comprarEntrada(Cliente cli, Sala sala, int fila, int numero) {
        if (cli == null || sala == null) return false;
        if (!sala.estaLibre(fila, numero)) return false;
        sala.getButaca(fila, numero).ocupar();
       entradas.add(new Entrada(cli, sala, fila, numero, sala.getPrecioEntrada(), LocalDateTime.now()));

        return true;
    }

    // Datos demo para probar sin JavaFX
    public void cargarDemo() {
        clientes.add(new Cliente("Ana", "ana@mail.com", "1234"));
        clientes.add(new Cliente("Luis", "luis@mail.com", "1234"));
        salas.add(new Sala(1, "Interstellar", 1200, 10, 12));
        salas.add(new Sala(2, "Oppenheimer",2000, 10, 12));
    }
}

