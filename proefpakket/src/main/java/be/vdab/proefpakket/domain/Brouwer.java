package be.vdab.proefpakket.domain;

import be.vdab.proefpakket.constraints.OndernemingsNr;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="brouwers")
public class Brouwer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @Embedded
    private Adres adres;
    @OndernemingsNr
    private Long ondernemingsNr;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Adres getAdres() {
        return adres;
    }

    public Long getOndernemingsNr() {
        return ondernemingsNr;
    }

    public void setOndernemingsNr(Long ondernemingsNr) {
        this.ondernemingsNr = ondernemingsNr;
    }
}