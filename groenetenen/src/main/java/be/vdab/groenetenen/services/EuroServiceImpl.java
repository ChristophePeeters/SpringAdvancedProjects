package be.vdab.groenetenen.services;

import be.vdab.groenetenen.restclients.KoersClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
class EuroServiceImpl implements EuroService {
    private final KoersClient koersClient;

    EuroServiceImpl(KoersClient koersClient) {
        this.koersClient = koersClient;
    }

    @Override
    public BigDecimal naarDollar(BigDecimal euro) {
        return euro.multiply(koersClient.getDollarKoers())
                .setScale(2, RoundingMode.HALF_UP);
    }
}