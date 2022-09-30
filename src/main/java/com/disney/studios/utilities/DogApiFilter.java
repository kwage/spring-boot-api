package com.disney.studios.utilities;

import com.disney.studios.models.Dog;
import org.json.JSONObject;

import java.util.*;

public class DogApiFilter {
    public static void filterDogListByKeyValuePair(ArrayList<Dog> dogs, String key, String value) {
        dogs.removeIf(dog -> {
            JSONObject dObject = dog.toJSONObject();
            if (!dObject.has(key)) return true;
            if (!dObject.get(key).toString().equals(value)) return true;
            return false;
        });
    }

    public static Map<String, List<Dog>> groupDogListByBreeds(ArrayList<Dog> dogs) {
        Map<String, List<Dog>> groupedDogs = new HashMap<>();
        for (Dog dog : dogs) {
            if (groupedDogs.containsKey(dog.getBreed())) {
                groupedDogs.get(dog.getBreed()).add(dog);
            } else {
                groupedDogs.put(dog.getBreed(), new ArrayList<Dog>(Arrays.asList(dog)));
            }
        }
        return groupedDogs;
    }
}
