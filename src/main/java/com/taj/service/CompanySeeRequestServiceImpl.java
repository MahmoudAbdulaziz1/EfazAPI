package com.taj.service;

import com.taj.entity.CompanySeeRequestEntity;
import com.taj.repository.CompanySeeRequestPaginationRepo;
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
public class CompanySeeRequestServiceImpl implements CompanySeeRequestService {
    @Autowired
    CompanySeeRequestPaginationRepo repo;

    @Override
    public List<CompanySeeRequestEntity> getCompanies(int pages, int limit) {
        List<CompanySeeRequestEntity> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(pages, limit);
        Page<CompanySeeRequestEntity> users = repo.findAll(pageableRequest);
        List<CompanySeeRequestEntity> userEntities = users.getContent();
        for (CompanySeeRequestEntity userEntity : userEntities) {
            CompanySeeRequestEntity userDto = new CompanySeeRequestEntity();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }
}
