package com.taj.controller;

import com.taj.model.CategoryNameDto;
import com.taj.model.TenderRequestCategoriesModel;
import com.taj.model.TenderRequestSchoolModel;
import com.taj.model.TenderRequestTenderModel;
import com.taj.repository.TakatafTenderRequestRepo;
import com.taj.repository.TenderRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by User on 8/30/2018.
 */
@RequestMapping("/request/tender")
@RestController
@CrossOrigin
public class TenderRequestController {

    @Autowired
    TenderRequestRepo repo;
    @Autowired
    TakatafTenderRequestRepo takatafTenderRequestRepo;

    @GetMapping("/{id}")
    public TenderRequestTenderModel getTenderRequestObject(@PathVariable int id) {
        Map<TenderRequestSchoolModel, List<TenderRequestCategoriesModel>> res = new HashMap<>();
        List<Map<String, Object>> list = repo.getTenderRequestObject(id);
        List<TenderRequestSchoolModel> schoolsList = new ArrayList<>();
        Set<TenderRequestSchoolModel> schools = new HashSet<>();
        List<CategoryNameDto> category = takatafTenderRequestRepo.categoryData(id);
        List<TenderRequestCategoriesModel> test2Models = new ArrayList<>();
        for (Map<String, Object> map : list) {
            TenderRequestSchoolModel model = new TenderRequestSchoolModel();
            TenderRequestCategoriesModel test2Model = new TenderRequestCategoriesModel();



            long schoolId = (long) map.get("school_id");
            String schoolName = (String) map.get("school_name");
            byte[] schoolLogo = (byte[]) map.get("school_logo_image");
            Timestamp date = (Timestamp) map.get("t_date");
            long t_date = 0;
            if (date == null){
                t_date = 0;
            }else {
                t_date = date.getTime();
            }

            //System.out.println(map.get("t_date")+" +++ "+ date+" ++++++ "+ date.getTime()+ " ++++ "+ new Timestamp(0));

            int categoryId = (int) map.get("id");
            String categoryName = (String) map.get("category_name");
            int count = (int) map.get("count");


            test2Model.setId(categoryId);
            test2Model.setCategory_name(categoryName);
            test2Model.setCount(count);

            model.setSchool_id(schoolId);
            model.setSchool_name(schoolName);
            model.setSchool_logo_image(Base64.getEncoder().encodeToString(schoolLogo));
            model.setT_date(t_date);
            //String encodedString = Base64.getEncoder().encodeToString(schoolLogo);
            schools.add(model);

            test2Models.add(test2Model);
        }

        for (TenderRequestSchoolModel obj : schools) {
            System.out.println(obj.toString());
//            res.put(obj,new ArrayList<Test2Model>());
//            List<Test2Model>  test2ModelArrayList=new ArrayList<>();
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
                    int categoryId = (int) map.get("id");
                    String categoryName = (String) map.get("category_name");
                    int count = (int) map.get("count");


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

        if (Long.parseLong(list.get(0).get("response_count").toString()) == 0) {
            TenderRequestTenderModel mainModel = new TenderRequestTenderModel(Long.parseLong(list.get(0).get("tender_id").toString()),
                    (String) list.get(0).get("tender_title"),
                    (String) list.get(0).get("tender_explain"), ((Timestamp) (list.get(0).get("tender_display_date"))).getTime(),
                    ((Timestamp) list.get(0).get("tender_expire_date")).getTime(), Long.parseLong(list.get(0).get("response_count").toString()), category, null);


            return mainModel;
        } else {
            TenderRequestTenderModel mainModel = new TenderRequestTenderModel(Long.parseLong(list.get(0).get("tender_id").toString()),
                    (String) list.get(0).get("tender_title"),
                    (String) list.get(0).get("tender_explain"), ((Timestamp) (list.get(0).get("tender_display_date"))).getTime(),
                    ((Timestamp) list.get(0).get("tender_expire_date")).getTime(), Long.parseLong(list.get(0).get("response_count").toString()), category, schoolsList);


            return mainModel;
        }


    }
}
