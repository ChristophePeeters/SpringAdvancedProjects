package be.vdab.proefpakket.services;

import be.vdab.proefpakket.restclients.WeerClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
class DefaultWeerService implements WeerService {
    private final WeerClient weerClient;
    DefaultWeerService(WeerClient weerClient) {
        this.weerClient = weerClient;
    }
    @Override
    public BigDecimal getTemperatuur(String plaats) {
        return weerClient.getTemperatuur(plaats);
    }
}