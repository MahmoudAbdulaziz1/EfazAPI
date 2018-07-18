package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.TakatafFirstPriceModel;
import com.taj.repository.TakatafFirstPriceRepo;
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
@RequestMapping("/evvaz/takataf/first")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TakatafFirstPriceController {


    @Autowired
    TakatafFirstPriceRepo repo;

    @Autowired
    ObjectMapper mapper;

    /**
     * @return list of company categories
     */

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafFirstPriceModel> getAll() {
        return repo.getTkatafFirstPrices();
    }

    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public TakatafFirstPriceModel getPrice(@PathVariable int id) {
        return repo.getTkatafFirstPrice(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode addPrice(@Valid @RequestBody TakatafFirstPriceModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.addTkatafFirstPrice(model.getF_from(), model.getF_to(), model.getF_price());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("f_from", model.getF_from());
            objectNode.put("f_to", model.getF_to());
            objectNode.put("f_price", model.getF_price());

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
    public ObjectNode updatePrice(@Valid @RequestBody TakatafFirstPriceModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }

        int res = repo.updateTkatafFirstPrice(model.getF_id(), model.getF_from(), model.getF_to(), model.getF_price());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("f_id", model.getF_id());
            objectNode.put("f_from", model.getF_from());
            objectNode.put("f_to", model.getF_to());
            objectNode.put("f_price", model.getF_price());

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
    public ObjectNode deletePrice(@Valid @RequestBody TakatafFirstPriceModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.deleteTkatafFirstPrice(model.getF_id());

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
