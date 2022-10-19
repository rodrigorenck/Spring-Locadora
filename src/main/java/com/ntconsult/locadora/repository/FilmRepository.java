package com.ntconsult.locadora.repository;

import com.ntconsult.locadora.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByAvailableIsTrue();
    List<Film> findByAvailableIsFalse();
    Optional<Film> findByName(String name);
}
