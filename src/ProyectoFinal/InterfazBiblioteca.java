package ProyectoFinal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InterfazBiblioteca {
    private static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Gestión de Biblioteca");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Botón para agregar usuario
        JButton addUserButton = new JButton("Agregar Usuario");
        addUserButton.setBounds(10, 20, 150, 25);
        panel.add(addUserButton);

        // Botón para agregar libro
        JButton addBookButton = new JButton("Agregar Libro");
        addBookButton.setBounds(10, 50, 150, 25);
        panel.add(addBookButton);

        // Botón para prestar libro
        JButton borrowBookButton = new JButton("Prestar Libro");
        borrowBookButton.setBounds(10, 80, 150, 25);
        panel.add(borrowBookButton);

        // Botón para devolver libro
        JButton returnBookButton = new JButton("Devolver Libro");
        returnBookButton.setBounds(10, 110, 150, 25);
        panel.add(returnBookButton);

        // Botón para generar reporte
        JButton reportButton = new JButton("Generar Reporte");
        reportButton.setBounds(10, 140, 150, 25);
        panel.add(reportButton);

        // Acción para agregar usuario
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("Ingrese ID del Usuario:");
                String nombre = JOptionPane.showInputDialog("Ingrese Nombre del Usuario:");
                String email = JOptionPane.showInputDialog("Ingrese Email del Usuario:");
                biblioteca.agregarUsuario(new Usuario(id, nombre, email));
                JOptionPane.showMessageDialog(null, "Usuario agregado correctamente.");
            }
        });

        // Acción para agregar libro
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("Ingrese ID del Libro:");
                String titulo = JOptionPane.showInputDialog("Ingrese Título del Libro:");
                String autor = JOptionPane.showInputDialog("Ingrese Autor del Libro:");
                String categoria = JOptionPane.showInputDialog("Ingrese Categoría del Libro:");
                biblioteca.agregarLibro(new Libro(id, titulo, autor, categoria));
                JOptionPane.showMessageDialog(null, "Libro agregado correctamente.");
            }
        });

        // Acción para prestar libro
        borrowBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idUsuario = JOptionPane.showInputDialog("Ingrese ID del Usuario:");
                String tituloLibro = JOptionPane.showInputDialog("Ingrese Título del Libro:");
                biblioteca.prestarLibro(idUsuario, tituloLibro);
                JOptionPane.showMessageDialog(null, "Libro prestado correctamente.");
            }
        });

        // Acción para devolver libro
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idUsuario = JOptionPane.showInputDialog("Ingrese ID del Usuario:");
                String tituloLibro = JOptionPane.showInputDialog("Ingrese Título del Libro:");
                biblioteca.devolverLibro(idUsuario, tituloLibro);
                JOptionPane.showMessageDialog(null, "Libro devuelto correctamente.");
            }
        });

        // Acción para generar reporte
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Libro> librosPrestados = biblioteca.librosPrestados();
                List<Libro> librosDisponibles = biblioteca.librosDisponibles();

                StringBuilder report = new StringBuilder();
                report.append("Libros Prestados:\n");
                for (Libro libro : librosPrestados) {
                    report.append(libro.toString()).append("\n");
                }

                report.append("\nLibros Disponibles:\n");
                for (Libro libro : librosDisponibles) {
                    report.append(libro.toString()).append("\n");
                }

                JTextArea textArea = new JTextArea(report.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new java.awt.Dimension(380, 200));
                JOptionPane.showMessageDialog(null, scrollPane, "Reporte de Libros", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}

