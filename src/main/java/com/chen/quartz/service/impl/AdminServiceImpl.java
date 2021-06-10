package com.chen.quartz.service.impl;

import com.chen.quartz.dao.AdminMapper;
import com.chen.quartz.entity.AdminVO;
import com.chen.quartz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    public AdminVO findAllAdmin(){
        return adminMapper.findAllAdmin();
    }

    @Autowired
    public void setUserDao (AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }
}
