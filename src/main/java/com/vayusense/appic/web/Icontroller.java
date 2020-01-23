package com.vayusense.appic.web;

//import com.vayusense.appic.config.Config;
import com.vayusense.appic.dto.ErrorModel;
import com.vayusense.appic.dto.StateDto;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.persistence.paging.PageSupport;
import com.vayusense.appic.service.RabbitMQSender;
import com.vayusense.appic.service.StateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@Api(value = "state", description = "the state API")
@RequestMapping("/api/v1")
public class Icontroller {

    private final StateService stateService;
    private final RabbitMQSender rabbitMQSender;
    //private final Config config;

   /* @GetMapping("/service")
    public String getServiceName() {
        return config.getServiceName();
    }*/

    @ApiOperation(value = "", notes = "retrieve a state list with paging", response = PageSupport.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "succsess state response", response = PageSupport.class),
            @ApiResponse(code = 400, message = "bad request", response = ErrorModel.class),
            @ApiResponse(code = 401, message = "authorization error", response = ErrorModel.class),
            @ApiResponse(code = 401, message = "forbidden", response = ErrorModel.class),
            @ApiResponse(code = 404, message = "not found", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "server error", response = ErrorModel.class) })
    @GetMapping("/state")
    public ResponseEntity<Mono<PageSupport<State>>> findAll(@RequestParam("page") int page , @RequestParam("size")int size,
                                                            @RequestParam("order") String order){
        return ResponseEntity.ok().body(stateService.findAll(page, size, order));

    }

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("co2") Integer co2,@RequestParam("ph") Integer ph){
        StateDto state = new StateDto();
        state.setCo2(co2);
        state.setPh(ph);
        rabbitMQSender.send(state);
        return  "Message sent to the RabbitMQ JavaInUse Successfully";
    }
    @GetMapping(value = "/producerapp2")
    public String producerapp2(@RequestParam("co2") Integer co2,@RequestParam("ph") Integer ph){
        StateDto state = new StateDto();
        state.setCo2(co2);
        state.setPh(ph);
        rabbitMQSender.sendToApp2(state);
        return  "Message sent to the RabbitMQ JavaInUse Successfully";
    }




}
