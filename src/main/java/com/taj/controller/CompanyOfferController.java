package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.CompanyAdminGetOffers;
import com.taj.model.CompanyOfferModel;
import com.taj.model.getCompanyOffer;
import com.taj.model.getOffer;
import com.taj.repository.CompanyOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    ObjectMapper mapper;

    /**
     * add offer from company
     *
     * @param model
     * @return 1 if success or 0 if failed
     */

    @PostMapping("/add")
    public ResponseEntity<ObjectNode> addCompanyOffer(@RequestBody CompanyOfferModel model) {
//
//        if (errors.hasErrors()) {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("state", 400);
//            objectNode.put("message", "Validation Failed");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
//        } else {
            int res = repo.addCompanyOffer(model.getOffer_images_id(), model.getOffer_title(), model.getOffer_explaination(),
                    model.getOffer_cost(), model.getOffer_display_date(), model.getOffer_expired_date(), model.getOffer_deliver_date(),
                    model.getCompany_id(), model.getOffer_count());
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                objectNode.put("offer_images_id", model.getOffer_images_id());
                objectNode.put("offer_title", model.getOffer_title());
                objectNode.put("offer_explaination", model.getOffer_explaination());
                objectNode.put("offer_cost", model.getOffer_cost());
                objectNode.put("offer_display_date", model.getOffer_display_date().toString());
                objectNode.put("offer_expired_date", model.getOffer_expired_date().toString());
                objectNode.put("offer_deliver_date", model.getOffer_deliver_date().toString());
                objectNode.put("company_id", model.getCompany_id());
                objectNode.put("offer_count", model.getOffer_count());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        //}
    }
    /**
     * @return all offers of all companies
     */

    @GetMapping("/getAll")
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
    public ResponseEntity<getOffer> getCompanyOffer(@PathVariable int id) {
        if (repo.checkIfExist(id)){
            CompanyOfferModel model = repo.getCompanyOffer(id);

            return ResponseEntity.status(HttpStatus.OK).body(new getOffer("200", model));

        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getOffer("400", null));
        }

    }

    /**
     * update offer of company
     *
     * @param model
     * @return 1 if success or 0 if failed
     */
    @PutMapping("/update")
    public ResponseEntity<JsonNode> updateCompanyOffer(@Valid @RequestBody CompanyOfferModel model, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            //objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (repo.checkIfExist(model.getOffer_id())){
            int res = repo.updateCompanyOffer(model.getOffer_id(), model.getOffer_images_id(), model.getOffer_title(), model.getOffer_explaination(),
                    model.getOffer_cost(), model.getOffer_display_date(), model.getOffer_expired_date(), model.getOffer_deliver_date(),
                    model.getCompany_id(), model.getOffer_count());

            if (res == 1){
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status",200);
                objectNode.put("offer_id", model.getOffer_id());
                objectNode.put("offer_images_id", model.getOffer_images_id());
                objectNode.put("offer_title", model.getOffer_title());
                objectNode.put("offer_explaination", model.getOffer_explaination());
                objectNode.put("offer_cost", model.getOffer_cost());
                objectNode.put("offer_display_date", model.getOffer_display_date().toString());
                objectNode.put("offer_expired_date", model.getOffer_expired_date().toString());
                objectNode.put("offer_deliver_date", model.getOffer_deliver_date().toString());
                objectNode.put("company_id", model.getCompany_id());
                objectNode.put("offer_count", model.getOffer_count());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            }else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not exist");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    /**
     * delete offer by offer id
     *
     * @param id
     * @return 1 if success or 0 if failed
     */
    @PutMapping("/delete/{id}")
    public ResponseEntity<ObjectNode> deleteCompanyOffer(@PathVariable int id) {

        if (repo.checkIfExist(id)){
            int res = repo.deleteCompanyOffer(id);
            if (res == 1){
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "success");

                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            }else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    /**
     * get one company offers by company id
     *
     * @param id
     * @return list of company offer
     */

    @GetMapping("/get/company/{id}")
    public ResponseEntity<getCompanyOffer> getSingleCompanyOffer(@PathVariable int id) {
        List<CompanyOfferModel> offers = repo.getCompanyOffers(id);
        return ResponseEntity.status(HttpStatus.OK).body(new getCompanyOffer("200", offers));
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
    public List<String> getData(@PathVariable int id) {
        return repo.getProgressDate(id);
    }

    @GetMapping(value="/admin/getAll")
    public List<CompanyAdminGetOffers> getCompanyOffersForDash() {
        return repo.getAllOffersForDash();
    }

}