package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mp.mapper.DepMapper;
import com.mp.mapper.EmpMapper;
import com.mp.pojo.Dep;
import com.mp.pojo.Emp;
import com.mp.pojo.EmpVo;
import com.mp.pojo.PageBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlusTest extends  SbMpTempApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DepMapper depMapper;
    @Test
    public void test() {

        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Emp::getSex, "男")
                             .like(Emp::getName, "a")
        ;
        List<Emp> empList = empMapper.selectList(queryWrapper);
        for (Emp emp : empList) {
            System.out.println(emp);
        }

    }

    @Test
    public void test2() {

        Emp emp = empMapper.findById(54);

        System.out.println(emp);
    }

    @Test
    public void test3() {

        Dep dep = depMapper.findByDepId(1);

        System.out.println(dep);
    }

    @Test
    public void test4() {
        List<EmpVo> empList = empMapper.findByPage(new PageBean());
        for (EmpVo emp: empList) {
            System.err.println(emp);
        }

    }

    @Test
    public void test5() {
        try {
            int a= 5/0;
            System.out.println("0");
        }catch (Exception e)
        {
            System.out.println("1");
        }
        finally {
            System.out.println("2");
        }



    }
}
