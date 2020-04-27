package com.mp.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class User {

  private Integer id;
  private String name;
  private String password;
  private String perms;
  private Set<Role> roles;


}
