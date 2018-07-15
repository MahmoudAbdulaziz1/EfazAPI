package com.taj.controller;

import com.taj.model.TakatafTenderModel;
import com.taj.repository.TakatafTenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@RequestMapping("/evvaz/takataf/tender")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TakatafTenderController {

    @Autowired
    TakatafTenderRepo repo;

    /**
     * @param model add company category to database
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public void addTender(@RequestBody TakatafTenderModel model) {
        repo.addTender(model.getTender_id(), model.getTender_logo(), model.getTender_title(), model.getTender_explain(), model.getTender_display_date(), model.getTender_expire_date(),
                model.getTender_deliver_date(), model.getTender_company_id(), model.getTender_is_confirmed(), model.getTender_is_available(), model.getTender_f_id(),
                model.getTender_s_id(), model.getTender_t_id(), model.getTender_cat_id());

    }


    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderModel> getTenders() {
        return repo.getTenders();

    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public TakatafTenderModel getTender(@PathVariable int id) {
        return repo.getTender(id);
    }

    @GetMapping("/getCompany/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderModel> getTenderByCompany(@PathVariable int id) {
        return repo.getTenderByCompany(id);
    }

    @GetMapping("/getCategory/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderModel> getTenderByCategory(@PathVariable int id) {
        return repo.getTenderByCategory(id);
    }

    @GetMapping("/getAvailable/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderModel> getTenderByIsAvailable(@PathVariable int id) {
        return repo.getTenderByIsAvailable(id);
    }

    @GetMapping("/getConfirm/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderModel> getTenderByIsConfirm(@PathVariable int id) {
        return repo.getTenderByIsConfirm(id);
    }


    @GetMapping("/getTitle/{title}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderModel> filterTenderByTitle(@PathVariable String title) {
        return repo.getTenderByTitle(title);
    }

    @GetMapping("/getExplain/{explain}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderModel> filterTenderByExplain(@PathVariable String explain) {
        return repo.getTenderByExplain(explain);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int updateTender(@RequestBody TakatafTenderModel model) {
        return repo.updateTender(model.getTender_id(), model.getTender_logo(), model.getTender_title(), model.getTender_explain(),
                model.getTender_display_date(), model.getTender_expire_date(), model.getTender_deliver_date(), model.getTender_company_id(),
                model.getTender_is_confirmed(), model.getTender_is_available(), model.getTender_f_id(), model.getTender_s_id(), model.getTender_t_id(),
                model.getTender_cat_id());
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int deleteTender(@PathVariable int id){
        return repo.deleteTender(id);
    }


}
