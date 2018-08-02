package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.SchoolFollowCompany;
import com.taj.model.getFollowedList;
import com.taj.model.getFollowersList;
import com.taj.repository.SchoolFollowCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 7/19/2018.
 */

@RequestMapping("/follow")
@RestController
@CrossOrigin
public class SchoolFollowCompanyController {


    @Autowired
    SchoolFollowCompanyRepo repo;
    @Autowired
    ObjectMapper mapper;


    @PostMapping("/add")
    public ResponseEntity<ObjectNode> addFollower(@RequestBody @Valid SchoolFollowCompany model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            //objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        if (repo.isExist(model.getFollow_id())) {

            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "id exist");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            if (repo.isExistFollwing(model.getOrganization_id()) && repo.isExistFollwer(model.getFollower_id())) {
                int res = repo.addFollower(model.getOrganization_id(), model.getFollower_id());
                if (res == 1) {

                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 200);
                    objectNode.put("Organization_id", model.getOrganization_id());
                    objectNode.put("Follower_id", model.getFollower_id());

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
                objectNode.put("message", "organization not exist");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }

        }


    }

    @GetMapping("/getAll")
    public List<SchoolFollowCompany> getAllFollowers() {
        return repo.getAllFollowers();
    }

    @GetMapping("/get/{id}")
    public SchoolFollowCompany getById(@PathVariable int id) {
        return repo.getById(id);
    }

    //followers
    @GetMapping("/get/following/{schoolId}")
    public ResponseEntity<getFollowersList> getAllSchoolFollowing(@PathVariable int schoolId) {


        if (repo.isExistFollwer(schoolId)) {
            List<SchoolFollowCompany> followers = repo.getAllSchoolFollowing(schoolId);

            return ResponseEntity.status(HttpStatus.OK).body(new getFollowersList("200", followers));
        } else {
            List<SchoolFollowCompany> followers = repo.getAllSchoolFollowing(schoolId);

            return ResponseEntity.status(HttpStatus.OK).body(new getFollowersList("400", followers));
        }

    }

    //who's followed
    @GetMapping("/get/followers/{companyId}")
    public ResponseEntity<getFollowedList> getCompanyAllFollowers(@PathVariable int companyId) {

        if (repo.isExistFollwer(companyId)) {
            List<SchoolFollowCompany> followed = repo.getCompanyAllFollowers(companyId);
            return ResponseEntity.status(HttpStatus.OK).body(new getFollowedList("200", followed));
        } else {
            List<SchoolFollowCompany> followed = repo.getCompanyAllFollowers(companyId);
            return ResponseEntity.status(HttpStatus.OK).body(new getFollowedList("400", followed));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ObjectNode> updateSchoolFollowCompany(@Valid @RequestBody SchoolFollowCompany model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            //objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (repo.isExist(model.getFollow_id()) && repo.isExistFollwer(model.getFollower_id()) && repo.isExistFollwing(model.getOrganization_id())) {
            int res = repo.updateSchoolFollowCompany(model.getFollow_id(), model.getOrganization_id(), model.getFollower_id());

            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                objectNode.put("Follow_id", model.getFollow_id());
                objectNode.put("Organization_id", model.getOrganization_id());
                objectNode.put("Follower_id", model.getFollower_id());

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
            objectNode.put("message", "id not exist");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ObjectNode deleteSchoolFollowCompany(@PathVariable int id) {
        if (repo.isExist(id)) {
            int res = repo.deleteSchoolFollowCompany(id);
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                objectNode.put("message", "success");

                return objectNode;
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return objectNode;
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not exist");

            return objectNode;
        }

    }


}
