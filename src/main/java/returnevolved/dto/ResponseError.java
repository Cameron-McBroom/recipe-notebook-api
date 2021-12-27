package returnevolved.dto;

import returnevolved.exception.CustomException;

public class ResponseError {

    /** A code unique to our application.*/
    private String error;

    /** The UI presentable error message */
    private String message;

    /** Any details of the error that can be used by the developer */
    private String detail;

    public ResponseError(String error, String message, String detail) {
        this.error = error;
        this.message = message;
        this.detail = detail;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public static ResponseError fromEx(Exception e) {
        return new ResponseError("unspecified-exception", e.getLocalizedMessage(), e.getMessage());
    }

    public static ResponseError fromEx(String title, Exception e) {
        return new ResponseError(title, e.getLocalizedMessage(), e.getMessage());
    }

    public static ResponseError fromCustomEx(CustomException e) {
        return new ResponseError(e.getError(), e.getMessage(), e.getDetail());
    }





}
