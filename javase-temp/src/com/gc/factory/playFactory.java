package com.gc.factory;

import com.gc.service.Impl.Computer;
import com.gc.service.Impl.Phone;
import com.gc.service.PlayGame;

public class playFactory {

    /**
     * 普通工厂模式：特点：
     * @param type  实现类的名称
     * @return 共同实现的接口
     */
    public PlayGame  play(String type){
        if("phone".equals(type)){
           return  new Phone();
        }else  if("computer".equals(type)){
            return  new Computer();
        }else{
            System.err.print("请输入类型");
            return  null;
        }
    }
}
