package com.monomalpoly.api.dice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiceRestController {

    @RequestMapping("/dice")
    public Dice dice() {
        return Dice.draw();
    }
}
