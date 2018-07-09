package com.taj.repository;

import com.taj.entity.CompanyResponseRequestEntity;
import com.taj.entity.CompanySeeRequestEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by User on 7/9/2018.
 */
public interface CompanyResponseRequestPaginationRepo extends PagingAndSortingRepository<CompanyResponseRequestEntity, Integer>{
}
