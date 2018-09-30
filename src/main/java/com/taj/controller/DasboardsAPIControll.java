package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.*;
import com.taj.model.offer_description.CustomCompanyModelWithViewAndDescRes;
import com.taj.model.offer_description.CustomeCompanyOfferModel2DTo;
import com.taj.model.offer_description.getCustomeCompanyOffer2;
import com.taj.model.offer_description.getCustomeOffer2;
import com.taj.model.school.request.image.GetCollectiveTenderPartOneDTO2;
import com.taj.model.school.request.image.GetCollectiveTenders2;
import com.taj.model.school.request.image.SchoolNewRequestsDTO2;
import com.taj.model.school.request.image.getSchoolCustomNewRequestById;
import com.taj.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by User on 9/5/2018.
 */
@RequestMapping("/evvaz")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DasboardsAPIControll {


    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String SUCCESS = "success";
    private static final String FAILED = "failed";

    @Autowired
    AdminOrdersRepo adminRepo;
    @Autowired
    TakatafTenderNewRepo repo;
    @Autowired
    CategoryRepo catRepo;
    @Autowired
    RegistrationRepo registrationRepo;

    @Autowired
    NewRegisterRepo newRegisterRepo;
    @Autowired
    TenderRequestRepo tenderRequestRepo;
    @Autowired
    TakatafTenderRequestRepo takatafTenderRequestRepo;

    @Autowired
    CollectiveTenderDetailsForCompanyRepo collectiveTenderDetailsForCompanyRepo;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    CollectiveTenderCompaniesRequestForCompanyRepo collectiveTenderCompaniesRequestForCompanyRepo;
    @Autowired
    AdminOrdersRepo adminOrdersRepo;
    @Autowired
    SchoolFollowCompanyRepo schoolFollowCompanyRepo;
    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    SchoolRequestNewRepo schoolRequestNewRepo;
    @Autowired
    SchoolRequestRepo schoolRequestRepo;
    @Autowired
    SchoolRequestCategoryRepo schoolRequestCategoryRepo;
    @Autowired
    CompanyResponseSchoolRequestRepo companyResponseSchoolRequestRepo;
    @Autowired
    CustomCompanyOfferRepo customCompanyOfferRepo;
    @Autowired
    CompanyOfferRepo companyOfferRepo;
    @Autowired
    private SchoolProfileRepo schoolProfileRepo;

    @Autowired

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("admin/orders/history/")
    public List<AdminHistoryOrdersModel> getAllHistoryOrders() {
        return adminRepo.getAllHistoryOrders();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("takataf/tenders/")
    public ResponseEntity<ObjectNode> addTender(@RequestBody @Valid TakatafTenderNewModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (model.getTender_display_date() < new Timestamp(System.currentTimeMillis()).getTime()
                || model.getTender_expire_date() < new Timestamp(System.currentTimeMillis()).getTime()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed school date in past");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        if (model.getTender_display_date() >= model.getTender_expire_date()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed school display date is greater than  expired date");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (model.getTender_company_expired_date() == 0 || model.getTender_company_expired_date() == 0) {

        } else {

            if (model.getTender_company_display_date() < model.getTender_expire_date()) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 400);
                objectNode.put("message", "Validation Failed company date in past");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }

            if (model.getTender_company_display_date() < new Timestamp(System.currentTimeMillis()).getTime()
                    || model.getTender_company_expired_date() < new Timestamp(System.currentTimeMillis()).getTime()) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 400);
                objectNode.put("message", "Validation Failed company date in past");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }

            if (model.getTender_company_display_date() >= model.getTender_company_expired_date()) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 400);
                objectNode.put("message", "Validation Failed company display date is greater than  expired date");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        }

        int res = repo.addTender(model.getTender_logo(), model.getTender_title(), model.getTender_explain(),
                model.getTender_display_date(), model.getTender_expire_date(), model.getTender_company_display_date(),
                model.getTender_company_expired_date(), model.getCats());


        if (res > 0) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("tender_logo", model.getTender_logo());
            objectNode.put("tender_title", model.getTender_title());
            objectNode.put("tender_explain", model.getTender_explain());
            objectNode.put("tender_display_date", model.getTender_display_date());
            objectNode.put("tender_expire_date", model.getTender_expire_date());
            objectNode.put("tender_company_display_date", model.getTender_company_display_date());
            objectNode.put("tender_company_expire_date", model.getTender_company_expired_date());


            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else if (res == -100) {
            ObjectNode objectNode = mapper.createObjectNode();
            ArrayNode nodes = mapper.createArrayNode();
            List<CategoriesInUse> data = repo.getCategoriesInUse();
            for (int i = 0; i < data.size(); i++) {
                int categorys = repo.getCategoryId(data.get(i).getCategory_name());

                if (categorys > 0) {
                    nodes.add(data.get(i).getCategory_name());
                }
            }
            objectNode.set("cats", nodes);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("takataf/tenders/")
    public List<TakatafTenderPOJO> getTenders() {
        return repo.getTenders();

    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("takataf/tenders/{id}")
    public ResponseEntity<TakatafTenderPOJO> getTender(@PathVariable int id) {
        if (repo.isExist(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(repo.getTender(id));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(repo.getTender(id));
        }


    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("takataf/tenders/admin")
    public ResponseEntity<ArrayNode> getAdminTenders() {
        List<TakatafMyTenderPageDTO> model = repo.getAdminTenders();
        ArrayNode arr = mapper.createArrayNode();
        for (int i=0; i<model.size(); i++){
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put("tender_id",model.get(i).getTender_id());
            nodes.put("tender_title",model.get(i).getTender_title());
            nodes.put("tender_explain",model.get(i).getTender_explain());
            if (model.get(i).getTender_company_display_date()==0){
                String nullvalue =null;
                nodes.put("tender_company_display_date",nullvalue);

            }else {
                nodes.put("tender_company_display_date",model.get(i).getTender_company_display_date());
            }
            if (model.get(i).getTender_company_expired_date()==0){
                String nullvalue =null;
                nodes.put("tender_company_expired_date", nullvalue);

            }else {
                nodes.put("tender_company_expired_date",model.get(i).getTender_company_expired_date());

            }
            nodes.put("tender_display_date",model.get(i).getTender_display_date());
            nodes.put("tender_expire_date",model.get(i).getTender_expire_date());
            nodes.put("response_count",model.get(i).getResponse_count());
            nodes.put("cat_num",model.get(i).getCat_num());
            arr.add(nodes);
        }
        return ResponseEntity.status(HttpStatus.OK).body(arr);

    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("takataf/tenders/")
    public ResponseEntity<ObjectNode> updateTender(@RequestBody TakatafTenderUpdatePOJO model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


        if (model.getTender_display_date() < new Timestamp(System.currentTimeMillis()).getTime()
                || model.getTender_expire_date() < new Timestamp(System.currentTimeMillis()).getTime()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed school date in past");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        if (model.getTender_display_date() >= model.getTender_expire_date()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed school display date is greater than  expired date");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


        if (model.getTender_company_display_date() < new Timestamp(System.currentTimeMillis()).getTime()
                || model.getTender_company_expired_date() < new Timestamp(System.currentTimeMillis()).getTime()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed company date in past");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        if (model.getTender_company_display_date() >= model.getTender_company_expired_date()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed company display date is greater than  expired date");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


        int res = repo.updateTender(model.getTender_id(), model.getTender_logo(), model.getTender_title(),
                model.getTender_explain(), model.getTender_display_date(), model.getTender_expire_date(),
                model.getTender_company_display_date(), model.getTender_company_expired_date(), model.getCats());

        if (res > 0) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("tender_id", model.getTender_id());
            objectNode.put("tender_logo", model.getTender_logo());
            objectNode.put("tender_title", model.getTender_title());
            objectNode.put("tender_explain", model.getTender_explain());
            objectNode.put("tender_display_date", model.getTender_display_date());
            objectNode.put("tender_expire_date", model.getTender_expire_date());
            objectNode.put("tender_company_display_date", model.getTender_company_display_date());
            objectNode.put("tender_company_expire_date", model.getTender_company_expired_date());


            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else if (res == -100) {
            ObjectNode objectNode = mapper.createObjectNode();
            ArrayNode nodes = mapper.createArrayNode();
            List<CategoriesInUse> data = repo.getCategoriesInUse();
            for (int i = 0; i < data.size(); i++) {
                int categorys = repo.getCategoryId(data.get(i).getCategory_name());

                if (categorys > 0) {
                    nodes.add(data.get(i).getCategory_name());
                }
            }
            objectNode.set("cats", nodes);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("takataf/tenders/{id}")
    public int deleteTenderWithItsResponse(@PathVariable int id) {
        return repo.deleteTenderWithItsResponse(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("cat/getAll")
    public List<CategoryModel> getCategories() {
        return catRepo.getCategories();
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/getInActive")
    public List<RegistrationModel> getInActiveCompaines() {
        return registrationRepo.getInActiveCompanies();
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/getInActives")
    public List<NewRegisterModel> getInActiveCompainesNew() {
        return newRegisterRepo.getInActiveCompanies();
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/confirm/{id}")
    public ObjectNode confirmEmail(@PathVariable int id) {
        int res = registrationRepo.confirmEmail(id);

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        } else if (res == -100) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "email with role already exist");

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/confirms/{id}")
    public ObjectNode confirmEmailWithCity(@PathVariable int id) {
        int res = newRegisterRepo.confirmEmail(id);

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/get/{id}")
    public RegistrationModel getUser(@PathVariable int id) {
        return registrationRepo.getUser(id);
    }


//    @PreAuthorize("hasAuthority('admin')")
//    @GetMapping("register/get/{id}")
//    public NewRegisterModel getUser(@PathVariable int id) {
//        return newRegisterRepo.getUser(id);
//    }
//
//


    @PreAuthorize("hasAuthority('admin') OR hasAuthority('school')")
    @GetMapping("request/tender/{id}")
    public TenderRequestTenderModel2 getTenderRequestObject(@PathVariable int id) {
        Map<TenderRequestSchoolModel, List<TenderRequestCategoriesModel>> res = new HashMap<>();
        List<Map<String, Object>> list = tenderRequestRepo.getTenderRequestObjectWithCompanyDates(id);
        List<TenderRequestSchoolModel> schoolsList = new ArrayList<>();
        Set<TenderRequestSchoolModel> schools = new HashSet<>();
        List<TenderRequestCategoriesModel> test2Models = new ArrayList<>();
        for (Map<String, Object> map : list) {
            TenderRequestSchoolModel model = new TenderRequestSchoolModel();
            TenderRequestCategoriesModel test2Model = new TenderRequestCategoriesModel();


            long schoolId = (long) map.get("school_id");
            String schoolName = (String) map.get("school_name");
            byte[] schoolLogo = (byte[]) map.get("school_logo_image");
            Timestamp date = (Timestamp) map.get("t_date");
            long t_date = 0;
            if (date == null) {
                t_date = 0;
            } else {
                t_date = date.getTime();
            }

            //System.out.println(map.get("t_date")+" +++ "+ date+" ++++++ "+ date.getTime()+ " ++++ "+ new Timestamp(0));
            try {
                int categoryId = (int) map.get("id");
                String categoryName = (String) map.get("category_name");
                int count = (int) map.get("count");
                test2Model.setId(categoryId);
                test2Model.setCategory_name(categoryName);
                test2Model.setCount(count);
            } catch (NullPointerException e) {
                test2Model = null;
            }
            try {
                model.setSchool_id(schoolId);
                model.setSchool_name(schoolName);
                model.setSchool_logo_image(Base64.getEncoder().encodeToString(schoolLogo));
                model.setT_date(t_date);
                //String encodedString = Base64.getEncoder().encodeToString(schoolLogo);
                schools.add(model);
            } catch (NullPointerException e) {
                schools.add(null);
            }

            test2Models.add(test2Model);
        }

        try {


            for (TenderRequestSchoolModel obj : schools) {

                List<TenderRequestCategoriesModel> test2ModelArrayList = null;
                test2ModelArrayList = new ArrayList<>();

                int i = 0;
                for (Map<String, Object> map : list) {
//                obj.getCategories().add(test2ModelList);


                    if (res.get(obj) == null) {
                        res.put(obj, new ArrayList<TenderRequestCategoriesModel>());

                    }
//                if (res.containsKey(obj)) {
                    if (map.get("school_id").equals(obj.getSchool_id())) {
                        TenderRequestCategoriesModel test2Model = new TenderRequestCategoriesModel();
                        try {
                            int categoryId = (int) map.get("id");
                            String categoryName = (String) map.get("category_name");
                            int count = (int) map.get("count");


                            test2Model.setId(categoryId);
                            test2Model.setCategory_name(categoryName);
                            test2Model.setCount(count);
                        } catch (NullPointerException e) {
                            test2Model = null;
                        }


                        test2ModelArrayList.add(test2Model);
                        res.get(obj).add(i, test2Model);
                        i++;
                    }

                }

                obj.setCategories(test2ModelArrayList);
                schoolsList.add(obj);
            }
        } catch (NullPointerException e) {
            schoolsList.add(null);
        }

        if (Long.parseLong(list.get(0).get("response_count").toString()) == 0) {
            List<CategoryNameDto> category = takatafTenderRequestRepo.categoryData(id);

            TenderRequestTenderModel2 mainModel = new TenderRequestTenderModel2(Long.parseLong(list.get(0).get("tender_id").toString()),
                    (String) list.get(0).get("tender_title"),
                    (String) list.get(0).get("tender_explain"), ((Timestamp) (list.get(0).get("tender_display_date"))).getTime(),
                    ((Timestamp) list.get(0).get("tender_expire_date")).getTime(),
                    ((Timestamp) list.get(0).get("tender_company_display_date")).getTime(),
                    ((Timestamp) list.get(0).get("tender_company_expired_date")).getTime(),
                    Long.parseLong(list.get(0).get("response_count").toString()), category, null);


            return mainModel;
        } else {
            List<CategoryNameDto> category = takatafTenderRequestRepo.categoryData(id);
            TenderRequestTenderModel2 mainModel = new TenderRequestTenderModel2(Long.parseLong(list.get(0).get("tender_id").toString()),
                    (String) list.get(0).get("tender_title"),
                    (String) list.get(0).get("tender_explain"), ((Timestamp) (list.get(0).get("tender_display_date"))).getTime(),
                    ((Timestamp) list.get(0).get("tender_expire_date")).getTime(),
                    ((Timestamp) list.get(0).get("tender_company_display_date")).getTime(),
                    ((Timestamp) list.get(0).get("tender_company_expired_date")).getTime(),
                    Long.parseLong(list.get(0).get("response_count").toString()), category, schoolsList);


            return mainModel;
        }


    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/register/Archive/{id}")
    public ResponseEntity<ObjectNode> archiveCompanyRequest(@PathVariable int id) {
        int respons = newRegisterRepo.archiveCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("register/unArchive/{id}")
    public ResponseEntity<ObjectNode> unArchiveCompanyRequest(@PathVariable int id) {
        int respons = newRegisterRepo.unArchiveCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("register/consider/{id}")
    public ResponseEntity<ObjectNode> considrateCompanyRequest(@PathVariable int id) {
        int respons = newRegisterRepo.considrateCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("register/unconsider/{id}")
    public ResponseEntity<ObjectNode> unCosidrateCompanyRequest(@PathVariable int id) {
        int respons = newRegisterRepo.unCosidrateCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/getInActive/archived")
    public List<NewRegisterModel> getInActiveCompaniesArchived() {
        return newRegisterRepo.getInActiveCompaniesArchived();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/getInActive/consider")
    public List<NewRegisterModel> getInActiveCompaniesConsiderate() {
        return newRegisterRepo.getInActiveCompaniesConsiderate();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/getInActive/both")
    public List<NewRegisterModel> getInActiveCompaniesBoth() {
        return newRegisterRepo.getInActiveCompaniesBoth();
    }


    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/tender/request/{id}")
    public TenderRequestTenders getAllRequestsWithNameById2(@PathVariable int id) {

        Map<TenderRequestSchools, List<TenderRequestCategories>> res = new HashMap<>();
        List<Map<String, Object>> list = takatafTenderRequestRepo.getAllRequestsWithNameByTender2(id);
        List<TenderRequestSchools> schoolsList = new ArrayList<>();
        Set<TenderRequestSchools> schools = new HashSet<>();
        List<TenderRequestCategories> test2Models = new ArrayList<>();
        for (Map<String, Object> map : list) {
            TenderRequestSchools model = new TenderRequestSchools();
            TenderRequestCategories test2Model = new TenderRequestCategories();

            try {
                int schoolId = (int) map.get("school_id");
                String schoolName = (String) map.get("school_name");
                byte[] schoolLogo = (byte[]) map.get("school_logo_image");

                long categoryId = (long) map.get("id");
                String categoryName = (String) map.get("category_name");
                long count = (long) map.get("count");


                test2Model.setId(categoryId);
                test2Model.setCategory_name(categoryName);
                test2Model.setCount(count);

                model.setSchool_id(schoolId);
                model.setSchool_name(schoolName);
                model.setSchool_logo_image(Base64.getEncoder().encodeToString(schoolLogo));
                //String encodedString = Base64.getEncoder().encodeToString(schoolLogo);
                schools.add(model);

                test2Models.add(test2Model);
            } catch (NullPointerException e) {
                schools.clear();
            }
        }
        for (TenderRequestSchools obj : schools) {
            System.out.println(obj.toString());
//            res.put(obj,new ArrayList<Test2Model>());
//            List<Test2Model>  test2ModelArrayList=new ArrayList<>();
            List<TenderRequestCategories> test2ModelArrayList = null;
            test2ModelArrayList = new ArrayList<>();

            int i = 0;
            for (Map<String, Object> map : list) {
//                obj.getCategories().add(test2ModelList);


                if (res.get(obj) == null) {
                    res.put(obj, new ArrayList<TenderRequestCategories>());

                }
//                if (res.containsKey(obj)) {
                if (map.get("school_id").equals(obj.getSchool_id())) {
                    TenderRequestCategories test2Model = new TenderRequestCategories();
                    long categoryId = (long) map.get("id");
                    String categoryName = (String) map.get("category_name");
                    long count = (long) map.get("count");


                    test2Model.setId(categoryId);
                    test2Model.setCategory_name(categoryName);
                    test2Model.setCount(count);

                    test2ModelArrayList.add(test2Model);
                    res.get(obj).add(i, test2Model);
                    i++;
                }

            }

            obj.setCategories(test2ModelArrayList);
            schoolsList.add(obj);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        //res = objectMapper.convertValue(res, Map.class);

        List<CategoryNameDto> categorie = takatafTenderRequestRepo.categoryData(id);
        TenderRequestTenders mainModel = new TenderRequestTenders(Long.parseLong(list.get(0).get("tender_id").toString()),
                (String) list.get(0).get("tender_title"),
                (String) list.get(0).get("tender_explain"), ((Timestamp) (list.get(0).get("tender_display_date"))).getTime(),
                ((Timestamp) list.get(0).get("tender_expire_date")).getTime(), Long.parseLong(list.get(0).get("response_count").toString()), categorie, schoolsList);


//        ObjectNode tenderNode = objectMapper.createObjectNode();
//        tenderNode.put("tender_id", mainModel.getTender_id());
//        tenderNode.put("tender_title", mainModel.getTender_title());
//        tenderNode.put("tender_explain", mainModel.getTender_explain());
//        ArrayNode tenderCategory = objectMapper.createArrayNode();

        return mainModel;
    }


//    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
//    @GetMapping("/tender/request/{id}")
//    public GetSingCollectiveTenderById2 getAllRequestsWithNameById(@PathVariable int id) {
//        List<TakatafSinfleSchoolRequestDTO> tenderList = takatafTenderRequestRepo.getAllRequestsWithNameByTender(id);
//        List<CategoryNameDto> categorie = takatafTenderRequestRepo.categoryData(id);
//
//        GetSingleCollectiveByIdPartOneDTO2 data = new GetSingleCollectiveByIdPartOneDTO2(tenderList.get(0).getTender_id(), tenderList.get(0).getTender_title(),
//                tenderList.get(0).getTender_explain(), tenderList.get(0).getTender_display_date(),
//                tenderList.get(0).getTender_expire_date(), tenderList.get(0).getResponse_count(), categorie);
//        List<GetSingleCollectiveByIdPartTwoDTO> schools = new ArrayList<>();
//        List<TenderCollectiveByIdPart3DTO> categories = new ArrayList<>();
//        if (tenderList.get(0).getResponse_count() > 0) {
//
//            TenderCollectiveByIdPart3DTO category = new TenderCollectiveByIdPart3DTO(tenderList.get(0).getId(),
//                    tenderList.get(0).getCategory_name(), tenderList.get(0).getCount());
//            categories.add(category);
//            GetSingleCollectiveByIdPartTwoDTO school0 = new GetSingleCollectiveByIdPartTwoDTO
//                    (tenderList.get(0).getSchool_name(), tenderList.get(0).getSchool_logo_image(), categories);
//            schools.add(school0);
//            int i = 1;
//            while (i < tenderList.size()) {
//                if (tenderList.get(i).getTender_id() == tenderList.get(i - 1).getTender_id()) {
//                    int j = i;
//                    TenderCollectiveByIdPart3DTO categorys = new TenderCollectiveByIdPart3DTO(tenderList.get(i).getId(),
//                            tenderList.get(i).getCategory_name(), tenderList.get(i).getCount());
//                    categories.add(categorys);
//                } else {
//                    GetSingleCollectiveByIdPartTwoDTO school = new GetSingleCollectiveByIdPartTwoDTO
//                            (tenderList.get(i).getSchool_name(), tenderList.get(i).getSchool_logo_image(), categories);
//                    schools.add(school);
//                }
//                i++;
//            }
//
//
//        }
//
//
//        GetSingCollectiveTenderById2 tener = new GetSingCollectiveTenderById2(data, schools);
//        return tener;
//    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('school')")
    @PutMapping("/tender/request/update")
    public int updateRequest(@RequestBody TakatafTenderRequestModel model) {
        return takatafTenderRequestRepo.updateRequest(model.getRequest_school_id(), model.getRequest_tender_id(), model.getCategory());
    }


    @PreAuthorize("hasAuthority('admin') or hasAuthority('school')")
    @GetMapping("/tender/request/get/{id}/{school_id}")
    public ResponseEntity<ObjectNode> getRequestWithNameById(@PathVariable int id, @PathVariable int school_id) {//GetTakatafTenderForScoolRequestDTO2
        List<TakatafSingleSchoolRequestByIDDTO2> tenderList = takatafTenderRequestRepo.getAllRequestsWithNameByIdzs2(id, school_id);
//        if (tenderList.isEmpty()) {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("status", 400);
//            objectNode.put("message", "no data for this tender with this school");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
//        }


        List<CategoryNameDto> categorie = takatafTenderRequestRepo.categoryData(id);
        ArrayNode cats = mapper.createArrayNode();

        for (int j = 0; j < categorie.size(); j++) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put("category_name", categorie.get(j).getCat_name());
            cats.add(nodes);
        }

        ObjectNode dataNode = mapper.createObjectNode();
        dataNode.put("tender_id", tenderList.get(0).getTender_id());
        dataNode.put("tender_title", tenderList.get(0).getTender_title());
        dataNode.put("tender_explain", tenderList.get(0).getTender_explain());
        dataNode.put("tender_display_date", tenderList.get(0).getTender_display_date());
        dataNode.put("tender_expire_date", tenderList.get(0).getTender_expire_date());
        dataNode.put("response_count", tenderList.get(0).getResponse_count());
        dataNode.set("category", cats);
        GetSingleCollectiveByIdPartOneDTO data = new GetSingleCollectiveByIdPartOneDTO(tenderList.get(0).getTender_id(), tenderList.get(0).getTender_title(),
                tenderList.get(0).getTender_explain(), tenderList.get(0).getTender_display_date(),
                tenderList.get(0).getTender_expire_date(), tenderList.get(0).getResponse_count());
        List<GetGetTakatafTenderSchollPrt2DTO2> categories = new ArrayList<>();
        ArrayNode arrNode = mapper.createArrayNode();
        for (TakatafSingleSchoolRequestByIDDTO2 obj : tenderList) {
            GetGetTakatafTenderSchollPrt2DTO2 category = new GetGetTakatafTenderSchollPrt2DTO2(obj.getId(), obj.getCategory_name(), obj.getCount());
            categories.add(category);
            if (obj.getId() == 0 || obj.getCategory_name().equals(null) || obj.getCategory_name().trim().equals("")) {
                ObjectNode catData = mapper.createObjectNode();
                arrNode.add(catData);
            } else {
                ObjectNode catData = mapper.createObjectNode();
                catData.put("id", obj.getId());
                catData.put("category_name", obj.getCategory_name());
                catData.put("count", obj.getCount());

                arrNode.add(catData);
            }

        }

        ObjectNode result = mapper.createObjectNode();
        result.set("data", dataNode);
        result.set("categories", arrNode);
        GetTakatafTenderForScoolRequestDTO2 tener = new GetTakatafTenderForScoolRequestDTO2(data, categories);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/tender/details/company/{id}")
    public List<CollectiveTenderDetailsForCompanyModel> getTenderDetails(@PathVariable int id) {
        return collectiveTenderDetailsForCompanyRepo.getTenderDetails(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/tender/companies/{id}")
    public List<CollectiveTenderCompaniesRequestForCompanyModel> getTenderCompanies(@PathVariable int id) {
        return collectiveTenderCompaniesRequestForCompanyRepo.getTenderCompanies(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/tender/companies/{company_id}/{request_id}")
    public int approveRequest(@PathVariable int company_id, @PathVariable int request_id) {
        return collectiveTenderCompaniesRequestForCompanyRepo.approveRequest(company_id, request_id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/admin/orders/")
    public List<AdminOrdersModel> getAllOrders() {
        return adminOrdersRepo.getAllOrders();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/admin/orders/{id}")
    public AdminSingleOrderModel getOrder(@PathVariable int id) {
        return adminOrdersRepo.getOrder(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/admin/orders/history/{id}")
    public AdminSingleOrderHistoryModel getHistoryOrder(@PathVariable int id) {
        return adminOrdersRepo.getHistoryOrder(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/admin/orders/ship")
    public int addShipping(@RequestBody ShippingDTO dto) {
        return adminOrdersRepo.addShipping(dto.getShip(), dto.getShip_company_offer_id());
    }

    @PreAuthorize("hasAuthority('school')  or hasAuthority('company')")
    @GetMapping("/follow/school/{id}")
    public List<FollowSchoolProfilesDto> getSchoolsWithFollow(@PathVariable int id) {
        return schoolFollowCompanyRepo.getSchoolsWithFollow(id);
    }

///////////ADMIN//////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PreAuthorize("hasAuthority('admin')  or hasAuthority('school')  or hasAuthority('company')")
    @GetMapping("/follow/companies/{id}")
    public List<FollowSchoolProfilesDto> getSchoolsFollowCompany(@PathVariable int id) {
        return schoolFollowCompanyRepo.getSchoolsFollowCompany(id);
    }


    @PreAuthorize("hasAuthority('admin')  or hasAuthority('school')  or hasAuthority('company')")
    @GetMapping("/follow/schools/{id}")
    public List<FollowCompanyProfilesDto> getCompainesFollowedBySchool(@PathVariable int id) {
        return schoolFollowCompanyRepo.getCompainesFollowedBySchool(id);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/follow/company/{id}")
    public List<getCompaniesWithFollowDTo> getCompaniesWithFollow(@PathVariable int id) {
        return schoolFollowCompanyRepo.getCompaniesWithFollow(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/follow/school/followers/{schoolId}")
    public ResponseEntity<List<CompanyFollowSch0oolDto>> getSchoolAllFollowers(@PathVariable int schoolId) {

        if (schoolFollowCompanyRepo.isExistFollwer(schoolId)) {
            List<CompanyFollowSch0oolDto> followed = schoolFollowCompanyRepo.getSchoolAllFollowersNew(schoolId);
            return ResponseEntity.status(HttpStatus.OK).body(followed);
        } else {
            List<CompanyFollowSch0oolDto> followed = schoolFollowCompanyRepo.getSchoolAllFollowersNew(schoolId);
            return ResponseEntity.status(HttpStatus.OK).body(followed);
        }
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('company')")
    @GetMapping("/orders/{id}")
    public ResponseEntity<List<CompanyBackOrderModel>> getOrdersForCompany(@PathVariable int id) {
        return ordersRepo.getOrdersForCompany(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @PostMapping("/school/profile/addProfile")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public ResponseEntity<ObjectNode> AddUserProfile(@Valid @RequestBody SchoolProfileModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (schoolProfileRepo.isExistInLogin(model.getSchool_id(), "school")) {

            if (!repo.isExist(model.getSchool_id())) {
                int res = schoolProfileRepo.addSchoolProfile(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                        model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                        model.getSchool_website_url(), model.getSchool_lng(), model.getSchool_lat(), model.getSchool_cover_image(), model.getSchool_phone_number());
                if (res == 1) {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("school_name", model.getSchool_name());
                    objectNode.put("school_logo_image", model.getSchool_logo_image());
                    objectNode.put("school_address", model.getSchool_address());
                    objectNode.put("school_service_desc", model.getSchool_service_desc());
                    objectNode.put("school_link_youtube", model.getSchool_link_youtube());
                    objectNode.put("school_website_url", model.getSchool_website_url());
                    objectNode.put("school_lng", model.getSchool_lng());
                    objectNode.put("school_lat", model.getSchool_lat());
                    objectNode.put("school_cover_image", model.getSchool_cover_image());
                    objectNode.put("school_phone_number", model.getSchool_phone_number());
                    return ResponseEntity.status(HttpStatus.OK).body(objectNode);
                } else {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 400);
                    objectNode.put("message", "not success");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
                }
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "id Already has profile");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }


        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "id not found");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/school/profile/get/{id}")
    public CustomSchoolProfileModel getProfileForAdmin(@PathVariable int id) {
        return schoolProfileRepo.getSchoolProfileForAdmin(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @PutMapping("/school/profile/update")
    public ResponseEntity<ObjectNode> updateProfileForAdmin(@Valid @RequestBody SchoolProfileModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }

        int res = schoolProfileRepo.updateProfileForAdmin(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url(), model.getSchool_cover_image(), model.getSchool_phone_number());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("school_name", model.getSchool_name());
            objectNode.put("school_logo_image", model.getSchool_logo_image());
            objectNode.put("school_address", model.getSchool_address());
            objectNode.put("school_service_desc", model.getSchool_service_desc());
            objectNode.put("school_link_youtube", model.getSchool_link_youtube());
            objectNode.put("school_website_url", model.getSchool_website_url());
            objectNode.put("school_cover_image", model.getSchool_cover_image());
            objectNode.put("school_phone_number", model.getSchool_phone_number());
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/tenders/school/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestNewDto2> getSchoolRequestsBySchool(@PathVariable int id) {
        return schoolRequestNewRepo.getRequestsBySchoolID(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/tenders/")
    public List<SchoolRequestNewDto> getSchoolSingleRequest() {
        return schoolRequestNewRepo.getRequestsAll();
    }

    @PreAuthorize("hasAuthority('school')")
    @PutMapping("/school/tenders/")
    public JsonNode updateRequestModel(@Valid @RequestBody SchoolRequestsDTO model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");

            return objectNode;
        }
        int res = schoolRequestNewRepo.updateRequest(model.getRequest_id(), model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_display_date(), model.getRequest_expired_date(), model.getSchool_id(), model.getRequest_category_id());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("request_id", model.getRequest_id());
            objectNode.put("request_title", model.getRequest_title());
            objectNode.put("request_explaination", model.getRequest_explaination());
            objectNode.put("request_display_date", model.getRequest_display_date());
            objectNode.put("request_expired_date", model.getRequest_expired_date());
            objectNode.put("school_id", model.getSchool_id());
            objectNode.put("request_category_id", model.getRequest_category_id());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not success");

            return objectNode;
        }
    }

    @PreAuthorize("hasAuthority('school')")
    @PostMapping("/school/tenders/")
    public ResponseEntity<JsonNode> addSchoolRequest(@RequestBody @Valid SchoolNewRequestsDTO2 model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        if (model.getRequest_display_date() < new Timestamp(System.currentTimeMillis()).getTime()
                || model.getRequest_expired_date() < new Timestamp(System.currentTimeMillis()).getTime()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed offer date in past");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        if (model.getRequest_display_date() >= model.getRequest_expired_date()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed offer display date is greater than  expired date");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


        int res = schoolRequestNewRepo.addRequest(model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_display_date(), model.getRequest_expired_date(), model.getSchool_id(), model.getRequest_category_name(), model.getImage_one());
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();

            objectNode.put("request_title", model.getRequest_title());
            objectNode.put("request_explaination", model.getRequest_explaination());
            objectNode.put("request_display_date", model.getRequest_display_date());
            objectNode.put("request_expired_date", model.getRequest_expired_date());
            objectNode.put("school_id", model.getSchool_id());
            objectNode.put("request_category_id", model.getRequest_category_name());
            objectNode.put("image", model.getImage_one());

            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/tenders/request/school/{id}")
    public GetCollectiveTenders2 getRequestOfSchoolByID(@PathVariable int id) {
        List<getSchoolCustomNewRequestById> obj = schoolRequestNewRepo.getRequestOfSchoolByID(id);
        GetCollectiveTenderPartOneDTO2 tender = new GetCollectiveTenderPartOneDTO2(obj.get(0).getRequest_id(), obj.get(0).getRequest_title(),
                obj.get(0).getRequest_explaination(), obj.get(0).getRequest_display_date(), obj.get(0).getRequest_expired_date(),
                obj.get(0).getSchool_id(), obj.get(0).getResponse_count(), obj.get(0).getImage_one());


        List<GetCollectiveTenderPartYTwoDTO> companies = new ArrayList<>();
        if (obj.get(0).getResponse_count() > 0) {
            for (getSchoolCustomNewRequestById one : obj) {
                //if( one.getRequest_category_name().equals(null) )
                GetCollectiveTenderPartYTwoDTO part2 = new GetCollectiveTenderPartYTwoDTO(one.getRequest_category_name() + "", one.getCompany_name() + "",
                        one.getCompany_logo_image(), one.getCategory_name() + "", one.getResponsed_cost(), one.getResponse_date(),
                        one.getResponse_id(), one.getResponsed_company_id(), one.getIs_aproved(), one.getResponse_desc());
                companies.add(part2);
            }
        }

        if (obj.get(0).getResponse_count() == 0) {
            GetCollectiveTenders2 tenders = new GetCollectiveTenders2(tender, null);
            return tenders;
        } else {
            GetCollectiveTenders2 tenders = new GetCollectiveTenders2(tender, companies);
            return tenders;
        }


    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/requests/get/history/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestHistoryDto> getSchoolHistoryRequestBySchoolId(@PathVariable int id) {
        return schoolRequestRepo.getHistoryRequestsBySchoolId(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/requests/get/order/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestHistoryDto> getSchoolOrderRequestBySchoolId(@PathVariable int id) {
        return schoolRequestRepo.getOrderRequestsBySchoolId(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/category/getAll")
//    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestCategoryModel> getSchoolCategories() {
        return schoolRequestCategoryRepo.getSchoolRequestCategories();
    }

    @PreAuthorize("hasAuthority('school')")
    @PostMapping("/tender/request/add")
    public ResponseEntity<ObjectNode> addTenderRequest(@Valid @RequestBody TakatafTenderRequestModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        int res = takatafTenderRequestRepo.add$Request(model.getRequest_school_id(), model.getRequest_tender_id(),
                model.getIs_aproved(), model.getDate(), model.getCategory());
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("request_id", model.getRequest_id());
            objectNode.put("request_school_id", model.getRequest_school_id());
            objectNode.put("request_tender_id", model.getRequest_tender_id());
            objectNode.put("is_aproved", model.getIs_aproved());
            objectNode.put("date", model.getDate());

            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else if (res == -1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "you already request this");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

    @PreAuthorize("hasAuthority('school')")
    @PutMapping("/response/school/request/accept/{id}")
    public ResponseEntity<JsonNode> acceptCompanyResponseSchoolRequest(@PathVariable int id) {

        int res = companyResponseSchoolRequestRepo.acceptResponseSchoolRequest(id);
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 200);
            objectNode.put("message", "accepted");
            objectNode.put("agree_flag", 1);
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }
    }

    @PreAuthorize("hasAuthority('school')")
    @PutMapping("/response/school/request/refuse/{id}")
    public ResponseEntity<JsonNode> refuseCompanyResponseSchoolRequest(@PathVariable int id) {
        int res = companyResponseSchoolRequestRepo.refuseResponseSchoolRequest(id);
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 200);
            objectNode.put("message", "refuse success");
            objectNode.put("agree_flag", 0);
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "refuse failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

    @PreAuthorize("hasAuthority('school')")
    @DeleteMapping("/response/school/request/delete/{id}")
    public ObjectNode deleteCompanyResponseSchoolRequest(@PathVariable int id) {
        int res = companyResponseSchoolRequestRepo.deleteResponseSchoolRequest(id);

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @PreAuthorize("hasAuthority('company')")
    @PostMapping("/company/offer/")
    public ResponseEntity<ObjectNode> addCompanyOffers(@Valid @RequestBody CustomCompanyOfferModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            if (model.getOffer_display_date() < new Timestamp(System.currentTimeMillis()).getTime()
                    || model.getOffer_expired_date() < new Timestamp(System.currentTimeMillis()).getTime()) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 400);
                objectNode.put("message", "Validation Failed offer date in past");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }

            if (model.getOffer_display_date() >= model.getOffer_expired_date()) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 400);
                objectNode.put("message", "Validation Failed offer display date is greater than  expired date");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }

            int res = customCompanyOfferRepo.addOfferEdeited(model.getImage_one(), model.getImage_two(), model.getImage_third(), model.getImage_four(), model.getOffer_title(), model.getOffer_explaination(),
                    model.getOffer_cost(), model.getOffer_display_date(), model.getOffer_expired_date(), model.getOffer_deliver_date(),
                    model.getCompany_id(), model.getOffer_count(), model.getCity(), model.getArea(), model.getLng(), model.getLat());
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                //objectNode.put("offer_images_id", model.getOffer_images_id());
                objectNode.put("offer_title", model.getOffer_title());
                objectNode.put("offer_explaination", model.getOffer_explaination());
                objectNode.put("offer_cost", model.getOffer_cost());
                objectNode.put("offer_display_date", model.getOffer_display_date().toString());
                objectNode.put("offer_expired_date", model.getOffer_expired_date().toString());
                objectNode.put("offer_deliver_date", model.getOffer_deliver_date().toString());
                objectNode.put("company_id", model.getCompany_id());
                objectNode.put("offer_count", model.getOffer_count());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        }

    }

    @PreAuthorize("hasAuthority('company')")
    @GetMapping("/company/offers/{id}")
    public ResponseEntity<getCustomeOffer> getCompanyOffer(@PathVariable int id) {
        if (customCompanyOfferRepo.checkIfExist(id)) {
            CustomCompanyModelWithView model = customCompanyOfferRepo.getCompanyOffer(id);

            return ResponseEntity.status(HttpStatus.OK).body(new getCustomeOffer("200", model));

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getCustomeOffer("400", null));
        }

    }





    @PreAuthorize("hasAuthority('company')")
    @GetMapping("/company/offer/{id}")
    public ResponseEntity<getCustomeOffer2> getCompanyOfferWithDesc(@PathVariable int id) {
        if (customCompanyOfferRepo.checkIfExist(id)) {
            CustomCompanyModelWithViewAndDescRes model = customCompanyOfferRepo.getCompanyOfferWithDesc(id);

            return ResponseEntity.status(HttpStatus.OK).body(new getCustomeOffer2("200", model));

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getCustomeOffer2("400", null));
        }

    }

///getCompanyOffersWithDesc
    @PreAuthorize("hasAuthority('company')")
    @GetMapping("/company/offer/{id}/company")
    public ResponseEntity<getCustomeCompanyOffer2> getSingleCompanyOfferWithDesc(@PathVariable int id) {
        List<CustomeCompanyOfferModel2DTo> offers = customCompanyOfferRepo.getCompanyOffersWithDesc(id);
        if (offers != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new getCustomeCompanyOffer2("200", offers));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getCustomeCompanyOffer2("400", offers));
        }

    }







    @PreAuthorize("hasAuthority('company')")
    @GetMapping("/company/offer/{id}/companies")
    public ResponseEntity<getCustomeCompanyOffer> getSingleCompanyOffer(@PathVariable int id) {
        List<CustomeCompanyOfferModel2> offers = customCompanyOfferRepo.getCompanyOffers(id);
        if (offers != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new getCustomeCompanyOffer("200", offers));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getCustomeCompanyOffer("400", offers));
        }

    }












    @PreAuthorize("hasAuthority('company')")
    @PutMapping("/company/offer/")
    public ResponseEntity<JsonNode> updateCompanyOfferWithImage(@Valid @RequestBody CustomCompanyOfferModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            //objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (customCompanyOfferRepo.checkIfExist(model.getOffer_id())) {
            int res = customCompanyOfferRepo.updateCompanyOfferWithImages(model.getOffer_id(), model.getImage_one(), model.getImage_two(), model.getImage_third(), model.getImage_four(),
                    model.getOffer_title(), model.getOffer_explaination(), model.getOffer_cost(), model.getOffer_display_date(),
                    model.getOffer_expired_date(), model.getOffer_deliver_date(), model.getCompany_id(), model.getOffer_count(), model.getCity(), model.getArea(),
                    model.getLng(), model.getLat());

            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                objectNode.put("offer_id", model.getOffer_id());
                objectNode.put("image_one", model.getImage_one());
                objectNode.put("image_two", model.getImage_two());
                objectNode.put("image_three", model.getImage_third());
                objectNode.put("image_four", model.getImage_four());
                objectNode.put("offer_title", model.getOffer_title());
                objectNode.put("offer_explaination", model.getOffer_explaination());
                objectNode.put("offer_cost", model.getOffer_cost());
                objectNode.put("offer_display_date", model.getOffer_display_date());
                objectNode.put("offer_expired_date", model.getOffer_expired_date());
                objectNode.put("offer_deliver_date", model.getOffer_deliver_date());
                objectNode.put("company_id", model.getCompany_id());
                objectNode.put("offer_count", model.getOffer_count());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not exist");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    @PreAuthorize("hasAuthority('company')")
    @DeleteMapping("/companyOffer/delete/{id}")
    public ResponseEntity<ObjectNode> deleteCompanyOffer(@PathVariable int id) {

        if (companyOfferRepo.checkIfExist(id)) {
            int res = companyOfferRepo.deleteCompanyOffer(id);
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "success");

                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }


    @PreAuthorize("hasAuthority('company') or hasAuthority('school')")
    @PostMapping("/follow/add")
    public ResponseEntity<ObjectNode> addFollower(@RequestBody @Valid SchoolFollowCompany model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            //objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        if (repo.isExist(model.getFollow_id())) {

            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "id exist");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            if (schoolFollowCompanyRepo.isExistFollwing(model.getOrganization_id()) && schoolFollowCompanyRepo.isExistFollwer(model.getFollower_id())) {
                int res = schoolFollowCompanyRepo.addFollower(model.getOrganization_id(), model.getFollower_id());
                if (res == 1) {

                    int id = schoolFollowCompanyRepo.getId(model.getOrganization_id(), model.getFollower_id());

                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 200);
                    objectNode.put("follow_id", id);
                    objectNode.put("Organization_id", model.getOrganization_id());
                    objectNode.put("Follower_id", model.getFollower_id());

                    return ResponseEntity.status(HttpStatus.OK).body(objectNode);
                } else if (res == 0) {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 400);
                    objectNode.put("message", "not success");

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
                } else {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 400);
                    objectNode.put("message", "is already follow it");

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
                }

            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "organization not exist");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }

        }


    }


    @PreAuthorize("hasAuthority('admin') or hasAuthority('school') or hasAuthority('company')")
    @DeleteMapping("/follow/org/{org_id}/follower/{follow_id}")
    public ObjectNode deleteSchoolFollowCompanyByIds(@PathVariable int org_id, @PathVariable int follow_id) {
        if (schoolFollowCompanyRepo.isRecordExist(org_id, follow_id)) {
            int res = schoolFollowCompanyRepo.deleteSchoolFollowCompanyByIds(org_id, follow_id);
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                objectNode.put("message", "success");

                return objectNode;
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return objectNode;
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not exist");

            return objectNode;
        }

    }


}
