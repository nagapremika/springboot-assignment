package com.stackroute.globalexception;

import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
    public class MuzixControllerAdvice {
    private ResponseEntity <VndErrors> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity< >(new VndErrors(logRef, message), httpStatus);
    }
        @ExceptionHandler(TrackNotFoundException.class) //Handles TrackNotFoundException
        public ResponseEntity<VndErrors> notFoundException(final TrackNotFoundException e) {
            return error(e, HttpStatus.NOT_FOUND, e.getMessage());
        }

        @ExceptionHandler(TrackAlreadyExistsException.class) //Handles TrackAlreadyExistsException
        public ResponseEntity<VndErrors> assertionException(final TrackAlreadyExistsException e) {
            return error(e, HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

