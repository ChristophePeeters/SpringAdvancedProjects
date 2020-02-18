package be.vdab.groenetenen.controllers;

import be.vdab.groenetenen.domain.Filiaal;
import be.vdab.groenetenen.forms.VanTotPostcodeForm;
import be.vdab.groenetenen.services.FiliaalService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

// enkele imports
@Controller
@RequestMapping(path = "filialen", produces = MediaType.TEXT_HTML_VALUE)
class FiliaalController {
    private final FiliaalService filiaalService;
    FiliaalController(FiliaalService filiaalService) {
        this.filiaalService = filiaalService;
    }
    @GetMapping("vantotpostcodeform")
    public ModelAndView vanTotPostcodeForm() {
        return new ModelAndView("vantotpostcode")
                .addObject(new VanTotPostcodeForm(null, null));
    }
    @GetMapping("vantotpostcode")
    public ModelAndView vanTotPostcode(@Valid VanTotPostcodeForm form, Errors errors) {
        ModelAndView modelAndView = new ModelAndView("vantotpostcode");
        if (! errors.hasErrors()) {
            modelAndView.addObject("filialen",
                    filiaalService.findByPostcode(form.getVan(), form.getTot()));
        }
        return modelAndView;
    }
    @GetMapping("{optionalFiliaal}")
    public ModelAndView read(@PathVariable Optional<Filiaal> optionalFiliaal) {
        ModelAndView modelAndView = new ModelAndView("filiaal");
        optionalFiliaal.ifPresent(filiaal -> modelAndView.addObject(filiaal));
        return modelAndView;
    }
    @GetMapping("perid")
    public String findById() {
        return "filiaalPerId";
    }
}