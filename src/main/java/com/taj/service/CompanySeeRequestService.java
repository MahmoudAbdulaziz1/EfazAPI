package com.taj.service;

import com.taj.entity.CompanySeeRequestEntity;

import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
public interface CompanySeeRequestService {
    public List<CompanySeeRequestEntity> getCompanies(int pages, int limit);
}
