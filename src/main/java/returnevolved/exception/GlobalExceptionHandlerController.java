package returnevolved.exception;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import returnevolved.dto.JsonResponse;

@RestControllerAdvice
public class GlobalExceptionHandlerController {


//  @ExceptionHandler(CustomException.class)
//  @ResponseBody
//  public JsonResponse<CustomException> handleCustomException(HttpServletResponse res, CustomException ex) {
//    res.setStatus(ex.getHttpStatus().value());
//    return new JsonResponse<>(false, ex.getLocalizedMessage());
//  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public JsonResponse<MethodArgumentNotValidException> handleNotValidArgument(HttpServletResponse res, MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();

    StringBuilder errorMessage = new StringBuilder();

    for (int i = 0; i < fieldErrors.size(); i++) {
      System.out.println(fieldErrors.get(i).toString());
      errorMessage.append(fieldErrors.get(i).getDefaultMessage());

      if (i + 1 != fieldErrors.size()) {
        errorMessage.append(", ");
      }
    }

    return new JsonResponse<>(false, errorMessage.toString());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public JsonResponse<Exception> handleNotReadable(HttpServletResponse res, HttpMessageNotReadableException e) {
    return new JsonResponse<>(false, e.getCause().getLocalizedMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public JsonResponse<Exception> handleException(HttpServletResponse res, Exception e) {
    return new JsonResponse<>(false, e.getMessage());
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public JsonResponse<Exception> handleAccessDenied(Exception e) {
    return new JsonResponse<>(false, e.getMessage());
  }
}



