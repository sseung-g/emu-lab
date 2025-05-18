package com.practice.emulab.service;

import com.practice.emulab.domain.CycleData;
import com.practice.emulab.domain.CycleInfo;
import com.practice.emulab.dto.CycleDataDto;
import com.practice.emulab.repository.CycleInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmulatorService {
    private final CycleInfoRepository cycleInfoRepository;
    private boolean isEmulatorRunning  = false; // 에뮬레이터 상태
    private final Queue<CycleDataDto> cycleDataBuffer = new LinkedList<>();
    private int secondCount = 0; // 몇 초 지났는지 센다
    private Random random = new Random(); // 랜덤값 생성용


    public void turnOn(){
        isEmulatorRunning = true;
        log.info("Emulator start");
    }


    public void turnOff(){
        isEmulatorRunning = false;
        log.info("Emulator stop");
    }

    // 에뮬레이터 상태 확인
    public boolean isOn() {
        return isEmulatorRunning;
    }


    // 1초마다 실행되는 함수 (스케줄러 사용)
    @Scheduled(fixedRate = 1000) // 1000ms = 1초마다 호출
    public void generateDataEverySecond() {
        // 에뮬레이터가 꺼져있으면 아무 작업도 하지 않음
        if (!isEmulatorRunning) {
            return;
        }

        // 1초가 지났으니 카운터 증가
        secondCount++;

        // 새 데이터 생성 - 랜덤값 포함
        CycleDataDto data = CycleDataDto.builder()
                .sec(String.valueOf(secondCount))               // int → String 변환
                .gcd("A")
                .lat(String.valueOf(4140338L + random.nextInt(100)))   // long → String 변환
                .lon(String.valueOf(217403L + random.nextInt(100)))
                .ang(String.valueOf(270))
                .spd(String.valueOf(100))
                .sum(String.valueOf(10000))
                .bat(String.valueOf(100))
                .build();

        // 리스트에 새 데이터 추가
        cycleDataBuffer.offer(data);
        log.info("1초 데이터 생성 - sec: {}", secondCount);

        // 60초가 되면 저장 및 초기화
        if (cycleDataBuffer.size() >= 60) {
            saveCycleInfoToDB();
            cycleDataBuffer.clear();
            secondCount = 0;
        }

    }

//     DB에 데이터를 저장하는 함수 (트랜잭션 포함)
    @Transactional
    public void saveCycleInfoToDB() {
        // 저장할 데이터 없으면 종료
        if (cycleDataBuffer.isEmpty()) {
            log.info("저장할 데이터가 없습니다.");
            return;
        }

        // 현재 시간 문자열 만들기 (yyyyMMddHHmm)
        String oTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));


// DTO 리스트를 복사 (Queue에서 안전하게 복사)
        List<CycleDataDto> listToSave = new ArrayList<>(cycleDataBuffer);

// CycleInfo 엔티티 생성
        CycleInfo cycleInfo = CycleInfo.builder()
                .mdn("01234567890")
                .tid("A001")
                .mid("6")
                .pv("5")
                .did("1")
                .oTime(oTime)
                .cCnt(String.valueOf(listToSave.size()))
                .build();

// CycleData 엔티티 리스트 생성
        List<CycleData> entityList = listToSave.stream()
                .map(dto -> CycleData.builder()
                        .sec(dto.getSec())
                        .gcd(dto.getGcd())
                        .lat(dto.getLat())
                        .lon(dto.getLon())
                        .ang(dto.getAng())
                        .spd(dto.getSpd())
                        .sum(dto.getSum())
                        .bat(dto.getBat())
                        .cycleInfo(cycleInfo)  // 연관관계 매핑
                        .build())
                .collect(Collectors.toList());

// CycleInfo에 CycleData 리스트 세팅
        cycleInfo.setCList(entityList);

// DB에 저장
        cycleInfoRepository.save(cycleInfo);

        log.info("60초 데이터가 DB에 저장되었습니다. 저장된 데이터 수: {}", entityList.size());
    }
}
