package com.taj.controller;

import com.taj.model.TakatafSchoolSeeTenderModel;
import com.taj.model.TakatafTenderRequestModel;
import com.taj.repository.TakatafSchoolSeeTenderRepo;
import com.taj.repository.TakatafTenderRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@RequestMapping("/tenderRequest")
@RestController
@CrossOrigin
public class TakatafTenderRequestController {

    @Autowired
    TakatafTenderRequestRepo repo;

    @PostMapping("/add")
    public int addTenderRequest(@RequestBody TakatafTenderRequestModel model){
        return repo.add$Request(model.getRequest_id(), model.getRequest_school_id(), model.getRequest_tender_id(), model.getIs_aproved());
    }

    @GetMapping("/getAll")
    public List<TakatafTenderRequestModel> getTendersSeen(){
        return repo.getTenderRequests();
    }
    @GetMapping("/get/{id}")
    public TakatafTenderRequestModel getTenderRequests(@PathVariable int id){
        return repo.getTenderRequest(id);
    }

    @GetMapping("/getSchool/{schoolId}")
    public List<TakatafTenderRequestModel> getTenderRequestsBySchool(@PathVariable int schoolId){
        return repo.getTenderRequestsBySchool(schoolId);
    }

    @GetMapping("/getTender/{tenderId}")
    public List<TakatafTenderRequestModel> getTenderRequestsByTender(@PathVariable int tenderId){
        return repo.getTenderRequestsByTender(tenderId);
    }

    @GetMapping("/getAprove/{aproveId}")
    public List<TakatafTenderRequestModel> getTenderRequestsByAprove(@PathVariable int aproveId){
        return repo.getTenderRequestsByAprove(aproveId);
    }

    @PutMapping("/accept/{id}")
    public int acceptTenderRequest(@PathVariable int id){
        return repo.acceptTenderRequest(id);
    }
    @PutMapping("/refuse/{id}")
    public int refuseTenderRequest(@PathVariable int id){
        return repo.refuseTenderRequest(id);
    }

    @PutMapping("/update")
    public int updateTenderRequest(@RequestBody TakatafTenderRequestModel model){
        return repo.updateTenderRequest(model.getRequest_id(), model.getRequest_school_id(), model.getRequest_tender_id(), model.getIs_aproved());
    }
    @PutMapping("/delete/{id}")
    public int deleteTenderRequest(@PathVariable int id){
        return repo.deleteTenderRequest(id);
    }

}
