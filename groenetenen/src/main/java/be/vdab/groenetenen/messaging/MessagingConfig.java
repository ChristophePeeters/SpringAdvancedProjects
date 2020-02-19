package be.vdab.groenetenen.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MarshallingMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
class MessagingConfig {
    @Bean
    Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(OfferteEnOffertesURL.class);
        return marshaller;
    }
    @Bean
    MarshallingMessageConverter converter (Jaxb2Marshaller marshaller) {
        return new MarshallingMessageConverter(marshaller);
    }
}