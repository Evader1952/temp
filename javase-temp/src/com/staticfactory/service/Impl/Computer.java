package com.staticfactory.service.Impl;

import com.staticfactory.service.PlayGame;

public class Computer implements PlayGame {

    @Override
    public void game() {
        System.out.println("玩电脑游戏");
    }
}
