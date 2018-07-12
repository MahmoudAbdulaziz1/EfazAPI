package com.taj.controller;

import com.taj.model.CompanyOfferModel;
import com.taj.repository.CompanyOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MahmoudAhmed on 6/4/2018.
 */
@RequestMapping("/evvaz/companyOffer")
@RestController
@CrossOrigin
public class CompanyOfferController {
    @Autowired
    CompanyOfferRepo repo;

    /**
     * add offer from company
     *
     * @param model
     * @return 1 if success or 0 if failed
     */

    @PostMapping("/addOffer")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int addCompanyOffer(@RequestBody CompanyOfferModel model) {
        return repo.addCompanyOffer(model.getOffer_logo(), model.getOffer_title(), model.getOffer_explaination(),
                model.getOffer_cost(), model.getOffer_display_date(), model.getOffer_expired_date(), model.getOffer_deliver_date(),
                model.getCompany_id());
    }

    /**
     * @return all offers of all companies
     */

    @GetMapping("/getOffers")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<CompanyOfferModel> getCompanyOffers() {
        return repo.getAllOffers();
    }

    /**
     * get one offer by  offer id
     *
     * @param id
     * @return offer by offer id
     */

    @GetMapping("/getOffer/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public CompanyOfferModel getCompanyOffer(@PathVariable int id) {
        return repo.getCompanyOffer(id);
    }

    /**
     * update offer of company
     *
     * @param model
     * @return 1 if success or 0 if failed
     */
    @PutMapping("/updateOffer")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int updateCompanyOffer(@RequestBody CompanyOfferModel model) {
        return repo.updateCompanyOffer(model.getOffer_id(), model.getOffer_logo(), model.getOffer_title(), model.getOffer_explaination(),
                model.getOffer_cost(), model.getOffer_display_date(), model.getOffer_expired_date(), model.getOffer_deliver_date(),
                model.getCompany_id());
    }

    /**
     * delete offer by offer id
     *
     * @param id
     * @return 1 if success or 0 if failed
     */
    @PutMapping("/deleteOffer/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int deleteCompanyOffer(@PathVariable int id) {
        return repo.deleteCompanyOffer(id);
    }

    /**
     * get one company offers by company id
     *
     * @param id
     * @return list of company offer
     */

    @GetMapping("/getOffers/company/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<CompanyOfferModel> getSingleCompanyOffer(@PathVariable int id) {
        return repo.getCompanyOffers(id);
    }

    /**
     * compute and return num of days, hours, minutes since offer added until now
     * return also offer display date and offer expired date
     * tke offer id
     *
     * @param id
     * @return list(1- )
     */

    @GetMapping("/getData/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<String> getData(@PathVariable int id) {
        return repo.getProgressDate(id);
    }

}