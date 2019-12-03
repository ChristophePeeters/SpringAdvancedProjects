package be.vdab.proefpakket.forms;

import be.vdab.proefpakket.constraints.OndernemingsNr;

import javax.validation.constraints.NotNull;

public class OndernemingsNrForm {
    @NotNull
    @OndernemingsNr
    private final Long ondernemingsNr; // geparametriseerde constructor en getter

    public OndernemingsNrForm(@NotNull Long ondernemingsNr) {
        this.ondernemingsNr = ondernemingsNr;
    }

    public Long getOndernemingsNr() {
        return ondernemingsNr;
    }
}
