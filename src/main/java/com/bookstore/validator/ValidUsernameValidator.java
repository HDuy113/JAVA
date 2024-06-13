package com.bookstore.validator;

import com.bookstore.repository.IUerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidUsernameValidator implements ConstraintValidator<ValidUsername,String> {
    @Autowired
    private IUerRepository uerRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(uerRepository == null){
            return true;
        }
        return uerRepository.findByUsername(value) ==null;
    }


}
