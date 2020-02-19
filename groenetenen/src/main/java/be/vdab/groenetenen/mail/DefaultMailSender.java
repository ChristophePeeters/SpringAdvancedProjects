package be.vdab.groenetenen.mail;

import be.vdab.groenetenen.entities.Offerte;
import be.vdab.groenetenen.exceptions.KanMailNietZendenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
class DefaultMailSender implements MailSender {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JavaMailSender sender;
    private final String emailAdresWebMaster;

    DefaultMailSender(JavaMailSender sender,
                      @Value("${emailAdresWebMaster}") String emailAdresWebMaster) {
        this.sender = sender;
        this.emailAdresWebMaster = emailAdresWebMaster;
    }

    @Override
    @Async
    public void nieuweOfferte(Offerte offerte, String offertesURL) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(offerte.getEmailAdres());
            helper.setSubject("Nieuwe offerte");
            String offerteURL = offertesURL + offerte.getId();
            helper.setText("Uw offerte heeft het nummer <strong>" + offerte.getId() + "</strong>." +
                    "Je vindt de offerte <a href='" + offerteURL + "'>hier</a>.", true);
            sender.send(message);
        } catch (MessagingException | MailException ex) {
            logger.error("Kan mail nieuwe offerte niet versturen", ex);
            throw new KanMailNietZendenException();
        }
    }

    @Override
    public void aantalOffertesMail(long aantal) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(emailAdresWebMaster);
            helper.setSubject("Aantal offertes");
            helper.setText("Aantal offertes:<strong>" + aantal + "</strong>", true);
            sender.send(message);
        } catch (MessagingException | MailException ex) {
            logger.error("Kan mail aantal offertes niet versturen", ex);
            throw new KanMailNietZendenException();
        }
    }
}