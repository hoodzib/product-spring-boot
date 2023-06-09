package com.example.carlendar.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "product")
public class ProductEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Please enter valid name.")
    private String name;

    private LocalDate date;

    @NotNull(message = "Please enter valid price")
    private Float price;

    @NotNull(message = "Please enter valid amount")
    private Long amount;


    @org.springframework.data.annotation.Transient
    private Long fromTypeId;


    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    private TypeEntity typeEntity;


    public ProductEntity(Long id, String name, LocalDate date, Float price, Long amount, Long fromTypeId, TypeEntity typeEntity) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
        this.amount = amount;
        this.fromTypeId = fromTypeId;
        this.typeEntity = typeEntity;
    }

    public ProductEntity() {
    }

    public Long getFromTypeId() {
        return fromTypeId;
    }

    public void setFromTypeId(Long fromTypeId) {
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeEntity typeEntity) {
        this.typeEntity = typeEntity;
    }
}
