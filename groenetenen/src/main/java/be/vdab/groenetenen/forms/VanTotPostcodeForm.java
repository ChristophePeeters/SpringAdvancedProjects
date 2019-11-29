package be.vdab.groenetenen.forms;

import be.vdab.groenetenen.constraints.Postcode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class VanTotPostcodeForm {
    @NotNull
    @Postcode
    private final Integer van;
    @NotNull
    @Postcode
    private final Integer tot;

    public VanTotPostcodeForm(@NotNull Integer van, @NotNull Integer tot) {
        this.van = van;
        this.tot = tot;
    }

    public Integer getVan() {
        return van;
    }

    public Integer getTot() {
        return tot;
    }
}
