package be.vdab.proefpakket.entities;

import be.vdab.proefpakket.domain.Adres;
import be.vdab.proefpakket.domain.Brouwer;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "bestellingen")
public class Bestelling implements Serializable {
    public interface Stap1 {
    }

    public interface Stap2 {
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate datum = LocalDate.now();
    @NotBlank(groups = Stap1.class)
    private String voornaam;
    @NotBlank(groups = Stap1.class)
    private String familienaam;
    @NotNull(groups = Stap1.class)
    @Email(groups = Stap1.class)
    private String emailAdres;
    @Valid
    @Embedded
    private Adres adres;
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "brouwerid")
    private Brouwer brouwer;

    public long getId() {
        return id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public Adres getAdres() {
        return adres;
    }

    public Brouwer getBrouwer() {
        return brouwer;
    }
// getters voor de private variabelen (behalve serialVersionUID)
}