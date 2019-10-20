package br.com.makersweb.beerstore.service;

import br.com.makersweb.beerstore.model.Beer;
import br.com.makersweb.beerstore.repository.BeerRepository;
import br.com.makersweb.beerstore.service.exception.BeerAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Beer> beerByNameAndType = beerRepository.findByNameAndType(beer.getName(), beer.getType());

        if (beerByNameAndType.isPresent()) {
            throw new BeerAlreadyExistException();
        }

        return beerRepository.save(beer);
    }
}
