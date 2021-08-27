package com.bogdan.demo.entities;

import com.bogdan.demo.enums.CategoryDescription;
import com.bogdan.demo.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "category")
@Entity
@Builder
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category_type")
    private CategoryType categoryType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category_description")
    private CategoryDescription categoryDescription;

    @Column(name = "description")
    private String description;

    @Column(name = "frienship_years")
    private int friendshipYears;

}
