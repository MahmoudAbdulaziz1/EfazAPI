package com.taj.controller;

import com.taj.model.SchoolSeeRequest;
import com.taj.model.TakatafSchoolSeeTenderModel;
import com.taj.repository.SchoolSeeRequestRepo;
import com.taj.repository.TakatafSchoolSeeTenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
@RequestMapping("/offerSeen")
@RestController
@CrossOrigin
public class SchoolSeeRequestController {


    @Autowired
    SchoolSeeRequestRepo repo;

    @PostMapping("/add")
    public int addOfferSeen(@RequestBody SchoolSeeRequest model){
        return repo.addSeen(model.getSeen_id(), model.getSeen_offer_id(), model.getSeen_offer_school_id());
    }

    @GetMapping("/getAll")
    public List<SchoolSeeRequest> getOffersSeen(){
        return repo.getOffersSeen();
    }
    @GetMapping("/get/{id}")
    public SchoolSeeRequest getOffersSeen(@PathVariable int id){
        return repo.getRequestSeen(id);
    }

    @GetMapping("/getSchool/{schoolId}")
    public List<SchoolSeeRequest> getOffersSeenBySchool(@PathVariable int schoolId){
        return repo.getOffersSeenBySchool(schoolId);
    }

    @GetMapping("/getOffer/{offerId}")
    public List<SchoolSeeRequest> getOffersSeenByOffer(@PathVariable int offerId){
        return repo.getOffersSeenByOffer(offerId);
    }

    @PutMapping("/update")
    public int updateOffersSeen(@RequestBody SchoolSeeRequest model){
        return repo.updateOffersSeen(model.getSeen_id(), model.getSeen_offer_id(), model.getSeen_offer_school_id());
    }
    @PutMapping("/delete/{id}")
    public int deleteOffersSeen(@PathVariable int id){
        return repo.deleteOffersSeen(id);
    }
}
