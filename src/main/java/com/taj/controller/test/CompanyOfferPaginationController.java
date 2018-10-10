package com.taj.controller.test;

import com.taj.entity.test.CompanyOfferEntity;
import com.taj.service.test.CompanyOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 10/10/2018.
 */
@RequestMapping("/usr")
@RestController
@CrossOrigin
public class CompanyOfferPaginationController {
    @Autowired
    CompanyOfferService service;

    @GetMapping(value = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CompanyOfferEntity>> getAllArticles(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                   @RequestParam(value = "limit", defaultValue = "5") int limit) {
        List<CompanyOfferEntity> list = service.getOffers(page, limit);
        return new ResponseEntity<List<CompanyOfferEntity>>(list, HttpStatus.OK);
    }
}
