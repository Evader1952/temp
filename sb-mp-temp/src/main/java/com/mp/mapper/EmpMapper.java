package com.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.pojo.Emp;
import com.mp.pojo.EmpVo;
import com.mp.pojo.PageBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmpMapper extends BaseMapper<Emp> {

    @Select("SELECT * FROM `empinfo` WHERE id=#{id}")
    Emp findById(Integer id);

    List<EmpVo> findByPage(PageBean pageBean);
}
