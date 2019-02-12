package com.monomalpoly.api;

import java.util.HashMap;

public class JSONResponse {
  private HashMap<String, Object> response;

  public static JSONResponse builder() {
    return new JSONResponse();
  }

  public JSONResponse() {
    response = new HashMap<>();
  }

  public JSONResponse with(String key, Object value) {
    response.put(key, value);

    return this;
  }

  public HashMap<String, Object> build() {
    return this.response;
  }
}
