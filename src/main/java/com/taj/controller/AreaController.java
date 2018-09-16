package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.AreaModel;
import com.taj.repository.AreaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 9/16/2018.
 */
@RequestMapping("/area")
@RestController
@CrossOrigin
public class AreaController {

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String NOFOUND = "This id not found";
    private static final String AREANAME = "area_name";

    @Autowired
    AreaRepo areaRepo;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/")
    public ResponseEntity<ObjectNode> addArea(@RequestBody AreaModel model) {
        int response = areaRepo.addArea(model.getAreaName());
        if (response == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(AREANAME, model.getAreaName());
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(MESSAGE, "Not added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<AreaModel>> getCities() {
        if (areaRepo.getAreas().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(areaRepo.getAreas());
        }
        return ResponseEntity.status(HttpStatus.OK).body(areaRepo.getAreas());


    }

    @GetMapping("/{areaId}")
    public ResponseEntity<ObjectNode> getCityById(@PathVariable int areaId) {
        if (!(areaRepo.checkIfExist(areaId))) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, "400");
            objectNode.put(MESSAGE, NOFOUND);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            AreaModel areaModel = areaRepo.getareaById(areaId);
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("area_id", areaModel.getAreaId());
            objectNode.put(AREANAME, areaModel.getAreaName());
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ObjectNode> updateCity(@RequestBody AreaModel model) {
        if (areaRepo.checkIfExist(model.getAreaId())) {

            int response = areaRepo.updateArea(model.getAreaId(), model.getAreaName());
            if (response == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("city_id", model.getAreaId());
                objectNode.put(AREANAME, model.getAreaName());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put(STATUS, "400");
                objectNode.put(MESSAGE, "not updated");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }


        } else {

            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, "400");
            objectNode.put(MESSAGE, NOFOUND);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);


        }
    }

    @DeleteMapping("/{areaId}")
    public ResponseEntity<ObjectNode> deleteCity(@PathVariable int areaId) {
        if (!(areaRepo.checkIfExist(areaId))) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, "400");
            objectNode.put(MESSAGE, NOFOUND);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            int response = areaRepo.deleteArea(areaId);
            if (response == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put(STATUS, "200");
                objectNode.put(MESSAGE, "Successfully deleted");
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put(STATUS, "400");
                objectNode.put(MESSAGE, "not deleted");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        }
    }


}
