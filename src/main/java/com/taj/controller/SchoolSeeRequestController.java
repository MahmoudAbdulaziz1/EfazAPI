package com.taj.controller;

import com.taj.model.SchoolSeeRequest;
import com.taj.model.TakatafSchoolSeeTenderModel;
import com.taj.repository.SchoolSeeRequestRepo;
import com.taj.repository.TakatafSchoolSeeTenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
@RequestMapping("/evvaz/offer/seen")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SchoolSeeRequestController {


    @Autowired
    SchoolSeeRequestRepo repo;

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PostMapping("/add")
    public int addOfferSeen(@RequestBody SchoolSeeRequest model){
        return repo.addSeen(model.getSeen_id(), model.getSeen_offer_id(), model.getSeen_offer_school_id());
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolSeeRequest> getOffersSeen(){
        return repo.getOffersSeen();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public SchoolSeeRequest getOffersSeen(@PathVariable int id){
        return repo.getRequestSeen(id);
    }

    @GetMapping("/getSchool/{schoolId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolSeeRequest> getOffersSeenBySchool(@PathVariable int schoolId){
        return repo.getOffersSeenBySchool(schoolId);
    }

    @GetMapping("/getOffer/{offerId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolSeeRequest> getOffersSeenByOffer(@PathVariable int offerId){
        return repo.getOffersSeenByOffer(offerId);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int updateOffersSeen(@RequestBody SchoolSeeRequest model){
        return repo.updateOffersSeen(model.getSeen_id(), model.getSeen_offer_id(), model.getSeen_offer_school_id());
    }
    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int deleteOffersSeen(@PathVariable int id){
        return repo.deleteOffersSeen(id);
    }
}
