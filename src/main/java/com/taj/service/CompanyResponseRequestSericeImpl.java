package com.taj.service;

import com.taj.entity.CompanyResponseRequestEntity;
import com.taj.entity.CompanySeeRequestEntity;
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
 * Created by User on 7/9/2018.
 */
@Service
public class CompanyResponseRequestSericeImpl implements CompanyResponseRequestService{


    @Autowired
    CompanyResponseRequestPaginationRepo repo;
    @Override
    public List<CompanyResponseRequestEntity> getCompanies(int pages, int limit) {
        List<CompanyResponseRequestEntity> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(pages, limit);
        Page<CompanyResponseRequestEntity> users = repo.findAll(pageableRequest);
        List<CompanyResponseRequestEntity> userEntities = users.getContent();
        for (CompanyResponseRequestEntity userEntity : userEntities) {
            CompanyResponseRequestEntity userDto = new CompanyResponseRequestEntity();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }
}
