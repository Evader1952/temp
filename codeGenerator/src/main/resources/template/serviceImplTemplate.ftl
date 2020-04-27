package ${package}.service.impl;

import ${commonPack}.base.dao.mybatis.BaseMapper;
import ${commonPack}.base.service.impl.BaseMybatisServiceImpl;
import ${fullClassName};
import ${package}.mapper.${className?cap_first}Mapper;
import ${package}.service.${className?cap_first}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ${user}
 * @date ${date}
 **/
@Service
public class ${className?cap_first}ServiceImpl extends BaseMybatisServiceImpl<${className?cap_first},Long> implements ${className?cap_first}Service {

    @Autowired
    private ${className?cap_first}Mapper ${className}Mapper;


    @Override
    protected BaseMapper<${className?cap_first}, Long> getBaseMapper() {
        return ${className}Mapper;
    }
}
