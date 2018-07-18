package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.TakatafTenderModel;
import com.taj.repository.TakatafTenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    ObjectMapper mapper;

    /**
     * @param model add company category to database
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode addTender(@Valid @RequestBody TakatafTenderModel model, Errors errors) {
        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res =  repo.addTender(model.getTender_logo(), model.getTender_title(), model.getTender_explain(), model.getTender_display_date(), model.getTender_expire_date(),
                model.getTender_deliver_date(), model.getTender_company_id(), model.getTender_is_confirmed(), model.getTender_is_available(), model.getTender_f_id(),
                model.getTender_s_id(), model.getTender_t_id(), model.getTender_cat_id());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("tender_id", model.getTender_id());
            objectNode.put("tender_logo", model.getTender_logo());
            objectNode.put("tender_title", model.getTender_title());
            objectNode.put("tender_explain", model.getTender_explain());
            objectNode.put("tender_display_date", model.getTender_display_date().toString());
            objectNode.put("tender_expire_date", model.getTender_expire_date().toString());
            objectNode.put("tender_deliver_date", model.getTender_deliver_date().toString());
            objectNode.put("tender_company_id", model.getTender_company_id());
            objectNode.put("tender_is_confirmed", model.getTender_is_confirmed());
            objectNode.put("tender_is_available", model.getTender_is_available());
            objectNode.put("tender_f_id", model.getTender_f_id());
            objectNode.put("tender_s_id", model.getTender_s_id());
            objectNode.put("tender_t_id", model.getTender_t_id());
            objectNode.put("tender_cat_id", model.getTender_cat_id());
            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

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
    public ObjectNode updateTender(@Valid @RequestBody TakatafTenderModel model, Errors errors) {
        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateTender(model.getTender_id(), model.getTender_logo(), model.getTender_title(), model.getTender_explain(),
                model.getTender_display_date(), model.getTender_expire_date(), model.getTender_deliver_date(), model.getTender_company_id(),
                model.getTender_is_confirmed(), model.getTender_is_available(), model.getTender_f_id(), model.getTender_s_id(), model.getTender_t_id(),
                model.getTender_cat_id());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("tender_id", model.getTender_id());
            objectNode.put("tender_logo", model.getTender_logo());
            objectNode.put("tender_title", model.getTender_title());
            objectNode.put("tender_explain", model.getTender_explain());
            objectNode.put("tender_display_date", model.getTender_display_date().toString());
            objectNode.put("tender_expire_date", model.getTender_expire_date().toString());
            objectNode.put("tender_deliver_date", model.getTender_deliver_date().toString());
            objectNode.put("tender_company_id", model.getTender_company_id());
            objectNode.put("tender_is_confirmed", model.getTender_is_confirmed());
            objectNode.put("tender_is_available", model.getTender_is_available());
            objectNode.put("tender_f_id", model.getTender_f_id());
            objectNode.put("tender_s_id", model.getTender_s_id());
            objectNode.put("tender_t_id", model.getTender_t_id());
            objectNode.put("tender_cat_id", model.getTender_cat_id());
            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode deleteTender(@PathVariable int id){
        int res = repo.deleteTender(id);
        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }


}
