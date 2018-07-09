package com.taj.service;

import com.taj.entity.SchoolRequestOfferEntity;

import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
public interface SchoolRequestOfferService {
    public List<SchoolRequestOfferEntity> getSchools(int page, int limit);

}
