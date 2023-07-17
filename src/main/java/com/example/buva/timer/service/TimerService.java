package com.example.buva.timer.service;

import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.buva.timer.dto.TimerCancelReq;
import com.example.buva.timer.dto.TimerSetReq;

import lombok.RequiredArgsConstructor;

@Service
public class TimerService {
    private final ThreadPoolTaskScheduler taskScheduler;
    private final Map<String, ScheduledFuture<?>> alarmsMap;

    public TimerService() {
        this.taskScheduler = new ThreadPoolTaskScheduler();
        this.taskScheduler.initialize();
        this.alarmsMap = new ConcurrentHashMap<>();
    }
    // 만드는 중 무슨 스케쥴러 써야할지 고민
    
    // @Async
    // @Transactional
    // public ResponseEntity<?> setAlarm(TimerSetReq timerSetReq) {
    //     try {
    //         ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(() -> {
    //             System.out.println("알림 울리는 기능 만들기");
    //             Thread.sleep(timerSetReq.durationInSeconds());
    //         }, null);
    //         alarmsMap.put(timerSetReq.alarmId(), scheduledFuture);

    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
    //     return ResponseEntity.ok().body(null);
    // }

    @Transactional
    public ResponseEntity<?> cancelAlarm(TimerCancelReq timerCancelReq) {
        ScheduledFuture<?> scheduledFuture = alarmsMap.get(timerCancelReq.alarmId());
        if (!scheduledFuture.isDone()) {
            scheduledFuture.cancel(false);
            // DB
        }
        alarmsMap.remove(timerCancelReq.alarmId());
        return ResponseEntity.ok().body(null);
    }

}
