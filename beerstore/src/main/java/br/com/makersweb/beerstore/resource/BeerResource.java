package br.com.makersweb.beerstore.resource;

import br.com.makersweb.beerstore.model.Beer;
import br.com.makersweb.beerstore.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author aaristides
 */
@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired
    private BeerRepository beerRepository;

    @GetMapping
    public List<Beer> all() {
        return beerRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer create(@Valid @RequestBody Beer beer) {
        return beerRepository.save(beer);
    }
}
