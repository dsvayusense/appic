package com.vayusense.appic.service;

import com.vayusense.appic.dto.StateDto;
import com.vayusense.appic.entities.MachineLearningLog;
import com.vayusense.appic.errorhandler.BusinessException;
import com.vayusense.appic.persistence.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    public void saveLog(MachineLearningLog mlLog) {
        log.info("start -> insert log ");
        logRepository.insert(mlLog).onErrorMap(e -> new BusinessException(e.getMessage())).log().subscribe();

    }

    public MachineLearningLog maplog(StateDto stateDto) {

             MachineLearningLog machineLearningLog = new MachineLearningLog();
             machineLearningLog.setId(stateDto.getBatchId() + stateDto.getBatchAgeInMin());
             machineLearningLog.setBatchId(stateDto.getBatchId());
             machineLearningLog.setBatchAgeInMin(stateDto.getBatchAgeInMin());
             machineLearningLog.setBatchStartDate(stateDto.getBatchStartDate());
             machineLearningLog.setBatchSerialNumber(stateDto.getBatchSerialNumber());
             machineLearningLog.setFermenterName(stateDto.getFermenterName());
             machineLearningLog.setFermenterVolInL(stateDto.getFermenterVolInL());
             machineLearningLog.setMessage("success machine learning log");
             machineLearningLog.setModule("IC");
             machineLearningLog.setCalcDate(LocalDateTime.now());
             machineLearningLog.setLevel("info");
             return machineLearningLog;


    }
}
