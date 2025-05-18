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

    private String sec;  // 발생 시간 초
    private String gcd;  // GPS 상태 (A,V,0)
    private String lat;  // 위도 X1000000
    private String lon;  // 경도 X1000000
    private String ang;  // 방향
    private String spd;  // 속도 (km/h)
    private String sum;  // 누적 주행 거리 (m)
    private String bat;  // 배터리 전압

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cycle_info_id")
    private CycleInfo cycleInfo;
}