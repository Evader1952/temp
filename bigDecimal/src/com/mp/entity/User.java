package com.mp.entity;

import java.util.Date;

/**
 * @author duchong
 * @description 用户
 * @date 2019-8-3 10:05:37
 */

public class User  {

    private Long id;

    private String uid;

    private String username;

    private String password;

    private String mobile;

    private String nickName;

    private Date createTime;

    private Integer state;

    private Integer type;

    private String salt;

    private String storeMarkCode;

    private String pwd;

    public enum State {
        /**
         * 开启
         */
        OPEN(1),
        /**
         * 关闭
         */
        CLOSE(0);

        private Integer code;

        State(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }


    public enum Type {
        /**
         * 管理员
         */
        ADMIN(0),
        /**
         * 门店角色
         */
        USER(1),
        /**
         * 商户角色
         */
        MANAGE(2);

        private Integer code;

        Type(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStoreMarkCode() {
        return storeMarkCode;
    }

    public void setStoreMarkCode(String storeMarkCode) {
        this.storeMarkCode = storeMarkCode;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
