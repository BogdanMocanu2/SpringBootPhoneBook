package com.bogdan.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "phone_book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PhoneBookEntity {

    @Id
    @Column(name = "id")
    // This strategy uses a database sequence instead of an auto-incrementing column as in GenerationType.IDENTITY.
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surName")
    private String surName;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private CategoryEntity category;

}
