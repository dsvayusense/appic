package com.vayusense.appic.web;

import com.vayusense.appic.dto.DeviceEvent;
import com.vayusense.appic.dto.ResponseError;
import com.vayusense.appic.dto.StateDto;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.errorhandler.ResourceBadReqException;
import com.vayusense.appic.facade.OrderServiceFacade;
import com.vayusense.appic.persistence.paging.PageSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.*;
import java.io.IOException;

@RestController
@AllArgsConstructor
@Api(value = "state", description = "the state API")
@RequestMapping("/api/v1")
@Validated
public class Icontroller {

    private final OrderServiceFacade facade;

    @GetMapping(value = "/device", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<DeviceEvent> getServiceName() throws IOException {
       return facade.eventPingRequest();

    }
    @GetMapping("/device/vayumeter")
    public Mono<ResponseEntity<DeviceEvent>> pingRequestVayumeter() throws IOException {
        return facade.pingRequestVayumeter().map(deviceEvent -> ResponseEntity.ok(deviceEvent));
    }

    @ApiOperation(value = "", notes = "retrieve a state list with paging", response = PageSupport.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "succsess state response", response = PageSupport.class),
            @ApiResponse(code = 400, message = "bad request", response = ResponseError.class),
            @ApiResponse(code = 401, message = "authorization error", response = ResponseError.class),
            @ApiResponse(code = 402, message = "forbidden", response = ResponseError.class),
            @ApiResponse(code = 404, message = "not found", response = ResponseError.class),
            @ApiResponse(code = 500, message = "server error", response = ResponseError.class) })
    @GetMapping("/page/state")
    public Mono<ResponseEntity<PageSupport<State>>> findAll(@RequestParam("page") @PositiveOrZero() Integer page ,  @RequestParam("size") @Min(1) Integer size,
                                                            @RequestParam("order") String order){
        return facade.findAll(page, size, order).map(statePageSupport -> ResponseEntity.ok(statePageSupport));
    }

    @GetMapping(value ={"/state", "/state/{id}"})
    public Mono<ResponseEntity<State>> findById(@PathVariable(value = "id",required = false) @NotNull String id){
        return facade.getStateById(id).map(state -> ResponseEntity.ok(state));
    }

    @PostMapping(value = "/state")
    public Mono<ResponseEntity<StateDto>> producer(@RequestBody @Valid StateDto stateDto){
        facade.send(stateDto);
        return Mono.just(ResponseEntity.ok(stateDto));
    }


    @GetMapping(value = "/producerapp2")
    public String producerapp2(@RequestParam("co2") Integer co2,@RequestParam("ph") Integer ph) {
        StateDto state = new StateDto();
        state.setCo2(co2);
        state.setPh(ph);
        facade.sendToApp2(state);
        return  "Message sent to the RabbitMQ JavaInUse Successfully";
    }




}
