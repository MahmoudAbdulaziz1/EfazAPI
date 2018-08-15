package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.SchoolRequestNewDto;
import com.taj.model.SchoolRequestsDTO;
import com.taj.repository.SchoolRequestNewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 8/15/2018.
 */
@RequestMapping("/school/tenders")
@RestController
@CrossOrigin
public class SchoolRequestNewController {

    @Autowired
    SchoolRequestNewRepo repo;
    @Autowired
    ObjectMapper mapper;


    @PostMapping("/")
    public ResponseEntity<JsonNode> addSchoolRequest(@RequestBody @Valid SchoolRequestsDTO model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        int res = repo.addRequest(model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_display_date(), model.getRequest_expired_date(), model.getSchool_id(), model.getRequest_category_id());
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();

            objectNode.put("request_title", model.getRequest_title());
            objectNode.put("request_explaination", model.getRequest_explaination());
            objectNode.put("request_display_date", model.getRequest_display_date());
            objectNode.put("request_expired_date", model.getRequest_expired_date());
            objectNode.put("school_id", model.getSchool_id());
            objectNode.put("request_category_id", model.getRequest_category_id());

            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public ResponseEntity<SchoolRequestNewDto> getSchoolSingleRequest(@PathVariable int id) {
        if (repo.isExist(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(repo.getRequestByID(id));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/")
    public List<SchoolRequestNewDto> getSchoolSingleRequest() {
        return repo.getRequestsAll();
    }


    @GetMapping("/school/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestNewDto> getSchoolRequestsBySchool(@PathVariable int id) {
        return repo.getRequestsBySchoolID(id);
    }

    @GetMapping("/cat/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestNewDto> getSchoolRequestsByCategory(@PathVariable String id) {
        return repo.getRequestsByCategoryID(id);
    }

    @PutMapping("/")
    public JsonNode updateRequestModel(@Valid @RequestBody SchoolRequestsDTO model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");

            return objectNode;
        }
        int res = repo.updateRequest(model.getRequest_id(), model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_display_date(), model.getRequest_expired_date(), model.getSchool_id(), model.getRequest_category_id());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("request_id", model.getRequest_id());
            objectNode.put("request_title", model.getRequest_title());
            objectNode.put("request_explaination", model.getRequest_explaination());
            objectNode.put("request_display_date", model.getRequest_display_date());
            objectNode.put("request_expired_date", model.getRequest_expired_date());
            objectNode.put("school_id", model.getSchool_id());
            objectNode.put("request_category_id", model.getRequest_category_id());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not success");

            return objectNode;
        }
    }

    @DeleteMapping("/{id}")
    public ObjectNode deleteSchoolRequest(@PathVariable int id) {
        if (repo.isExist(id)){
            int res = repo.deleteSchoolRequest(id);
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "success");

                return objectNode;
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "not success");

                return objectNode;
            }
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "not element by this id");

            return objectNode;
        }

    }


}
