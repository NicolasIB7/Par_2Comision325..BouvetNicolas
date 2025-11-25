package cine.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

public class CineData {

    private static final ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private static final ObservableList<Sala> salas = FXCollections.observableArrayList();
    private static final ObservableList<Entrada> entradas = FXCollections.observableArrayList();

    public static ObservableList<Cliente> getClientes() {
        return clientes;
    }

    public static ObservableList<Sala> getSalas() {
        return salas;
    }

    public static ObservableList<Entrada> getEntradas() {
        return entradas;
    }
    private static Cliente clienteActual;

    public static Cliente getClienteActual() {
        return clienteActual;
    }

    public static void setClienteActual(Cliente c) {
        clienteActual = c;
    }

    public static Cliente login(String email, String password) {
        for (Cliente c : clientes) {
            if (c.getEmail().equalsIgnoreCase(email)
                    && c.getPassword().equals(password)) {
                return c;
            }
        }
        return null;
    }

    public static boolean registrarCliente(Cliente c) {
        for (Cliente cli : clientes) {
            if (cli.getEmail().equalsIgnoreCase(c.getEmail())) {
                return false;
            }
        }
        clientes.add(c);
        return true;
    }

    public static boolean comprarEntrada(Cliente cli, Sala sala, int fila, int numero) {
        if (cli == null || sala == null) {
            return false;
        }
        if (!sala.estaLibre(fila, numero)) {
            return false;
        }
        sala.getButaca(fila, numero).ocupar();
        entradas.add(new Entrada(
                cli,
                sala,
                fila,
                numero,
                sala.getPrecioEntrada(),
                LocalDateTime.now()
        ));

        return true;
    }

    public static void cargarDatosDemo() {
        if (!clientes.isEmpty() || !salas.isEmpty()) {
            return;
        }
        salas.addAll(
                new Sala(1, "Avengers", 5000, 10, 12),
                new Sala(2, "El conjuro 4", 7500, 8, 10),
                new Sala(3, "Rápido y furioso 11", 9000, 8, 10),
                new Sala(4, "Weapons", 5000, 10, 12)
        );

    }
}
