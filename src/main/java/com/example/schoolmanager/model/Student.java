package com.example.schoolmanager.model;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;
    private String email;

    public Student() {
        // constructor rỗng BẮT BUỘC
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
