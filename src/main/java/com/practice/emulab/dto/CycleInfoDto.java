package com.practice.emulab.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CycleInfoDto {
    private String mdn;   // 차량 번호

    // tid, mid, pv, did 는 디폴트 값으로 설정하기 때문에 받지 않음

    private String oTime;
    private String cCnt;
    private List<CycleDataDto> cList;
}