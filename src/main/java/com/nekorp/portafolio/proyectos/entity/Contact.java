package com.nekorp.portafolio.proyectos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "contacts")
public class Contact {

    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false, length = 100)
    private String name;

    @Setter
    @Getter
    @Column(nullable = false, length = 100)
    private String email;

    @Setter
    @Getter
    @Column(length = 200)
    private String subject;

    @Setter
    @Getter
    @Column(columnDefinition = "TEXT")
    private String message;

    @Setter
    @Getter
    @Column(name = "phone")
    private String phone;

    @Setter
    @Getter
    @Column(name = "company")
    private String company;

    @Setter
    @Getter
    @Column(name = "read_status")
    private boolean readStatus = false;

    @Setter
    @Getter
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Constructores
    public Contact() {}

    public Contact(String name, String email, String subject, String message) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

}
