package be.vdab.groenetenen.messaging;

import be.vdab.groenetenen.mail.MailSender;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
class NieuweOfferteListener {
    private final MailSender mailSender;
    NieuweOfferteListener(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    @JmsListener(destination = "${nieuweOfferteQueue}")
    void ontvangBoodschap(OfferteEnOffertesURL offerteEnOffertesURL) {
        mailSender.nieuweOfferte(offerteEnOffertesURL.getOfferte(),
                offerteEnOffertesURL.getOffertesURL());
    }
}
