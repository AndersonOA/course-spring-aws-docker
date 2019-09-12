package br.com.makersweb.beerstore.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author aaristides
 */
@RestController
@RequestMapping("/beers")
public class BeerResource {

    @GetMapping
    public List<String> all() {
        return Arrays.asList("Colorado Indica", "Stella Artois", "Brahma", "Skol");
    }
}
