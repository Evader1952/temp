package com.mp.common.enums.mybatis;

import com.mp.common.enums.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lvlu
 * @date 2019-05-15 14:10
 **/
public class BaseEnumHandler<T extends BaseEnum<Integer>> extends BaseTypeHandler<T> {
    private Class<T> xclazz;
    private Method valuesMethod;

    public BaseEnumHandler() {
        this.xclazz = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments())[0];
        try {
            valuesMethod = xclazz.getMethod("values");
        } catch (Exception e) {
            throw new RuntimeException("can't get values method from " + xclazz);
        }
    }

    private T convertToEnum(Integer code) {
        try {
            if (code == null) {
                return null;
            }
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

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setInt(i, parameter.getCode());
        } else {
            ps.setObject(i, parameter.getCode(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convertToEnum(rs.getInt(columnName));
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convertToEnum(rs.getInt(columnIndex));
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convertToEnum(cs.getInt(columnIndex));
    }
}
