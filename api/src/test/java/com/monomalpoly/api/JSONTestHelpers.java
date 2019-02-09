package com.monomalpoly.api;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class JSONTestHelpers {
    public String getRequest(String endpoint, MockMvc mvc) throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(endpoint)
            .accept(MediaType.APPLICATION_JSON))
            .andReturn();

        assertEquals(200, result.getResponse().getStatus());

        return result.getResponse().getContentAsString();
    }

    public JSONObject getJSONRequest(String endpoint, MockMvc mvc) throws Exception {
        return new JSONObject(getRequest(endpoint, mvc));
    }
}
