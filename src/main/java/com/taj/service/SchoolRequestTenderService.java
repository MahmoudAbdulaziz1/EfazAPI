package com.taj.service;

import com.taj.entity.SchoolRequestTenderEntity;

import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
public interface SchoolRequestTenderService {

    public List<SchoolRequestTenderEntity> getSchools(int page, int limit);
}
