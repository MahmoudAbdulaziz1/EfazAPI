package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.CompanyRequestCollectiveTenderModel;
import com.taj.repository.CompanyRequestCollectiveTenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 9/2/2018.
 */
@RequestMapping("/company/request/collective")
@RestController
@CrossOrigin
public class CompanyRequestCollectiveTenderController {

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    @Autowired
    CompanyRequestCollectiveTenderRepo repo;
    @Autowired
    ObjectMapper mapper;

    @PostMapping("/")
    public ResponseEntity<ObjectNode> addRequest(@Valid @RequestBody CompanyRequestCollectiveTenderModel model, Errors errors) {
        ObjectNode node = mapper.createObjectNode();
        if (errors.hasErrors()) {
            node.put(STATUS, 400);
            node.put(MESSAGE, "Validation Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
        int res = repo.addRequest(model.getResponse_takataf_company_id(), model.getResponse_takataf_request_id(), model.getResponsed_cost(),
                model.getResponsed_from(), model.getResponsed_to(), model.getResponse_desc());
        if (res == 1) {
            node.put(STATUS, 200);
            node.put("response_takataf_company_id", model.getResponse_takataf_company_id());
            node.put("response_takataf_request_id", model.getResponse_takataf_request_id());
            node.put("responsed_cost", model.getResponsed_cost());
            node.put("response_desc", model.getResponse_desc());
//            node.put("response_date", model.getResponse_date());
            node.put("updated_date", String.valueOf(new Timestamp(System.currentTimeMillis()).getTime()));
            return ResponseEntity.status(HttpStatus.OK).body(node);
        } else {
            node.put(STATUS, 400);
            node.put(MESSAGE, "Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
    }

    @GetMapping("/")
    public List<CompanyRequestCollectiveTenderModel> getAll() {
        return repo.getAll();
    }


}
