package com.taj.repository;

import com.taj.entity.LoginDetailsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by User on 7/9/2018.
 */
public interface LoginDetailsPaginationRepo extends PagingAndSortingRepository<LoginDetailsEntity, Integer> {
}
