package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.domain.Brouwer;
import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.forms.OndernemingsNrForm;
import be.vdab.proefpakket.services.BestellingService;
import be.vdab.proefpakket.services.BrouwerService;
import be.vdab.proefpakket.services.GemeenteService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Component
@SessionAttributes("bestelling")
@RequestMapping("brouwers")
class BrouwerController {
    private static final String VIEW = "brouwers/brouwer";
    private static final String REDIRECT_BIJ_BROUWER_NIET_GEVONDEN = "redirect:/";
    private final BrouwerService brouwerService;
    private final GemeenteService gemeenteService;
    private final BestellingService bestellingService;

    BrouwerController(BrouwerService brouwerService,
                      GemeenteService gemeenteService,
                      BestellingService bestellingService) {
        this.brouwerService = brouwerService;
        this.gemeenteService = gemeenteService;
        this.bestellingService = bestellingService;
    }

    @GetMapping("{optionalBrouwer}")
    public ModelAndView read(@PathVariable Optional<Brouwer> optionalBrouwer) {
        ModelAndView modelAndView = new ModelAndView("brouwer");
        optionalBrouwer.ifPresent(brouwer -> modelAndView.addObject(brouwer));
        return modelAndView;
    }

    @GetMapping("{optionalBrouwer}/ondernemingsnr")
    public ModelAndView ondernemingsNr(@PathVariable Optional<Brouwer> optionalBrouwer) {
        ModelAndView modelAndView = new ModelAndView("ondernemingsnr");
        optionalBrouwer.ifPresent(brouwer -> modelAndView.addObject(brouwer)
                .addObject(new OndernemingsNrForm(brouwer.getOndernemingsNr())));
        return modelAndView;
    }

    @PostMapping("{optionalBrouwer}/ondernemingsnr")
    public ModelAndView ondernemingsNr(@PathVariable Optional<Brouwer> optionalBrouwer,
                                       @Valid OndernemingsNrForm form, Errors errors, RedirectAttributes redirect) {
        if (!optionalBrouwer.isPresent()) {
            return new ModelAndView("ondernemingsnr");
        }
        Brouwer brouwer = optionalBrouwer.get();
        if (errors.hasErrors()) {
            return new ModelAndView("ondernemingsnr").addObject(brouwer);
        }
        brouwer.setOndernemingsNr(form.getOndernemingsNr());
        brouwerService.update(brouwer);
        redirect.addAttribute("id", brouwer.getId());
        return new ModelAndView("redirect:/brouwers/{id}");
    }

    @GetMapping("{optionalBrouwer}/proefpakket")
    public ModelAndView proefpakket(@PathVariable Optional<Brouwer> optionalBrouwer) {
        ModelAndView modelAndView = new ModelAndView("proefpakketstap1");
        optionalBrouwer.ifPresent(
                brouwer -> modelAndView.addObject(brouwer).addObject(new Bestelling()));
        return modelAndView;
    }

    @InitBinder("bestelling")
    void initBinder(DataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @PostMapping(value = "{optionalBrouwer}/proefpakket", params = "stap2")
    public ModelAndView proefpakketNaarStap2(
            @PathVariable Optional<Brouwer> optionalBrouwer,
            @Validated(Bestelling.Stap1.class) Bestelling bestelling, Errors errors) {
        if (optionalBrouwer.isPresent()) {
            Brouwer brouwer = optionalBrouwer.get();
            if (errors.hasErrors()) {
                return new ModelAndView("proefpakketstap1").addObject(brouwer);
            }
            return new ModelAndView("proefpakketstap2").addObject(brouwer)
                    .addObject("gemeenten", gemeenteService.findAll());
        }
        return new ModelAndView("proefpakketstap2");
    }

    @PostMapping(value = "{optionalBrouwer}/proefpakket", params = "stap1")
    public ModelAndView proefpakketNaarStap1(
            @PathVariable Optional<Brouwer> optionalBrouwer, Bestelling bestelling) {
        ModelAndView modelAndView = new ModelAndView("proefpakketstap1");
        optionalBrouwer.ifPresent(brouwer -> modelAndView.addObject(brouwer));
        return modelAndView;
    }

    @PostMapping(value = "{brouwer}/proefpakket", params = "opslaan")
    public ModelAndView proefpakketOpslaan(@PathVariable Brouwer brouwer,
                                           @Validated(Bestelling.Stap2.class) Bestelling bestelling, Errors errors,
                                           SessionStatus session) {
        if (errors.hasErrors()) {
            return new ModelAndView("proefpakketstap2")
                    .addObject("gemeenten", gemeenteService.findAll());
        }
        bestellingService.create(bestelling);
        session.setComplete();
        return new ModelAndView("redirect:/");
    }
}