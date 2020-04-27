package com.mp.mapper;


import com.mp.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	@Select("select * from user where name=#{name}")
	 User findByName(String name);

	@Select("select * from user where id=#{id}")
	 User findById(Integer id);
}
