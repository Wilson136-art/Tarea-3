/*
 TAREA MODULO 3
Elaborado por: Wilson Ricardo Flores Juarez
Cuenta:202230120066
 */
package com.mycompany.libros;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Libro {
    private final String titulo;
    private final String autor;
    private final int anoPublicacion;
    private final String genero;
    private boolean disponible;

    public Libro(String titulo, String autor, int anoPublicacion, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.genero = genero;
        this.disponible = true; // Por defecto, un libro está disponible cuando se agrega a la biblioteca
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void prestar() {
        disponible = false;
    }

    public void devolver() {
        disponible = true;
    }

    @Override 
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anoPublicacion=" + anoPublicacion +
                ", genero='" + genero + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}

class Biblioteca {
    private final List<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    public void prestarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo) && libro.estaDisponible()) {
                libro.prestar();
                System.out.println("Se presto el libro: " + libro.getTitulo());
                return;
            }
        }
        System.out.println("El libro no está disponible para préstamo.");
    }

    public void devolverLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo) && !libro.estaDisponible()) {
                libro.devolver();
                System.out.println("Se devolvio el libro: " + libro.getTitulo());
                return;
            }
        }
        System.out.println("El libro no se puede devolver, o no está registrado en la biblioteca.");
    }

    public void mostrarLibros() {
        System.out.println("Libros disponibles en la biblioteca:");
        for (Libro libro : libros) {
            if (libro.estaDisponible()) {
                System.out.println(libro);
            }
        }
    }
    
    public void mostrarTodosLosLibros() {
        System.out.println("Todos los libros en la biblioteca:");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }
}

public class Libros {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Agregar algunos libros de ejemplo
        biblioteca.agregarLibro(new Libro("El Senor de los Anillos", "J.R.R. Tolkien", 1954, "Fantasia"));
        biblioteca.agregarLibro(new Libro("Cien anos de soledad", "Gabriel Garcia Marquez", 1967, "Realismo magico"));
        biblioteca.agregarLibro(new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", 1997, "Fantasia"));

        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menu de la Biblioteca ---");
            System.out.println("1. Agregar un nuevo libro");
            System.out.println("2. Buscar libro por titulo");
            System.out.println("3. Buscar libro por autor");
            System.out.println("4. Mostrar libros disponibles");
            System.out.println("5. Mostrar todos los libros");
            System.out.println("6. Prestar libro");
            System.out.println("7. Devolver libro");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el titulo del libro: ");
                    String nuevoTitulo = scanner.nextLine();
                    System.out.print("Ingrese el autor del libro: ");
                    String nuevoAutor = scanner.nextLine();
                    System.out.print("Ingrese el ano de publicacion del libro: ");
                    int nuevoAno = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    System.out.print("Ingrese el genero del libro: ");
                    String nuevoGenero = scanner.nextLine();
                    biblioteca.agregarLibro(new Libro(nuevoTitulo, nuevoAutor, nuevoAno, nuevoGenero));
                    System.out.println("Libro agregado exitosamente.");
                    break;
                case 2:
                    System.out.print("Ingrese el titulo del libro: ");
                    String titulo = scanner.nextLine();
                    List<Libro> resultadosTitulo = biblioteca.buscarPorTitulo(titulo);
                    if (!resultadosTitulo.isEmpty()) {
                        System.out.println("Resultados de la busqueda:");
                        for (Libro libro : resultadosTitulo) {
                            System.out.println(libro);
                        }
                    } else {
                        System.out.println("No se encontraron libros con ese titulo.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del autor: ");
                    String autor = scanner.nextLine();
                    List<Libro> resultadosAutor = biblioteca.buscarPorAutor(autor);
                    if (!resultadosAutor.isEmpty()) {
                        System.out.println("Resultados de la busqueda:");
                        for (Libro libro : resultadosAutor) {
                            System.out.println(libro);
                        }
                    } else {
                        System.out.println("No se encontraron libros de ese autor.");
                    }
                    break;
                case 4:
                    biblioteca.mostrarLibros();
                    break;
                case 5:
                    biblioteca.mostrarTodosLosLibros();
                    break;
                case 6:
                    System.out.print("Ingrese el titulo del libro a prestar: ");
                    String libroPrestar = scanner.nextLine();
                    biblioteca.prestarLibro(libroPrestar);
                    break;
                case 7:
                    System.out.print("Ingrese el titulo del libro a devolver: ");
                    String libroDevolver = scanner.nextLine();
                    biblioteca.devolverLibro(libroDevolver);
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, seleccione una opcion valida.");
            }
        } while (opcion != 8);
    }
}
