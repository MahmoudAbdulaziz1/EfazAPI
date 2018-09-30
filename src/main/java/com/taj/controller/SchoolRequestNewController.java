package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.*;
import com.taj.model.school.request.image.GetCollectiveTenderPartOneDTO2;
import com.taj.model.school.request.image.GetCollectiveTenders2;
import com.taj.model.school.request.image.SchoolNewRequestsDTO2;
import com.taj.model.school.request.image.getSchoolCustomNewRequestById;
import com.taj.model.school_request_image_web.SchoolRequestWithImageByIdDto;
import com.taj.repository.AddSchoolRequestImagesRepo;
import com.taj.repository.SchoolRequestNewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    AddSchoolRequestImagesRepo addSchoolRequestImagesRepo;
    @Autowired
    ObjectMapper mapper;


    @PostMapping("/")
    public ResponseEntity<JsonNode> addSchoolRequest(@RequestBody @Valid SchoolNewRequestsDTO2 model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


        int res = repo.addRequest(model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_display_date(), model.getRequest_expired_date(), model.getSchool_id(), model.getRequest_category_name(), model.getImage_one());
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();

            objectNode.put("request_title", model.getRequest_title());
            objectNode.put("request_explaination", model.getRequest_explaination());
            objectNode.put("request_display_date", model.getRequest_display_date());
            objectNode.put("request_expired_date", model.getRequest_expired_date());
            objectNode.put("school_id", model.getSchool_id());
            objectNode.put("request_category_id", model.getRequest_category_name());
            objectNode.put("image", model.getImage_one());

            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

//    @GetMapping("/{id}")
//    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
//    public ResponseEntity<SchoolRequestNewDtoById> getSchoolSingleRequest(@PathVariable int id) {
//        if (repo.isExist(id)) {
//            return ResponseEntity.status(HttpStatus.OK).body(repo.getRequestByID(id));
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolRequestWithImageByIdDto> getSchoolSingleRequestByImage(@PathVariable int id) {
        if (repo.isExist(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(repo.getRequestByIDWithImage(id));
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
    public List<SchoolRequestNewDto2> getSchoolRequestsBySchool(@PathVariable int id) {
        return repo.getRequestsBySchoolID(id);
    }

    @GetMapping("/request/school/{id}")
    public GetCollectiveTenders2 getRequestOfSchoolByID(@PathVariable int id) {
        List<getSchoolCustomNewRequestById> obj = repo.getRequestOfSchoolByID(id);
        GetCollectiveTenderPartOneDTO2 tender = new GetCollectiveTenderPartOneDTO2(obj.get(0).getRequest_id(), obj.get(0).getRequest_title(),
                obj.get(0).getRequest_explaination(), obj.get(0).getRequest_display_date(), obj.get(0).getRequest_expired_date(),
                obj.get(0).getSchool_id(), obj.get(0).getResponse_count(), obj.get(0).getImage_one());


        List<GetCollectiveTenderPartYTwoDTO> companies = new ArrayList<>();
        if (obj.get(0).getResponse_count() > 0) {
            for (getSchoolCustomNewRequestById one : obj) {
                //if( one.getRequest_category_name().equals(null) )
                GetCollectiveTenderPartYTwoDTO part2 = new GetCollectiveTenderPartYTwoDTO(one.getRequest_category_name() + "", one.getCompany_name() + "",
                        one.getCompany_logo_image(), one.getCategory_name() + "", one.getResponsed_cost(), one.getResponse_date(),
                        one.getResponse_id(), one.getResponsed_company_id(), one.getIs_aproved(), one.getResponse_desc());
                companies.add(part2);
            }
        }

        if (obj.get(0).getResponse_count() == 0) {
            GetCollectiveTenders2 tenders = new GetCollectiveTenders2(tender, null);
            return tenders;
        } else {
            GetCollectiveTenders2 tenders = new GetCollectiveTenders2(tender, companies);
            return tenders;
        }


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
        if (repo.isExist(id)) {
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
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "not element by this id");

            return objectNode;
        }

    }

    @GetMapping("/response/{request_id}")
    public ResponseEntity<RequstResponsePOJO> getSingleTenderDetails(@PathVariable int request_id) {
        if (repo.isExist(request_id)) {
            if (repo.getSingleTenderDetails(request_id) != null) {
                RequstResponsePOJO requstResponsePOJO = new RequstResponsePOJO(200, repo.getSingleTenderDetails(request_id));
                return ResponseEntity.status(HttpStatus.OK).body(requstResponsePOJO);
            } else {
                RequstResponsePOJO requstResponsePOJO = new RequstResponsePOJO(400, repo.getSingleTenderDetails(request_id));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(requstResponsePOJO);
            }
        } else {

            RequstResponsePOJO requstResponsePOJO = new RequstResponsePOJO(400, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(requstResponsePOJO);

        }


    }

    @PutMapping("/accept/{request_id}")
    public ResponseEntity<ObjectNode> acceptOffer(@PathVariable int request_id) {
        if (repo.isExist(request_id)) {
            int response = repo.acceptOffer(request_id);
            if (response == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "success");
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "not sucess");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "no exist request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    @PutMapping("/cancel/{request_id}")
    public ResponseEntity<ObjectNode> cancelOffer(@PathVariable int request_id) {
        if (repo.isExist(request_id)) {
            int response = repo.cancelOffer(request_id);
            if (response == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "success");
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "not sucess");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "no exist request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }


}
