package com.monomalpoly.api.dice;

import com.monomalpoly.api.JSONTestHelpers;
import org.json.JSONObject;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

@RunWith(PowerMockRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest({Random.class, DiceRestController.class})
public class DiceRestControllerTest extends JSONTestHelpers {

    @Autowired
    private MockMvc mvc;

    private Random mockRandom = mock(Random.class);

    @Before
    public void setUpMock() throws Exception {
        PowerMockito.whenNew(Random.class)
            .withNoArguments()
            .thenReturn(mockRandom);
    }

    @Test
    public void diceWithDouble() throws Exception {
        when(mockRandom.nextInt(anyInt()))
            .thenReturn(3, 3);

        JSONObject result = getJSONRequest("/dice", mvc);

        // (3 + 3) + 1 + 1 = 8 (car +1 dans le contrôleur)
        assertEquals(8, result.getInt("value"));
        assertEquals(true, result.getBoolean("isDouble"));
        assertEquals("Vous avez fait 8 avec un double.", result.getString("message"));
    }

    @Test
    public void diceWithoutDouble() throws Exception {
        when(mockRandom.nextInt(anyInt()))
            .thenReturn(3, 1);

        JSONObject result = getJSONRequest("/dice", mvc);

        // (3 + 1) + 1 + 1 = 6 (car +1 dans le contrôleur)
        assertEquals(6, result.getInt("value"));
        assertEquals(false, result.getBoolean("isDouble"));
        assertEquals("Vous avez fait 6.", result.getString("message"));
    }
}
