package org.duocuc.controller;

import org.duocuc.model.Movie;
import org.duocuc.model.MovieDAO;

import java.sql.SQLException;
import java.util.List;

public class MovieController {
    private MovieDAO movieDAO;

    public MovieController() {
        this.movieDAO = new MovieDAO();
    }

    public String insertMovie(int id, String titulo, String director, int anio, int duracion, String genero) {
        try {
            Movie movie = new Movie(id, titulo, director, anio, duracion, genero);
            movieDAO.insertMovie(movie);
            return "Película insertada con éxito.";
        } catch (SQLException e) {
            return "Error al insertar la película: " + e.getMessage();
        }
    }

    public String updateMovie(int id, String titulo, String director, int anio, int duracion, String genero) {
        try {
            Movie movie = new Movie(id, titulo, director, anio, duracion, genero);
            movieDAO.updateMovie(movie);
            return "Película actualizada con éxito.";
        } catch (SQLException e) {
            return "Error al actualizar la película: " + e.getMessage();
        }
    }

    public String deleteMovie(int id) {
        try {
            movieDAO.deleteMovie(id);
            return "Película eliminada con éxito.";
        } catch (SQLException e) {
            return "Error al eliminar la película: " + e.getMessage();
        }
    }

    public Movie getMovieById(int id) {
        try {
            return movieDAO.getMovieById(id);
        } catch (SQLException e) {
            System.err.println("Error al obtener la película: " + e.getMessage());
            return null;
        }
    }

    public List<Movie> listAllMovies() {
        try {
            return movieDAO.listAllMovies();
        } catch (SQLException e) {
            System.err.println("Error al listar las películas: " + e.getMessage());
            return null;
        }
    }

    public List<Movie> searchMoviesByGenre(String genre) {
        try {
            return movieDAO.searchMoviesByGenre(genre);
        } catch (SQLException e) {
            System.err.println("Error al buscar películas por género: " + e.getMessage());
            return null;
        }
    }

    public List<Movie> searchMoviesByYearRange(int startYear, int endYear) {
        try {
            return movieDAO.searchMoviesByYearRange(startYear, endYear);
        } catch (SQLException e) {
            System.err.println("Error al buscar películas por rango de años: " + e.getMessage());
            return null;
        }
    }

    // Método adicional para validar la entrada de datos
    private boolean validateMovieData(int id, String titulo, String director, int anio, int duracion, String genero) {
        if (id <= 0 || titulo == null || titulo.isEmpty() || director == null || director.isEmpty() ||
                anio <= 0 || duracion <= 0 || genero == null || genero.isEmpty()) {
            return false;
        }
        return true;
    }
}