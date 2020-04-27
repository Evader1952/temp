package com.mp.pojo;


import lombok.Data;

@Data
public class EmpVo {
    private  Integer id;
    private  String name;
    private  String sex;
    private  String time;
    private  Integer depid;
    private  Dep dep;
}
