package com.example.carlendar.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "record")
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDate date;

    private Float price;

    private Integer amount;

    @org.springframework.data.annotation.Transient
    private  int fromTypeId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id",referencedColumnName = "type_id")
    private TypeEntity typeEntity;


    public RecordEntity(){

    }
    public RecordEntity(Long id, String name, LocalDate date, Float price, Integer amount, TypeEntity typeEntity) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
        this.amount = amount;
        this.typeEntity = typeEntity;
    }

    public int getFromTypeId() {
        return fromTypeId;
    }

    public void setFromTypeId(int fromTypeId) {
        this.fromTypeId = fromTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeEntity typeEntity) {
        this.typeEntity = typeEntity;
    }
}

