package com.staticfactory.factory;

import com.staticfactory.service.Impl.Phone;
import com.staticfactory.service.PlayGame;
import com.staticfactory.service.factoryType;

public class phoneFactory implements factoryType {

    @Override
    public PlayGame playGameType() {
        return new Phone();
    }
}


