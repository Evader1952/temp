package com.mp.mapper;

import com.mp.pojo.Dep;
import org.apache.ibatis.annotations.Select;


public interface DepMapper {

    @Select(" SELECT * FROM `depinfo` WHERE `depid`=#{depid}")
    Dep findByDepId(Integer depid);
}
