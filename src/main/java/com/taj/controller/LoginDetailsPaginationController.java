package com.taj.controller;

import com.taj.entity.*;
import com.taj.model.LoginDetailsModel;
import com.taj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
@RequestMapping("/admin")
@RestController
@CrossOrigin
public class LoginDetailsPaginationController {

    @Autowired
    LoginDetailsService userService;


    @GetMapping(value = "/login/details/getAll",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<LoginDetailsEntity> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "10") int limit) {

        List<LoginDetailsEntity> returnValue = new ArrayList<>();
        returnValue = userService.getDetails(page, limit);
        // other code here
        return returnValue;
    }


    @Autowired
    SchoolSeeOfferService seeOfferService;


    @GetMapping(value = "/get/schools/see/requests",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<SchoolSeeOfferEntity> getSchoolSeeOffer(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "limit", defaultValue = "10") int limit) {

        List<SchoolSeeOfferEntity> returnValue = new ArrayList<>();
        returnValue = seeOfferService.getDetails(page, limit);
        // other code here
        return returnValue;
    }

    @Autowired
    SchoolRequestOfferService requestOfferService;


    @GetMapping(value = "/get/school/request/offer",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<SchoolRequestOfferEntity> getSchoolRequestOffer(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "limit", defaultValue = "10") int limit) {

        List<SchoolRequestOfferEntity> returnValue = new ArrayList<>();
        returnValue = requestOfferService.getSchools(page, limit);
        // other code here
        return returnValue;
    }

    @Autowired
    CompanySeeRequestService companySeeRequestService;

    @GetMapping(value = "/get/company/see/request",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<CompanySeeRequestEntity> getCompanySeeRequest(@RequestParam(value = "page", defaultValue = "0") int page,
                                                              @RequestParam(value = "limit", defaultValue = "10") int limit) {

        List<CompanySeeRequestEntity> returnValue = new ArrayList<>();
        returnValue = companySeeRequestService.getCompanies(page, limit);
        // other code here
        return returnValue;
    }

    @Autowired
    CompanyResponseRequestService companyResponseRequestService;

    @GetMapping(value = "/get/company/response/request",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<CompanyResponseRequestEntity> getCompanyResponseRequest(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                        @RequestParam(value = "limit", defaultValue = "10") int limit) {

        List<CompanyResponseRequestEntity> returnValue = new ArrayList<>();
        returnValue = companyResponseRequestService.getCompanies(page, limit);
        // other code here
        return returnValue;
    }

    @Autowired
    SchoolSeeTenderService schoolSeeTenderService;
    @GetMapping(value = "/get/school/see/tender",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<SchoolSeeTenderEntity> getSchoolSeeTender(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                        @RequestParam(value = "limit", defaultValue = "10") int limit) {

        List<SchoolSeeTenderEntity> returnValue = new ArrayList<>();
        returnValue = schoolSeeTenderService.getSchools(page, limit);
        // other code here
        return returnValue;
    }

    @Autowired
    SchoolRequestTenderService schoolRequestTenderService;
    @GetMapping(value = "/get/school/request/tender",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<SchoolRequestTenderEntity> getSchoolRequestTender(@RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "limit", defaultValue = "10") int limit) {

        List<SchoolRequestTenderEntity> returnValue = new ArrayList<>();
        returnValue = schoolRequestTenderService.getSchools(page, limit);
        // other code here
        return returnValue;
    }
}
