package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.*;
import com.taj.repository.TakatafTenderRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@RequestMapping("/tender/request")
@RestController
@CrossOrigin
public class TakatafTenderRequestController {

    @Autowired
    TakatafTenderRequestRepo repo;
    @Autowired
    ObjectMapper mapper;

    @PostMapping("/add")
    public ResponseEntity<ObjectNode> addTenderRequest(@Valid @RequestBody TakatafTenderRequestModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        int res = repo.add$Request(model.getRequest_school_id(), model.getRequest_tender_id(), model.getIs_aproved(), model.getDate(), model.getCategory());
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("request_id", model.getRequest_id());
            objectNode.put("request_school_id", model.getRequest_school_id());
            objectNode.put("request_tender_id", model.getRequest_tender_id());
            objectNode.put("is_aproved", model.getIs_aproved());
            objectNode.put("date", model.getDate());

            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else if (res == -1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "you already request this");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

    @GetMapping("/")
    public List<TakatafSinfleSchoolRequestDTO> getAllRequestsWithNAme() {
        return repo.getAllRequestsWithNAme();
    }

    @GetMapping("/{id}")
    public GetSingCollectiveTenderById getAllRequestsWithNameById(@PathVariable int id) {
        List<TakatafSinfleSchoolRequestDTO> tenderList = repo.getAllRequestsWithNameByTender(id);

        GetSingleCollectiveByIdPartOneDTO data = new GetSingleCollectiveByIdPartOneDTO(tenderList.get(0).getTender_id(), tenderList.get(0).getTender_title(),
                tenderList.get(0).getTender_explain(), tenderList.get(0).getTender_display_date(),
                tenderList.get(0).getTender_expire_date(), tenderList.get(0).getResponse_count());
        List<GetSingleCollectiveByIdPartTwoDTO> schools = new ArrayList<>();
        List<TenderCollectiveByIdPart3DTO> categories = new ArrayList<>();
        if (tenderList.get(0).getResponse_count() > 0) {


            TenderCollectiveByIdPart3DTO category = new TenderCollectiveByIdPart3DTO(tenderList.get(0).getId(),
                    tenderList.get(0).getCategory_name(), tenderList.get(0).getCount());
            categories.add(category);
            int i = 1;
            while (i < tenderList.size()) {
                if (tenderList.get(i).getTender_id() == tenderList.get(i - 1).getTender_id()) {
                    TenderCollectiveByIdPart3DTO categorys = new TenderCollectiveByIdPart3DTO(tenderList.get(i).getId(),
                            tenderList.get(i).getCategory_name(), tenderList.get(i).getCount());
                } else {
                    GetSingleCollectiveByIdPartTwoDTO school = new GetSingleCollectiveByIdPartTwoDTO
                            (tenderList.get(i).getSchool_name(), tenderList.get(i).getSchool_logo_image(), categories);
                }
                i++;
            }


        }


        GetSingCollectiveTenderById tener = new GetSingCollectiveTenderById(data, schools);
        return tener;
    }

    @GetMapping("/get/{id}")
    public GetTakatafTenderForScoolRequestDTO getRequestWithNameById(@PathVariable int id) {
        List<TakatafSingleSchoolRequestByIDDTO> tenderList = repo.getAllRequestsWithNameByIdzs(id);

        GetSingleCollectiveByIdPartOneDTO data = new GetSingleCollectiveByIdPartOneDTO(tenderList.get(0).getTender_id(), tenderList.get(0).getTender_title(),
                tenderList.get(0).getTender_explain(), tenderList.get(0).getTender_display_date(),
                tenderList.get(0).getTender_expire_date(), tenderList.get(0).getResponse_count());
        List<GetGetTakatafTenderSchollPrt2DTO> categories = new ArrayList<>();

        for (TakatafSingleSchoolRequestByIDDTO obj : tenderList) {
            GetGetTakatafTenderSchollPrt2DTO category = new GetGetTakatafTenderSchollPrt2DTO(obj.getId(), obj.getCategory_name());
            categories.add(category);
        }
        GetTakatafTenderForScoolRequestDTO tener = new GetTakatafTenderForScoolRequestDTO(data, categories);
        return tener;
    }

//    @GetMapping("/getAll")
//    public List<TakatafTenderRequestModel> getTendersSeen(){
//        return repo.getTenderRequests();
//    }
//    @GetMapping("/get/{id}")
//    public TakatafTenderRequestModel getTenderRequests(@PathVariable int id){
//        return repo.getTenderRequest(id);
//    }
//
//    @GetMapping("/getSchool/{schoolId}")
//    public List<TakatafTenderRequestModel> getTenderRequestsBySchool(@PathVariable int schoolId){
//        return repo.getTenderRequestsBySchool(schoolId);
//    }
//
//    @GetMapping("/getTender/{tenderId}")
//    public List<TakatafTenderRequestModel> getTenderRequestsByTender(@PathVariable int tenderId){
//        return repo.getTenderRequestsByTender(tenderId);
//    }
//
//    @GetMapping("/getApproval/{aproveId}")
//    public List<TakatafTenderRequestModel> getTenderRequestsByAprove(@PathVariable int aproveId){
//        return repo.getTenderRequestsByAprove(aproveId);
//    }
//
//    @PutMapping("/accept/{id}")
//    public int acceptTenderRequest(@PathVariable int id){
//        return repo.acceptTenderRequest(id);
//    }
//    @PutMapping("/refuse/{id}")
//    public int refuseTenderRequest(@PathVariable int id){
//        return repo.refuseTenderRequest(id);
//    }
//
//    @PutMapping("/update")
//    public ObjectNode updateTenderRequest(@Valid @RequestBody TakatafTenderRequestModel model, Errors errors){
//        if (errors.hasErrors()){
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("state", 400);
//            objectNode.put("message", "Validation Failed");
//            objectNode.put("details", errors.getAllErrors().toString());
//            return objectNode;
//        }
//        int res = repo.updateTenderRequest(model.getRequest_id(), model.getRequest_school_id(), model.getRequest_tender_id(), model.getIs_aproved(), model.getDate());
//        if (res == 1){
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("request_id", model.getRequest_id());
//            objectNode.put("request_school_id", model.getRequest_school_id());
//            objectNode.put("request_tender_id", model.getRequest_tender_id());
//            objectNode.put("is_aproved", model.getIs_aproved());
//            objectNode.put("date", model.getDate());
//
//            return objectNode;
//        }else {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("value", "not success");
//
//            return objectNode;
//        }
//    }
//    @PutMapping("/delete/{id}")
//    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
//    public ObjectNode deleteTenderRequest(@PathVariable int id){
//        int res = repo.deleteTenderRequest(id);
//        if (res == 1){
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("value", "success");
//
//            return objectNode;
//        }else {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("value", "not success");
//
//            return objectNode;
//        }
//    }

}
