package com.mp.service.impl;

import com.mp.mapper.DepMapper;
import com.mp.pojo.Dep;
import com.mp.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepServiceImpl implements DepService {

    @Autowired
    private DepMapper depMapper;

    @Override
    public Dep findByDepId(Integer depid) {
        return depMapper.findByDepId(depid);
    }
}
