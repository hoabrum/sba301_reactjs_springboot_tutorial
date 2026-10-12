package com.orchidmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orchids")
public class Orchid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "orchid_name")
    private String orchidName;

    @Column(name = "is_natural")
    private boolean isNatural;

    @Column(name = "image")
    private String image;//URL
}
