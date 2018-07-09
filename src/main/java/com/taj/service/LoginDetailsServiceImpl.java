package com.taj.service;

import com.taj.entity.LoginDetailsEntity;
import com.taj.repository.LoginDetailsPaginationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
@Service
public class LoginDetailsServiceImpl implements LoginDetailsService {
    @Autowired
    private LoginDetailsPaginationRepo repo;

    @Override
    public List<LoginDetailsEntity> getDetails(int page, int limit) {
        List<LoginDetailsEntity> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<LoginDetailsEntity> users = repo.findAll(pageableRequest);
        List<LoginDetailsEntity> userEntities = users.getContent();
        for (LoginDetailsEntity userEntity : userEntities) {
            LoginDetailsEntity userDto = new LoginDetailsEntity();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }
}
