package org.duocuc.view;

import org.duocuc.controller.MovieController;

import javax.swing.*;
import java.awt.*;

public class AddMoviePanel extends MoviePanelBase {

    public AddMoviePanel(MovieController controller) {
        super(controller,"Agregar Nueva Película");
        initButtonComponents();
    }

    private void initButtonComponents() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Agregar");
        addButton.setPreferredSize(new Dimension(120, 30));
        addButton.addActionListener(e -> addMovie());
        buttonPanel.add(addButton);

        JButton clearButton = new JButton("Limpiar");
        clearButton.setPreferredSize(new Dimension(120, 30));
        clearButton.addActionListener(e -> clearFields());
        buttonPanel.add(clearButton);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
    }

    private void addMovie() {
        try {

            int id = getNumberFormat(idField.getText(), "id");
            String titulo = getValidString(tituloField.getText(), "titulo");
            String director = getValidString(directorField.getText(), "director");
            int anio = getYearFormat(anioField.getText());
            int duracion = getNumberFormat(duracionField.getText(), "duracion");
            String genero = getValidString(generoField.getText(), "genero");

            String result = controller.insertMovie(id, titulo, director, anio, duracion, genero);
            JOptionPane.showMessageDialog(this, result);
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para ID, Año y Duración.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}