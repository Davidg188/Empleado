/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

/**
 *
 * @author DavidG
 */
final class Empleado {
    private String nombre;
    private int edad;
    private double salario;

    public Empleado(String nombre, int edad, double salario) {
        setNombre(nombre);
        setEdad(edad);
        setSalario(salario);
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String obtenerInformacion() {
        return String.format("Nombre: %s, Edad: %d, Salario: %.2f", nombre, edad, salario);
    }
}

class GestorEmpleados {
    private final Empleado[] empleados;
    private int contador;

    public GestorEmpleados(int capacidad) {
        this.empleados = new Empleado[capacidad];
        this.contador = 0;
    }

    public void agregarEmpleado(String nombre, int edad, double salario) {
        if (contador >= empleados.length) {
            System.out.println("No hay espacio para mas empleados");
        } else {
            empleados[contador++] = new Empleado(nombre, edad, salario);
            System.out.println("Empleado agregado exitosamente");
        }
    }

    public void mostrarEmpleados() {
        if (contador == 0) {
            System.out.println("No hay empleados registrados");
        } else {
            for (int i = 0; i < contador; i++) {
                System.out.println(empleados[i].obtenerInformacion());
            }
        }
    }

    public void buscarEmpleado(String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (empleados[i].getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Empleado encontrado: " + empleados[i].obtenerInformacion());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Empleado no encontrado");
        }
    }

    public void modificarEmpleado(String nombre, int nuevaEdad, double nuevoSalario) {
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (empleados[i].getNombre().equalsIgnoreCase(nombre)) {
                empleados[i].setEdad(nuevaEdad);
                empleados[i].setSalario(nuevoSalario);
                System.out.println("Empleado modificado exitosamente");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Empleado no encontrado.");
        }
    }

    public void eliminarEmpleado(String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (empleados[i].getNombre().equalsIgnoreCase(nombre)) {
                for (int j = i; j < contador - 1; j++) {
                    empleados[j] = empleados[j + 1];
                }
                empleados[--contador] = null;
                System.out.println("Empleado eliminado exitosamente");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Empleado no encontrado.");
        }
    }

    public void ordenarEmpleadosPorSalario() {
        for (int i = 0; i < contador - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < contador; j++) {
                if (empleados[j].getSalario() < empleados[minIndex].getSalario()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Empleado temp = empleados[i];
                empleados[i] = empleados[minIndex];
                empleados[minIndex] = temp;
            }
        }
        System.out.println("Empleados ordenados por salario:");
        mostrarEmpleados();
    }

    public void ordenarEmpleadosPorEdad() {
        for (int i = 0; i < contador - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < contador; j++) {
                if (empleados[j].getEdad() < empleados[minIndex].getEdad()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Empleado temp = empleados[i];
                empleados[i] = empleados[minIndex];
                empleados[minIndex] = temp;
            }
        }
        System.out.println("Empleados ordenados por edad:");
        mostrarEmpleados();
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        GestorEmpleados gestor = new GestorEmpleados(10);
        while (true) {
            mostrarMenu();
            int opcion = leerEntero();
            switch (opcion) {
                case 1 -> agregarEmpleado(gestor);
                case 2 -> gestor.mostrarEmpleados();
                case 3 -> buscarEmpleado(gestor);
                case 4 -> modificarEmpleado(gestor);
                case 5 -> eliminarEmpleado(gestor);
                case 6 -> gestor.ordenarEmpleadosPorSalario();
                case 7 -> gestor.ordenarEmpleadosPorEdad();
                case 8 -> {
                    System.out.println("Saliendo");
                    return;
                }
                default -> System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n|||||| MENU ||||||");
        System.out.println("1. Agregar empleado");
        System.out.println("2. Mostrar empleados");
        System.out.println("3. Buscar empleado");
        System.out.println("4. Modificar empleado");
        System.out.println("5. Eliminar empleado");
        System.out.println("6. Ordenar empleados por salario");
        System.out.println("7. Ordenar empleados por edad");
        System.out.println("8. Salir");
        System.out.print("Elija una opcion: ");
    }

    private static void agregarEmpleado(GestorEmpleados gestor) throws Exception {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = leerCadena();
        System.out.print("Ingrese la edad del empleado: ");
        int edad = leerEntero();
        System.out.print("Ingrese el salario del empleado: ");
        double salario = leerDecimal();
        gestor.agregarEmpleado(nombre, edad, salario);
    }

    private static void buscarEmpleado(GestorEmpleados gestor) throws Exception {
        System.out.print("Ingrese el nombre del empleado a buscar: ");
        String nombre = leerCadena();
        gestor.buscarEmpleado(nombre);
    }

    private static void modificarEmpleado(GestorEmpleados gestor) throws Exception {
        System.out.print("ingrese el nombre del empleado a modificar: ");
        String nombre = leerCadena();
        System.out.print("Ingrese la nueva edad: ");
        int nuevaEdad = leerEntero();
        System.out.print("Ingrese el nuevo salario: ");
        double nuevoSalario = leerDecimal();
        gestor.modificarEmpleado(nombre, nuevaEdad, nuevoSalario);
    }

    private static void eliminarEmpleado(GestorEmpleados gestor) throws Exception {
        System.out.print("Ingrese el nombre del empleado a eliminar: ");
        String nombre = leerCadena();
        gestor.eliminarEmpleado(nombre);
    }

    private static int leerEntero() throws Exception {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            try {
                numero = Integer.parseInt(leerCadena());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("ingrese un numero valido: ");
            }
        }
        return numero;
    }

    private static double leerDecimal() throws Exception {
        double numero = 0;
        boolean valido = false;
        while (!valido) {
            try {
                numero = Double.parseDouble(leerCadena());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("ingrese un numero valido: ");
            }
        }
        return numero;
    }

    private static String leerCadena() throws Exception {
        StringBuilder input = new StringBuilder();
        int caracter;
        while ((caracter = System.in.read()) != '\n') {
            input.append((char) caracter);
        }
        return input.toString().trim();
    }
}