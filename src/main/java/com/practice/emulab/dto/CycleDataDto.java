package com.practice.emulab.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CycleDataDto {
    private String sec;
    private String gcd;
    private String lat;
    private String lon;
    private String ang;
    private String spd;
    private String sum;
    private String bat;
}