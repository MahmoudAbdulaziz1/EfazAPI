package com.taj.service;

import com.taj.entity.SchoolSeeOfferEntity;
import com.taj.entity.SchoolSeeTenderEntity;
import com.taj.repository.SchoolSeeTenderPaginationRepo;
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
public class SchoolSeeTenderServiceImpl implements SchoolSeeTenderService {
    @Autowired
    SchoolSeeTenderPaginationRepo repo;
    @Override
    public List<SchoolSeeTenderEntity> getSchools(int page, int limit) {
        List<SchoolSeeTenderEntity> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<SchoolSeeTenderEntity> users = repo.findAll(pageableRequest);
        List<SchoolSeeTenderEntity> userEntities = users.getContent();
        for (SchoolSeeTenderEntity userEntity : userEntities) {
            SchoolSeeTenderEntity userDto = new SchoolSeeTenderEntity();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }
}
