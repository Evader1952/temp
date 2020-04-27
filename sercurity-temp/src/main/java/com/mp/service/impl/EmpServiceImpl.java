package com.mp.service.impl;

import com.mp.mapper.EmpMapper;
import com.mp.pojo.Emp;
import com.mp.pojo.EmpVo;
import com.mp.pojo.PageBean;
import com.mp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    @Override
    public Emp findByEmpId(Integer id) {
        return null;
    }



    @Override
    public Map<String, Object> findByPage(PageBean pageBean) {
        List<Emp> countList  = empMapper.selectList(null);
        List<EmpVo> empList= empMapper.findByPage(pageBean);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", countList.size());
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", empList);
        return map;
    }
}
