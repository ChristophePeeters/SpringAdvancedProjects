package be.vdab.proefpakket.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OndernemingsNrValidator
        implements ConstraintValidator<OndernemingsNr, Long> {
    @Override
    public void initialize(OndernemingsNr postcode) {
    }
    @Override
    public boolean isValid(Long ondernemingsNr,
                           ConstraintValidatorContext context) {
        if (ondernemingsNr == null) {
            return true;
        }
        long laatste2Cijfers = ondernemingsNr % 100L;
        long overigeCijfers= ondernemingsNr / 100;
        return laatste2Cijfers == 97 - overigeCijfers % 97;
    }
}