package com.monomalpoly.api.dice;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DiceTest {
    @Test
    public void basic() throws Exception {
        Dice d = new Dice(10, true, "foo");

        assertEquals(10, d.getValue());
        assertEquals(true, d.getIsDouble());
        assertEquals("foo", d.getMessage());
    }
}
