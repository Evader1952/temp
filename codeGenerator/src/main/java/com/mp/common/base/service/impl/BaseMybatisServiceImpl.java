package com.mp.common.base.service.impl;

import com.mp.common.base.dao.mybatis.BaseMapper;
import com.mp.common.base.service.BaseService;
import com.mp.common.entity.Identifiable;
import com.mp.common.exception.DaoException;
import com.mp.common.utils.BeanUtil;
import com.mp.common.utils.DataUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author tuchuan
 * @description
 * @date 2019/3/14 17:56
 */
public abstract class BaseMybatisServiceImpl<T extends Identifiable<ID>, ID extends Serializable> implements BaseService<T, ID> {

    private static final String SQL_SELECT = "select";
    private static final String SQL_SELECTCOUNT = "selectCount";
    private static final String SQL_SELECTBYID = "selectById";
    private static final String SQL_INSERT = "insert";
    private static final String SQL_INSERTBATCH = "insertBatch";
    private static final String SQL_UPDATE = "update";
    private static final String SQL_UPDATESELECTIVE = "updateSelective";
    private static final String SQL_DELETE = "delete";

    /**
     * 数据访问层
     * @return
     * */
    protected abstract BaseMapper<T, ID> getBaseMapper();

    @Override
    public <Q extends T> Page<T> queryPage(Q query, Pageable pageable) {
        Assert.notNull(query, "查询条件不能为空");
        try {
            List<T> contentList = getBaseMapper().select(getParams(query, pageable));
            Long total = this.queryCount(query);
            return new PageImpl<T>(contentList, pageable, total);
        } catch (Exception e) {
            throw new DaoException(String.format("查询分页出错！语句：%s", getSqlName(SQL_SELECT)), e);
        }
    }

    @Override
    public T queryById(ID id) {
        Assert.notNull(id, "id不能为空");
        try {
            return getBaseMapper().selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException(String.format("根据ID查询对象出错！语句：%s", getSqlName(SQL_SELECTBYID)), e);
        }
    }

    @Override
    public <Q extends T> T queryOne(Q query) {
        Assert.notNull(query, "查询条件不能为空");
        try {
            List<T> list = queryList(query, PageRequest.of(0, 1));
            return DataUtil.isEmpty(list) ? null : list.get(0);
        } catch (Exception e) {
            throw new DaoException(String.format("查询列表出错！语句：%s", getSqlName(SQL_SELECT)), e);
        }
    }

    @Override
    public <Q extends T> List<T> queryList(Q query) {
        Assert.notNull(query, "查询条件不能为空");
        try {
            return getBaseMapper().select(getParams(query));
        } catch (Exception e) {
            throw new DaoException(String.format("查询列表出错！语句：%s", getSqlName(SQL_SELECT)), e);
        }
    }

    @Override
    public <Q extends T> List<T> queryList(Q query, Pageable pageable) {
        Assert.notNull(query, "查询条件不能为空");
        try {
            return getBaseMapper().select(getParams(query, pageable));
        } catch (Exception e) {
            throw new DaoException(String.format("查询分页列表出错！语句：%s", getSqlName(SQL_SELECT)), e);
        }
    }

    @Override
    public Long queryCount() {
        try {
            Long total = getBaseMapper().selectCount(null);
            return total;
        } catch (Exception e) {
            throw new DaoException(String.format("查询总量出错！语句：%s", getSqlName(SQL_SELECTCOUNT)), e);
        }
    }

    @Override
    public <Q extends T> Long queryCount(Q query) {
        Assert.notNull(query, "查询条件不能为空");
        try {
            Long total = getBaseMapper().selectCount(getParams(query));
            return total;
        } catch (Exception e) {
            throw new DaoException(String.format("条件查询总量出错！语句：%s", getSqlName(SQL_SELECTCOUNT)), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T insert(T entity) {
        Assert.notNull(entity, "插入对象不能为空");
        try {
            getBaseMapper().insert(entity);
            return entity;
        } catch (Exception e) {
            throw new DaoException(String.format("插入对象出错！语句：%s", getSqlName(SQL_INSERT)), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<T> list) {
        Assert.notEmpty(list, "插入列表不能为空");
        try {
            getBaseMapper().insertBatch(list);
        } catch (Exception e) {
            throw new DaoException(String.format("插入对象出错！语句：%s", getSqlName(SQL_INSERTBATCH)), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T updateById(T entity) {
        Assert.notNull(entity, "修改对象不能为空");
        Assert.notNull(entity.getId(), "修改对象ID不能为空");
        try {
            getBaseMapper().updateById(entity);
            return entity;
        } catch (Exception e) {
            throw new DaoException(String.format("插入对象出错！语句：%s", getSqlName(SQL_UPDATE)), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T updateByIdSelective(T entity) {
        Assert.notNull(entity, "修改对象不能为空");
        Assert.notNull(entity.getId(), "修改对象ID不能为空");
        try {
            getBaseMapper().updateByIdSelective(entity);
            return entity;
        } catch (Exception e) {
            throw new DaoException(String.format("插入对象出错！语句：%s", getSqlName(SQL_UPDATESELECTIVE)), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(T entity) {
        Assert.notNull(entity, "删除对象不能为空");
        Assert.notNull(entity.getId(), "删除对象ID不能为空");
        try {
            getBaseMapper().deleteById(entity.getId());
        } catch (Exception e) {
            throw new DaoException(String.format("删除对象出错！语句：%s", getSqlName(SQL_DELETE)), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(ID id) {
        Assert.notNull(id, "删除对象ID不能为空");
        try {
            getBaseMapper().deleteById(id);
        } catch (Exception e) {
            throw new DaoException(String.format("删除对象出错！语句：%s", getSqlName(SQL_DELETE)), e);
        }
    }

    private String getSqlName(String method) {
        return new StringBuilder(this.getClass().getName()).append(".").append(method).toString();
    }


    /**
     * 获取查询参数
     *
     * @param query 查询对象
     * @return Map 查询参数
     */
    protected Map<String, Object> getParams(T query) throws Exception {
        return BeanUtil.objectToMap(query);
    }

    /**
     * 获取查询参数
     *
     * @param query    查询对象
     * @param pageable 分页对象
     * @return Map 查询参数
     */
    protected Map<String, Object> getParams(T query, Pageable pageable) throws Exception {
        Map<String, Object> params = BeanUtil.objectToMap(query);
        if (pageable != null) {
            params.put("offset", pageable.getOffset());
            params.put("limit", pageable.getPageSize());
            if (pageable.getSort() != null) {
                String sorting = pageable.getSort().toString();
                params.put("sorting", sorting.replace(":", ""));
            }
        }
        return params;
    }
}
