package com.taj.service;

import com.taj.entity.SchoolRequestTenderEntity;
import com.taj.entity.SchoolSeeOfferEntity;
import com.taj.repository.SchoolRequestTenderPaginationRepo;
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
public class SchoolRequestTenderServiceImpl implements SchoolRequestTenderService {
    @Autowired
    SchoolRequestTenderPaginationRepo repo;
    @Override
    public List<SchoolRequestTenderEntity> getSchools(int page, int limit) {
        List<SchoolRequestTenderEntity> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<SchoolRequestTenderEntity> users = repo.findAll(pageableRequest);
        List<SchoolRequestTenderEntity> userEntities = users.getContent();
        for (SchoolRequestTenderEntity userEntity : userEntities) {
            SchoolRequestTenderEntity userDto = new SchoolRequestTenderEntity();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }
}
