package be.vdab.proefpakket.restclients;

import be.vdab.proefpakket.exceptions.KanTemperatuurNietLezenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
class OpenWeatherMapClient implements WeerClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String uri;
    private final RestTemplate template;

    OpenWeatherMapClient(@Value("${openWeatherMapURL}") String uri,
                         RestTemplateBuilder builder) {
        this.uri = uri;
        this.template = builder.build();
    }

    @Override
    public BigDecimal getTemperatuur(String plaats) {
        try {
            return template.getForObject(uri, Weer.class, plaats).getMain().getTemp();
        } catch (Exception ex) {
            logger.error("kan temperatuur niet lezen", ex);
            throw new KanTemperatuurNietLezenException();
        }
    }
}