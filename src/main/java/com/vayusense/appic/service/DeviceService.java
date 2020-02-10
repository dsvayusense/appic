package com.vayusense.appic.service;

import com.vayusense.appic.dto.DeviceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Service
@Slf4j
public class DeviceService {

    @Value("${monitor.ipaddress}")
    private String ipAddress;

    public Flux<DeviceEvent> eventPingRequest() throws UnknownHostException, IOException
    {
        Flux fluxping = null;
        boolean flag = false;
        InetAddress geek = InetAddress.getByName(ipAddress);
        System.out.println("Sending Ping Request to " + ipAddress);
        if (geek.isReachable(5000))
             flag = true;
        else
            //log and send email
            flag = false;
        boolean finalFlag = flag;
        return  fluxping = Mono.just(geek).flatMapMany(s -> {

                     if ( finalFlag == true) {
                         Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
                          Flux<DeviceEvent> deviceEventFlux =
                                 Flux.fromStream(
                                         Stream.generate(() -> new DeviceEvent(s.getHostName(),
                                                 new Date()))
                                 );
                          return  Flux.zip(interval, deviceEventFlux).map(Tuple2::getT2);

                     }else {
                         //send some mail and log
                         log.info(" Host is not reachable ");
                         return  null;
                     }


        });

    }

    public Mono<DeviceEvent> pingRequestVayumeter() throws UnknownHostException {
        InetAddress geek = InetAddress.getByName(ipAddress);
        DeviceEvent deviceEvent = new DeviceEvent(geek.getHostName(),new Date());
        Mono<DeviceEvent> monoVayumeter = Mono.just(deviceEvent);
        return monoVayumeter;

    }
}
