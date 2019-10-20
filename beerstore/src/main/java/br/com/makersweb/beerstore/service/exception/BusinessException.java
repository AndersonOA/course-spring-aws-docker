package br.com.makersweb.beerstore.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author aaristides
 */
@RequiredArgsConstructor
@Getter
public class BusinessException extends RuntimeException {

    private final String code;
    private final HttpStatus status;

}
