package com.mp.service.impl;

import com.mp.mapper.EmpMapper;
import com.mp.pojo.Emp;
import com.mp.pojo.EmpVo;
import com.mp.pojo.PageBean;
import com.mp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    //String类型操作对象
    private ValueOperations<String,Object> valueOperations;
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

    @Override
    public List<Emp> findAll() {

        try {
            List<Emp>  list = (List<Emp>) valueOperations.get("allEmp");

            if(list==null){
                List<Emp> empList = empMapper.selectList(null);
                valueOperations.set("allEmp", empList);
                System.out.println("从数据库中取数据");

            }else {
                System.out.println("从缓存中取数据");

            }
            return list;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return  null;

    }

    @Override
    public List<Emp> findAll2() {
        try {
            List<Emp>  list = (List<Emp>) valueOperations.get("allEmp");

            if(list==null) {
            //双重检测锁
                synchronized (this) {
                    List<Emp>  list1 = (List<Emp>) valueOperations.get("allEmp");

                    if (list1 == null) {

                        List<Emp> empList = empMapper.selectList(null);
                        valueOperations.set("allEmp", empList);
                        System.out.println("从数据库中取数据");

                    } else {
                        System.out.println("从缓存中取数据");

                    }

                }
            }else {
                System.out.println("从缓存中取数据");
            }
            return list;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return  null;

    }
}
