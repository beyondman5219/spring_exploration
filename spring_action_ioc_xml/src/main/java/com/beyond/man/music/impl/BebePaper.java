package com.beyond.man.music.impl;

import com.beyond.man.music.CompactDisc;


public class BebePaper implements CompactDisc {
    private String title = "哦 baby 你就是我的唯一……";
    private String artist = "你就是我的唯一";


    @Override
    public void paly() {
        System.out.println("Playing  = " + title);
    }
}
