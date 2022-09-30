package com.disney.studios.controllers.api;

import com.disney.studios.models.Dog;
import com.disney.studios.models.User;
import com.disney.studios.models.data.DogDao;
import com.disney.studios.models.data.UserDao;
import com.disney.studios.validators.UserApiValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    DogDao dogDao;

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
    public ResponseEntity createUser(@RequestBody Map<String, Object> payload) {
        try {
            UserApiValidator.isUserPostPayloadValid(payload);
            User user = User.createUserFromMap(payload);
            user = userDao.save(user);
            return new ResponseEntity<>(user.toString(), HttpStatus.CREATED);
        } catch(Exception ex) {
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
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity getUserById(@PathVariable String id) {
        try {
            if (id == null) throw new Exception("An ID is required to get a specific user.");
            Optional<User> user = userDao.findById(Integer.parseInt(id));
            if (!user.isPresent()) throw new Exception("No user exists for ID");
            return new ResponseEntity<>(user.get().toJSONObject().toString(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject error = new JSONObject().put("message", ex.getMessage());
            return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "/{id}/like",
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    public ResponseEntity putUsersDogLike(@PathVariable String id, @RequestBody Map<String, Object> payload) {
        try {
            if (id == null) throw new Exception("An ID is required to get a specific user.");
            UserApiValidator.isUserDogRatePutPayloadValid(payload);

            // Get and check user
            Optional<User> user = userDao.findById(Integer.parseInt(id));
            if (!user.isPresent()) throw new Exception("No user exists for ID");

            // Get and check dog
            Optional<Dog> dog = dogDao.findById(Integer.parseInt(payload.get("dogId").toString()));
            if (!dog.isPresent()) throw new Exception("No dog exists for ID");

            // get user and dog to update
            User updatedUser = user.get();
            Dog updatedDog = dog.get();

            // validate user has not liked dog yet
            if (updatedUser.getAllRatedDogs().contains(updatedDog)) throw new Exception("User already rated dog id '" + updatedDog.getId() + "'. Cannot like dog more than once.");

            // update user
            updatedUser.addLikedDog(dog.get());

            // update dog
            updatedDog.setLikes(updatedDog.getLikes() + 1);

            // save user
            userDao.save(updatedUser);

            // save dog
            dogDao.save(updatedDog);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject error = new JSONObject().put("message", ex.getMessage());
            return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "/{id}/dislike",
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    public ResponseEntity putUsersDogDislike(@PathVariable String id, @RequestBody Map<String, Object> payload) {
        try {
            if (id == null) throw new Exception("An ID is required to get a specific user.");
            UserApiValidator.isUserDogRatePutPayloadValid(payload);

            // Get and check user
            Optional<User> user = userDao.findById(Integer.parseInt(id));
            if (!user.isPresent()) throw new Exception("No user exists for ID");

            // Get and check dog
            Optional<Dog> dog = dogDao.findById(Integer.parseInt(payload.get("dogId").toString()));
            if (!dog.isPresent()) throw new Exception("No dog exists for ID");

            // get user and dog to update
            User updatedUser = user.get();
            Dog updatedDog = dog.get();

            // validate user has not liked dog yet
            if (updatedUser.getAllRatedDogs().contains(updatedDog)) throw new Exception("User already rated dog id '" + updatedDog.getId() + "'. Cannot like dog more than once.");

            // update user
            updatedUser.addDislikedDog(dog.get());

            // update dog
            updatedDog.setDislikes(updatedDog.getDislikes() + 1);

            // save user
            userDao.save(updatedUser);

            // save dog
            dogDao.save(updatedDog);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject error = new JSONObject().put("message", ex.getMessage());
            return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
