package ola.application.dto.signup;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResponseDTO implements Serializable {

    private static final long serialVersionUID =1L;

    private final String filed;

    private final String message;

    public ResponseDTO(String filed, String message) {
        this.filed = filed;
        this.message = message;
    }

}

