package com.taj.service.test;

import com.taj.entity.CompanySeeRequestEntity;
import com.taj.entity.test.CompanyOfferEntity;
import com.taj.repository.test.CompanyOfferPaginationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10/10/2018.
 */
@Service
public class CompanyOfferServiceImpl implements CompanyOfferService {

    @Autowired
    private CompanyOfferPaginationRepo repo;


    @Override
    public List<CompanyOfferEntity> getOffers(int pages, int limit) {
        List<CompanyOfferEntity> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(pages, limit);
        Page<CompanyOfferEntity> users = repo.findAll(pageableRequest);
        List<CompanyOfferEntity> userEntities = users.getContent();
        for (CompanyOfferEntity userEntity : userEntities) {
            CompanyOfferEntity userDto = new CompanyOfferEntity();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }

//    @Transactional(readOnly = true)
//    @Override
//    public List<String> findBySearchTerm(int id, Pageable pageRequest) {
//        Specification<String> searchSpec = titleOrDescriptionContainsIgnoreCase(id);
//        Page<String> ss = repo.findAll(searchSpec, pageRequest);
//        return TodoMapper.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
//    }
}
