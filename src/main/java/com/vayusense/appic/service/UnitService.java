package com.vayusense.appic.service;

import com.vayusense.appic.entities.Unit;
import com.vayusense.appic.errorhandler.BusinessException;
import com.vayusense.appic.persistence.UnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    public void saveUnitProd(Unit unit) {
         unitRepository.insert(unit).onErrorMap(e -> new BusinessException(e.getMessage())).log().subscribe();

    }

    public void saveUnitRnd(Unit unit) {
         unitRepository.insert(unit).onErrorMap(e -> new BusinessException(e.getMessage())).log().subscribe();
    }

    public Unit mapUnitRnd(Unit unit) {
        unit.setTemperature("C");
        unit.setFermenterVolInL("L");
        unit.setPressure("Bar");
        unit.setAirFlow("Normal m3/h");
        unit.setFs("Kg/h");
        unit.setFa("Kg/h");
        unit.setAgitation("RPM");
        unit.setDissolvedOxygen("%");
        unit.setCo2("%");
        unit.setMass("Ton");
        unit.setPower("foretold");
        unit.setIncyte("pF/cm");
        unit.setAmnConcent("g/L");
        unit.setDextroseConcent("%");
        unit.setTobraConcent("µg/g");
        unit.setKanamConcent("µg/g");
        unit.setPcv("%");
        return unit;

    }

    public Unit mapUnitProd(Unit unit) {
        unit.setTemperature("C");
        unit.setFermenterVolInL("L");
        unit.setPressure("Bar");
        unit.setAirFlow("Normal L/h");
        unit.setFs("ml/h");
        unit.setFa("ml/h");
        unit.setAgitation("RPM");
        unit.setDissolvedOxygen("%");
        unit.setCo2("%");
        unit.setMass("kg");
        unit.setPower("W");
        unit.setIncyte("pF/cm");
        unit.setAmnConcent("g/L");
        unit.setDextroseConcent("%");
        unit.setTobraConcent("µg/g");
        unit.setKanamConcent("µg/g");
        unit.setPcv("%");
        return unit;

    }

}
