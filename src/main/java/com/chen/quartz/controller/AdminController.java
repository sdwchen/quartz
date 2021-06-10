package com.chen.quartz.controller;

import com.chen.quartz.entity.AdminVO;
import com.chen.quartz.service.AdminService;
import com.chen.quartz.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {


    private AdminService adminService;

    @GetMapping(value = "/findAllAdmin")
    public AdminVO findAllAdmin(){
        System.out.printf("a");
        return adminService.findAllAdmin();

    }

    @Autowired
    private void setAdminService(AdminServiceImpl adminService){
        this.adminService=adminService;
    }
}
