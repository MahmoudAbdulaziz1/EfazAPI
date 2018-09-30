package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.NewCustomSchoolProfileModel;
import com.taj.model.NewSchoolProfileModel;
import com.taj.model.NewSchoolProfileModel2;
import com.taj.model.SchoolProfileModel;
import com.taj.model.new_school_profile_map.NewCustomSchoolProfileModelDTO;
import com.taj.model.new_school_profile_map.NewSchoolProfileModel2DTO;
import com.taj.model.new_school_profile_map.NewSchoolProfileModelDTO;
import com.taj.repository.NewSchoolProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 9/18/2018.
 */
@RequestMapping("/school/profil")
@RestController
@CrossOrigin
public class NewSchoolProfileController {


    @Autowired
    ObjectMapper mapper;
    @Autowired
    private NewSchoolProfileRepo repo;

    @PostMapping("/")
    public ResponseEntity<ObjectNode> AddUserProfile(@RequestBody @Valid SchoolProfileModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (repo.isExistInLogin(model.getSchool_id(), "school")) {

            if (!repo.isExist(model.getSchool_id())) {
                int res = repo.addSchoolProfile(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                        model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                        model.getSchool_website_url(), model.getSchool_lng(), model.getSchool_lat(), model.getSchool_cover_image(), model.getSchool_phone_number());
                if (res == 1) {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("school_name", model.getSchool_name());
                    objectNode.put("school_logo_image", model.getSchool_logo_image());
                    objectNode.put("school_address", model.getSchool_address());
                    objectNode.put("school_service_desc", model.getSchool_service_desc());
                    objectNode.put("school_link_youtube", model.getSchool_link_youtube());
                    objectNode.put("school_website_url", model.getSchool_website_url());
                    objectNode.put("school_lng", model.getSchool_lng());
                    objectNode.put("school_lat", model.getSchool_lat());
                    objectNode.put("school_cover_image", model.getSchool_cover_image());
                    objectNode.put("school_phone_number", model.getSchool_phone_number());
                    return ResponseEntity.status(HttpStatus.OK).body(objectNode);
                } else {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 400);
                    objectNode.put("message", "not success");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
                }
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "id Already has profile");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }


        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "id not found");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    @GetMapping("/")
    public List<NewSchoolProfileModel2> getProfiles() {
        return repo.getSchoolSProfiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectNode> getProfile(@PathVariable int id) {


        if (repo.isExist(id)) {
            NewSchoolProfileModel model = repo.getSchoolProfile(id);
            ObjectNode node = mapper.createObjectNode();
            node.put("school_id", model.getSchool_id());
            node.put("school_name", model.getSchool_name());
            node.put("school_logo_image", model.getSchool_logo_image());
            node.put("school_address", model.getSchool_address());
            node.put("school_service_desc", model.getSchool_service_desc());
            node.put("school_link_youtube", model.getSchool_link_youtube());
            node.put("school_website_url", model.getSchool_website_url());
            node.put("school_lng", model.getSchool_lng());
            node.put("school_lat", model.getSchool_lat());
            node.put("school_cover_image", model.getSchool_cover_image());
            node.put("school_phone_number", model.getSchool_phone_number());
            node.put("area", model.getArea());
            node.put("city", model.getCity());
            return ResponseEntity.status(HttpStatus.OK).body(node);

        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put("status", 400);
            node.put("message", "no profile to this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);

        }
    }


    @PutMapping("/")
    public ResponseEntity<ObjectNode> updateProfile(@Valid @RequestBody NewSchoolProfileModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }

        int res = repo.updateProfile(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url(), model.getSchool_lng(), model.getSchool_lat(), model.getSchool_cover_image(),
                model.getSchool_phone_number(), model.getArea(), model.getCity());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("school_name", model.getSchool_name());
            objectNode.put("school_logo_image", model.getSchool_logo_image());
            objectNode.put("school_address", model.getSchool_address());
            objectNode.put("school_service_desc", model.getSchool_service_desc());
            objectNode.put("school_link_youtube", model.getSchool_link_youtube());
            objectNode.put("school_website_url", model.getSchool_website_url());
            objectNode.put("school_lng", model.getSchool_lng());
            objectNode.put("school_lat", model.getSchool_lat());
            objectNode.put("school_cover_image", model.getSchool_cover_image());
            objectNode.put("school_phone_number", model.getSchool_phone_number());
            objectNode.put("area", model.getArea());
            objectNode.put("city", model.getCity());
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "update not completed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    @GetMapping("/exist/{id}")
    public int isExist(@PathVariable int id) {
        return repo.checkSchoolProfile(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectNode> deleteSchoolProfile(@PathVariable int id) {
        int res = repo.deleteSchoolProfile(id);
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("messsage", "success");
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<ObjectNode> getProfileForAdmin(@PathVariable int id) {

        if (repo.isExist(id)) {
            NewCustomSchoolProfileModel model = repo.getSchoolProfileForAdmin(id);
            ObjectNode node = mapper.createObjectNode();
            node.put("school_id", model.getSchool_id());
            node.put("school_name", model.getSchool_name());
            node.put("school_logo_image", model.getSchool_logo_image());
            node.put("school_address", model.getSchool_address());
            node.put("school_service_desc", model.getSchool_service_desc());
            node.put("school_link_youtube", model.getSchool_link_youtube());
            node.put("school_website_url", model.getSchool_website_url());
            node.put("school_cover_image", model.getSchool_cover_image());
            node.put("school_phone_number", model.getSchool_phone_number());
            node.put("area", model.getArea());
            node.put("city", model.getCity());
            return ResponseEntity.status(HttpStatus.OK).body(node);

        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put("status", 400);
            node.put("message", "no profile to this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);

        }

    }

    @PutMapping("/update")
    public ResponseEntity<ObjectNode> updateProfileForAdmin(@Valid @RequestBody NewSchoolProfileModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }

        int res = repo.updateProfileForAdmin(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url(), model.getSchool_cover_image(), model.getSchool_phone_number(), model.getArea(), model.getCity());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("school_name", model.getSchool_name());
            objectNode.put("school_logo_image", model.getSchool_logo_image());
            objectNode.put("school_address", model.getSchool_address());
            objectNode.put("school_service_desc", model.getSchool_service_desc());
            objectNode.put("school_link_youtube", model.getSchool_link_youtube());
            objectNode.put("school_website_url", model.getSchool_website_url());
            objectNode.put("school_cover_image", model.getSchool_cover_image());
            objectNode.put("school_phone_number", model.getSchool_phone_number());
            objectNode.put("area", model.getArea());
            objectNode.put("city", model.getCity());
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "update not completed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/s/")
    public List<NewSchoolProfileModel2DTO> getProfiles2() {
        return repo.getSchoolSProfiles2();
    }

    @GetMapping("/s/{id}")
    public ResponseEntity<ObjectNode> getProfile2(@PathVariable int id) {


        if (repo.isExist(id)) {
            NewSchoolProfileModelDTO model = repo.getSchoolProfile2(id);
            ObjectNode node = mapper.createObjectNode();
            node.put("school_id", model.getSchool_id());
            node.put("school_name", model.getSchool_name());
            node.put("school_logo_image", model.getSchool_logo_image());
            node.put("school_address", model.getSchool_address());
            node.put("school_service_desc", model.getSchool_service_desc());
            node.put("school_link_youtube", model.getSchool_link_youtube());
            node.put("school_website_url", model.getSchool_website_url());
            node.put("school_lng", model.getSchool_lng());
            node.put("school_lat", model.getSchool_lat());
            node.put("school_cover_image", model.getSchool_cover_image());
            node.put("school_phone_number", model.getSchool_phone_number());
            node.put("area", model.getArea());
            node.put("city", model.getCity());
            node.put("lng", model.getLng());
            node.put("lat", model.getLat());
            return ResponseEntity.status(HttpStatus.OK).body(node);

        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put("status", 400);
            node.put("message", "no profile to this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);

        }
    }

    @PutMapping("/s/")
    public ResponseEntity<ObjectNode> updateProfile2(@Valid @RequestBody NewSchoolProfileModelDTO model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }

        int res = repo.updateProfile2(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url(), model.getSchool_lng(), model.getSchool_lat(), model.getSchool_cover_image(),
                model.getSchool_phone_number(), model.getArea(), model.getCity(), model.getLng(), model.getLat());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("school_name", model.getSchool_name());
            objectNode.put("school_logo_image", model.getSchool_logo_image());
            objectNode.put("school_address", model.getSchool_address());
            objectNode.put("school_service_desc", model.getSchool_service_desc());
            objectNode.put("school_link_youtube", model.getSchool_link_youtube());
            objectNode.put("school_website_url", model.getSchool_website_url());
            objectNode.put("school_lng", model.getSchool_lng());
            objectNode.put("school_lat", model.getSchool_lat());
            objectNode.put("school_cover_image", model.getSchool_cover_image());
            objectNode.put("school_phone_number", model.getSchool_phone_number());
            objectNode.put("area", model.getArea());
            objectNode.put("city", model.getCity());
            objectNode.put("lng", model.getLng());
            objectNode.put("lat", model.getLat());
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "update not completed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    @GetMapping("/s/get/{id}")
    public ResponseEntity<ObjectNode> getProfileForAdmin2(@PathVariable int id) {

        if (repo.isExist(id)) {
            NewCustomSchoolProfileModelDTO model = repo.getSchoolProfileForAdmin2(id);
            ObjectNode node = mapper.createObjectNode();
            node.put("school_id", model.getSchool_id());
            node.put("school_name", model.getSchool_name());
            node.put("school_logo_image", model.getSchool_logo_image());
            node.put("school_address", model.getSchool_address());
            node.put("school_service_desc", model.getSchool_service_desc());
            node.put("school_link_youtube", model.getSchool_link_youtube());
            node.put("school_website_url", model.getSchool_website_url());
            node.put("school_cover_image", model.getSchool_cover_image());
            node.put("school_phone_number", model.getSchool_phone_number());
            node.put("area", model.getArea());
            node.put("city", model.getCity());
            node.put("lng", model.getLng());
            node.put("lat", model.getLat());
            return ResponseEntity.status(HttpStatus.OK).body(node);

        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put("status", 400);
            node.put("message", "no profile to this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);

        }

    }

    @PutMapping("/s/update")
    public ResponseEntity<ObjectNode> updateProfileForAdmin2(@Valid @RequestBody NewSchoolProfileModelDTO model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }

        int res = repo.updateProfileForAdmin2(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url(), model.getSchool_cover_image(), model.getSchool_phone_number(), model.getArea(),
                model.getCity(), model.getLng(), model.getLat());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("school_name", model.getSchool_name());
            objectNode.put("school_logo_image", model.getSchool_logo_image());
            objectNode.put("school_address", model.getSchool_address());
            objectNode.put("school_service_desc", model.getSchool_service_desc());
            objectNode.put("school_link_youtube", model.getSchool_link_youtube());
            objectNode.put("school_website_url", model.getSchool_website_url());
            objectNode.put("school_cover_image", model.getSchool_cover_image());
            objectNode.put("school_phone_number", model.getSchool_phone_number());
            objectNode.put("area", model.getArea());
            objectNode.put("city", model.getCity());
            objectNode.put("lat", model.getLng());
            objectNode.put("lng", model.getLat());
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "update not completed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }


}
