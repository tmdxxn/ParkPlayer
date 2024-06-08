package com.movie.parkplayer.dto;

import com.movie.parkplayer.entity.Notice;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private Long noti_id;

    private String mem_id;

    private String noti_title;

    private String noti_category;

    private String noti_content;

    private Date noti_date;

    public Notice toNotice() {
        return Notice.builder()
                .noti_id(noti_id)
                .mem_id(mem_id)
                .noti_category(noti_category)
                .noti_title(noti_title)
                .noti_content(noti_content)
                .noti_date(noti_date)
                .build();
    }

}
