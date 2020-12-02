package com.beyond.man.music.impl;

import com.beyond.man.music.CompactDisc;


public class BebePaper implements CompactDisc {
    private String title = "喜欢你……";
    private String artist = "黑凤梨";


    @Override
    public void paly() {
        System.out.println("Playing  = " + title);
    }
}
