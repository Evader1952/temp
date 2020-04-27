package com.mp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor  //无参构造
@AllArgsConstructor  //有参构造
public class User implements Serializable {

    private String name;
    private String pwd;
    private Integer age;
    private Date creationTime;

}
