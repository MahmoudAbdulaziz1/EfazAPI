package com.taj.repository;

import com.taj.entity.SchoolRequestOfferEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by User on 7/9/2018.
 */

public interface SchoolRequestOfferPaginationRepo extends PagingAndSortingRepository<SchoolRequestOfferEntity, Integer> {

}
