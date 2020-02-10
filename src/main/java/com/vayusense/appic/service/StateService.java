package com.vayusense.appic.service;

import com.vayusense.appic.persistence.paging.PageSupport;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.persistence.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StateService {
    private final StateRepository stateRepository;

    public Mono<PageSupport<State>> findAll(int page, int size, String order) {
        Pageable paging = PageRequest.of(page,size,Sort.by("co2").descending());
        return stateRepository.findAll()
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                                .stream()
                                .skip(paging.getPageNumber() * paging.getPageSize())
                                .limit(paging.getPageSize())
                                .sorted(Comparator.comparingInt(State::getCo2).reversed())
                                .collect(Collectors.toList()),
                        paging.getPageNumber(), paging.getPageSize(), list.size()));
    }

    public void saveState(State state){
        stateRepository.save(state).subscribe();
    }



}
