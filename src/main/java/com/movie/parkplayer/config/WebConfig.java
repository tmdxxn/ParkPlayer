package com.movie.parkplayer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

// 날짜 형태 변환
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Date 타입 -> "yyyy-MM-dd" 형식으로 변환
        registry.addFormatterForFieldType(Date.class, new DateFormatter("yyyy-MM-dd"));
    }
}
