package br.com.makersweb.beerstore.service;

import br.com.makersweb.beerstore.model.Beer;
import br.com.makersweb.beerstore.repository.BeerRepository;
import br.com.makersweb.beerstore.service.exception.BeerAlreadyExistException;
import br.com.makersweb.beerstore.service.exception.BeerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * @author aaristides
 */
@Service
public class BeerService {

    private BeerRepository beerRepository;

    public BeerService(@Autowired BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Beer save(final Beer beer) {
        verifyIfBeerExists(beer);
        return beerRepository.save(beer);
    }

    private void verifyIfBeerExists(final Beer beer) {
        Optional<Beer> beerByNameAndType = beerRepository.findByNameAndType(beer.getName(), beer.getType());

        if (beerByNameAndType.isPresent() && (beer.isNew() ||
                isUpdatingToADifferentBeer(beer, beerByNameAndType))) {
            throw new BeerAlreadyExistException();
        }
    }

    private boolean isUpdatingToADifferentBeer(Beer beer, Optional<Beer> beerByNameAndType) {
        return beer.alreadyExist() && !beerByNameAndType.get().equals(beer);
    }

    public void delete(final Long id) {
        try {
            beerRepository.deleteById(id);
        } catch (EntityNotFoundException | EmptyResultDataAccessException e) {
            throw new BeerNotFoundException();
        }
    }
}
