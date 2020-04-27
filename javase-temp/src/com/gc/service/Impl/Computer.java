package com.gc.service.Impl;

import com.gc.service.PlayGame;

public class Computer implements PlayGame {

    @Override
    public void game() {
        System.out.println("玩电脑游戏");
    }
}
