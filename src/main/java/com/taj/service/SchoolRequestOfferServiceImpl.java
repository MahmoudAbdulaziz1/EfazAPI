package com.taj.service;

import com.taj.entity.SchoolRequestOfferEntity;
import com.taj.repository.SchoolRequestOfferPaginationRepo;
import com.taj.repository.SchoolRequestOfferRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
@Service
public class SchoolRequestOfferServiceImpl implements SchoolRequestOfferService {

    @Autowired
    private SchoolRequestOfferPaginationRepo repo;

    @Override
    public List<SchoolRequestOfferEntity> getSchools(int page, int limit) {


        List<SchoolRequestOfferEntity> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<SchoolRequestOfferEntity> users = repo.findAll(pageableRequest);
        List<SchoolRequestOfferEntity> userEntities = users.getContent();
        for (SchoolRequestOfferEntity userEntity : userEntities) {
            SchoolRequestOfferEntity userDto = new SchoolRequestOfferEntity();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }
}

