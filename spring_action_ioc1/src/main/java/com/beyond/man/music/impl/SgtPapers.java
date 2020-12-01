package com.beyond.man.music.impl;

import com.beyond.man.music.CompactDisc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component("sgtPapers")
public class SgtPapers implements CompactDisc {
    private String title = "哦 baby 你就是我的唯一……";
    private String artist = "你就是我的唯一";


    @Override
    public void paly() {
        System.out.println("Playing  = " + title);
    }
}
