package com.mp.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("empinfo")
public class Emp {
    @TableId("id")
    private  Integer id;
    private  String name;
    private  String sex;
    private  String time;
    private  Integer depid;

}
