package com.example.aplicatie_gestionare_voluntariat.repository;

import com.example.aplicatie_gestionare_voluntariat.model.Coordinator;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Integer> {
    List<Coordinator> findAllBy(Pageable pageable);
}