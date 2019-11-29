package be.vdab.groenetenen.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
@Constraint(
        validatedBy = VanTotPostcodeFormVanKleinerDanOfGelijkAanTotValidator.class)
public @interface VanTotPostcodeFormVanKleinerDanOfGelijkAanTot {
    String message() default
            "{be.vdab.groenetenen.constraints.VanKleinerDanOfGelijkAanTot.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
