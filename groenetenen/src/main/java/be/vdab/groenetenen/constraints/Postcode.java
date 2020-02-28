package be.vdab.groenetenen.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PostcodeValidator.class)

public @interface Postcode {
    String message() default "{be.vdab.groenetenen.constraints.Postcode.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
