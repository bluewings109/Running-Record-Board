package org.onlypearson.runningrecord.web.beanvalidator;

import org.junit.jupiter.api.Test;
import org.onlypearson.runningrecord.web.record.form.RecordSaveForm;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BeanValidatorTest {

    @Test
    void beanValidation(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        RecordSaveForm recordSaveForm = new RecordSaveForm();
        recordSaveForm.setDateTime(null);

        Set<ConstraintViolation<RecordSaveForm>> validate = validator.validate(recordSaveForm);

        for (ConstraintViolation<RecordSaveForm> violation : validate) {
            System.out.println("violation = " + violation);
            System.out.println("violation.getMessage() = " + violation.getMessage());
        }


    }
}
