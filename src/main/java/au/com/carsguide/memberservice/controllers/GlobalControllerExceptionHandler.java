package au.com.carsguide.memberservice.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
class GlobalControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleConflict() {
        logger.warn("Data integrity violation encountered. Returning HTTP Status 409 CONFLICT.");
        return "Member already exists";
    }
}
