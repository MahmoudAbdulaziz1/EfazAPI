package com.taj.controller;

import com.taj.model.TakatafFirstPriceModel;
import com.taj.repository.TakatafFirstPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

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
    public int addPrice(@RequestBody TakatafFirstPriceModel model) {
        return repo.addTkatafFirstPrice(model.getF_from(), model.getF_to(), model.getF_price());

    }

    /**
     * @param model update current category
     */

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int updatePrice(@RequestBody TakatafFirstPriceModel model) {
        return repo.updateTkatafFirstPrice(model.getF_id(), model.getF_from(), model.getF_to(), model.getF_price());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/delete")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int deletePrice(@RequestBody TakatafFirstPriceModel model) {
        return repo.deleteTkatafFirstPrice(model.getF_id());

    }

}
