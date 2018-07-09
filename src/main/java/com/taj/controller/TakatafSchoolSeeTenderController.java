package com.taj.controller;

import com.taj.model.TakatafSchoolSeeTenderModel;
import com.taj.repository.TakatafSchoolSeeTenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@RequestMapping("/tenderSeen")
@RestController
@CrossOrigin
public class TakatafSchoolSeeTenderController {

    @Autowired
    TakatafSchoolSeeTenderRepo repo;

    @PostMapping("/add")
    public int addTenderSeen(@RequestBody TakatafSchoolSeeTenderModel model){
        return repo.addSeen(model.getSeen_id(), model.getSeen_tender_id(), model.getSeen_school_id());
    }

    @GetMapping("/getAll")
    public List<TakatafSchoolSeeTenderModel> getTendersSeen(){
        return repo.getTendersSeen();
    }
    @GetMapping("/get/{id}")
    public TakatafSchoolSeeTenderModel getTendersSeen(@PathVariable int id){
        return repo.getTenderSeen(id);
    }

    @GetMapping("/getSchool/{schoolId}")
    public List<TakatafSchoolSeeTenderModel> getTendersSeenBySchool(@PathVariable int schoolId){
        return repo.getTendersSeenBySchool(schoolId);
    }

    @GetMapping("/getTender/{tenderId}")
    public List<TakatafSchoolSeeTenderModel> getTendersSeenByTender(@PathVariable int tenderId){
        return repo.getTendersSeenByTender(tenderId);
    }

    @PutMapping("/update")
    public int updateTendersSeen(@RequestBody TakatafSchoolSeeTenderModel model){
        return repo.updateTendersSeen(model.getSeen_id(), model.getSeen_tender_id(), model.getSeen_school_id());
    }
    @PutMapping("/delete/{id}")
    public int deleteTendersSeen(@PathVariable int id){
        return repo.deleteTendersSeen(id);
    }
}
