package com.monomalpoly.api.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.ArrayList;

@RestController
public class PropertyRestController {
    @Autowired
    private PropertyRepository propertyRepository;

    @RequestMapping("property")
    public List<Property> properties() {
        List<Property> l = new ArrayList<Property>();
        for(Property p : playerRepository.findAll())
            l.add(p);
        return l;
    }
}
