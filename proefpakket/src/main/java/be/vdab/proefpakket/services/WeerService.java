package be.vdab.proefpakket.services;

import java.math.BigDecimal;

public interface WeerService {
    BigDecimal getTemperatuur(String plaats);
}