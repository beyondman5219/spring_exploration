package com.beyond.man.config;

import com.beyond.man.music.CompactDisc;
import com.beyond.man.music.impl.SgtPapers;
import com.beyond.man.music.impl.SptPapers;
import com.beyond.man.play.MusicPlay;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 使用配置类的方式自动发现和装配Bean
 */
@Configuration
@ImportResource("classpath:SpringBean2.xml")
public class CDPlayConfig {

}
