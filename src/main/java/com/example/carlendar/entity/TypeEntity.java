package com.example.carlendar.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "type")
public class TypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long type_id;

    private String typename;

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

}
