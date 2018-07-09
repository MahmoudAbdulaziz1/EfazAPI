package com.taj.controller;

import com.taj.model.TakatafTenderModel;
import com.taj.repository.TakatafTenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@RequestMapping("/takatafTender")
@RestController
@CrossOrigin
public class TakatafTenderController {

    @Autowired
    TakatafTenderRepo repo;

    /**
     * @param model add company category to database
     */
    @PostMapping("/add")
    public void addTender(@RequestBody TakatafTenderModel model) {
        repo.addTender(model.getTender_id(), model.getTender_logo(), model.getTender_title(), model.getTender_explain(), model.getTender_display_date(), model.getTender_expire_date(),
                model.getTender_deliver_date(), model.getTender_company_id(), model.getTender_is_confirmed(), model.getTender_is_available(), model.getTender_f_id(),
                model.getTender_s_id(), model.getTender_t_id(), model.getTender_cat_id());

    }


    @GetMapping("/getAll")
    public List<TakatafTenderModel> getTenders() {
        return repo.getTenders();

    }

    @GetMapping("/get/{id}")
    public TakatafTenderModel getTender(@PathVariable int id) {
        return repo.getTender(id);
    }

    @GetMapping("/getCompany/{id}")
    public List<TakatafTenderModel> getTenderByCompany(@PathVariable int id) {
        return repo.getTenderByCompany(id);
    }

    @GetMapping("/getCategory/{id}")
    public List<TakatafTenderModel> getTenderByCategory(@PathVariable int id) {
        return repo.getTenderByCategory(id);
    }

    @GetMapping("/getAvailable/{id}")
    public List<TakatafTenderModel> getTenderByIsAvailable(@PathVariable int id) {
        return repo.getTenderByIsAvailable(id);
    }

    @GetMapping("/getConfirm/{id}")
    public List<TakatafTenderModel> getTenderByIsConfirm(@PathVariable int id) {
        return repo.getTenderByIsConfirm(id);
    }


    @GetMapping("/getTitle/{title}")
    public List<TakatafTenderModel> filterTenderByTitle(@PathVariable String title) {
        return repo.getTenderByTitle(title);
    }

    @GetMapping("/getExplain/{explain}")
    public List<TakatafTenderModel> filterTenderByExplain(@PathVariable String explain) {
        return repo.getTenderByExplain(explain);
    }

    @PutMapping("/update")
    public int updateTender(@RequestBody TakatafTenderModel model) {
        return repo.updateTender(model.getTender_id(), model.getTender_logo(), model.getTender_title(), model.getTender_explain(),
                model.getTender_display_date(), model.getTender_expire_date(), model.getTender_deliver_date(), model.getTender_company_id(),
                model.getTender_is_confirmed(), model.getTender_is_available(), model.getTender_f_id(), model.getTender_s_id(), model.getTender_t_id(),
                model.getTender_cat_id());
    }

    @PutMapping("/delete/{id}")
    public int deleteTender(@PathVariable int id){
        return repo.deleteTender(id);
    }


}
