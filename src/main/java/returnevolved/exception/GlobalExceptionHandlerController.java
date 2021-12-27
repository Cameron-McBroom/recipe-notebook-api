package returnevolved.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import returnevolved.dto.ResponseError;

@RestControllerAdvice
public class GlobalExceptionHandlerController {


  @ExceptionHandler(CustomException.class)
  @ResponseBody
  public ResponseError handleCustomException(HttpServletResponse res, CustomException ex) {
    res.setStatus(ex.getHttpStatus().value());
    return ResponseError.fromCustomEx(ex);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public List<ResponseError> handleNotValidArgument(HttpServletResponse res, MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();
    List<ResponseError> responseErrors = new ArrayList<>();

    for (FieldError error: fieldErrors) {
      responseErrors.add(new ResponseError("field-error", error.getDefaultMessage(), error.getField()));
    }

    return responseErrors;
  }
}



