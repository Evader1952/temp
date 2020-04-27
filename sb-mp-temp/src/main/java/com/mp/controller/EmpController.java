package com.mp.controller;

import com.mp.mapper.EmpMapper;
import com.mp.pojo.Emp;
import com.mp.pojo.PageBean;
import com.mp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EmpController {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpService empService;
    @RequestMapping("/index")
    public  List<Emp>  xx() {

        List<Emp> empList = empMapper.selectList(null);
        return empList;
    }

    @GetMapping("/findAllByPage")
    public Map<String, Object> findAllByPage(PageBean pageBean) {

        return empService.findByPage(pageBean);

    }

    @RequestMapping("/add")

    public String add(Emp emp){

        String  msg="1";  //添加成功
        try {
            empMapper.insert(emp);
            msg="0";    //添加成功
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping("/update")

    public String update(Emp emp){
        String  msg="1";  //添加成功
        try {
            empMapper.updateById(emp);
            msg="0";    //添加成功
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }


    @RequestMapping("/delete")

    public  String  del(Integer id){
        String  msg="1";  //删除成功
        try {
            empMapper.deleteById(id);
            msg="0";    //删除成功
        }catch (Exception e){
            e.printStackTrace();
        }
        return  msg;
    }

    @RequestMapping("/delMany")

    public String delMany(Integer[] id){
        String  msg="1";  //删除失败
        System.out.println(id);
        try {
            for (Integer empId:id) {
                empMapper.deleteById(id);
            }
            msg="0";    //删除成功
        }catch (Exception e){
            e.printStackTrace();
        }
        return  msg;

    }

}
