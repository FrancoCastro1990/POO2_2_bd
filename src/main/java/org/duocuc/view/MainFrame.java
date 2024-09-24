package org.duocuc.view;

import org.duocuc.controller.MovieController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private MovieController controller;
    private JPanel contentPanel;

    public MainFrame(MovieController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Películas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear barra de herramientas
        JToolBar toolBar = new JToolBar();
        toolBar.add(createToolBarButton("Agregar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(new AddMoviePanel(controller));
            }
        }));
        toolBar.add(createToolBarButton("Modificar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(new UpdateMoviePanel(controller));
            }
        }));
        toolBar.add(createToolBarButton("Eliminar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(new DeleteMoviePanel(controller));
            }
        }));
        toolBar.add(createToolBarButton("Listar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(new ListMoviesPanel(controller));
            }
        }));
        toolBar.add(createToolBarButton("Buscar por Género", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(new SearchByGenrePanel(controller));
            }
        }));
        toolBar.add(createToolBarButton("Buscar por Años", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(new SearchByYearRangePanel(controller));
            }
        }));

        add(toolBar, BorderLayout.NORTH);

        // Panel de contenido principal
        contentPanel = new JPanel(new CardLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Panel de bienvenida
        JPanel welcomePanel = new JPanel();
        welcomePanel.add(new JLabel("Bienvenido al sistema de gestión de películas"));
        contentPanel.add(welcomePanel, "Welcome");
    }

    private JButton createToolBarButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private void showPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}