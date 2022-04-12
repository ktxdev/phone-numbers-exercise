package com.ktxdev.phonenumbersexercise.utils;

import com.ktxdev.phonenumbersexercise.models.PhoneState;
import org.springframework.core.convert.converter.Converter;

public class PhoneStateStringToEnumConverter implements Converter<String, PhoneState> {
    @Override
    public PhoneState convert(String source) {
        try {
            return PhoneState.valueOf(source);
        } catch (IllegalArgumentException ex){
            return null;
        }
    }
}
