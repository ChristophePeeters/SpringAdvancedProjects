package be.vdab.proefpakket.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = OndernemingsNrValidator.class)
public @interface OndernemingsNr {
    String message() default
            "{be.vdab.proefpakket.constraints.OndernemingsNummer.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}