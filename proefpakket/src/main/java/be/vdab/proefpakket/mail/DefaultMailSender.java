package be.vdab.proefpakket.mail;

import be.vdab.proefpakket.exceptions.KanMailNietZendenException;
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
    public void proefpakket(String emailAdres, String brouwerNaam) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailAdres);
            message.setSubject("Proefpakket " + brouwerNaam);
            message.setText("Bedankt voor uw interesse. U ontvangt uw proefpakket " +
                    brouwerNaam + " binnenkort.");
            sender.send(message);
        } catch (MailException ex) {
            logger.error("Kan mail proefpakket niet versturen", ex);
            throw new KanMailNietZendenException();
        }
    }
}