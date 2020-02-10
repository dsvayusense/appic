package com.vayusense.appic.web;

//import com.vayusense.appic.config.Config;
import com.vayusense.appic.dto.DeviceEvent;
import com.vayusense.appic.dto.ErrorModel;
import com.vayusense.appic.dto.StateDto;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.facade.OrderServiceFacade;
import com.vayusense.appic.persistence.paging.PageSupport;
import com.vayusense.appic.service.DeviceService;
import com.vayusense.appic.service.RabbitMQSender;
import com.vayusense.appic.service.StateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Api(value = "state", description = "the state API")
@RequestMapping("/api/v1")
public class Icontroller {

    private final OrderServiceFacade facade;

    @GetMapping(value = "/device", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<DeviceEvent> getServiceName() throws IOException {
       return facade.eventPingRequest();

    }
    @GetMapping("/device/vayumeter")
    public Mono<DeviceEvent> pingRequestVayumeter() throws IOException {
        return facade.pingRequestVayumeter();
    }

    @ApiOperation(value = "", notes = "retrieve a state list with paging", response = PageSupport.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "succsess state response", response = PageSupport.class),
            @ApiResponse(code = 400, message = "bad request", response = ErrorModel.class),
            @ApiResponse(code = 401, message = "authorization error", response = ErrorModel.class),
            @ApiResponse(code = 402, message = "forbidden", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "not found", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "server error", response = ErrorModel.class) })
    @GetMapping("/state")
    public ResponseEntity<Mono<PageSupport<State>>> findAll(@RequestParam("page") int page , @RequestParam("size")int size,
                                                            @RequestParam("order") String order){
        return ResponseEntity.ok().body(facade.findAll(page, size, order));

    }

    @PostMapping(value = "/producer")
    public ResponseEntity<Mono<StateDto>> producer(@RequestBody StateDto stateDto){
        facade.send(stateDto);
        return  ResponseEntity.ok().body(Mono.just(stateDto));
    }


    @GetMapping(value = "/producerapp2")
    public String producerapp2(@RequestParam("co2") Integer co2,@RequestParam("ph") Integer ph){
        StateDto state = new StateDto();
        state.setCo2(co2);
        state.setPh(ph);
        facade.sendToApp2(state);
        return  "Message sent to the RabbitMQ JavaInUse Successfully";
    }




}
