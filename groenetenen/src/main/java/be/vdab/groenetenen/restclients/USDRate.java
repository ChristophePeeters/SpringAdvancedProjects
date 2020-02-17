package be.vdab.groenetenen.restclients;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

// enkele imports
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class USDRate {
    private Rates rates;

    public Rates getRates() {
        return rates;
    }
}