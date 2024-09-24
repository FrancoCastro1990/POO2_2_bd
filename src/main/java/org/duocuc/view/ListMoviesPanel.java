package org.duocuc.view;

import org.duocuc.controller.MovieController;
import org.duocuc.model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListMoviesPanel extends JPanel {
    private MovieController controller;
    private JTable movieTable;
    private DefaultTableModel tableModel;

    public ListMoviesPanel(MovieController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Título del panel
        JLabel titleLabel = new JLabel("Lista de Películas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Crear la tabla
        String[] columnNames = {"ID", "Título", "Director", "Año", "Duración", "Género"};
        tableModel = new DefaultTableModel(columnNames, 0);
        movieTable = new JTable(tableModel);
        movieTable.setFillsViewportHeight(true);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(movieTable);
        add(scrollPane, BorderLayout.CENTER);

        // Botón de actualizar
        JButton refreshButton = new JButton("Actualizar Lista");
        refreshButton.addActionListener(e -> refreshMovieList());
        add(refreshButton, BorderLayout.SOUTH);

        refreshMovieList(); // Cargar la lista inicial
    }

    private void refreshMovieList() {
        List<Movie> movies = controller.listAllMovies();
        tableModel.setRowCount(0); // Limpiar la tabla
        if (movies != null && !movies.isEmpty()) {
            for (Movie movie : movies) {
                Object[] row = {
                        movie.getId(),
                        movie.getTitulo(),
                        movie.getDirector(),
                        movie.getAnio(),
                        movie.getDuracion(),
                        movie.getGenero()
                };
                tableModel.addRow(row);
            }
        }
    }
}