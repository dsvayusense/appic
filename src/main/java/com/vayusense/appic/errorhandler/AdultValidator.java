package com.vayusense.appic.errorhandler;

import com.vayusense.appic.dto.StateDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdultValidator  implements ConstraintValidator<Adult, StateDto> {

    @Override
    public boolean isValid(StateDto stateDto, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
