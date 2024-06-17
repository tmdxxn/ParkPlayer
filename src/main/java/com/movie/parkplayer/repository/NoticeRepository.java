package com.movie.parkplayer.repository;

import com.movie.parkplayer.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice , Long> {

}
