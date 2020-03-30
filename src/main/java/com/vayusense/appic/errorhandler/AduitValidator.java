package com.vayusense.appic.errorhandler;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AduitValidator implements ConstraintValidator<Adult,String> {

    @Override
    public boolean isValid(String fermenterName, ConstraintValidatorContext constraintValidatorContext) {
        return fermenterName.equals("Prod") || fermenterName.equals("RnDA") || fermenterName.equals("RnDB") ;
    }
}
