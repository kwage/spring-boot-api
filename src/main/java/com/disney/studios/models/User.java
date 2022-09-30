package com.disney.studios.models;

import org.dom4j.jaxb.JAXBObjectHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    @NotNull
    private String username;

    @OneToMany
    private List<Dog> likedDogs;

    @OneToMany
    private List<Dog> dislikedDogs;

    private User() { likedDogs = new ArrayList<>(); }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Dog> getLikedDogs() {
        return likedDogs;
    }

    public void setLikedDogs(List<Dog> likedDogs) {
        this.likedDogs = likedDogs;
    }

    public void addLikedDog(Dog likedDog) {
        this.likedDogs.add(likedDog);
    }

    public List<Dog> getDislikedDogs() {
        return dislikedDogs;
    }

    public void setDislikedDogs(List<Dog> dislikedDogs) {
        this.dislikedDogs = dislikedDogs;
    }

    public void addDislikedDog(Dog dislikedDog) {
        this.dislikedDogs.add(dislikedDog);
    }

    public List<Dog> getAllRatedDogs() {
        List<Dog> allRatedDogs = Stream.of(likedDogs, dislikedDogs)
                .flatMap(dogs -> dogs.stream())
                .collect(Collectors.toList());
        return allRatedDogs;
    }

    public JSONObject toJSONObject() {
        JSONArray likedDogsArr = new JSONArray();
        for(Dog dog : likedDogs) {
            likedDogsArr.put(dog.toJSONObject());
        }
        return new JSONObject()
                .put("id", this.id)
                .put("username", this.username)
                .put("likedDogs", likedDogsArr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return this.toJSONObject().toString();
    }

    public static User createUserFromMap(Map<String, Object> map) {
        BeanWrapper user = new BeanWrapperImpl(new User());
        for (Map.Entry<String, Object> property : map.entrySet()) {
            if (property.getKey().equals("id")) continue;
            user.setPropertyValue(property.getKey(), property.getValue());
        }

        return (User) user.getWrappedInstance();
    }
}
