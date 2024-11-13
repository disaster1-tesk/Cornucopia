package com.disaster.mode.behavior.visitor;

public class Success extends Action {
    @Override
    public void getManResult(Man man) {
        System.out.println(man.getName()+"给的评价为：该歌手很成功");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println(woman.getName()+"给的评价为：该歌手很成功");
    }
}
