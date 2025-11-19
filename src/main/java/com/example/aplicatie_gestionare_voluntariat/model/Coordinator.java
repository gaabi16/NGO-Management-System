package com.example.aplicatie_gestionare_voluntariat.model;

import jakarta.persistence.*;

@Entity
@Table(name = "coordinators")
public class Coordinator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coordinator")
    private Integer idCoordinator;

    @Column(name = "id_user", nullable = false, unique = true)
    private Integer idUser;

    @Column(name = "id_ong", nullable = false)
    private Integer idOng;

    @Column(length = 100)
    private String department;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "employment_type", length = 50)
    private String employmentType;

    // Relații opționale pentru afișare
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ong", insertable = false, updatable = false)
    private Ong ong;

    // Constructori
    public Coordinator() {}

    public Coordinator(Integer idUser, Integer idOng, String department,
                       Integer experienceYears, String employmentType) {
        this.idUser = idUser;
        this.idOng = idOng;
        this.department = department;
        this.experienceYears = experienceYears;
        this.employmentType = employmentType;
    }

    // Getteri și Setteri
    public Integer getIdCoordinator() {
        return idCoordinator;
    }

    public void setIdCoordinator(Integer idCoordinator) {
        this.idCoordinator = idCoordinator;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdOng() {
        return idOng;
    }

    public void setIdOng(Integer idOng) {
        this.idOng = idOng;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }
}