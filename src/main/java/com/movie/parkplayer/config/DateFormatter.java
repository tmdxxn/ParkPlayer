package com.movie.parkplayer.config;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

// (추후 삭제 예정)
// 회원가입시 생년월일 Date -> 문자열로 변환
public class DateFormatter implements Formatter<Date> {

    private final SimpleDateFormat dateFormat;

    public DateFormatter(String pattern) {
        this.dateFormat = new SimpleDateFormat(pattern);
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return dateFormat.parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        return dateFormat.format(object);
    }
}
