package cine.storage;

import cine.model.CineData;
import cine.model.CineEstado;
import cine.model.Cliente;
import cine.model.Sala;
import cine.model.Entrada;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CineStorage {

    private static final String ARCHIVO = "cine.dat";

    public static boolean cargarDatos() {
        File f = new File(ARCHIVO);
        if (!f.exists()) {
            return false;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof CineEstado estado) {

                List<Cliente> clientes = estado.getClientes();
                List<Sala> salas = estado.getSalas();
                List<Entrada> entradas = estado.getEntradas();

                CineData.getClientes().setAll(clientes);
                CineData.getSalas().setAll(salas);
                CineData.getEntradas().setAll(entradas);

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {

            List<Cliente> clientes = new ArrayList<>(CineData.getClientes());
            List<Sala> salas = new ArrayList<>(CineData.getSalas());
            List<Entrada> entradas = new ArrayList<>(CineData.getEntradas());

            CineEstado estado = new CineEstado(clientes, salas, entradas);

            oos.writeObject(estado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
