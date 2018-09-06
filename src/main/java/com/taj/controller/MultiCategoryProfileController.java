package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.*;
import com.taj.repository.MultiCategoryProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 9/4/2018.
 */
@RequestMapping("/company/profile")
@RestController
@CrossOrigin
public class MultiCategoryProfileController {

    @Autowired
    MultiCategoryProfileRepo repo;
    @Autowired
    ObjectMapper mapper;

    @PostMapping("/")
    public ResponseEntity<ObjectNode> addProfileWithCategories(@RequestBody @Valid MultiCategoryProfileModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (repo.isExist(model.getCompany_id())) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "already has profile in this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            int res = repo.addProfileWithCategories(model.getCompany_id(), model.getCompany_name(), model.getCompany_logo_image(),
                    model.getCompany_address(),  model.getCompany_link_youtube(),
                    model.getCompany_website_url(), model.getCompany_lng(), model.getCompany_lat(),
                    model.getCompany_cover_image(), model.getCompany_phone_number(), model.getCompany_desc(), model.getCategory());

            ArrayNode category = mapper.createArrayNode();

            for (int i = 0; i < model.getCategory().size(); i++) {
                ObjectNode node = mapper.createObjectNode();
                node.put("category_name", model.getCategory().get(i).getCategory_name());
                category.add(node);
                System.out.println(node.toString() + "");
            }
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 200);
            objectNode.put("company_id", model.getCompany_id());
            objectNode.put("company_name", model.getCompany_name());
            objectNode.put("company_logo_image", model.getCompany_logo_image());
            objectNode.put("company_address", model.getCompany_address());
            //objectNode.put("company_category_id", model.getCompany_category_id());
            objectNode.put("company_link_youtube", model.getCompany_link_youtube());
            objectNode.put("company_website_url", model.getCompany_website_url());
            objectNode.put("company_lng", model.getCompany_lng());
            objectNode.put("company_lat", model.getCompany_lat());
            objectNode.put("company_cover_image", model.getCompany_cover_image());
            objectNode.put("company_phone_number", model.getCompany_phone_number());
            objectNode.put("company_desc", model.getCompany_desc());
            objectNode.set("categories", category);
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);


        }
    }

    @GetMapping("/")
    public List<MultiCategoryProfileGetAllDTO> getProfiles() {
        return repo.getProfiles();
    }

    @GetMapping("/{id}")
    public MultiCategoryProfileDTO getProfile(@PathVariable int id) {
        List<TakatfTenderCategoryPOJO> category = new ArrayList<>();
        List<MultiCategoryProfileDTOS> allData = repo.getProfile(id);
        for (int i = 0; i < allData.size(); i++) {
            TakatfTenderCategoryPOJO pojo = new TakatfTenderCategoryPOJO(allData.get(i).getCompany_cat_name());
            category.add(pojo);
        }
        MultiCategoryProfileDTO result = new MultiCategoryProfileDTO(allData.get(0).getCompany_id(), allData.get(0).getCompany_name(),
                allData.get(0).getCompany_logo_image(), allData.get(0).getCompany_address(),
                allData.get(0).getCompany_link_youtube(), allData.get(0).getCompany_website_url(), allData.get(0).getCompany_lng(),
                allData.get(0).getCompany_lat(), allData.get(0).getCompany_cover_image(), allData.get(0).getCompany_phone_number(),
                allData.get(0).getFollower_count(), allData.get(0).getOffer_count(), allData.get(0).getCompany_desc(), category);
        return result;
    }

    @PutMapping("/")
    public ResponseEntity<ObjectNode> updateProfile(@RequestBody @Valid MultiCategoryProfileModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (repo.isExist(model.getCompany_id())) {


            int res = repo.updateProfile(model.getCompany_id(), model.getCompany_name(), model.getCompany_logo_image(), model.getCompany_address(),
                    model.getCompany_link_youtube(), model.getCompany_website_url(), model.getCompany_lng(),
                    model.getCompany_lat(), model.getCompany_cover_image(), model.getCompany_phone_number(), model.getCompany_desc(), model.getCategory());
            if (res == 1) {
                ArrayNode category = mapper.createArrayNode();

                for (int i = 0; i < model.getCategory().size(); i++) {
                    ObjectNode node = mapper.createObjectNode();
                    node.put("category_name", model.getCategory().get(i).getCategory_name());
                    category.add(node);
                    System.out.println(node.toString() + "");
                }
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 200);
                objectNode.put("company_id", model.getCompany_id());
                objectNode.put("company_name", model.getCompany_name());
                objectNode.put("company_logo_image", model.getCompany_logo_image());
                objectNode.put("company_address", model.getCompany_address());
                objectNode.put("company_link_youtube", model.getCompany_link_youtube());
                objectNode.put("company_website_url", model.getCompany_website_url());
                objectNode.put("company_lng", model.getCompany_lng());
                objectNode.put("company_lat", model.getCompany_lat());
                objectNode.put("company_cover_image", model.getCompany_cover_image());
                objectNode.put("company_phone_number", model.getCompany_phone_number());
                objectNode.put("company_desc", model.getCompany_desc());
                objectNode.set("categories", category);
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);


            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 400);
                objectNode.put("message", "failed");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }


        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "already has profile in this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<GetCompanyByCategoryCompany> getProfileByCategoryAndroid(@PathVariable String id) {
        List<CompanyProfileDto> model = repo.getProfileByCategory(id);
        if (model.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new GetCompanyByCategoryCompany("200", model));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetCompanyByCategoryCompany("400", null));
        }
    }

}
