package com.mp.common.enums.jpa;


import com.mp.common.enums.BaseEnum;

import javax.persistence.AttributeConverter;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @author lvlu
 * @date 2019-05-10 14:22
 **/
public abstract class BaseEnumConverter<T extends BaseEnum<Integer>> implements AttributeConverter<T, Integer> {

    private Class<T> xclazz;
    private Method valuesMethod;

    @SuppressWarnings("unchecked")
    public BaseEnumConverter() {
        this.xclazz = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments())[0];
        try {
            valuesMethod = xclazz.getMethod("values");
        } catch (Exception e) {
            throw new RuntimeException("can't get values method from " + xclazz);
        }
    }

    @Override
    public Integer convertToDatabaseColumn(T t) {
        return t == null ? null : t.getCode();
    }

    @Override
    public T convertToEntityAttribute(Integer code) {
        try {
            T[] values = (T[]) valuesMethod.invoke(null);
            for (T t : values) {
                if (t.getCode().equals(code)) {
                    return t;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("can't convertToEntityAttribute" + e.getMessage());
        }
        throw new RuntimeException("unknown dbData " + code);
    }
}
