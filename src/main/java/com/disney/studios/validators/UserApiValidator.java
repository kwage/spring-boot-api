package com.disney.studios.validators;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserApiValidator {

    private final static String[] USER_POST_PAYLOAD_REQUIRED_KEYS = {"username"};

    private final static String[] USER_LIKE_PUT_PAYLOAD_REQUIRED_KEYS = {"dogId"};

    public static void isUserPostPayloadValid(Map<String,Object> payload) throws Exception {
        for (String key : USER_POST_PAYLOAD_REQUIRED_KEYS) {
            if (!payload.containsKey(key)) { throw new Exception("Could not create user! The key '" + key + "' is required!"); }
        }
        Set<String> detachedSet = new HashSet<>(payload.keySet());
        detachedSet.removeAll(Arrays.asList(USER_POST_PAYLOAD_REQUIRED_KEYS));
        if (detachedSet.size() > 0) {
            throw new Exception("Improper user create payload! The following properties are not allowed: " + detachedSet.toString());
        }
    }

    public static void isUserDogRatePutPayloadValid(Map<String,Object> payload) throws Exception {
        for (String key : USER_LIKE_PUT_PAYLOAD_REQUIRED_KEYS) {
            if (!payload.containsKey(key)) { throw new Exception("Could not update user likes! The key '" + key + "' is required!"); }
        }
        Set<String> detachedSet = new HashSet<>(payload.keySet());
        detachedSet.removeAll(Arrays.asList(USER_LIKE_PUT_PAYLOAD_REQUIRED_KEYS));
        if (detachedSet.size() > 0) {
            throw new Exception("Improper user create payload! The following properties are not allowed: " + detachedSet.toString());
        }
    }
}
