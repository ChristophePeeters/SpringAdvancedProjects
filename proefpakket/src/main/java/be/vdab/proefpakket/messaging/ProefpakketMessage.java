package be.vdab.proefpakket.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProefpakketMessage {
    private String emailAdres;
    private String brouwerNaam;

    protected ProefpakketMessage() {
    }

    public ProefpakketMessage(String emailAdres, String brouwerNaam) {
        this.emailAdres = emailAdres;
        this.brouwerNaam = brouwerNaam;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public String getBrouwerNaam() {
        return brouwerNaam;
    }
}