package be.vdab.groenetenen.messaging;

import be.vdab.groenetenen.entities.Offerte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferteEnOffertesURL {
    private Offerte offerte;
    private String offertesURL;

    public OfferteEnOffertesURL(Offerte offerte, String offertesURL) {
        this.offerte = offerte;
        this.offertesURL = offertesURL;
    }

    OfferteEnOffertesURL() {
    }

    public Offerte getOfferte() {
        return offerte;
    }

    public String getOffertesURL() {
        return offertesURL;
    }
}