package org.duocuc.view;

import org.duocuc.controller.MovieController;
import org.duocuc.model.Movie;

import javax.swing.*;
import java.awt.*;

public class UpdateMoviePanel extends MoviePanelBase {

    public UpdateMoviePanel(MovieController controller) {
        super(controller,"Modificar Película");
        inutButtonComponents();
    }

    private void inutButtonComponents() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton searchButton = new JButton("Buscar");
        searchButton.setPreferredSize(new Dimension(120, 30));
        searchButton.addActionListener(e -> searchMovie());
        buttonPanel.add(searchButton);

        JButton updateButton = new JButton("Actualizar");
        updateButton.setPreferredSize(new Dimension(120, 30));
        updateButton.addActionListener(e -> updateMovie());
        buttonPanel.add(updateButton);

        JButton clearButton = new JButton("Limpiar");
        clearButton.setPreferredSize(new Dimension(120, 30));
        clearButton.addActionListener(e -> clearFields());
        buttonPanel.add(clearButton);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
    }

    private void searchMovie() {
        try {
            int id = Integer.parseInt(idField.getText());
            Movie movie = controller.getMovieById(id);
            if (movie != null) {
                tituloField.setText(movie.getTitulo());
                directorField.setText(movie.getDirector());
                anioField.setText(String.valueOf(movie.getAnio()));
                duracionField.setText(String.valueOf(movie.getDuracion()));
                generoField.setText(movie.getGenero());
            } else {
                JOptionPane.showMessageDialog(this, "Película no encontrada.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.");
        }
    }

    private void updateMovie() {
        try {

            int id = getNumberFormat(idField.getText(), "id");
            String titulo = getValidString(tituloField.getText(), "titulo");
            String director = getValidString(directorField.getText(), "director");
            int anio = getYearFormat(anioField.getText());
            int duracion = getNumberFormat(duracionField.getText(), "duracion");
            String genero = getValidString(generoField.getText(), "genero");


            String result = controller.updateMovie(id, titulo, director, anio, duracion, genero);
            JOptionPane.showMessageDialog(this, result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para ID, Año y Duración.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}