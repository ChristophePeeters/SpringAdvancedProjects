package be.vdab.groenetenen.domain;

import be.vdab.groenetenen.constraints.Postcode;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

// enkele imports
@Embeddable
public class Adres implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank
    private String straat;
    @NotBlank
    private String huisNr;
    @NotNull
    @Postcode
    private int postcode;
    @NotBlank
    private String gemeente;


    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}