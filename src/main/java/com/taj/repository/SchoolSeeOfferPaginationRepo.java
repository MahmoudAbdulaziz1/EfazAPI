package com.taj.repository;

import com.taj.entity.SchoolSeeOfferEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by User on 7/9/2018.
 */
public interface SchoolSeeOfferPaginationRepo extends PagingAndSortingRepository<SchoolSeeOfferEntity ,Integer> {
}
