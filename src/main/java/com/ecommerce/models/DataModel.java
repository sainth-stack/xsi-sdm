package com.ecommerce.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="data_model")
public class DataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String systemDataModelName;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Columns> columns;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Relationships> relationships;
    private Integer version;
    private String createdBy;
    private String modifiedBy;
    private Boolean isDeleted;
    private Boolean isDraft;
    private Date createdDate;
    private Date modifiedDate;
    private String orgName;
}