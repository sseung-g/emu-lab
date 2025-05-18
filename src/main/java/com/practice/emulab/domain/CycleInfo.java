package com.practice.emulab.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cycle_info")
public class CycleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mdn;   // 차량 번호

    @Builder.Default
    private String tid = "A001";   // 터미널 아이디 (고정값)

    @Builder.Default
    private String mid = "6";      // 제조사 아이디 (고정값)

    @Builder.Default
    private String pv = "5";       // 패킷 버전 (고정값)

    @Builder.Default
    private String did = "1";      // 디바이스 아이디 (고정값)

    private String oTime;          // 발생 시간 (yyyyMMddHHmm)
    private String cCnt;           // 주기 정보 개수 (ex. 60)

    @OneToMany(mappedBy = "cycleInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CycleData> cList;
}