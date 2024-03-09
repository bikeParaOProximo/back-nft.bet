package patoes.bet.patoes.bet.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecuperarSenhaRequestDTO {

    private Integer confirmacao;
    private String email;
    private String novaSenha;
}
