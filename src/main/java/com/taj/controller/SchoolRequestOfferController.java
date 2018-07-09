package com.taj.controller;

import com.taj.model.CompanyResponseSchoolRequestModel;
import com.taj.model.SchoolRequestOfferModel;
import com.taj.repository.SchoolRequestOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/8/2018.
 */

@RequestMapping("/schoolRequestOffer")
@RestController
@CrossOrigin
public class SchoolRequestOfferController {

    @Autowired
    SchoolRequestOfferRepo repo;

    @PostMapping("/add")
    public int addSchoolRequestOffer(@RequestBody SchoolRequestOfferModel model) {
        return repo.addSchoolRequestOffer(model.getRequest_id(), model.getRequsted_school_id() ,model.getRequsted_offer_id(), model.getIs_accepted());
    }

    @GetMapping("/getAll")
    public List<SchoolRequestOfferModel> getSchoolRequestsOffer() {
        return repo.getSchoolRequestOffer();
    }

    @GetMapping("/get/{id}")
    public SchoolRequestOfferModel getSchoolRequestOffer(@PathVariable int id) {
        return repo.getSchoolRequestOffer(id);
    }

    @GetMapping("/getSchool/{schoolId}")
    public List<SchoolRequestOfferModel> getSchoolRequestOfferBySchool(@PathVariable int schoolId) {
        return repo.getSchoolRequestOfferBySchool(schoolId);
    }

    @GetMapping("/getOffer/{offerId}")
    public List<SchoolRequestOfferModel> SchoolRequestOfferByOffer(@PathVariable int offerId) {
        return repo.getSchoolRequestOfferByOffer(offerId);
    }

    @GetMapping("/getAccept/{accept}")
    public List<SchoolRequestOfferModel> SchoolRequestOfferByAprove(@PathVariable int accept) {
        return repo.getSchoolRequestOfferByAccept(accept);
    }

    @PutMapping("/accept/{id}")
    public int acceptSchoolRequestOffer(@PathVariable int id) {
        return repo.acceptSchoolRequestOffer(id);
    }
    @PutMapping("/refuse/{id}")
    public int refuseSchoolRequestOffer(@PathVariable int id) {
        return repo.refuseSchoolRequestOffer(id);
    }

    @PutMapping("/update")
    public int updateSchoolRequestOffer(@RequestBody SchoolRequestOfferModel model) {
        return repo.updateResponseSchoolRequest(model.getRequest_id(), model.getRequsted_school_id(), model.getRequsted_offer_id(), model.getIs_accepted());
    }

    @PutMapping("/delete/{id}")
    public int deleteSchoolRequestOffer(@PathVariable int id) {
        return repo.deleteResponseSchoolRequest(id);
    }

}
