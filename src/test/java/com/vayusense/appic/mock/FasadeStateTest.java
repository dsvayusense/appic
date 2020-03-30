package com.vayusense.appic.mock;

import com.vayusense.appic.dto.StateDto;
import com.vayusense.appic.entities.Controller;
import com.vayusense.appic.entities.Monitored;
import com.vayusense.appic.entities.State;
import com.vayusense.appic.facade.OrderServiceFacade;
import com.vayusense.appic.persistence.StateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FasadeStateTest {

    @Autowired
    private OrderServiceFacade orderServiceFacade;
    @MockBean
    StateRepository stateRepository;
    @Mock
    private ModelMapper modelMapper = new ModelMapper();
    private State state = new State();


    @Test
    public void testById()
    {
        state.setId("111A20");
        state.setBatchId("1234a");
        state.setBatchAgeInMin(1);
        state.setFermenterVolInL(1);
        state.setFermenterName("RnDA");
        state.setBatchSerialNumber(1000);
        Monitored monitored = new Monitored();
        monitored.setAmnTimeInMin(1);
        monitored.setCo2(2.1);
        state.setMonitored(monitored);
        Controller controller = new Controller();
        controller.setAgitationAction(1.2);
        controller.setBatchTimeInMin(2);
        state.setController(controller);
        when(stateRepository.findById("111A20")).thenReturn(Mono.just(state));
        Mono<StateDto> result = orderServiceFacade.findById("111A20");
        System.out.println(result.block());
        assertNotNull(result);
        verify(stateRepository, times(1)).findById("111A20");
    }

    @Test
    public void createState() {
        state.setId("311A20");
        state.setBatchId("12234a");
        state.setBatchAgeInMin(1);
        state.setFermenterVolInL(1);
        state.setFermenterName("RnDA");
        state.setBatchSerialNumber(10);
        Monitored monitored = new Monitored();
        monitored.setAmnTimeInMin(1);
        monitored.setCo2(2.1);
        state.setMonitored(monitored);
        Controller controller = new Controller();
        controller.setAgitationAction(1.2);
        controller.setBatchTimeInMin(2);
        state.setController(controller);
        when(stateRepository.save(state)).thenReturn(Mono.just(state));
        Mono<State> result = orderServiceFacade.save(state);
        assertNotNull(result);
        verify(stateRepository, times(1)).save(state);

    }


}