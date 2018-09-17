package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taj.model.AreaDataDto;
import com.taj.model.AreaWithCityDto;
import com.taj.model.CityDataDto;
import com.taj.repository.AreaWithCityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by User on 9/17/2018.
 */
@RequestMapping("/area/cities")
@RestController
@CrossOrigin
public class AreaWithCompanyController {

    @Autowired
    AreaWithCityRepo repo;

    @GetMapping("/")
    public AreaWithCityDto getTestObject() {
        Map<AreaDataDto, List<CityDataDto>> res = new HashMap<>();
        List<Map<String, Object>> list = repo.getTestObject();
        List<AreaDataDto> schoolsList = new ArrayList<>();
        Set<AreaDataDto> schools = new HashSet<>();
        List<CityDataDto> test2Models = new ArrayList<>();

        //for add id to set
        for (Map<String, Object> map : list) {
            AreaDataDto model = new AreaDataDto();
            CityDataDto test2Model = new CityDataDto();


            int areaId = (int) map.get("area_id");
            String areaName = (String) map.get("area_name");

            try{
                int cityId = (int) map.get("city_id");
                String cityName = (String) map.get("city_name");


                test2Model.setCityId(cityId);
                test2Model.setCityName(cityName);
            }catch (NullPointerException e){
                test2Model= null;
            }



            model.setAreaId(areaId);
            model.setAreaName(areaName);
            //String encodedString = Base64.getEncoder().encodeToString(schoolLogo);
            schools.add(model);

            test2Models.add(test2Model);
        }


        for (AreaDataDto obj : schools) {
            System.out.println(obj.toString());
//            res.put(obj,new ArrayList<Test2Model>());
//            List<Test2Model>  test2ModelArrayList=new ArrayList<>();
            List<CityDataDto> test2ModelArrayList = null;
            test2ModelArrayList = new ArrayList<>();

            int i = 0;
            for (Map<String, Object> map : list) {
//                obj.getCategories().add(test2ModelList);


                if (res.get(obj) == null) {
                    res.put(obj, new ArrayList<CityDataDto>());

                }
//                if (res.containsKey(obj)) {
                if (map.get("area_id").equals(obj.getAreaId())) {
                    CityDataDto test2Model = new CityDataDto();
                    try{
                        int categoryId = (int) map.get("city_id");
                        String categoryName = (String) map.get("city_name");


                        test2Model.setCityId(categoryId);
                        test2Model.setCityName(categoryName);
                    }catch (NullPointerException e){
                        test2Model= null;
                    }




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

        AreaWithCityDto mainModel = new AreaWithCityDto(schoolsList);


//        ObjectNode tenderNode = objectMapper.createObjectNode();
//        tenderNode.put("tender_id", mainModel.getTender_id());
//        tenderNode.put("tender_title", mainModel.getTender_title());
//        tenderNode.put("tender_explain", mainModel.getTender_explain());
//        ArrayNode tenderCategory = objectMapper.createArrayNode();

        return mainModel;


    }
}
