/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

public class CineData {
    private static final ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private static final ObservableList<Sala> salas = FXCollections.observableArrayList();
    private static final ObservableList<Entrada> entradas = FXCollections.observableArrayList();

    public static ObservableList<Cliente> getClientes(){ return clientes; }
    public static ObservableList<Sala> getSalas(){ return salas; }
    public static ObservableList<Entrada> getEntradas(){ return entradas; }
// arriba, junto con el resto de atributos estáticos
private static Cliente clienteActual;

public static Cliente getClienteActual() {
    return clienteActual;
}

public static void setClienteActual(Cliente c) {
    clienteActual = c;
}

// método de login
public static Cliente login(String email, String password) {
    return clientes.stream()
            .filter(c -> c.getEmail().equalsIgnoreCase(email)
                      && c.getPassword().equals(password))
            .findFirst()
            .orElse(null);
}

    // registrar cliente (email único)
    public static boolean registrarCliente(Cliente c){
        boolean existe = clientes.stream()
                .anyMatch(x -> x.getEmail().equalsIgnoreCase(c.getEmail()));
        if (existe) return false;
        return clientes.add(c);
    }

    // comprar entrada (marca butaca ocupada y crea Entrada)
    public static boolean comprarEntrada(Cliente cli, Sala sala, int fila, int numero){
        if (cli == null || sala == null) return false;
        if (!sala.estaLibre(fila, numero)) return false;
        sala.getButaca(fila, numero).ocupar();
        entradas.add(new Entrada(cli, sala, fila, numero, LocalDateTime.now()));
        return true;
    }

    // datos de prueba
    public static void cargarDatosDemo(){
        if (!clientes.isEmpty() || !salas.isEmpty()) return; // para no repetir
        clientes.addAll(
            new Cliente("Ana", "ana@mail.com", "1234"),
            new Cliente("Luis", "luis@mail.com", "1234")
        );
        salas.addAll(
            new Sala(1, "Interstellar", 10, 12),
            new Sala(2, "Oppenheimer", 10, 12)
        );
    }
}
