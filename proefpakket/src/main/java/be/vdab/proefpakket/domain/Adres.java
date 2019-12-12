package be.vdab.proefpakket.domain;

import be.vdab.proefpakket.entities.Bestelling;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class Adres implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank(groups = Bestelling.Stap2.class)
    private String straat;
    @NotBlank(groups = Bestelling.Stap2.class)
    private String huisNr;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "gemeenteid")
    @NotNull(groups = Bestelling.Stap2.class)
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
// getters voor straat, huisNr en gemeente
}