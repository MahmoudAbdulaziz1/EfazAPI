package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.*;
import com.taj.repository.SchoolRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
@RequestMapping("/school/requests")
@RestController
@CrossOrigin
public class SchoolRequestController {

    @Autowired
    SchoolRequestRepo repo;
    @Autowired
    ObjectMapper mapper;

//    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PostMapping("/addRequest")
    public JsonNode addSchoolRequest(@Valid @RequestBody SchoolRequestsModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }

        int res = repo.addRequest(model.getRequest_details_file(), model.getImages_id(), model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_display_date(), model.getRequest_expired_date(), model.getRequest_deliver_date(), model.getRequest_payment_date(),
                model.getRequest_is_available(), model.getRequest_is_conformied(), model.getSchool_id(), model.getRequest_category_id(),
                model.getReceive_palce_id(), model.getExtended_payment(), model.getRequest_count());
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("request_id", model.getRequest_id());
            objectNode.put("request_details_file", model.getRequest_details_file());
            //objectNode.put("images_id", model.getImages_id());
            objectNode.put("request_title", model.getRequest_title());
            objectNode.put("request_explaination", model.getRequest_explaination());
            objectNode.put("request_display_date", model.getRequest_display_date());
            objectNode.put("request_expired_date", model.getRequest_expired_date());
            objectNode.put("request_deliver_date", model.getRequest_deliver_date());
            objectNode.put("request_payment_date", model.getRequest_payment_date());
            objectNode.put("request_is_available", model.getRequest_is_available());
            objectNode.put("request_is_conformied", model.getRequest_is_conformied());
            objectNode.put("school_id", model.getSchool_id());
            objectNode.put("request_category_id", model.getRequest_category_id());
            objectNode.put("receive_palce_id", model.getReceive_palce_id());
            objectNode.put("extended_payment", model.getExtended_payment());
            objectNode.put("request_count", model.getRequest_count());
            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @GetMapping("/getRequests")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestsModel> getSchoolSingleRequest() {
        return repo.getRequests();
    }

    @GetMapping("/getSchoolRequests/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestsModel> getSchoolRequests(@PathVariable int id) {
        return repo.getRequestsByID(id);
    }

    @GetMapping("/getRequest/{id}")
   // @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public SchoolRequestsModel getSchoolRequest(@PathVariable int id) {
        return repo.getRequestByID(id);
    }

    @PutMapping("/updateRequest")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public JsonNode updateRequestModel(@Valid @RequestBody SchoolRequestsModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateRequest(model.getRequest_id(), model.getRequest_details_file(), model.getImages_id(), model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_display_date(), model.getRequest_expired_date(), model.getRequest_deliver_date(), model.getRequest_payment_date(),
                model.getRequest_is_available(), model.getRequest_is_conformied(), model.getSchool_id(), model.getRequest_category_id(),
                model.getReceive_palce_id(), model.getExtended_payment(), model.getRequest_count());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("request_id", model.getRequest_id());
            objectNode.put("request_details_file", model.getRequest_details_file());
            objectNode.put("images_id", model.getImages_id());
            objectNode.put("request_title", model.getRequest_title());
            objectNode.put("request_explaination", model.getRequest_explaination());
            objectNode.put("request_display_date", model.getRequest_display_date());
            objectNode.put("request_expired_date", model.getRequest_expired_date());
            objectNode.put("request_deliver_date", model.getRequest_deliver_date());
            objectNode.put("request_payment_date", model.getRequest_payment_date());
            objectNode.put("request_is_available", model.getRequest_is_available());
            objectNode.put("request_is_conformied", model.getRequest_is_conformied());
            objectNode.put("school_id", model.getSchool_id());
            objectNode.put("request_category_id", model.getRequest_category_id());
            objectNode.put("receive_palce_id", model.getReceive_palce_id());
            objectNode.put("extended_payment", model.getExtended_payment());
            objectNode.put("request_count", model.getRequest_count());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public ObjectNode deleteSchoolRequest(@PathVariable int id) {
        int res = repo.deleteSchoolRequest(id);
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @GetMapping("/filterConfirm/{isConfirmed}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsModel> filterByIsConfirmed(@PathVariable int isConfirmed) {
        return repo.filterByIsConfirmed(isConfirmed);
    }

    @GetMapping("/filterAvailable/{isAvailable}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsModel> filterByIsAvailable(@PathVariable int isAvailable) {
        return repo.filterByIsAvailable(isAvailable);
    }


    @GetMapping("/filterTitle/{title}")
   //@PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsModel> filterByTitle(@PathVariable String title) {
        return repo.filterByTitle(title);
    }

    @GetMapping("/filterExplain/{explain}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsModel> filterByExplain(@PathVariable String explain) {
        return repo.filterByExplain(explain);
    }

    @GetMapping("/filterCat/{cat}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsByIdModel> filterByCategory(@PathVariable int cat) {
        return repo.filterByCategory(cat);
    }

    @GetMapping("/filterPlace/{place}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsModel> filterByReceivePlace(@PathVariable int place) {
        return repo.filterByReceivePlace(place);
    }

    @GetMapping("/get/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestDto> getSchoolRequestBySchoolId(@PathVariable int id) {
        return repo.getRequestsBySchoolId(id);
    }

    @GetMapping("/get/history/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestHistoryDto> getSchoolHistoryRequestBySchoolId(@PathVariable int id) {
        return repo.getHistoryRequestsBySchoolId(id);
    }

    @GetMapping("/get/histories/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public SchoolHistoryAllDto getSchoolHistoryRequestBySchoolIdMulti(@PathVariable int id) {
        List<SchoolRequestHistoryDto> dto = repo.getHistoryRequestsBySchoolId(id);
        SchoolHistoryRequestDTO request = new SchoolHistoryRequestDTO(dto.get(0).getRequest_id(), dto.get(0).getRequest_title(),
                dto.get(0).getRequest_count(), dto.get(0).getRequest_date(), dto.get(0).getResponse_date());
        List<SchoolHistoryResponseDTO> response = new ArrayList<>();
        for (SchoolRequestHistoryDto dtos :dto){
            SchoolHistoryResponseDTO obj = new SchoolHistoryResponseDTO(dtos.getResponsed_cost(),
                    dtos.getResponsed_company_id(), dtos.getResponse_id());
            response.add(obj);
        }
        SchoolHistoryAllDto reesult = new SchoolHistoryAllDto(request, response);
        return  reesult;
    }

    @GetMapping("/get/order/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestHistoryDto> getSchoolOrderRequestBySchoolId(@PathVariable int id) {
        return repo.getOrderRequestsBySchoolId(id);
    }

    @GetMapping("/get/orders/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public SchoolHistoryAllDto getSchoolOrderRequestBySchoolIdMulti(@PathVariable int id) {
        List<SchoolRequestHistoryDto> dto = repo.getOrderRequestsBySchoolId(id);
        SchoolHistoryRequestDTO request = new SchoolHistoryRequestDTO(dto.get(0).getRequest_id(), dto.get(0).getRequest_title(),
                dto.get(0).getRequest_count(), dto.get(0).getRequest_date(), dto.get(0).getResponse_date());
        List<SchoolHistoryResponseDTO> response = new ArrayList<>();
        for (SchoolRequestHistoryDto dtos :dto){
            SchoolHistoryResponseDTO obj = new SchoolHistoryResponseDTO(dtos.getResponsed_cost(),
                    dtos.getResponsed_company_id(), dtos.getResponse_id());
            response.add(obj);
        }
        SchoolHistoryAllDto reesult = new SchoolHistoryAllDto(request, response);
        return  reesult;
    }


}
