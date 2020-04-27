package com.gc.test;

import com.gc.factory.playFactory;
import com.gc.service.PlayGame;

public class PlayTest {

    public static void main(String[] args) {
        //普通工厂模式
        playFactory factiory = new playFactory();
        PlayGame game = factiory.play("phone");

        game.game();
    }
}
