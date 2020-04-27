package com.mp.common.base.service.impl;

import com.google.common.base.Preconditions;
import com.mp.common.base.dao.jpa.BaseRepository;
import com.mp.common.base.service.BaseService;
import com.mp.common.entity.Identifiable;
import com.mp.common.utils.BeanUtil;
import com.mp.common.utils.DataUtil;
import org.hibernate.Criteria;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.*;

/**
 * @author lvlu
 * @date 2019-01-19 11:18
 **/
public abstract class BaseServiceImpl<T extends Identifiable<ID>, ID extends Serializable> implements BaseService<T, ID> {

    /**
     * 数据访问层
     * @return
     * */
    public abstract BaseRepository<T, ID> getRepository();

    @Override
    public T queryById(ID id) {
        return getRepository().getOne(id);
    }

    @Override
    public <Q extends T> Page<T> queryPage(Q query, Pageable pageable) {
        Specification<T> specification = makeSimpleSpecification(query);
        return getRepository().findAll(specification, pageable);
    }

    @Override
    public <Q extends T> List<T> queryList(Q query) {
        Specification<T> specification = makeSimpleSpecification(query);
        return getRepository().findAll(specification);
    }

    @Override
    public <Q extends T> List<T> queryList(Q query, Pageable pageable) {
        Specification<T> specification = makeSimpleSpecification(query);
        Page<T> page = getRepository().findAll(specification, pageable);
        return page == null ? null : page.getContent();
    }

    @Override
    public Long queryCount() {
        return getRepository().count();
    }

    @Override
    public <Q extends T> Long queryCount(Q query) {
        Specification<T> specification = makeSimpleSpecification(query);
        return getRepository().count(specification);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T insert(T entity) {
        return getRepository().saveAndFlush(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<T> list) {
        Preconditions.checkArgument(DataUtil.isNotEmpty(list), "批量插入传入列表为空");
        Preconditions.checkArgument(list.size() <= 100,
                "列表数据过多，建议使用jdbcTemplate.updateBatch(...)重写本方法");
        getRepository().saveAll(list);
        getRepository().flush();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T updateById(T entity) {
        Preconditions.checkArgument(DataUtil.isNotEmpty(entity), "待修改的数据不能为空");
        Preconditions.checkArgument(DataUtil.isNotEmpty(entity.getId()), "待修改的数据不能为空");
        return getRepository().saveAndFlush(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T updateByIdSelective(T entity) {
        Preconditions.checkArgument(DataUtil.isNotEmpty(entity), "待修改的数据不能为空");
        Preconditions.checkArgument(DataUtil.isNotEmpty(entity.getId()), "待修改的数据不能为空");
        T target = getRepository().getOne(entity.getId());
        copyNonNullProperties(entity, target);
        return getRepository().saveAndFlush(entity);
    }

    private static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullProperties(src));
    }

    /**
     * 将为空的properties给找出来,然后返回出来
     *
     * @param src
     * @return
     */
    private static String[] getNullProperties(Object src) {
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> emptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            Object srcValue = srcBean.getPropertyValue(p.getName());
            if (srcValue == null) {
                emptyName.add(p.getName());
            }
        }
        String[] result = new String[emptyName.size()];
        return emptyName.toArray(result);
    }

    @Override
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    protected Specification<T> makeSimpleSpecification(final T query) {
        Specification<T> specification = (root, criteriaQuery, criteriaBuilder) -> {
            try {
                Map<String, Object> map = BeanUtil.objectToMap(query);
                Map<String, Criteria> criteriaMap = new HashMap<>();
                List<Predicate> predicates = new ArrayList<>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (entry.getKey().equals("pageNo")) {
                        continue;
                    }
                    Object obj = entry.getValue();
                    if (obj == null) {
                        continue;
                    }
                    if (entry.getKey().length() > 3) {
                        String key = entry.getKey();
                        String field = null;
                        Criteria criteria = null;

                        switch (key.substring(0, 3)) {
                            case "GT_":
                                field = key.substring(3);
                                if (obj instanceof Comparable) {
                                    predicates.add(criteriaBuilder.greaterThan(root.get(field), (Comparable) obj));
                                }
                                break;
                            case "EQ_":
                                field = key.substring(3);
                                predicates.add(criteriaBuilder.equal(root.get(field), obj));
                                break;
                            case "NE_":
                                field = key.substring(3);
                                predicates.add(criteriaBuilder.notEqual(root.get(field), obj));
                                break;
                            case "GTE":
                                field = key.substring(4);
                                if (obj instanceof Comparable) {
                                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(field), (Comparable) obj));
                                }
                                break;
                            case "LT_":
                                field = key.substring(3);
                                if (obj instanceof Comparable) {
                                    predicates.add(criteriaBuilder.lessThan(root.get(field), (Comparable) obj));
                                }
                                break;
                            case "LTE":
                                field = key.substring(4);
                                if (obj instanceof Comparable) {
                                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(field), (Comparable) obj));
                                }
                                break;
                            case "IN_":
                                field = key.substring(3);
                                CriteriaBuilder.In<T> in = criteriaBuilder.in(root.get(field));
                                if (obj instanceof Collection) {
                                    ((Collection<T>) obj).forEach(in::value);
                                }
                                predicates.add(in);
                                break;
                            default:
                                field = key;
                                predicates.add(criteriaBuilder.equal(root.get(field), obj));
                                break;
                        }
                    } else {
                        String field = entry.getKey();
                        predicates.add(criteriaBuilder.equal(root.get(field), entry.getValue()));
                    }
                }
                Predicate[] arr = new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(arr));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        return specification;
    }
}
