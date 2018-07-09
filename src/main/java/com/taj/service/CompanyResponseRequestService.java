package com.taj.service;

import com.taj.entity.CompanyResponseRequestEntity;

import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
public interface CompanyResponseRequestService {

    public List<CompanyResponseRequestEntity>getCompanies(int pages, int limit);
}
