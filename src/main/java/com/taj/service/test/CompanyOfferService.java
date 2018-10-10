package com.taj.service.test;

import com.taj.entity.test.CompanyOfferDtoTest;
import com.taj.entity.test.CompanyOfferEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

/**
 * Created by User on 10/10/2018.
 */
public interface CompanyOfferService {
    List<CompanyOfferEntity> getOffers(int page, int limit);
//    SpringDataJaxb.PageDto getAllOffers(int page, int limit);
//    public List<String> findBySearchTerm(int id, Pageable pageRequest);

}
