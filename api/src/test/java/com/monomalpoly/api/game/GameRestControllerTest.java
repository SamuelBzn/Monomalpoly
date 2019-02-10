package com.monomalpoly.api.game;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.monomalpoly.api.JSONTestHelpers;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameRestControllerTest extends JSONTestHelpers {

    @Autowired
    private MockMvc mvc;

    @Test
    public void newGame() throws Exception {
        JSONObject expected = new JSONObject();

        expected.put("nbUsers", 4);
        expected.put("countNbUsers", 4);
        expected.put("state", "init");

        String data = getRequest("/game/new/4/init", mvc);

        JSONAssert.assertEquals(expected.toString(), data, false);
    }

    @Test
    public void countNbUsers() throws Exception {
        getRequest("/game/new/7/foo", mvc);

        JSONObject response = getJSONRequest("/game/countNbUsers", mvc);

        assertEquals(7, response.getInt("value"));
    }

    @Test
    public void decreaseCountNbUsers() throws Exception {
        getRequest("/game/new/10/foo", mvc);

        JSONObject response = getJSONRequest("/game/decreaseCountNbUsers", mvc);

        assertEquals(9, response.getInt("value"));
    }

    @Test
    public void editState() throws Exception {
        getRequest("/game/new/10/foo", mvc);
        getRequest("/game/editState/bar", mvc);

        JSONObject response = getJSONRequest("/game/", mvc);

        assertEquals("bar", response.getString("state"));


    }

    @Test
    public void game() throws Exception {
        getRequest("/game/new/2/woot", mvc);

        String result = getRequest("/game", mvc);

        JSONObject expected = new JSONObject();

        expected.put("nbUsers", 2);
        expected.put("countNbUsers", 2);
        expected.put("state", "woot");

        JSONAssert.assertEquals(expected.toString(), result, false);
    }

    @Test
    public void gameWithDefaultState() throws Exception {
        getRequest("/reset", mvc);

        String result = getRequest("/game", mvc);

        JSONObject expected = new JSONObject();

        expected.put("nbUsers", -1);
        expected.put("countNbUsers", -1);
        expected.put("state", "null");

        JSONAssert.assertEquals(expected.toString(), result, false);
    }

}
