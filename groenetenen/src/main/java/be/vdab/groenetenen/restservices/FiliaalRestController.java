package be.vdab.groenetenen.restservices;

import be.vdab.groenetenen.domain.Filiaal;
import be.vdab.groenetenen.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.groenetenen.exceptions.FiliaalNietGevondenException;
import be.vdab.groenetenen.services.FiliaalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/filialen")
class FiliaalRestController {
    private final FiliaalService filiaalService;

    FiliaalRestController(FiliaalService filiaalService) {
        this.filiaalService = filiaalService;
    }

    @GetMapping("{filiaal}")
    public Filiaal get(@PathVariable Optional<Filiaal> filiaal) {
        if (filiaal.isPresent()) {
            return filiaal.get();
        }
        throw new FiliaalNietGevondenException();
    }

    @ExceptionHandler(FiliaalNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void filiaalNietGevonden() {
    }

    @DeleteMapping("{filiaal}")
    public void delete(@PathVariable Optional<Filiaal> filiaal) {
        if (!filiaal.isPresent()) {
            throw new FiliaalNietGevondenException();
        }
        filiaalService.delete(filiaal.get());
    }

    @ExceptionHandler(FiliaalHeeftNogWerknemersException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String filiaalHeeftNogWerknemers() {
        return "filiaal heeft nog werknemers";
    }
}