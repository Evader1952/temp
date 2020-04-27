package com.mp.pojo;

import lombok.Data;

@Data
public class Permission {

  private Integer pid;
  private String name;
  private String desc;
  private String url;
  private Integer state;
  private Integer parentId;
  private String icon;




}
