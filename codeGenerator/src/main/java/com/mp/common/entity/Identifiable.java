package com.mp.common.entity;

import java.io.Serializable;

/**
 * @author lvlu
 * @date 2019-02-25 19:04
 **/
public interface Identifiable<ID extends Serializable> extends Serializable {

    ID getId();

    void setId(ID id);
}
