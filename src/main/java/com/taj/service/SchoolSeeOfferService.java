package com.taj.service;

import com.taj.entity.LoginDetailsEntity;
import com.taj.entity.SchoolSeeOfferEntity;

import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
public interface SchoolSeeOfferService {
    List<SchoolSeeOfferEntity> getDetails(int page, int limit);
}
