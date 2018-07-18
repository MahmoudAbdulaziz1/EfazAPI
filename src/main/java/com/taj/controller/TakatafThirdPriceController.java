package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.TakatafSecondPriceModel;
import com.taj.model.TakatafThirdPriceModel;
import com.taj.repository.TakatafThirdPriceRepo;
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
@RequestMapping("/evvaz/takataf/third")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TakatafThirdPriceController {

    @Autowired
    TakatafThirdPriceRepo repo;

    @Autowired
    ObjectMapper mapper;

    /**
     * @return list of company categories
     */

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafThirdPriceModel> getCategories() {
        return repo.getTkatafThirdPrices();
    }

    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public TakatafThirdPriceModel getCategory(@PathVariable int id) {
        return repo.getTkatafThirdPrice(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode addCategory(@Valid @RequestBody TakatafThirdPriceModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.addTkatafThirdPrice(model.getT_from(), model.getT_to(), model.getT_price());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("f_id", model.getS_id());
            objectNode.put("t_from", model.getT_from());
            objectNode.put("t_to", model.getT_to());
            objectNode.put("t_price", model.getT_price());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }

    /**
     * @param model update current category
     */

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode updateCategory(@Valid @RequestBody TakatafThirdPriceModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateTkatafSThirdPrice(model.getT_id(), model.getT_from(), model.getT_to(), model.getT_price());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("t_id", model.getT_id());
            objectNode.put("t_from", model.getT_from());
            objectNode.put("t_to", model.getT_to());
            objectNode.put("t_price", model.getT_price());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/delete")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode deleteCategory(@Valid @RequestBody TakatafThirdPriceModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.deleteTkatafThirdPrice(model.getT_id());

        if (res ==1) {
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
