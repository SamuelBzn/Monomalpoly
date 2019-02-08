package com.monomalpoly.api.chance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.ArrayList;
import java.util.Random.nextInt;
import Math.random;
import java.util.Random.ints;

@RestController
public class ChanceRestController {

    @RequestMapping("chance/effect")
    public HashMap<String, String> effect() {
        Random r = new Random();
        int result = r.nextInt((1 - 0) + 1) + 0;

        HashMap<String, String> response = new HashMap<String, String>();
        if(result == 0) {
            //Set la balance du joueur à -50
            response.put("message", "La banque te prend 50 euros, cheh !");
        }
        else {
            //Set la balance du joueur à +50
            response.put("message", "Tu gagnes 50 balles, gégé le sang. Mais tékaté ça n'arrivera pas souvent.");
        }

        return response;
    }
}
