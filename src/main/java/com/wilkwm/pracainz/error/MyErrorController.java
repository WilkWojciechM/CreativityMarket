package com.wilkwm.pracainz.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/error";
            }
            else if(statusCode == HttpStatus.BAD_GATEWAY.value()){
                return "error/error";
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()){
                return "error/error";
            }
            else if(statusCode == HttpStatus.UNAUTHORIZED.value()){
                return "error/error";
            }
            else if(statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()){
                return "error/error";
            }
            else if(statusCode == HttpStatus.GATEWAY_TIMEOUT.value()){
                return "error/error";
            }
        }
        return "error/error";
    }
}