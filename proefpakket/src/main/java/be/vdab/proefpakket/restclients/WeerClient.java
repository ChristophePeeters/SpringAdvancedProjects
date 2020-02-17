package be.vdab.proefpakket.restclients;

import java.math.BigDecimal;

public interface WeerClient {
    BigDecimal getTemperatuur(String plaats);
}