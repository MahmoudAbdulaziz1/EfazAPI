package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.*;
import com.taj.repository.AddOfferImageRepo;
import com.taj.repository.SchoolRequestOfferRepo;
import com.taj.repository.SchoolRequstImagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 8/2/2018.
 */
@RequestMapping("/request/image")
@RestController
@CrossOrigin
public class SchoolRequstImagesController {



    @Autowired
    SchoolRequstImagesRepo repo;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/")
    public ResponseEntity<Integer> addOfferImages(@RequestBody AddOfferImage model) {
        //if (repo.checkIfOfferExist(model.getOffer_id())){
        int response = repo.addRequestImage(model.getImage_one(), model.getImage_two(), model.getImage_third(), model.getImage_four());
        if (response > 0) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 200);
            objectNode.put("images_id", response);
            objectNode.put("image_one", model.getImage_one());
            objectNode.put("image_two", model.getImage_two());
            objectNode.put("image_third", model.getImage_third());
            objectNode.put("image_four", model.getImage_four());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
        }
//        }else {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("status", 400);
//            objectNode.put("message", "id not exsit");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
//        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ObjectNode> deleteCompanyOffer(@PathVariable int id) {

        if (repo.checkIfExist(id)) {
            int res = repo.deleteSchoolRequest(id);
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "success");

                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "no id like this");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    @GetMapping("/")
    public List<SchoolRequstImagesModel> getCompanyOffersImages() {

        List<SchoolRequstImagesModel> list = repo.getRequestImages();
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<getRequestImage> getOfferImages(@PathVariable int id) {
        if (repo.checkIfExist(id)) {
            SchoolRequstImagesModel model = repo.getSchoolRequestImagesById(id);

            return ResponseEntity.status(HttpStatus.OK).body(new getRequestImage("200", model));

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getRequestImage("400", null));
        }

    }

//    @GetMapping("/{id}/offer")
//    public ResponseEntity<getOfferImages> getCompanyOfferImage(@PathVariable int id) {
//        if (repo.checkIfOfferExist(id) == true) {
//            List<AddOfferImage> model = repo.getCompanyOfferImages(id);
//
//            return ResponseEntity.status(HttpStatus.OK).body(new getOfferImages("200", model));
//
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getOfferImages("400", null));
//        }
//
//    }
//
//    @PostMapping("/aa/{id}")
//    public boolean test(@PathVariable int id){
//        return repo.checkIfOfferExist(id);
//    }
//

    @PutMapping("/update")
    public ResponseEntity<JsonNode> updateCompanyOffer(@RequestBody SchoolRequstImagesModel model) {


        if (repo.checkIfExist(model.getImage_id())){
            int res = repo.updateSchoolRequestImages(model.getImage_id(), model.getImage_one(), model.getImage_two(),
                    model.getImage_three(), model.getImage_four());

            if (res == 1){
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status",200);
                objectNode.put("image_id", model.getImage_id());
                objectNode.put("image_one", model.getImage_one());
                objectNode.put("image_two", model.getImage_two());
                objectNode.put("image_third", model.getImage_three());
                objectNode.put("image_four", model.getImage_four());

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




}
