package com.taj.controller.company_request_collective;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.company_request_collective.CompanyRequestCollectiveByTnders;
import com.taj.model.company_request_collective.CompanyRequestCollectiveModel;
import com.taj.repository.company_request_colective.CompanyRequestCollectiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 10/3/2018.
 */
@RequestMapping("/company/collective")
@RestController
@CrossOrigin
public class CompanyRequestCollectiveController {

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    @Autowired
    CompanyRequestCollectiveRepo repo;
    @Autowired
    ObjectMapper mapper;

    @PostMapping("/")
    public ResponseEntity<ObjectNode> addRequestToCollective(@RequestBody @Valid CompanyRequestCollectiveModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "validation errors");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }

        if (repo.isExistTender(model.getResponse_takataf_request_id())) {
            if (repo.isExistOffer(model.getResponse_takataf_company_id(), model.getResponse_takataf_request_id())) {

                ObjectNode node = mapper.createObjectNode();
                node.put(STATUS, 400);
                node.put(MESSAGE, "already has offer to this tender");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
            } else {
                int res = repo.addRequestToCollective(model.getResponse_takataf_company_id(), model.getResponse_takataf_request_id(),
                        model.getResponsed_cost(), model.getResponse_desc());
                if (res == 1) {
                    ObjectNode node = mapper.createObjectNode();
                    node.put("company_id", model.getResponse_takataf_company_id());
                    node.put("tender_id", model.getResponse_takataf_request_id());
                    node.put("cost", model.getResponsed_cost());
                    node.put("response_desc", model.getResponse_desc());
                    return ResponseEntity.status(HttpStatus.OK).body(node);
                } else {
                    ObjectNode node = mapper.createObjectNode();
                    node.put(STATUS, 400);
                    node.put(MESSAGE, "Failed");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
                }
            }
        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "no tender for this this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }

    }

    @GetMapping("/")
    public List<CompanyRequestCollectiveModel> getAllCompanyResponses() {
        return repo.getAllCompanyResponses();
    }

    @GetMapping("/{id}")
    public CompanyRequestCollectiveModel getAllCompanyResponse(@PathVariable int id) {
        return repo.getCompanyResponse(id);
    }

    @GetMapping("/company/{companyId}")
    private List<CompanyRequestCollectiveByTnders> getCompanyResponseByCompany(@PathVariable int companyId) {
        return repo.getCompanyResponseByCompany(companyId);
    }

    @GetMapping("/tender/{tenderId}")
    private List<CompanyRequestCollectiveByTnders> getCompanyResponseByTender(@PathVariable int tenderId) {
        return repo.getCompanyResponseByTender(tenderId);
    }

    @PutMapping("/")
    public ResponseEntity<ObjectNode> updateRequestToCollective(@RequestBody @Valid CompanyRequestCollectiveModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "validation errors");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
        int res = repo.updateCompanyResponses(model.getResponse_takataf_company_id(),
                model.getResponse_takataf_request_id(), model.getResponsed_cost(), model.getResponse_desc());

        if (res == 1) {
            ObjectNode node = mapper.createObjectNode();
            node.put("company_id", model.getResponse_takataf_company_id());
            node.put("tender_id", model.getResponse_takataf_request_id());
            node.put("cost", model.getResponsed_cost());
            node.put("response_desc", model.getResponse_desc());
            return ResponseEntity.status(HttpStatus.OK).body(node);
        } else if (res == -1000) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "has no offer to this tender");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        } else if (res == -100) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "no tender for this this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }

    }


    @PostMapping("/add")
    public ResponseEntity<ObjectNode> addOrUpdateRequestToCollective(@RequestBody @Valid CompanyRequestCollectiveModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "validation errors");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }

        if (repo.isExistTender(model.getResponse_takataf_request_id())) {
            if (repo.isExistOffer(model.getResponse_takataf_company_id(), model.getResponse_takataf_request_id())) {

                int res = repo.updateCompanyResponses(model.getResponse_takataf_company_id(), model.getResponse_takataf_request_id(),
                        model.getResponsed_cost(), model.getResponse_desc());
                if (res == 1) {
                    ObjectNode node = mapper.createObjectNode();
                    node.put("company_id", model.getResponse_takataf_company_id());
                    node.put("tender_id", model.getResponse_takataf_request_id());
                    node.put("cost", model.getResponsed_cost());
                    node.put("response_desc", model.getResponse_desc());
                    return ResponseEntity.status(HttpStatus.OK).body(node);
                } else if (res == -1000) {
                    ObjectNode node = mapper.createObjectNode();
                    node.put(STATUS, 400);
                    node.put(MESSAGE, "has no offer to this tender");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
                } else if (res == -100) {
                    ObjectNode node = mapper.createObjectNode();
                    node.put(STATUS, 400);
                    node.put(MESSAGE, "no tender for this this id");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
                } else {
                    ObjectNode node = mapper.createObjectNode();
                    node.put(STATUS, 400);
                    node.put(MESSAGE, "Failed");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
                }
            } else {
                int res = repo.addRequestToCollective(model.getResponse_takataf_company_id(), model.getResponse_takataf_request_id(),
                        model.getResponsed_cost(), model.getResponse_desc());

                if (res == 1) {
                    ObjectNode node = mapper.createObjectNode();
                    node.put("company_id", model.getResponse_takataf_company_id());
                    node.put("tender_id", model.getResponse_takataf_request_id());
                    node.put("cost", model.getResponsed_cost());
                    node.put("response_desc", model.getResponse_desc());
                    return ResponseEntity.status(HttpStatus.OK).body(node);
                } else {
                    ObjectNode node = mapper.createObjectNode();
                    node.put(STATUS, 400);
                    node.put(MESSAGE, "Failed");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
                }
            }
        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "no tender for this this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }

    }

}
