package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.exceptions.KanTemperatuurNietLezenException;
import be.vdab.proefpakket.services.WeerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("weer")
class WeerController {
    private final WeerService weerService;
    WeerController(WeerService weerService) {
        this.weerService = weerService;
    }
    @GetMapping("{plaats}/temperatuur")
    public ModelAndView temperatuur(@PathVariable String plaats) {
        ModelAndView modelAndView = new ModelAndView("temperatuur");
        try {
            modelAndView.addObject("temperatuur", weerService.getTemperatuur(plaats));
        } catch (KanTemperatuurNietLezenException ex) {
        }
        return modelAndView;
    }
}