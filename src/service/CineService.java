package service;

import model.Cliente;
import model.Entrada;
import model.Funcion;
import model.Pelicula;
import model.Persona;
import util.InputUtil;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class CineService {

    private List<Cliente> clientes;
    private List<Funcion> funciones;
    private Scanner scanner;

    public CineService(){
        clientes = new ArrayList<>();
        funciones = new ArrayList<>();
        scanner = new Scanner(System.in);
        cargarDatosDePrueba();
    }


    public void iniciar(){

        int opcion = 0;
        boolean activado = true;

        while(activado){
            try{
                mostrarMenu();
                opcion = InputUtil.leerInt("Seleccione una opción: ");
                switch (opcion) {
                    case 1 -> crearCliente();
                    case 2 -> verCartelera();
                    case 3 -> comprarEntrada();
                    case 4 -> verEntradasCliente();
                    case 0 -> {
                        System.out.println("Saliendo del sistema...");
                        activado = false;
                    }
                    default -> System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }

    private void mostrarMenu(){
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1) Crear cliente");
        System.out.println("2) Ver cartelera");
        System.out.println("3) Comprar entrada");
        System.out.println("4) Ver entradas del cliente");
        System.out.println("0) Salir");
    }

    private void crearCliente(){
        String nombre = InputUtil.leerString("Ingrese el nombre del cliente: ");
        clientes.add(new Cliente(nombre));
        System.out.println("Cliente " + nombre + " registrado con éxito.\n");
    }

    private void verCartelera(){
        System.out.println("\n=== CARTELERA ===");
        if (funciones.isEmpty()) {
            System.out.println("No hay funciones disponibles.\n");
            return;
        }
        for (int i = 0; i < funciones.size(); i++) {
            System.out.println((i + 1) + ") " + funciones.get(i).toString());
        }
        System.out.println();
    }

    private void comprarEntrada() { // Método para comprar una entrada

        if (clientes.isEmpty()) { // Verifica si no hay clientes registrados
            System.out.println("No hay clientes"); // Muestra mensaje
            return; // Sale del método
        }

        int c; // Variable para almacenar el índice del cliente

        // Selección de cliente con validación usando do-while
        do {
            System.out.println("Seleccione cliente:"); // Solicita selección

            for (int i = 0; i < clientes.size(); i++) { // Recorre clientes
                System.out.println(i + ") " + clientes.get(i).getNombre()); // Muestra índice y nombre
            }

            c = InputUtil.leerInt("Ingrese opción: "); // Lee el índice ingresado

            if (c < 0 || c >= clientes.size()) { // Valida si está fuera de rango
                System.out.println("Cliente inválido"); // Mensaje de error
            }

        } while (c < 0 || c >= clientes.size()); // Repite hasta que sea válido

        int f; // Variable para índice de función

        // Selección de función con validación
        do {
            System.out.println("Seleccione función:"); // Solicita función
            verCartelera(); // Muestra cartelera

            f = InputUtil.leerInt("Ingrese número de función (1-" + funciones.size() + "): "); // Lee índice
            f--; // Convertir a índice de array

            if (f < 0 || f >= funciones.size()) { // Valida rango
                System.out.println("Esa película no está en cartelera"); // Mensaje error
            }

        } while (f < 0 || f >= funciones.size()); // Repite hasta válido

        int asiento; // Variable para el número de asiento
        Funcion funcion = funciones.get(f); // Obtiene la función seleccionada

        // Selección de asiento con validación
        do {
            System.out.println("Número de asiento (1-" + funcion.getCapacidadSala() + "):"); // Solicita asiento
            asiento = InputUtil.leerInt("Ingrese asiento: "); // Lee número

            if (!funcion.asientoValido(asiento)) { // Valida que esté dentro del rango
                System.out.println("Asiento inválido"); // Mensaje error
                asiento = -1;
            } else if (funcion.asientoYaVendido(asiento)) { // Verifica si ya está ocupado
                System.out.println("Ese asiento ya está ocupado"); // Mensaje
                asiento = -1; // Fuerza repetir el ciclo
            }

        } while (asiento < 0); // Repite hasta que el asiento sea válido

        Cliente cliente = clientes.get(c); // Obtiene el cliente seleccionado

        Entrada entrada = new Entrada(cliente, funcion, asiento); // Crea la entrada

        if (funcion.venderAsiento(asiento)) { // Intenta vender el asiento
            cliente.agregarEntrada(entrada); // Asocia la entrada al cliente
            System.out.println("Compra exitosa \n "); // Mensaje de éxito
        } else {
            System.out.println("No se pudo comprar entrada"); // Mensaje de fallo
        }
    }

    private void verEntradasCliente() { // Método para ver entradas de un cliente

        if (clientes.isEmpty()) { // Verifica si hay clientes
            System.out.println("No hay clientes"); // Mensaje
            return; // Sale del método
        }

        int c; // Índice del cliente

        // Selección de cliente con validación
        do {
            System.out.println("Seleccione cliente:"); // Solicita cliente

            for (int i = 0; i < clientes.size(); i++) { // Recorre clientes
                System.out.println(i + ") " + clientes.get(i).getNombre()); // Muestra nombres
            }

            c = InputUtil.leerInt("Ingrese opción: "); // Lee índice

            if (c < 0 || c >= clientes.size()) { // Valida rango
                System.out.println("Cliente inválido"); // Mensaje error
            }

        } while (c < 0 || c >= clientes.size()); // Repite hasta válido

        System.out.println("\n=== ENTRADAS DE " + clientes.get(c).getNombre() + " ===");
        List<Object> entradas = clientes.get(c).getEntradasCompradas();

        if (entradas.isEmpty()) {
            System.out.println("Este cliente no tiene entradas compradas.\n");
            return;
        }

        for (Object obj : entradas) { // Recorre entradas del cliente
            if (obj instanceof Entrada) {
                ((Entrada) obj).mostrarResumen(); // Muestra cada entrada
                System.out.println("---");
            }
        }
        System.out.println();
    }

    private void cargarDatosDePrueba() {
        Pelicula p1 = new Pelicula("Inception", 148);
        Pelicula p2 = new Pelicula("Avatar", 162);
        funciones.add(new Funcion(p1, "18:00", 10));
        funciones.add(new Funcion(p2, "20:30", 8));
    }
}
