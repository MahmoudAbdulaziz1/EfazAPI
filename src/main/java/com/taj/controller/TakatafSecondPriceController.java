package com.taj.controller;

import com.taj.model.TakatafFirstPriceModel;
import com.taj.model.TakatafSecondPriceModel;
import com.taj.repository.TakatafFirstPriceRepo;
import com.taj.repository.TakatafSecondPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@RequestMapping("/evvaz/takataf/second")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TakatafSecondPriceController {

    @Autowired
    TakatafSecondPriceRepo repo;

    /**
     * @return list of company categories
     */

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafSecondPriceModel> getAll() {
        return repo.getTkatafFirstPrices();
    }

    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public TakatafSecondPriceModel getPrice(@PathVariable int id) {
        return repo.getTkatafSecondPrice(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int addPrice(@RequestBody TakatafSecondPriceModel model) {
        return repo.addTkatafSecondPrice(model.getS_from(), model.getS_to(), model.getS_price());

    }

    /**
     * @param model update current category
     */

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int updatePrice(@RequestBody TakatafSecondPriceModel model) {
        return repo.updateTkatafSecondPrice(model.getS_id(), model.getS_from(), model.getS_to(), model.getS_price());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/delete")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int deletePrice(@RequestBody TakatafSecondPriceModel model) {
        return repo.deleteTkatafSecondPrice(model.getS_id());

    }

}
