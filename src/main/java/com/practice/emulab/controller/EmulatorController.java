package com.practice.emulab.controller;

import com.practice.emulab.service.EmulatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emulator")
@RequiredArgsConstructor
public class EmulatorController {
    private final EmulatorService emulatorService;

    @PostMapping("/start")
    public String start(){
        emulatorService.turnOn();
        return "Emulator 시작";
    }

    @PostMapping("/stop")
    public String stop(){
        emulatorService.turnOff();
        return "Emulator 종료";
    }
}
