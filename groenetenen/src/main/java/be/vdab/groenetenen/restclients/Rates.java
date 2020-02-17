package be.vdab.groenetenen.restclients;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Rates {
    @JsonProperty("USD")
    private BigDecimal usd;

    public BigDecimal getUSD() {
        return usd;
    }
}