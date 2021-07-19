package com.example.dtoexercise.util;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationUtilImp implements ValidationUtil{

    private final Validator validator;

    public ValidationUtilImp() {
       this.validator = Validation.buildDefaultValidatorFactory()
               .getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
        return validator.validate(entity).isEmpty();
    }

    @Override
    public <E> Set<ConstraintViolation<E>> getViolation(E entity) {
        return validator.validate(entity);
    }

}
