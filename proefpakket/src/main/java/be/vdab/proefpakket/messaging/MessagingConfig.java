package be.vdab.proefpakket.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


import javax.jms.ConnectionFactory;

@Configuration
class MessagingConfig {
    @Bean
    Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(ProefpakketMessage.class);
        return marshaller;
    }
    @Bean
    MarshallingMessageConverter converter (Jaxb2Marshaller marshaller) {
        return new MarshallingMessageConverter(marshaller);
    }
    @Bean
    DefaultJmsListenerContainerFactory factory(
            ConnectionFactory connectionFactory,
            MarshallingMessageConverter converter) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(converter);
        return factory;
    }
}