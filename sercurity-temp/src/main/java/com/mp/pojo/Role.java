package com.mp.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class Role  {

  private Integer roleid;
  private String roleName;
  private String mark;
  private String createTime;
  private Integer state;
  private Set<Permission> permissions;



}
