package org.duocuc.view;

import org.duocuc.controller.MovieController;

import javax.swing.*;
import java.awt.*;

public class DeleteMoviePanel extends JPanel {
    private MovieController controller;
    private JTextField idField;

    public DeleteMoviePanel(MovieController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        add(new JLabel("Eliminar Película"), gbc);

        idField = new JTextField(20);
        add(new JLabel("ID de la película:"), gbc);
        add(idField, gbc);

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(e -> deleteMovie());

        add(deleteButton, gbc);
    }

    private void deleteMovie() {
        try {
            int id = Integer.parseInt(idField.getText());
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea eliminar esta película?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                String result = controller.deleteMovie(id);
                JOptionPane.showMessageDialog(this, result);
                idField.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.");
        }
    }
}