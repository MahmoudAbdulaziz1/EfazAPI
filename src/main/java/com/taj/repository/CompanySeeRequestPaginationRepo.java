package com.taj.repository;

import com.taj.entity.CompanySeeRequestEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by User on 7/9/2018.
 */
public interface CompanySeeRequestPaginationRepo extends PagingAndSortingRepository<CompanySeeRequestEntity, Integer>{
}
