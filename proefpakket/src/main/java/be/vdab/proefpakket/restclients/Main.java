package be.vdab.proefpakket.restclients;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Main {
    private BigDecimal temp;

    public BigDecimal getTemp() {
        return temp;
    }
}