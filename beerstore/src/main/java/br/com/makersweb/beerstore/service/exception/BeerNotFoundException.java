package br.com.makersweb.beerstore.service.exception;

import org.springframework.http.HttpStatus;

/**
 * @author aaristides
 */
public class BeerNotFoundException extends BusinessException {

    public BeerNotFoundException() {
        super("beers-6", HttpStatus.BAD_REQUEST);
    }
}
