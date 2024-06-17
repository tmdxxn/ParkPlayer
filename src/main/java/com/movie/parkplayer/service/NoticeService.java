package com.movie.parkplayer.service;

import com.movie.parkplayer.entity.Notice;
import com.movie.parkplayer.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;
    
    //저장 및 글생성 메서드
    public void save(Notice notice) {
        noticeRepository.save(notice);
    }
    
    //글 전체 보기 메서드
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    public Optional<Notice> findById(Long id) {
        return noticeRepository.findById(id);
    }

    @Transactional
    public boolean deleteNoticeById(Long id) {
        return noticeRepository.findById(id)
                .map(notice -> {
                    noticeRepository.delete(notice);
                    return true;
                })
                .orElse(false);
    }
}
