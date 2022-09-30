package com.disney.studios.models;

import org.json.JSONObject;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "DOG")
public class Dog {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "img")
    @NotNull
    private String img;

    @Column(name = "breed")
    @NotNull
    private String breed;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "likes")
    @NotNull
    private int likes;

    @Column(name = "dislikes")
    @NotNull
    private int dislikes;


    /* private String name, breed, imgUrl;
    private int age;
    private int likeCount = 0; */

    private Dog() {}

    public Dog(String breed, String img) {
        this.breed = breed;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public JSONObject toJSONObject() {
        return new JSONObject()
                .put("id", this.id)
                .put("name", this.name)
                .put("age", this.age)
                .put("breed", this.breed)
                .put("img", this.img)
                .put("likes", this.likes)
                .put("dislikes", this.dislikes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breed);
    }

    @Override
    public String toString() {
        return this.toJSONObject().toString();
    }

    public static Dog createDogFromMap(Map<String, Object> map) {
        BeanWrapper dog = new BeanWrapperImpl(new Dog());
        for (Map.Entry<String, Object> property : map.entrySet()) {
            if (property.getKey().equals("id")) continue;
            dog.setPropertyValue(property.getKey(), property.getValue());
        }
        return (Dog) dog.getWrappedInstance();
    }

    public static Dog updateDogFromMap(Map<String, Object> map, Dog dog) {
        BeanWrapper dogWrapper = new BeanWrapperImpl(dog);
        for (Map.Entry<String, Object> property : map.entrySet()) {
            if (property.getKey().equals("id")) continue;
            dogWrapper.setPropertyValue(property.getKey(), property.getValue());
        }
        return (Dog) dogWrapper.getWrappedInstance();
    }
}
