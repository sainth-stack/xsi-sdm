package com.ecommerce.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="relationships")
public class Relationships {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelRelationShipsName;
    private String columnName;
    private String referenceSystemDataModel;
    private String referenceSDMColumnName;
}