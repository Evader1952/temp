package com.mp.common.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author duchong
 * @description
 * @date
 */
@Data
public class ExcelDto implements Serializable {

    private String[] headers;

    private String[] keys;

    private List<JSONObject> objectList;
}
