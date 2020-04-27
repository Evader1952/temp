package com.staticfactory.service.Impl;

import com.staticfactory.service.PlayGame;

public class Phone implements PlayGame {
    @Override
    public void game() {
        System.out.println("玩手机游戏");
    }
}
