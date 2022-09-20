package com.disney.studios.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;


@Entity
public class Dog {

    private @Id @GeneratedValue Long id;
    private String name;

    private Dog() {}

    public Dog(String firstName, String lastName, String description) {
        this.name = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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

    public void setFirstName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog -> " +
                "id=" + id +
                ", name='" + this.name + '\'';
    }
}
