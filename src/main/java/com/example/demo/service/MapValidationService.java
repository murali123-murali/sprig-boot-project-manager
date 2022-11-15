package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationService {

    public ResponseEntity<?> mapValidationService(BindingResult result)
    {
        if(result.hasErrors())
        {
            Map<String,String> errormap=new HashMap<>();

            for(FieldError error: result.getFieldErrors())
            {
                errormap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errormap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
