package br.com.makersweb.beerstore.service;

import br.com.makersweb.beerstore.model.Beer;
import br.com.makersweb.beerstore.model.enums.BeerType;
import br.com.makersweb.beerstore.repository.BeerRepository;
import br.com.makersweb.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

public class BeerServiceTest {

    private BeerService beerService;

    @Mock
    private BeerRepository beerRepositoryMocked;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        beerService = new BeerService(beerRepositoryMocked);
    }

    @Test(expected = BeerAlreadyExistException.class)
    public void should_deny_creation_of_beer_that_exists() {
        Beer beerInDatabase = new Beer();
        beerInDatabase.setId(10L);
        beerInDatabase.setName("Heineken");
        beerInDatabase.setType(BeerType.LAGER);
        beerInDatabase.setVolume(new BigDecimal("355"));

        when(beerRepositoryMocked.findByNameAndType("Heineken", BeerType.LAGER)).thenReturn(Optional.of(beerInDatabase));

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

        Beer nBeerInDatabase = new Beer();
        nBeerInDatabase.setId(10L);
        nBeerInDatabase.setName("Heineken");
        nBeerInDatabase.setType(BeerType.LAGER);
        nBeerInDatabase.setVolume(new BigDecimal("355"));

        when(beerRepositoryMocked.save(nBeer)).thenReturn(nBeerInDatabase);

        Beer beerSaved = beerService.save(nBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }
}
