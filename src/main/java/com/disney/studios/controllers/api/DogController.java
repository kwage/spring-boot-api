package com.disney.studios.controllers.api;

import com.disney.studios.models.Dog;
import com.disney.studios.models.data.DogDao;
import com.disney.studios.utilities.DogApiFilter;
import com.disney.studios.utilities.ParameterCleaner;
import com.disney.studios.validators.DogApiValidator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.*;

@Controller
@RequestMapping("api/dog")
public class DogController {

    @Autowired
    DogDao dogDao;

    /*
    TODO:
    - Take in comma delimited requests
     */
    @RequestMapping(
        value = {"", "/"},
        method = RequestMethod.GET,
        produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity getDogs(@RequestParam Map<String,String> filterRequestParams) {
        try {
            DogApiValidator.isFilterParametersValid(filterRequestParams);
            Iterable<Dog> allDogs = dogDao.findAll();
            ArrayList<Dog> dogs = new ArrayList<>();
            allDogs.forEach(dogs::add);

            for (String key : filterRequestParams.keySet()) {
                String value = ParameterCleaner.clean(filterRequestParams.get(key));
                DogApiFilter.filterDogListByKeyValuePair(dogs, key, value);
            }

            JSONArray array = new JSONArray();
            for (Dog dog : dogs) {
                array.put(dog.toJSONObject());
            }
            return new ResponseEntity<>(array.toString(), HttpStatus.OK);
        } catch(Exception ex) {
            JSONObject error = new JSONObject().put("message", ex.getMessage());
            return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    TODO:
    - Take in comma delimited requests
     */
    @RequestMapping(
            value = "/breeds",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity getDogsByBreed() {
        try {
            Iterable<Dog> allDogs = dogDao.findAll();
            ArrayList<Dog> dogs = new ArrayList<>();
            allDogs.forEach(dogs::add);

            Map<String, List<Dog>> groupedDogs = DogApiFilter.groupDogListByBreeds(dogs);
            JSONObject dogsByBreed = new JSONObject();
            for (Map.Entry<String, List<Dog>> entry : groupedDogs.entrySet()) {
                JSONArray arr = new JSONArray();
                // Display ONLY image (with id) as described by instructions
                for (Dog dog : entry.getValue()) {
                    arr.put(new JSONObject()
                            .put("id", dog.getId())
                            .put("img", dog.getImg()));
                }
                dogsByBreed.put(entry.getKey(), arr);
            }
            return new ResponseEntity<>(dogsByBreed.toString(), HttpStatus.OK);
        } catch(Exception ex) {
            JSONObject error = new JSONObject().put("message", ex.getMessage());
            return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
        }
    }


    /*
    TODO:
    - Take in USER ID and validate? If user info will be necessary
     */
    @RequestMapping(
            value = {"", "/"},
            method = RequestMethod.POST,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity createDog(@RequestBody Map<String, Object> payload) {
        try {
            DogApiValidator.isDogPostPayloadValid(payload);
            Dog dog = Dog.createDogFromMap(payload);
            dog = dogDao.save(dog);
            return new ResponseEntity<>(dog.toString(), HttpStatus.CREATED);
        } catch(Exception ex) {
            JSONObject error = new JSONObject().put("message", ex.getMessage());
            return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
        }
    }


    /*
    TODO:
    - Need to take in dog ID (possibly as path parameter)
    - Allow updates of all fields
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity getDogById(@PathVariable String id) {
        try {
            if (id == null) throw new Exception("An ID is required to get a specific dog.");
            Optional<Dog> dog = dogDao.findById(Integer.parseInt(id));
            if (!dog.isPresent()) throw new Exception("No dog exists for ID");
            return new ResponseEntity<>(dog.get().toJSONObject().toString(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject error = new JSONObject().put("message", ex.getMessage());
            return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    TODO:
    - Need to take in dog ID (possibly as path parameter)
    - Allow updates of all fields
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PATCH,
            produces = "application/json"
    )
    public ResponseEntity updateDogById(@PathVariable String id, @RequestBody Map<String, Object> payload) {
        try {
            if (id == null) throw new Exception("An ID is required to update a dog.");
            DogApiValidator.isDogUpdatePayloadValid(payload);

            Optional<Dog> optionalDog = dogDao.findById(Integer.parseInt(id));
            if (!optionalDog.isPresent()) throw new Exception("No dog exists for ID");
            Dog dog = optionalDog.get();
            Dog.updateDogFromMap(payload, dog);
            Dog updatedDog = dogDao.save(dog);
            return new ResponseEntity<>(updatedDog.toJSONObject().toString(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject error = new JSONObject().put("message", ex.getMessage());
            return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    TODO:
    - Need to take in dog ID (possibly as path parameter)
    - Allow updates of all fields
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json"
    )
    public ResponseEntity deleteDogById(@PathVariable String id) {
        try {
            if (id == null) throw new Exception("An ID is required to get a specific dog.");
            Optional<Dog> dog = dogDao.findById(Integer.parseInt(id));
            if (!dog.isPresent()) throw new Exception("No dog exists for ID");
            dogDao.delete(dog.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject error = new JSONObject().put("message", ex.getMessage());
            return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
