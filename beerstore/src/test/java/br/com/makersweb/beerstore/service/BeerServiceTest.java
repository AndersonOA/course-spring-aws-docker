package br.com.makersweb.beerstore.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import br.com.makersweb.beerstore.model.Beer;
import br.com.makersweb.beerstore.model.enums.BeerType;
import br.com.makersweb.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BeerServiceTest {

    private BeerService beerService;

    @Before
    public void setup() {
        beerService = new BeerService();
    }

    @Test(expected = BeerAlreadyExistException.class)
    public void should_deny_creation_of_beer_that_exists() {
        Beer nBeer = new Beer();
        nBeer.setName("Heineken");
        nBeer.setType(BeerType.LAGER);
        nBeer.setVolume(new BigDecimal("355"));

        beerService.save(nBeer);
    }

    @Test
    public void should_create_new_beer() {
        Beer nBeer = new Beer();
        nBeer.setName("Heineken");
        nBeer.setType(BeerType.LAGER);
        nBeer.setVolume(new BigDecimal("355"));

        Beer beerSaved = beerService.save(nBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }
}
