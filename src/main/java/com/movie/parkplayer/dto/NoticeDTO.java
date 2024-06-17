package com.movie.parkplayer.dto;

import com.movie.parkplayer.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private Long notiId;

    private String memId;

    private String notiTitle;

    private String notiCategory;

    private String notiContent;

    private Date notiDate;

    public Notice toNotice() {
        return Notice.builder()
                .notiId(notiId)
                .memId(memId)
                .notiCategory(notiCategory)
                .notiTitle(notiTitle)
                .notiContent(notiContent)
                .notiDate(notiDate)
                .build();
    }

}
