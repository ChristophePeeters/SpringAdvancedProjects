package be.vdab.groenetenen.restclients;

import be.vdab.groenetenen.exceptions.KanKoersNietLezenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Component
class FixerKoersClient implements KoersClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final URI uri;
    private final RestTemplate template;
    FixerKoersClient(@Value("${fixerURL}") URI uri, RestTemplateBuilder builder) {
        this.uri = uri;
        this.template = builder.build();
    }
    @Override
    public BigDecimal getDollarKoers() {
        try {
            USDRate rate = template.getForObject(uri, USDRate.class);
            return rate.getRates().getUSD();
        } catch (RestClientException ex) {
            logger.error("kan koers niet lezen", ex);
            throw new KanKoersNietLezenException();
        }
    }
}