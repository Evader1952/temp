package com.mp.test.mapper;

import com.mp.common.base.dao.mybatis.BaseMapper;
import com.mp.test.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @date 2019-12-21 11:25:32
 **/
@Mapper
public interface UserMapper extends BaseMapper<User,Long> {


}
