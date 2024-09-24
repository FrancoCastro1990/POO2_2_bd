package org.duocuc;

import org.duocuc.controller.MovieController;
import org.duocuc.view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovieController controller = new MovieController();
            MainFrame mainFrame = new MainFrame(controller);
            mainFrame.setVisible(true);
        });
    }
}
