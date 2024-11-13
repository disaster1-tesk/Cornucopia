package com.disaster.ioc.assembly;

import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisc{
    @Override
    public void play() {
        System.out.println("播放ing....");
    }
}
