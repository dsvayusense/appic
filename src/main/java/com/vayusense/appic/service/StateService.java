package com.vayusense.appic.service;

import com.vayusense.appic.dto.ActionDto;
import com.vayusense.appic.dto.ControllerDto;
import com.vayusense.appic.dto.MonitoredDto;
import com.vayusense.appic.dto.StateDto;
import com.vayusense.appic.entities.Controller;
import com.vayusense.appic.errorhandler.BusinessException;
import com.vayusense.appic.errorhandler.ResourceNotFoundException;
import com.vayusense.appic.persistence.paging.PageSupport;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.persistence.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Comparator;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StateService {
    private final StateRepository stateRepository;

    public Mono<PageSupport<State>> findAll(Integer page, Integer size, String order) {
        Pageable paging = PageRequest.of(page,size/*,Sort.by("fermenterVolInL").descending()*/);
        return stateRepository.findAll()
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                                .stream()
                                .skip(paging.getPageNumber() * paging.getPageSize())
                                .limit(paging.getPageSize())
                                //.sorted(Comparator.comparingInt(State::getFermenterVolInL).reversed())
                                .collect(Collectors.toList()),
                        paging.getPageNumber(), paging.getPageSize(), list.size())).
                        switchIfEmpty(Mono.error(new ResourceNotFoundException("No found with paging: " + page)));
    }

    public Mono<ActionDto> saveState(State state) {
        ModelMapper mapper = new ModelMapper();
        return stateRepository.insert(state).onErrorMap(e -> new BusinessException(e.getMessage())).log()
                .map(statem ->{
                           ControllerDto controllerMap = mapper.map(statem.getController(), ControllerDto.class);
                           return new ActionDto(statem.getBatchId(), statem.getFermenterVolInL(), statem.getBatchStartDate(),
                                   statem.getFermenterName(),statem.getBatchAgeInMin(), statem.getBatchSerialNumber(), controllerMap);
        });

    }

    public Mono<StateDto> findById(String id) {
        ModelMapper mapper = new ModelMapper();
        return stateRepository.findById(id).log().map(statem -> {
            MonitoredDto monitoreDto = mapper.map(statem.getMonitored(), MonitoredDto.class);
            ControllerDto controllerMap = mapper.map(statem.getController(), ControllerDto.class);
            return new StateDto(statem.getBatchId(), statem.getFermenterVolInL(), statem.getBatchStartDate(),
                    statem.getFermenterName(),statem.getBatchAgeInMin(), statem.getBatchSerialNumber(),monitoreDto, controllerMap);
                });
    }



}
