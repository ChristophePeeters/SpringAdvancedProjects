package be.vdab.groenetenen.restservices;

import be.vdab.groenetenen.services.FiliaalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filialen")
class FiliaalRestController {
    private final FiliaalService filiaalService;

    FiliaalRestController(FiliaalService filiaalService) {
        this.filiaalService = filiaalService;
    }
}