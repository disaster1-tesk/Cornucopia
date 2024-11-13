package com.disaster.ioc.assembly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("CDPlayer")
public class CDPlayer implements MediaPlayer{
    private CompactDisc cd;


    @Autowired
    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    @Override
    public void play() {
        System.out.println(String.format("%s在播放ing...",this.getClass().getSimpleName()));
    }

//    @Autowired(required = false)//防止报null加一个false
    public void cdPlay(CompactDisc cd){
        System.out.println(String.format("%s在播放ing...",cd.getClass().getSimpleName()));
    }
}
