package com.example.buva.timer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TimerCancelReq(@NotNull @NotBlank String alarmId) {}
    
