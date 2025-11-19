package com.example.aplicatie_gestionare_voluntariat.repository;

import com.example.aplicatie_gestionare_voluntariat.model.Ong;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OngRepository extends JpaRepository<Ong, Integer> {
    List<Ong> findAllBy(Pageable pageable);
}