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
            throw CustomException.status(HttpStatus.NOT_FOUND)
                    .error("resource-not-found")
                    .message("No resource found at location: " + reqUri)
                    .build();
        }
        else if (response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
            throw CustomException.status(HttpStatus.UNAUTHORIZED)
                    .error("unauthorised")
                    .message("You need to login to access " + reqUri)
                    .build();
        }
        else {
            throw CustomException.status(HttpStatus.BAD_REQUEST)
                    .error("bad-request")
                    .message(e.getLocalizedMessage())
                    .detail(e.getMessage())
                    .build();
        }
    }
}
