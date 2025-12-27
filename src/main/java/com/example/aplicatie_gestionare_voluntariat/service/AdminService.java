package com.example.aplicatie_gestionare_voluntariat.service;

import com.example.aplicatie_gestionare_voluntariat.model.Coordinator;
import com.example.aplicatie_gestionare_voluntariat.model.Ong;
import com.example.aplicatie_gestionare_voluntariat.model.User;
import com.example.aplicatie_gestionare_voluntariat.repository.CoordinatorRepository;
import com.example.aplicatie_gestionare_voluntariat.repository.OngRepository;
import com.example.aplicatie_gestionare_voluntariat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Metodele vechi pentru compatibilitate
    public List<User> getFirst5Users() {
        return userRepository.findAll(PageRequest.of(0, 5)).getContent();
    }

    public List<Ong> getFirst5Ongs() {
        return ongRepository.findAll(PageRequest.of(0, 5)).getContent();
    }

    public List<Coordinator> getFirst5Coordinators() {
        return coordinatorRepository.findAll(PageRequest.of(0, 5)).getContent();
    }

    // Metode noi pentru paginare
    public Page<User> getUsersPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idUser").ascending());
        return userRepository.findAll(pageable);
    }

    // CRUD pentru Users
    public User createUser(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Integer id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setRole(updatedUser.getRole());

            // Actualizează parola doar dacă este furnizată
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPasswordHash(passwordEncoder.encode(updatedUser.getPassword()));
            }

            return userRepository.save(existingUser);
        }
        return null;
    }

    public boolean deleteUser(Integer id, String currentUserEmail) {
        User userToDelete = userRepository.findById(id).orElse(null);
        if (userToDelete == null) {
            return false;
        }

        // Verifică dacă utilizatorul de șters este admin
        if (userToDelete.getRole() == User.Role.admin) {
            throw new IllegalStateException("Admin accounts cannot be deleted for security reasons!");
        }

        userRepository.deleteById(id);
        return true;
    }
}