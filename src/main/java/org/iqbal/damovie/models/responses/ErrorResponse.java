package org.iqbal.damovie.models.responses;

import lombok.AllArgsConstructor;

public class ErrorResponse extends CommonResponse {
    public ErrorResponse(String code, String message) {
        super.setCode(code);
        super.setMessage(message);
        super.setStatus("ERROR");
    }
}
