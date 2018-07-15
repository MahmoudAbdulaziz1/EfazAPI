package com.taj.controller;

import com.taj.model.TakatafSecondPriceModel;
import com.taj.model.TakatafThirdPriceModel;
import com.taj.repository.TakatafThirdPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

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
    public void addCategory(@RequestBody TakatafThirdPriceModel model) {
        repo.addTkatafThirdPrice(model.getT_from(), model.getT_to(), model.getT_price());

    }

    /**
     * @param model update current category
     */

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public void updateCategory(@RequestBody TakatafThirdPriceModel model) {
        repo.updateTkatafSThirdPrice(model.getT_id(), model.getT_from(), model.getT_to(), model.getT_price());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/delete")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public void deleteCategory(@RequestBody TakatafThirdPriceModel model) {
        repo.deleteTkatafThirdPrice(model.getT_id());

    }

}
