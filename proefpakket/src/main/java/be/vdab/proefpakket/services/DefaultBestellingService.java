package be.vdab.proefpakket.services;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.messaging.ProefpakketMessage;
import be.vdab.proefpakket.repositories.BestellingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultBestellingService implements BestellingService {
    private final BestellingRepository bestellingRepository;
    private final JmsTemplate template;
    private final String proefpakketQueue;

    DefaultBestellingService(BestellingRepository bestellingRepository,
                             JmsTemplate template,
                             @Value("${proefpakketQueue}") String proefpakketQueue) {
        this.bestellingRepository = bestellingRepository;
        this.template = template;
        this.proefpakketQueue = proefpakketQueue;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Bestelling bestelling) {
        bestellingRepository.save(bestelling);
        ProefpakketMessage message = new ProefpakketMessage(
                bestelling.getEmailAdres(),
                bestelling.getBrouwer().getNaam());
        template.convertAndSend(proefpakketQueue, message);
    }
}