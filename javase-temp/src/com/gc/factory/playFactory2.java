package com.gc.factory;

import com.gc.service.Impl.Computer;
import com.gc.service.Impl.Phone;
import com.gc.service.PlayGame;

public class playFactory2 {

    //多个工厂方法模式
    //工厂类单独定义了不同的方法q去实例化接口实现类
    //好处：方便拓展
    public PlayGame  producePhone(){
        return  new Phone();
    }

    public PlayGame  produceComputer(){
        return  new Computer();
    }
}
