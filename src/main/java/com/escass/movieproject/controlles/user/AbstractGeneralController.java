package com.escass.movieproject.controlles.user;

import com.escass.movieproject.results.Result;
import org.json.JSONObject;

public abstract class AbstractGeneralController {
    protected final JSONObject generateRestResponse(Result result) {
        JSONObject response = new JSONObject();
        response.put(Result.NAME_SINGULAR, result.nameToLower());
        return response;
    }
}