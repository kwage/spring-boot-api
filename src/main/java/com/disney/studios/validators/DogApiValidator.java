package com.disney.studios.validators;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DogApiValidator {

    private final static String[] DOG_POST_PAYLOAD_REQUIRED_KEYS = {"breed", "img"};
    private final static String[] DOG_POST_PAYLOAD_OPTIONAL_KEYS = {"name", "age"};

    private final static String[] DOG_UPDATE_PAYLOAD_OPTIONAL_KEYS = {"name", "age", "breed", "img"};

    private final static String[] FILTER_PROPERTIES = {"name", "breed"};

    public static void isFilterParametersValid(Map<String,String> filterParameters) throws Exception {
        Set<String> detachedSet = new HashSet<>(filterParameters.keySet());
        detachedSet.removeAll(Arrays.asList(FILTER_PROPERTIES));
        if (detachedSet.size() > 0) {
            throw new Exception("Improper request! The following properties are not filterable: " + detachedSet.toString());
        }
    }

    public static void isDogPostPayloadValid(Map<String,Object> payload) throws Exception {
        for (String key : DOG_POST_PAYLOAD_REQUIRED_KEYS) {
            if (!payload.containsKey(key)) { throw new Exception("Could not create dog! The key '" + key + "' is required!"); }
        }
        Set<String> detachedSet = new HashSet<>(payload.keySet());
        detachedSet.removeAll(Arrays.asList(DOG_POST_PAYLOAD_REQUIRED_KEYS));
        detachedSet.removeAll(Arrays.asList(DOG_POST_PAYLOAD_OPTIONAL_KEYS));
        if (detachedSet.size() > 0) {
            throw new Exception("Improper dog create payload! The following properties are not allowed: " + detachedSet.toString());
        }
    }

    public static void isDogUpdatePayloadValid(Map<String,Object> payload) throws Exception {
        Set<String> detachedSet = new HashSet<>(payload.keySet());
        detachedSet.removeAll(Arrays.asList(DOG_UPDATE_PAYLOAD_OPTIONAL_KEYS));
        if (detachedSet.size() > 0) {
            throw new Exception("Improper request! The following properties are not able to be updated: " + detachedSet.toString());
        }
    }

    public static void isDogLike(Map<String,Object> payload) throws Exception {
        Set<String> detachedSet = new HashSet<>(payload.keySet());
        detachedSet.removeAll(Arrays.asList(DOG_UPDATE_PAYLOAD_OPTIONAL_KEYS));
        if (detachedSet.size() > 0) {
            throw new Exception("Improper request! The following properties are not able to be updated: " + detachedSet.toString());
        }
    }
}
