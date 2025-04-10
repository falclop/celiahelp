package com.celiahelp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
