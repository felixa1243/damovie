package org.iqbal.damovie.models.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class CommonResponse {
    private String code;
    private String status;
    private String message;
}
