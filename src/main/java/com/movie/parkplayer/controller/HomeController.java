package com.movie.parkplayer.controller;

import com.movie.parkplayer.entity.Notice;
import com.movie.parkplayer.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private NoticeService noticeService;

    // 메인페이지
    @GetMapping("/main")
    public String welcome(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "index";
    }
    
    //전체 공지사항가는 메서드
    @GetMapping("/postwrite")
    public String adminNotice(Model model) {
        List<Notice> notices = noticeService.getAllNotices(); // NoticeService에서 공지사항 목록을 가져오는 메서드 호출
        model.addAttribute("notices", notices); // 모델에 공지사항 목록 추가
        return "postwrite";
    }
    
    //공지사항-글작성 클릭시 글작성하기페이지로 가는 메서드
    @GetMapping("/creatAd")
    public String createAdmin() {
        return "postwrite";
    }
    
    //글작성하기 페이지에서 글등록하기 버튼 메서드
    @GetMapping("/write")
    public String updateAm(@RequestParam(value = "title") String title,
                           @RequestParam(value = "category") String category,
                           @RequestParam(value = "content") String content, Model model) {
        Date nowdate = new Date();

        // 현재 로그인한 사용자의 정보를 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername(); // 사용자의 이름 또는 다른 정보 가져오기
        }

        Notice newNotice = Notice.builder()
                .mem_id(username)
                .noti_title(title)
                .noti_category(category)
                .noti_content(content)
                .noti_date(nowdate)
                .build();

        noticeService.save(newNotice);
        model.addAttribute("notice", newNotice);
        model.addAttribute("message", "공지사항이 성공적으로 생성되었습니다.");
        return "redirect:/adminPostview";
    }

    //글 상세페이지 이동 메서드
    @GetMapping("/notices/{id}/show")
    public String show(@PathVariable("id") Long id, Model model) {
        Optional<Notice> notice = noticeService.findById(id);
        if(notice.isPresent()) {
            model.addAttribute("notice",notice.get());
        }
        return "adminNotice";
    }

    //수정페이지 이동 메서드
    @GetMapping("/notices/{id}/edit")
    public String updateMeuu(@PathVariable("id") Long id, Model model) {
        System.out.println("Received ID: " + id);
        Optional<Notice> notice = noticeService.findById(id);
        if(notice.isPresent()) {
//            String title = notice.get().getTitle();
//            String content = notice.get().getContent();
            model.addAttribute("notice", notice.get());
            return "updateAdmin";
        }else {
            return "redirect:/admin";
        }
    }

    //글 수정 메서드
//    @PostMapping("/notices/{id}/edit")
//    public String updateNotice(@PathVariable("id") Long id,
//                               @RequestParam("title") String title,
//                               @RequestParam("content") String content) {
//        Optional<Notice> noticeOptional = noticeService.findById(id);
//        if (noticeOptional.isPresent()) {
//            Notice notice = noticeOptional.get();
//            notice.setTitle(title);
//            notice.setContent(content);
//            noticeService.save(notice);
//            return "redirect:/admin";
//        } else {
//            return "redirect:/admin";         }
//    }
    
    //patch사용
//    @PatchMapping("/notices/{id}/edit")
//    public ResponseEntity<Void> updateNotice(@PathVariable("id") Long id, @RequestBody Notice updatedNotice) {
//        Optional<Notice> noticeOptional = noticeService.findById(id);
//        if (noticeOptional.isPresent()) {
//            Notice notice = noticeOptional.get();
//            notice.setTitle(updatedNotice.getTitle());
//            notice.setContent(updatedNotice.getContent());
//            noticeService.save(notice);
//            return ResponseEntity.ok().build();
//        }else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    //글삭제 버튼 메서드
//    @DeleteMapping("/notices/{id}/delete")
//    public ResponseEntity<Void> deleteNotice(@PathVariable("id") Long id) {
//        boolean deleted = noticeService.deleteNoticeById(id);
//        if (deleted) {
//            return ResponseEntity.noContent().build(); // 삭제가 성공하면 204 No Content 상태 코드를 반환합니다.
//        } else {
//            return ResponseEntity.notFound().build(); // 삭제 대상이 없으면 404 Not Found 상태 코드를 반환합니다.
//        }
//    }


}
