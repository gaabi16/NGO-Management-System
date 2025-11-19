package com.example.aplicatie_gestionare_voluntariat.service;

import com.example.aplicatie_gestionare_voluntariat.model.Coordinator;
import com.example.aplicatie_gestionare_voluntariat.model.Ong;
import com.example.aplicatie_gestionare_voluntariat.model.User;
import com.example.aplicatie_gestionare_voluntariat.repository.CoordinatorRepository;
import com.example.aplicatie_gestionare_voluntariat.repository.OngRepository;
import com.example.aplicatie_gestionare_voluntariat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private CoordinatorRepository coordinatorRepository;

    public List<User> getFirst5Users() {
        return userRepository.findAll(PageRequest.of(0, 5)).getContent();
    }

    public List<Ong> getFirst5Ongs() {
        return ongRepository.findAll(PageRequest.of(0, 5)).getContent();
    }

    public List<Coordinator> getFirst5Coordinators() {
        return coordinatorRepository.findAll(PageRequest.of(0, 5)).getContent();
    }
}