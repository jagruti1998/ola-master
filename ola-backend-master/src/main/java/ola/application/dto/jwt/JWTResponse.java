package ola.application.dto.jwt;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JWTResponse {

    private String JwtToken;
}
