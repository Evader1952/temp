package com.mp.common.base.service;

import com.mp.common.entity.Identifiable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @author lvlu
 * @date 2019-01-19 11:18
 **/
public interface BaseService<T extends Identifiable<ID>, ID extends Serializable> {
    <Q extends T> Page<T> queryPage(Q query, Pageable pageable);

    T queryById(ID id);

    <Q extends T> T queryOne(Q query);

    <Q extends T> List<T> queryList(Q query);

    <Q extends T> List<T> queryList(Q query, Pageable pageable);

    Long queryCount();

    <Q extends T> Long queryCount(Q query);

    T insert(T entity);

    void insertBatch(List<T> list);

    T updateById(T entity);

    T updateByIdSelective(T entity);

    void delete(T entity);

    void deleteById(ID id);


}
