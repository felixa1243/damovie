package org.iqbal.damovie.models.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SuccessResponse<T> extends CommonResponse {
    T data;

    public SuccessResponse(String code, String message, T data) {
        super(code, "OK", message);
        this.data = data;
    }
}
