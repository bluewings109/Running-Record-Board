package org.onlypearson.runningrecord.controller;

import org.onlypearson.runningrecord.domain.Record;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RecordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Record.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Record record = (Record) target;

        //일시
        if(record.getDateTime() == null){
            errors.rejectValue("dateTime", "required");
        }

        //거리
        if(record.getDistance() == null || record.getDistance() <=0.0){
            errors.rejectValue("distance", "min", new Object[]{0}, null);
        }

        //시간,분,초 필수
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"durationHour", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"durationMin", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"durationSec", "required");

        if(record.getDurationHour() != null && record.getDurationHour() <0){
            errors.rejectValue("durationHour", "min", new Object[]{0}, null);
        }

        if(record.getDurationMin() != null && record.getDurationMin() <0){
            errors.rejectValue("durationMin", "min", new Object[]{0}, null);
        }

        if(record.getDurationMin() != null && record.getDurationSec() <0){
            errors.rejectValue("durationSec", "min", new Object[]{0}, null);
        }

        if(record.getDurationHour() != null && record.getDurationMin() != null && record.getDurationSec() != null
                && record.getDurationHour() == 0 && record.getDurationMin() == 0 && record.getDurationSec() == 0) {
            errors.reject("zeroDuration");
        }


    }
}
