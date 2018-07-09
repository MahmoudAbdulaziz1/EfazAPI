package com.taj.service;

import com.taj.entity.LoginDetailsEntity;
import com.taj.model.LoginDetailsModel;

import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
public interface LoginDetailsService {
    List<LoginDetailsEntity> getDetails(int page, int limit);
}
