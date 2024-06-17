//package com.movie.parkplayer.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.io.Serializable;
//
//// 구독정보 엔티티
//@Entity
//@Table(name = "mem_subscribe")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class SubscribeEntity implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "mem_num", nullable = false)
//    private UserEntity member;
//
//    @Column(nullable = false)
//    private Boolean subscriptionStatus = false; // 기본값으로 false 설정
//}