package com.monomalpoly.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.monomalpoly.api.player.PlayerRepository;
import com.monomalpoly.api.property.PropertyRepository;

@RestController
public class AppRestController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @RequestMapping("reset")
    public void reset() {
        playerRepository.deleteAll();
        propertyRepository.deleteAll();
    }
}
