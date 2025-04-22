/*
  Entidades JPA con Lombok para Celi@Help
*/

// Rol.java
package com.celiahelp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Tipo tipo = Tipo.GESTOR;

    public enum Tipo { GESTOR, ADMIN }
}