package com.example.springproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "employees")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "fathername")
    private String fathername;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "id_position")
    private Integer id_position;

    @Column(name = "id_rank")
    private Integer id_rank;

    @Column(name = "id_division")
    private Integer id_division;

    @Column(name = "contract_conclusion")
    private Date contract_conclusion;

    @Column(name = "contract_term")
    private Integer contract_term;

    public EmployeeEntity() {
    }

    public EmployeeEntity(Integer id, String lastname, String firstname, String fathername, Date birthday, Integer id_position, Integer id_rank, Integer id_division, Date contract_conclusion, Integer contract_term) {
        super();
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.fathername = fathername;
        this.birthday = birthday;
        this.id_position = id_position;
        this.id_rank = id_rank;
        this.id_division = id_division;
        this.contract_conclusion = contract_conclusion;
        this.contract_term = contract_term;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getId_position() {
        return id_position;
    }

    public void setId_position(Integer id_position) {
        this.id_position = id_position;
    }

    public Integer getId_rank() {
        return id_rank;
    }

    public void setId_rank(Integer id_rank) {
        this.id_rank = id_rank;
    }

    public Integer getId_division() {
        return id_division;
    }

    public void setId_division(Integer id_division) {
        this.id_division = id_division;
    }

    public Date getContract_conclusion() {
        return contract_conclusion;
    }

    public void setContract_conclusion(Date contract_conclusion) {
        this.contract_conclusion = contract_conclusion;
    }

    public Integer getContract_term() {
        return contract_term;
    }

    public void setContract_term(Integer contract_term) {
        this.contract_term = contract_term;
    }
}
