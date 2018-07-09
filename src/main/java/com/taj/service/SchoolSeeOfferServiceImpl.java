package com.taj.service;

import com.taj.entity.LoginDetailsEntity;
import com.taj.entity.SchoolSeeOfferEntity;
import com.taj.repository.SchoolSeeOfferPaginationRepo;
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
public class SchoolSeeOfferServiceImpl implements SchoolSeeOfferService {

    @Autowired
    private SchoolSeeOfferPaginationRepo repo;

    @Override
    public List<SchoolSeeOfferEntity> getDetails(int page, int limit) {
        List<SchoolSeeOfferEntity> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<SchoolSeeOfferEntity> users = repo.findAll(pageableRequest);
        List<SchoolSeeOfferEntity> userEntities = users.getContent();
        for (SchoolSeeOfferEntity userEntity : userEntities) {
            SchoolSeeOfferEntity userDto = new SchoolSeeOfferEntity();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }
}
