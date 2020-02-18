package be.vdab.groenetenen.mail;

import be.vdab.groenetenen.entities.Offerte;
import be.vdab.groenetenen.exceptions.KanMailNietZendenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
class DefaultMailSender implements MailSender {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JavaMailSender sender;

    DefaultMailSender(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public void nieuweOfferte(Offerte offerte) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(offerte.getEmailAdres());
            message.setSubject("Nieuwe offerte");
            message.setText("Uw offerte heeft het nummer " + offerte.getId());
            sender.send(message);
        } catch (MailException ex) {
            logger.error("Kan mail nieuwe offerte niet versturen", ex);
            throw new KanMailNietZendenException();
        }
    }
}