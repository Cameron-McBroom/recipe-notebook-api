package returnevolved.exception;

import org.springframework.http.HttpStatus;
import returnevolved.dto.ResponseError;

public class CustomException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /** The Status to be returned to the client */
  private final HttpStatus httpStatus;

  /** The unique error name */
  private final String error;

  /** The error details which can be used be another developer */
  private final String detail;

  /** The human-readable error message which can be used by the client */
  private final String message;

  private CustomException(HttpStatus httpStatus, String error, String message, String detail) {
    this.message = message;
    this.httpStatus = httpStatus;
    this.error = error;
    this.detail = detail;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public String getError() {
    return error;
  }

  public String getDetail() {
    return detail;
  }

  @Override
  public String getMessage() {
    return message;
  }

  /** The Status to be returned to the client */
  public static ExceptionError status(HttpStatus status) { return new Builder(status); }


  public interface ExceptionError {
    /** The unique error name */
    ExceptionMessage error(String error);

  }
  public interface ExceptionMessage {
    /** The human-readable error message which can be used by the client */
    Build message(String message);
  }

  public interface Build {
    /** The error details which can be used be another developer */
    Build detail(String detail);
    CustomException build();
  }

  private static class Builder implements ExceptionError, ExceptionMessage, Build {

    /** The Status to be returned to the client */
    private HttpStatus httpStatus;

    /** The unique error name */
    private String error;

    /** The error details which can be used be another developer */
    private String detail;

    /** The human-readable error message which can be used by the client */
    private String message;

    public Builder(HttpStatus httpStatus) {
      this.httpStatus = httpStatus;
    }

    @Override
    public ExceptionMessage error(String error) {
      this.error = error;
      return this;
    }

    @Override
    public Build message(String message) {
      this.message = message;
      return this;
    }

    @Override
    public Build detail(String detail) {
      this.detail = detail;
      return this;
    }

    @Override
    public CustomException build() {
      return new CustomException(httpStatus, error, message, detail);
    }
  }

}
