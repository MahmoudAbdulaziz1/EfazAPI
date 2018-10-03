package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.CategoryNameDto;
import com.taj.model.TenderRequestCategoriesModel;
import com.taj.model.TenderRequestSchoolModel;
import com.taj.model.TenderRequestTenderModel2;
import com.taj.model.retail_collective.RetailGetAllModel;
import com.taj.model.retail_collective.RetailModel;
import com.taj.repository.RetailRepo;
import com.taj.repository.TakatafTenderRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by User on 10/2/2018.
 */
@RequestMapping("/retail")
@RestController
@CrossOrigin
public class RetailController {

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    @Autowired
    RetailRepo retailRepo;
    @Autowired
    TakatafTenderRequestRepo takatafTenderRequestRepo;
    @Autowired
    ObjectMapper mapper;

    @PostMapping("/")
    public ResponseEntity<ObjectNode> retailAddRequest(@Valid @RequestBody RetailModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "validation error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
        if (retailRepo.isExistRequest(model.getRetail_school_id(), model.getRetail_tender_id())) {
            ObjectNode node = mapper.createObjectNode();
            node.put("retail", model.getRetail());
            node.put("school_id", model.getRetail_school_id());
            node.put("tender_id", model.getRetail_tender_id());
            return ResponseEntity.status(HttpStatus.OK).body(node);
        } else {
            int res = retailRepo.retailAddRequest(1, model.getRetail_school_id(), model.getRetail_tender_id());
            if (res == 1) {
                ObjectNode node = mapper.createObjectNode();
                node.put("retail", model.getRetail());
                node.put("school_id", model.getRetail_school_id());
                node.put("tender_id", model.getRetail_tender_id());
                return ResponseEntity.status(HttpStatus.OK).body(node);
            } else if (res == -100) {
                ObjectNode node = mapper.createObjectNode();
                node.put(STATUS, 400);
                node.put(MESSAGE, "no request for this this school");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
            } else {
                ObjectNode node = mapper.createObjectNode();
                node.put(STATUS, 400);
                node.put(MESSAGE, "Failed");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
            }


        }

    }

