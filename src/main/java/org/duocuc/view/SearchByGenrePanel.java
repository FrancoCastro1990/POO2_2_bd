package org.duocuc.view;

import org.duocuc.controller.MovieController;
import org.duocuc.model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchByGenrePanel extends JPanel {
    private MovieController controller;
    private JTextField genreField;
    private JTable movieTable;
    private DefaultTableModel tableModel;

    public SearchByGenrePanel(MovieController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Panel de búsqueda
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Género:"));
        genreField = new JTextField(20);
        searchPanel.add(genreField);
        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(e -> searchMovies());
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Crear la tabla
        String[] columnNames = {"ID", "Título", "Director", "Año", "Duración", "Género"};
        tableModel = new DefaultTableModel(columnNames, 0);
        movieTable = new JTable(tableModel);
        movieTable.setFillsViewportHeight(true);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(movieTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void searchMovies() {
        String genre = genreField.getText();
        List<Movie> movies = controller.searchMoviesByGenre(genre);
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
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron películas del género especificado.");
        }
    }
}