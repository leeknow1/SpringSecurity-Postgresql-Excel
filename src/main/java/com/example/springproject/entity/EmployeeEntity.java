package com.example.springproject.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;

@Entity
@Table(name = "employees")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastname;

    @Column
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstname;

    @Column
    @NotEmpty
    @Size(min = 2, max = 20)
    private String fathername;

    @Column
    @NotNull
    private Date birthday;

    @Column
    @NotNull
    @DecimalMin(value = "1")
    @DecimalMax(value = "4")
    private Integer id_position;

    @Column
    @NotNull
    @DecimalMin(value = "1")
    @DecimalMax(value = "7")
    private Integer id_rank;

    @Column
    @NotNull
    @DecimalMin(value = "1")
    @DecimalMax(value = "3")
    private Integer id_division;

    @Column
    @NotNull
    private Date contract_conclusion;

    @Column
    @NotNull
    @DecimalMin(value = "1")
    private Integer contract_term;
}
