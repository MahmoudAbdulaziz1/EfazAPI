package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.CompanyOfferModel;
import com.taj.repository.CompanyOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by MahmoudAhmed on 6/4/2018.
 */
@RequestMapping("/evvaz/companyOffer")
@RestController
@CrossOrigin
public class CompanyOfferController {
    @Autowired
    CompanyOfferRepo repo;
    @Autowired
    ObjectMapper mapper;

    /**
     * add offer from company
     *
     * @param model
     * @return 1 if success or 0 if failed
     */

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode addCompanyOffer(@Valid @RequestBody CompanyOfferModel model, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }

        int res = repo.addCompanyOffer(model.getOffer_logo(), model.getOffer_title(), model.getOffer_explaination(),
                model.getOffer_cost(), model.getOffer_display_date(), model.getOffer_expired_date(), model.getOffer_deliver_date(),
                model.getCompany_id());
        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("offer_logo", model.getOffer_logo());
            objectNode.put("offer_title", model.getOffer_title());
            objectNode.put("offer_explaination", model.getOffer_explaination());
            objectNode.put("offer_cost", model.getOffer_cost());
            objectNode.put("offer_display_date", model.getOffer_display_date().toString());
            objectNode.put("offer_expired_date", model.getOffer_expired_date().toString());
            objectNode.put("offer_deliver_date", model.getOffer_deliver_date().toString());
            objectNode.put("company_id", model.getCompany_id());
            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    /**
     * @return all offers of all companies
     */

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<CompanyOfferModel> getCompanyOffers() {
        return repo.getAllOffers();
    }

    /**
     * get one offer by  offer id
     *
     * @param id
     * @return offer by offer id
     */

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public CompanyOfferModel getCompanyOffer(@PathVariable int id) {
        return repo.getCompanyOffer(id);
    }

    /**
     * update offer of company
     *
     * @param model
     * @return 1 if success or 0 if failed
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public JsonNode updateCompanyOffer(@Valid @RequestBody CompanyOfferModel model, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateCompanyOffer(model.getOffer_id(), model.getOffer_logo(), model.getOffer_title(), model.getOffer_explaination(),
                model.getOffer_cost(), model.getOffer_display_date(), model.getOffer_expired_date(), model.getOffer_deliver_date(),
                model.getCompany_id());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("offer_id", model.getOffer_id());
            objectNode.put("offer_logo", model.getOffer_logo());
            objectNode.put("offer_title", model.getOffer_title());
            objectNode.put("offer_explaination", model.getOffer_explaination());
            objectNode.put("offer_cost", model.getOffer_cost());
            objectNode.put("offer_display_date", model.getOffer_display_date().toString());
            objectNode.put("offer_expired_date", model.getOffer_expired_date().toString());
            objectNode.put("offer_deliver_date", model.getOffer_deliver_date().toString());
            objectNode.put("company_id", model.getCompany_id());
            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    /**
     * delete offer by offer id
     *
     * @param id
     * @return 1 if success or 0 if failed
     */
    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int deleteCompanyOffer(@PathVariable int id) {
        return repo.deleteCompanyOffer(id);
    }

    /**
     * get one company offers by company id
     *
     * @param id
     * @return list of company offer
     */

    @GetMapping("/get/company/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<CompanyOfferModel> getSingleCompanyOffer(@PathVariable int id) {
        return repo.getCompanyOffers(id);
    }

    /**
     * compute and return num of days, hours, minutes since offer added until now
     * return also offer display date and offer expired date
     * tke offer id
     *
     * @param id
     * @return list(1- )
     */

    @GetMapping("/get/data/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<String> getData(@PathVariable int id) {
        return repo.getProgressDate(id);
    }

}