    @PutMapping("/confirm/")
    public ResponseEntity<ObjectNode> confirmRetailRequest(@Valid @RequestBody RetailModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "validation error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }

        int res = retailRepo.confirmRetailRequest(model.getRetail_school_id(), model.getRetail_tender_id());
        if (res == 1) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 200);
            node.put(MESSAGE, "retail confirmed");
            return ResponseEntity.status(HttpStatus.OK).body(node);
        } else if (res == -100) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "no request for this school");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "not confirmed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
    }

    @PutMapping("/remove/")
    public ResponseEntity<ObjectNode> removeRetailRequest(@Valid @RequestBody RetailModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "validation error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }

        int res = retailRepo.removeRetailRequest(model.getRetail_school_id(), model.getRetail_tender_id());
        if (res == 1) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 200);
            node.put(MESSAGE, "retail removed");
            return ResponseEntity.status(HttpStatus.OK).body(node);
        } else if (res == -100) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "no request for this school");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "not confirmed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
    }



    @GetMapping("/")
    public List<RetailGetAllModel> getRetailTenders() {
        return retailRepo.getRetailTenders();
    }

    @GetMapping("/tender/{school_id}/{tender_id}")
    public TenderRequestTenderModel2 getTenderRequestObjectWithCompanyDates(@PathVariable int school_id, @PathVariable int tender_id) {


        Map<TenderRequestSchoolModel, List<TenderRequestCategoriesModel>> res = new HashMap<>();
        List<Map<String, Object>> list = retailRepo.getTenderRequestObjectWithCompanyDates(school_id, tender_id);
        List<TenderRequestSchoolModel> schoolsList = new ArrayList<>();
        Set<TenderRequestSchoolModel> schools = new HashSet<>();
        List<TenderRequestCategoriesModel> test2Models = new ArrayList<>();
        for (Map<String, Object> map : list) {
            TenderRequestSchoolModel model = new TenderRequestSchoolModel();
            TenderRequestCategoriesModel test2Model = new TenderRequestCategoriesModel();


            int schoolId = (int) map.get("school_id");
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
                System.out.println("first one");
                int categoryId = (int) map.get("id");
                System.out.println("first one" + categoryId);
                String categoryName = (String) map.get("category_name");
                System.out.println("first one" + categoryName);
                int count = (int) map.get("count");
                System.out.println("first one" + count);
                test2Model.setId(categoryId);
                test2Model.setCategory_name(categoryName);
                test2Model.setCount(count);
            } catch (NullPointerException e) {
                System.out.println("first one" + e.getMessage());
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
            System.out.println(test2Model.getId() + " " + test2Model.getCount() + " " + test2Model.getCategory_name() + " " + test2Models.size());
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
                    System.out.println(map.get("school_id").equals(obj.getSchool_id()) + " test bool");
                    int testId = (int) map.get("school_id");
                    if (testId == obj.getSchool_id()) {
                        System.out.println(map.get("school_id") + " vs " + obj.getSchool_id());
                        TenderRequestCategoriesModel test2Model = new TenderRequestCategoriesModel();
                        try {
                            System.out.println("2nd ");
                            int categoryId = (int) map.get("id");
                            System.out.println("2nd " + categoryId);
                            String categoryName = (String) map.get("category_name");
                            System.out.println("2nd " + categoryName);
                            int count = (int) map.get("count");
                            System.out.println("2nd " + count);

                            test2Model.setId(categoryId);
                            test2Model.setCategory_name(categoryName);
                            test2Model.setCount(count);
                        } catch (NullPointerException e) {
                            test2Model = null;
                            System.out.println("2nd " + e.getMessage());
                        }


                        test2ModelArrayList.add(test2Model);
                        System.out.println("test model size " + test2Model.getCategory_name());
                        System.out.println("model size " + test2ModelArrayList.size());
                        res.get(obj).add(i, test2Model);
                        i++;
                    }

                }

                obj.setCategories(test2ModelArrayList);
                System.out.println("model size " + test2ModelArrayList.size());
                schoolsList.add(obj);
            }
        } catch (NullPointerException e) {
            schoolsList.add(null);
        }

        if (Long.parseLong(list.get(0).get("response_count").toString()) == 0) {
            List<CategoryNameDto> category = takatafTenderRequestRepo.categoryData(tender_id);

            TenderRequestTenderModel2 mainModel = new TenderRequestTenderModel2(Long.parseLong(list.get(0).get("tender_id").toString()),
                    (String) list.get(0).get("tender_title"),
                    (String) list.get(0).get("tender_explain"), ((Timestamp) (list.get(0).get("tender_display_date"))).getTime(),
                    ((Timestamp) list.get(0).get("tender_expire_date")).getTime(),
                    ((Timestamp) list.get(0).get("tender_company_display_date")).getTime(),
                    ((Timestamp) list.get(0).get("tender_company_expired_date")).getTime(),
                    Long.parseLong(list.get(0).get("response_count").toString()), category, null);


            return mainModel;
        } else {
            List<CategoryNameDto> category = takatafTenderRequestRepo.categoryData(tender_id);
            TenderRequestTenderModel2 mainModel = new TenderRequestTenderModel2(Long.parseLong(list.get(0).get("tender_id").toString()),
                    (String) list.get(0).get("tender_title"),
                    (String) list.get(0).get("tender_explain"), ((Timestamp) (list.get(0).get("tender_display_date"))).getTime(),
                    ((Timestamp) list.get(0).get("tender_expire_date")).getTime(),
                    ((Timestamp) list.get(0).get("tender_company_display_date")).getTime(),
                    ((Timestamp) list.get(0).get("tender_company_expired_date")).getTime(),
                    Long.parseLong(list.get(0).get("response_count").toString()), category, schoolsList);
            System.out.println(schoolsList.get(0).getCategories().size());

            return mainModel;
        }


    }

}
