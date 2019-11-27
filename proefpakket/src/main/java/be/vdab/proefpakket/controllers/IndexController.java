package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.services.BrouwerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {
    private final char[] alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final BrouwerService brouwerService;
    IndexController(BrouwerService brouwerService) {
        this.brouwerService = brouwerService;
    }
    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index", "alfabet", alfabet);
    }
    @GetMapping("alfabet/{letter}")
    public ModelAndView brouwers(@PathVariable String letter) {
        return new ModelAndView("index", "alfabet", alfabet).addObject("brouwers",
                brouwerService.findByBeginNaam(String.valueOf(letter)));
    }
}
