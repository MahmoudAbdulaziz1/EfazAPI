package com.taj.controller;

import com.taj.model.TakatafSchoolSeeTenderModel;
import com.taj.repository.TakatafSchoolSeeTenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@RequestMapping("/evvaz/tender/seen")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TakatafSchoolSeeTenderController {

    @Autowired
    TakatafSchoolSeeTenderRepo repo;

    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    @PostMapping("/add")
    public int addTenderSeen(@RequestBody TakatafSchoolSeeTenderModel model){
        return repo.addSeen(model.getSeen_id(), model.getSeen_tender_id(), model.getSeen_school_id());
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafSchoolSeeTenderModel> getTendersSeen(){
        return repo.getTendersSeen();
    }

    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/get/{id}")
    public TakatafSchoolSeeTenderModel getTendersSeen(@PathVariable int id){
        return repo.getTenderSeen(id);
    }

    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/getSchool/{schoolId}")
    public List<TakatafSchoolSeeTenderModel> getTendersSeenBySchool(@PathVariable int schoolId){
        return repo.getTendersSeenBySchool(schoolId);
    }

    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/getTender/{tenderId}")
    public List<TakatafSchoolSeeTenderModel> getTendersSeenByTender(@PathVariable int tenderId){
        return repo.getTendersSeenByTender(tenderId);
    }

    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @PutMapping("/update")
    public int updateTendersSeen(@RequestBody TakatafSchoolSeeTenderModel model){
        return repo.updateTendersSeen(model.getSeen_id(), model.getSeen_tender_id(), model.getSeen_school_id());
    }

    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @PutMapping("/delete/{id}")
    public int deleteTendersSeen(@PathVariable int id){
        return repo.deleteTendersSeen(id);
    }
}
