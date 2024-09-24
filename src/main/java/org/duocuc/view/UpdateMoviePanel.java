package org.duocuc.view;

import org.duocuc.controller.MovieController;
import org.duocuc.model.Movie;

import javax.swing.*;
import java.awt.*;

public class UpdateMoviePanel extends JPanel {
    private MovieController controller;
    private JTextField idField, tituloField, directorField, anioField, duracionField, generoField;

    public UpdateMoviePanel(MovieController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título del panel
        JLabel titleLabel = new JLabel("Modificar Película");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, gbc);

        // Campos de entrada
        idField = createLabeledTextField("ID:", gbc);
        tituloField = createLabeledTextField("Título:", gbc);
        directorField = createLabeledTextField("Director:", gbc);
        anioField = createLabeledTextField("Año:", gbc);
        duracionField = createLabeledTextField("Duración (minutos):", gbc);
        generoField = createLabeledTextField("Género:", gbc);

        // Botones
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

    private JTextField createLabeledTextField(String labelText, GridBagConstraints gbc) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(150, 25));
        panel.add(label, BorderLayout.WEST);

        JTextField textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(200, 30));
        panel.add(textField, BorderLayout.CENTER);

        add(panel, gbc);
        return textField;
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
            int id = Integer.parseInt(idField.getText());
            String titulo = tituloField.getText();
            String director = directorField.getText();
            int anio = Integer.parseInt(anioField.getText());
            int duracion = Integer.parseInt(duracionField.getText());
            String genero = generoField.getText();

            String result = controller.updateMovie(id, titulo, director, anio, duracion, genero);
            JOptionPane.showMessageDialog(this, result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos para ID, Año y Duración.");
        }
    }

    private void clearFields() {
        idField.setText("");
        tituloField.setText("");
        directorField.setText("");
        anioField.setText("");
        duracionField.setText("");
        generoField.setText("");
    }
}