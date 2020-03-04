package com.vayusense.appic.errorhandler;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AduitIntValidator implements ConstraintValidator<Adult,Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext constraintValidatorContext) {
        return page != null && page < 0 ;
    }
}
