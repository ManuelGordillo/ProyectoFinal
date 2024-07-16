package ProyectoFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Usuario> usuarios;
    private List<Libro> libros;
    private List<Prestamo> prestamos;

    public Biblioteca() {
        usuarios = new ArrayList<>();
        libros = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    // Gestión de Usuarios
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void actualizarUsuario(String id, String nombre, String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                usuario.setNombre(nombre);
                usuario.setEmail(email);
                break;
            }
        }
    }

    public void eliminarUsuario(String id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    // Gestión de Libros
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void actualizarLibro(String id, String titulo, String autor, String categoria) {
        for (Libro libro : libros) {
            if (libro.getId().equals(id)) {
                libro.setTitulo(titulo);
                libro.setAutor(autor);
                libro.setCategoria(categoria);
                break;
            }
        }
    }

    public void eliminarLibro(String id) {
        libros.removeIf(libro -> libro.getId().equals(id));
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
                return libro;
            }
        }
        return null;
    }

    // Préstamos y Devoluciones
    public void prestarLibro(String idUsuario, String idLibro) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        Libro libro = buscarLibroPorTitulo(idLibro);
        if (usuario != null && libro != null && libro.isDisponible()) {
            libro.setDisponible(false);
            Prestamo prestamo = new Prestamo(usuario, libro, LocalDate.now());
            prestamos.add(prestamo);
        }
    }

    public void devolverLibro(String idUsuario, String idLibro) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().getId().equals(idUsuario) && prestamo.getLibro().getId().equals(idLibro) && prestamo.getFechaDevolucion() == null) {
                prestamo.devolverLibro();
                break;
            }
        }
    }

    // Generación de Reportes
    public List<Libro> librosPrestados() {
        List<Libro> prestados = new ArrayList<>();
        for (Libro libro : libros) {
            if (!libro.isDisponible()) {
                prestados.add(libro);
            }
        }
        return prestados;
    }

    public List<Libro> librosDisponibles() {
        List<Libro> disponibles = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                disponibles.add(libro);
            }
        }
        return disponibles;
    }
}

