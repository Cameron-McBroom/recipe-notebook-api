package returnevolved.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import returnevolved.exception.CustomException;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ApiIgnore
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public Object error(HttpServletRequest request, HttpServletResponse response, Exception e) {

        var reqUri = ((String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI));
        var statusIsNotFound = (response.getStatus() == HttpServletResponse.SC_NOT_FOUND);

        if (statusIsNotFound) {
            throw new CustomException("No resource found at at location: " + reqUri, HttpStatus.NOT_FOUND); // or your REST 404 blabla...
        }
        else if (response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
            throw new CustomException("You need to log in first", HttpStatus.UNAUTHORIZED);
        }
        else {
            throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
