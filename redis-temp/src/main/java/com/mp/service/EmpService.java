package com.mp.service;

import com.mp.pojo.Emp;
import com.mp.pojo.PageBean;

import java.util.List;
import java.util.Map;


public interface EmpService {

    Map<String,Object> findByPage(PageBean pageBean);

    Emp findByEmpId(Integer id);

    List<Emp>  findAll();
    List<Emp>  findAll2();
}
