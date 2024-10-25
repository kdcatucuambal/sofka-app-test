package com.sofka.lab.customers.app.seed;

import com.sofka.lab.customers.app.models.dao.CustomerDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seed/customers")
public class SeedController {


    private CustomerDao customerDao;

    @RequestMapping("/create")
    public ResponseEntity<String> seed() {
        return null;
    }

}
