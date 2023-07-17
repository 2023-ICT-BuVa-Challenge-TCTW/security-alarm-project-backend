package com.example.buva.timer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TimerSetReq(@Min(1) long durationInSeconds, @NotNull @NotBlank String alarmId) {}
    
