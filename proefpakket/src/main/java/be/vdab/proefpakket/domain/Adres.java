package be.vdab.proefpakket.domain;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class Adres implements Serializable {
    private static final long serialVersionUID = 1L;
    private String straat;
    private String huisNr;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "gemeenteid")
    private Gemeente gemeente;

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public Gemeente getGemeente() {
        return gemeente;
    }
}
