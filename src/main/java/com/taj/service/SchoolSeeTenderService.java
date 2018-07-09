package com.taj.service;

import com.taj.entity.SchoolSeeTenderEntity;

import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
public interface SchoolSeeTenderService {

    public List<SchoolSeeTenderEntity> getSchools(int page, int limit);

}
