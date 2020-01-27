package be.vdab.proefpakket.restservices;

import be.vdab.proefpakket.domain.Gemeente;
import be.vdab.proefpakket.exceptions.GemeenteNietGevondenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gemeenten")
class GemeenteRestController {
    @GetMapping("{gemeente}")
    public Gemeente read(@PathVariable Optional<Gemeente> gemeente) {
        if (gemeente.isPresent()) {
            return gemeente.get();
        }
        throw new GemeenteNietGevondenException();
    }

    @ExceptionHandler(GemeenteNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void gemeenteNietGevonden() {
    }
}