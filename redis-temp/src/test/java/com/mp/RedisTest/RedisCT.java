package com.mp.RedisTest;

import com.mp.RedisTempApplicationTests;
import com.mp.mapper.EmpMapper;
import com.mp.pojo.Emp;
import com.mp.service.EmpService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisCT extends RedisTempApplicationTests {

    @Autowired
    //String类型操作对象
    private ValueOperations<String,Object> valueOperations;

    @Autowired
    private EmpService empService;

    @Autowired
    private EmpMapper empMapper;

    public  List<Emp> findAll(){
//        try {
        List<Emp>  list = (List<Emp>) valueOperations.get("allEmp");

        if(list==null){
            List<Emp> empList = empMapper.selectList(null);
            valueOperations.set("allEmp", empList);
            System.out.println("从数据库中取数据");

        }else {
            System.out.println("从缓存中取数据");

        }
        return list;
//        }
//        catch (Exception e){
//            System.err.println(e.getMessage());
//        }
//        return  null;
    }

    @Test
    public void test(){

        ExecutorService executorService= Executors.newFixedThreadPool(20);
        for(int i=1 ; i<=20000;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    findAll();
                }
            });
        }

        // return "test over";

    }

    @Test
    public void test1(){
       findAll();
    }
}
