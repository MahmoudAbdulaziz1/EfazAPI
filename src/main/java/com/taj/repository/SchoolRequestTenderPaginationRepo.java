package com.taj.repository;

import com.taj.entity.SchoolRequestTenderEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by User on 7/9/2018.
 */
public interface SchoolRequestTenderPaginationRepo extends PagingAndSortingRepository<SchoolRequestTenderEntity, Integer>{
}
