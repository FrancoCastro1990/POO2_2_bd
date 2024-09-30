package org.duocuc.view;
import org.duocuc.controller.MovieController;
import javax.swing.*;
import java.awt.*;

public abstract class MoviePanelBase extends JPanel {
    protected MovieController controller;
    protected JTextField idField, tituloField, directorField, anioField, duracionField, generoField;
    protected GridBagConstraints gbc;
    public MoviePanelBase(MovieController controller, String title) {
        this.controller = controller;
        initCommonComponents(title);
    }

    private void initCommonComponents(String title) {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);


        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, gbc);

        idField = createLabeledTextField("ID:", gbc);
        tituloField = createLabeledTextField("Título:", gbc);
        directorField = createLabeledTextField("Director:", gbc);
        anioField = createLabeledTextField("Año:", gbc);
        duracionField = createLabeledTextField("Duración (minutos):", gbc);
        generoField = createLabeledTextField("Género:", gbc);
    }

    protected JTextField createLabeledTextField(String labelText, GridBagConstraints gbc) {
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

    protected int getNumberFormat(String number, String fieldName) throws Exception {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            throw new Exception("Ingrese un numero valido en " + fieldName);
        }
    }

    protected String getValidString(String field, String fieldName) throws Exception {
        if (field == null || field.isEmpty()) {
            throw new Exception("Ingrese un valor en " + fieldName);
        }
        return field;
    }

    protected int getYearFormat(String year) throws Exception {
        if (year.length() != 4) {
            throw new Exception("Ingrese un año válido");
        }
        return Integer.parseInt(year);
    }

    protected void clearFields() {
        idField.setText("");
        tituloField.setText("");
        directorField.setText("");
        anioField.setText("");
        duracionField.setText("");
        generoField.setText("");
    }
}