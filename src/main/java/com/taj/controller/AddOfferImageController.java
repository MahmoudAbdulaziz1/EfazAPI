package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.AddOfferImage;
import com.taj.model.CompanyOfferModel;
import com.taj.model.getOfferImage;
import com.taj.model.getOfferImages;
import com.taj.repository.AddOfferImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 7/30/2018.
 */
@RequestMapping("/offer/image")
@RestController
@CrossOrigin
public class AddOfferImageController {

    @Autowired
    AddOfferImageRepo repo;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/")
    public ResponseEntity<ObjectNode> addOfferImages(@RequestBody AddOfferImage model) {
        if (repo.checkIfOfferExist(model.getOffer_id())){
            int response = repo.addOfferImage(model.getImage_one(), model.getImage_two(), model.getImage_third(), model.getImage_four(), model.getOffer_id());
            if (response == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                //objectNode.put("offer_id", model.getImages_id());
                objectNode.put("image_one", model.getImage_one());
                objectNode.put("image_two", model.getImage_two());
                objectNode.put("image_third", model.getImage_third());
                objectNode.put("image_four", model.getImage_four());
                objectNode.put("offer_id", model.getOffer_id());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "id not exsit");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ObjectNode> deleteCompanyOffer(@PathVariable int id) {
        int res = repo.deleteCompanyOffer(id);
        if (repo.checkIfExist(id)) {
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("value", "success");

                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("value", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "no id like this");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    @GetMapping("/")
    public List<AddOfferImage> getCompanyOffersImages() {

        List<AddOfferImage> list = repo.getOfferImages();
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<getOfferImage> getOfferImages(@PathVariable int id) {
        if (repo.checkIfExist(id)) {
            AddOfferImage model = repo.getCompanyOfferImage(id);

            return ResponseEntity.status(HttpStatus.OK).body(new getOfferImage("200", model));

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getOfferImage("400", null));
        }

    }

    @GetMapping("/{id}/offer")
    public ResponseEntity<getOfferImages> getCompanyOfferImage(@PathVariable int id) {
        if (repo.checkIfOfferExist(id) == true) {
            List<AddOfferImage> model = repo.getCompanyOfferImages(id);

            return ResponseEntity.status(HttpStatus.OK).body(new getOfferImages("200", model));

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getOfferImages("400", null));
        }

    }

    @PostMapping("/aa/{id}")
    public boolean test(@PathVariable int id){
        return repo.checkIfOfferExist(id);
    }


    @PutMapping("/update")
    public ResponseEntity<JsonNode> updateCompanyOffer(@RequestBody AddOfferImage model) {


        if (repo.checkIfExist(model.getImages_id())){
            int res = repo.updateCompanyOfferImages(model.getImages_id(), model.getImage_one(), model.getImage_two(),
                    model.getImage_third(), model.getImage_four(), model.getOffer_id());

            if (res == 1){
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status",200);
                objectNode.put("images_id", model.getImages_id());
                objectNode.put("image_one", model.getImage_one());
                objectNode.put("image_two", model.getImage_two());
                objectNode.put("image_third", model.getImage_third());
                objectNode.put("image_four", model.getImage_four());
                objectNode.put("offer_id", model.getOffer_id());

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
