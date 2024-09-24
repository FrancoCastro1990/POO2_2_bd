-- Crear la tabla MOVIE si no existe
CREATE TABLE IF NOT EXISTS MOVIE (
    id INT PRIMARY KEY,
    titulo VARCHAR(250) NOT NULL,
    director VARCHAR(100),
    anio INT,
    duracion INT,
    genero VARCHAR(50)
);

-- Procedimiento para insertar una nueva película
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS InsertMovie(
    IN p_id INT,
    IN p_titulo VARCHAR(250),
    IN p_director VARCHAR(100),
    IN p_anio INT,
    IN p_duracion INT,
    IN p_genero VARCHAR(50)
)
BEGIN
    DECLARE movie_count INT;
    
    -- Validar ID único
    SELECT COUNT(*) INTO movie_count FROM MOVIE WHERE id = p_id;
    
    IF movie_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El ID de la película ya existe.';
    ELSEIF p_id IS NULL OR p_titulo IS NULL OR p_director IS NULL OR p_anio IS NULL OR p_duracion IS NULL OR p_genero IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Todos los campos son obligatorios.';
    ELSE
        INSERT INTO MOVIE (id, titulo, director, anio, duracion, genero)
        VALUES (p_id, p_titulo, p_director, p_anio, p_duracion, p_genero);
    END IF;
END //
DELIMITER ;

-- Procedimiento para actualizar una película existente
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS UpdateMovie(
    IN p_id INT,
    IN p_titulo VARCHAR(250),
    IN p_director VARCHAR(100),
    IN p_anio INT,
    IN p_duracion INT,
    IN p_genero VARCHAR(50)
)
BEGIN
    DECLARE movie_count INT;
    
    -- Validar que la película existe
    SELECT COUNT(*) INTO movie_count FROM MOVIE WHERE id = p_id;
    
    IF movie_count = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: La película no existe.';
    ELSEIF p_titulo IS NULL OR p_director IS NULL OR p_anio IS NULL OR p_duracion IS NULL OR p_genero IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Todos los campos son obligatorios.';
    ELSE
        UPDATE MOVIE
        SET titulo = p_titulo,
            director = p_director,
            anio = p_anio,
            duracion = p_duracion,
            genero = p_genero
        WHERE id = p_id;
    END IF;
END //
DELIMITER ;

-- Procedimiento para eliminar una película
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS DeleteMovie(
    IN p_id INT
)
BEGIN
    DECLARE movie_count INT;
    
    -- Validar que la película existe
    SELECT COUNT(*) INTO movie_count FROM MOVIE WHERE id = p_id;
    
    IF movie_count = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: La película no existe.';
    ELSE
        DELETE FROM MOVIE WHERE id = p_id;
    END IF;
END //
DELIMITER ;

-- Procedimiento para buscar una película por ID
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS GetMovieById(
    IN p_id INT
)
BEGIN
    SELECT * FROM MOVIE WHERE id = p_id;
END //
DELIMITER ;

-- Procedimiento para listar todas las películas
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS ListAllMovies()
BEGIN
    SELECT * FROM MOVIE ORDER BY id;
END //
DELIMITER ;

-- Procedimiento para buscar películas por género
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS SearchMoviesByGenre(
    IN p_genero VARCHAR(50)
)
BEGIN
    SELECT * FROM MOVIE WHERE genero = p_genero ORDER BY titulo;
END //
DELIMITER ;

-- Procedimiento para buscar películas por rango de años
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS SearchMoviesByYearRange(
    IN p_anio_inicio INT,
    IN p_anio_fin INT
)
BEGIN
    SELECT * FROM MOVIE WHERE anio BETWEEN p_anio_inicio AND p_anio_fin ORDER BY anio, titulo;
END //
DELIMITER ;