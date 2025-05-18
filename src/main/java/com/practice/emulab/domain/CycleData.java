package com.practice.emulab.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cycle_data")
public class CycleData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mdn;   // 차량 번호
    private String tid;   // 'A001'로 고정
    private String mid;   // '6' 고정 (제조사 아이디)
    private String pv;    // '5' 고정 (패킷 버전)
    private String did;   // '1' 고정 (디바이스 아이디)

    private LocalDateTime oTime;  // 데이터 발생 시간

    private int sec;     // 발생시간 초 (0~59)
    private String gcd;  // GPS 상태 ('A', 'V', '0')
    private int lat;     // 위도
    private int lon;     // 경도
    private int ang;     // 방향 (0~365)
    private int spd;     // 속도 (km/h)
    private int sum;     // 누적 주행거리 (m)
    private int bat;     // 배터리 전압

}
