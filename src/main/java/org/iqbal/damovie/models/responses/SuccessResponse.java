package org.iqbal.damovie.models.responses;
public class SuccessResponse<T> extends CommonResponse {
    T data;

    public SuccessResponse(String code, String message, T data) {
        super(code, "Success", message);
        this.data = data;
    }
}
