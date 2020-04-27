package com.mp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnumType {

    private  String name;
    private  String sex;
    private  Integer state;


    public  enum  State{
        admin(1,"管理员"),
        user(2,"用户");

        State(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private  Integer  code;
        private  String desc;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public  String getStateDesc(){
        if (State.admin.getCode().equals(state)){
            return  State.admin.getDesc();
        }else  if (State.user.getCode().equals(state)){
            return  State.user.getDesc();
        }
        return  null;
    }
}
