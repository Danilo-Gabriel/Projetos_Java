package padawan_api.model.usuario.dto;


import jakarta.validation.constraints.NotBlank;


public record DadosAtualizaLoginDTO(

       
        Long id,
        
        String novoLogin,

        boolean ativo

) {


}


