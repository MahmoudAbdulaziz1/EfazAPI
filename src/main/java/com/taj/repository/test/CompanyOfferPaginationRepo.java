package com.taj.repository.test;

import com.taj.entity.test.CompanyOfferEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mahooud El swisy on 10/10/2018.
 */
public interface CompanyOfferPaginationRepo extends PagingAndSortingRepository<CompanyOfferEntity, Integer> {
    @Query("select o.offer_title from CompanyOfferEntity as o " +
            " where o.offer_id > :ID ")
    List<String> getAllOffers(@Param("ID") int id, Pageable pageable);

}
