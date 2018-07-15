package com.taj.controller;

import com.taj.model.TakatafSchoolSeeTenderModel;
import com.taj.model.TakatafTenderRequestModel;
import com.taj.repository.TakatafSchoolSeeTenderRepo;
import com.taj.repository.TakatafTenderRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@RequestMapping("/evvaz/tender/request")
@RestController
@CrossOrigin
public class TakatafTenderRequestController {

    @Autowired
    TakatafTenderRequestRepo repo;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int addTenderRequest(@RequestBody TakatafTenderRequestModel model){
        return repo.add$Request(model.getRequest_id(), model.getRequest_school_id(), model.getRequest_tender_id(), model.getIs_aproved());
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderRequestModel> getTendersSeen(){
        return repo.getTenderRequests();
    }
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public TakatafTenderRequestModel getTenderRequests(@PathVariable int id){
        return repo.getTenderRequest(id);
    }

    @GetMapping("/getSchool/{schoolId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderRequestModel> getTenderRequestsBySchool(@PathVariable int schoolId){
        return repo.getTenderRequestsBySchool(schoolId);
    }

    @GetMapping("/getTender/{tenderId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderRequestModel> getTenderRequestsByTender(@PathVariable int tenderId){
        return repo.getTenderRequestsByTender(tenderId);
    }

    @GetMapping("/getApproval/{aproveId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderRequestModel> getTenderRequestsByAprove(@PathVariable int aproveId){
        return repo.getTenderRequestsByAprove(aproveId);
    }

    @PutMapping("/accept/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int acceptTenderRequest(@PathVariable int id){
        return repo.acceptTenderRequest(id);
    }
    @PutMapping("/refuse/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int refuseTenderRequest(@PathVariable int id){
        return repo.refuseTenderRequest(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int updateTenderRequest(@RequestBody TakatafTenderRequestModel model){
        return repo.updateTenderRequest(model.getRequest_id(), model.getRequest_school_id(), model.getRequest_tender_id(), model.getIs_aproved());
    }
    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int deleteTenderRequest(@PathVariable int id){
        return repo.deleteTenderRequest(id);
    }

}
