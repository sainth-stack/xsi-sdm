package com.ecommerce.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="columns")
public class Columns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataModelColumnsName;
    private String postGresDataTypes;
    private Integer length;
    private Integer scale;
    private Boolean isNull;
    private Boolean isPrimaryKey;
    private String defaultValue;
}
