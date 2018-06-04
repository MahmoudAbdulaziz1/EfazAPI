package com.taj.controller;

import com.taj.model.CompanyOfferModel;
import com.taj.repository.CompanyOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MahmoudAhmed on 6/4/2018.
 */
@RequestMapping("/companyOffer")
@RestController
@CrossOrigin
public class CompanyOfferController {
    @Autowired
    CompanyOfferRepo repo;

    @PostMapping("/addOffer")
    public int addCompanyOffer(@RequestBody CompanyOfferModel model){
        return repo.addCompanyOffer(model.getOffer_id(), model.getOffer_logo(), model.getOffer_title(), model.getOffer_explaination(),
                model.getOffer_cost(), model.getOffer_display_date(), model.getOffer_expired_date(), model.getOffer_deliver_date(),
                model.getOffer_is_good(), model.getOffer_contact(), model.getOffer_website());
    }

    @GetMapping("/getOffers")
    public List<CompanyOfferModel> getCompanyOffers(){
        return repo.getCompanyOffers();
    }

    @GetMapping("/getOffer/{id}")
    public CompanyOfferModel getCompanyOffer(@PathVariable int id){
        return repo.getCompanyOffer(id);
    }
    @PutMapping("/updateOffer/{id}")
    public int updateCompanyOffer(@PathVariable int id, @RequestBody CompanyOfferModel model){
        return repo.updateCompanyOffer(id, model.getOffer_logo(), model.getOffer_title(), model.getOffer_explaination(),
                model.getOffer_cost(), model.getOffer_display_date(), model.getOffer_expired_date(), model.getOffer_deliver_date(),
                model.getOffer_is_good(), model.getOffer_contact(), model.getOffer_website());
    }
    @PutMapping("/deleteOffer/{id}")
    public int deleteCompanyOffer(@PathVariable int id){
        return repo.deleteCompanyOffer(id);
    }

    public void hasNew(){}
}