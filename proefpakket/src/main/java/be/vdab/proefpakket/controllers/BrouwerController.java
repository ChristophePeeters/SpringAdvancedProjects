package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.domain.Brouwer;
import be.vdab.proefpakket.forms.OndernemingsNrForm;
import be.vdab.proefpakket.services.BrouwerService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Component
@RequestMapping("brouwers")
class BrouwerController {
    private static final String VIEW = "brouwers/brouwer";
    private static final String REDIRECT_BIJ_BROUWER_NIET_GEVONDEN = "redirect:/";
    private final BrouwerService brouwerService;

    BrouwerController(BrouwerService brouwerService) {
        this.brouwerService = brouwerService;
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
}