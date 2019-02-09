package com.monomalpoly.api.dice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
public class DiceRestController {

    @RequestMapping("/dice")
    public Dice dice() {
    	int x = new Random().nextInt(6-1) + 1;
    	int y = new Random().nextInt(6-1) + 1;

    	if(x == y)
        	return new Dice(x+y, true, "Vous avez fait " + (x+y) + " avec un double.");
        else
            return new Dice(x+y, false, "Vous avez fait " + (x+y) + ".");
    }
}
