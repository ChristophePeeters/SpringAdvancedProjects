package be.vdab.proefpakket.messaging;

import be.vdab.proefpakket.mail.MailSender;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
class ProefpakketListener {
    private final MailSender mailSender;
    ProefpakketListener(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    @JmsListener(destination = "${proefpakketQueue}")
    void ontvangBoodschap(ProefpakketMessage message) {
        mailSender.proefpakket(message.getEmailAdres(), message.getBrouwerNaam());
    }
}