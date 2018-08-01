package com.taj.service;

import com.taj.entity.CompanyOffersEntity;
import com.taj.entity.CompanyResponseRequestEntity;
import com.taj.repository.CompanyOffersRepo;
import com.taj.repository.CompanyResponseRequestPaginationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/30/2018.
 */
//@Service
public class CompanyOffersService {
//    @Autowired
//    CompanyOffersRepo repo;
//
//    public List<CompanyOffersEntity> getCompanyOffers(int id, int pages, int limit) {
//        List<CompanyOffersEntity> returnValue = new ArrayList<>();
//        Pageable pageableRequest = PageRequest.of(pages, limit);
//        Page<CompanyOffersEntity> users = repo.findAll(pageableRequest);
//        List<CompanyOffersEntity> userEntities = users.getContent();
//        for (CompanyOffersEntity userEntity : userEntities) {
//            CompanyOffersEntity userDto = new CompanyOffersEntity();
//            BeanUtils.copyProperties(userEntity, userDto);
//            returnValue.add(userDto);
//        }
//        return returnValue;
//    }
}
