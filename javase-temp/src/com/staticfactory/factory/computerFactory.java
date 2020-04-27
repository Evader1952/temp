package com.staticfactory.factory;

import com.staticfactory.service.Impl.Computer;
import com.staticfactory.service.PlayGame;
import com.staticfactory.service.factoryType;

public class computerFactory implements factoryType {
    @Override
    public PlayGame playGameType() {
        return new Computer();
    }
}
