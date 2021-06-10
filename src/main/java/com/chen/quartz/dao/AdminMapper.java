package com.chen.quartz.dao;

import com.chen.quartz.entity.AdminVO;
import org.springframework.stereotype.Component;

@Component
public interface AdminMapper {

   AdminVO findAllAdmin();

}