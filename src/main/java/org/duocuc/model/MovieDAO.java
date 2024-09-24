package org.duocuc.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public void insertMovie(Movie movie) throws SQLException {
        String sql = "{CALL InsertMovie(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, movie.getId());
            stmt.setString(2, movie.getTitulo());
            stmt.setString(3, movie.getDirector());
            stmt.setInt(4, movie.getAnio());
            stmt.setInt(5, movie.getDuracion());
            stmt.setString(6, movie.getGenero());

            stmt.execute();
        }
    }

    public void updateMovie(Movie movie) throws SQLException {
        String sql = "{CALL UpdateMovie(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, movie.getId());
            stmt.setString(2, movie.getTitulo());
            stmt.setString(3, movie.getDirector());
            stmt.setInt(4, movie.getAnio());
            stmt.setInt(5, movie.getDuracion());
            stmt.setString(6, movie.getGenero());

            stmt.execute();
        }
    }

    public void deleteMovie(int id) throws SQLException {
        String sql = "{CALL DeleteMovie(?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    public Movie getMovieById(int id) throws SQLException {
        String sql = "{CALL GetMovieById(?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Movie(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("director"),
                            rs.getInt("anio"),
                            rs.getInt("duracion"),
                            rs.getString("genero")
                    );
                }
            }
        }
        return null;
    }

    public List<Movie> listAllMovies() throws SQLException {
        String sql = "{CALL ListAllMovies()}";
        List<Movie> movies = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("director"),
                        rs.getInt("anio"),
                        rs.getInt("duracion"),
                        rs.getString("genero")
                ));
            }
        }
        return movies;
    }

    public List<Movie> searchMoviesByGenre(String genre) throws SQLException {
        String sql = "{CALL SearchMoviesByGenre(?)}";
        List<Movie> movies = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, genre);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    movies.add(new Movie(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("director"),
                            rs.getInt("anio"),
                            rs.getInt("duracion"),
                            rs.getString("genero")
                    ));
                }
            }
        }
        return movies;
    }

    public List<Movie> searchMoviesByYearRange(int startYear, int endYear) throws SQLException {
        String sql = "{CALL SearchMoviesByYearRange(?, ?)}";
        List<Movie> movies = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, startYear);
            stmt.setInt(2, endYear);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    movies.add(new Movie(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("director"),
                            rs.getInt("anio"),
                            rs.getInt("duracion"),
                            rs.getString("genero")
                    ));
                }
            }
        }
        return movies;
    }
}