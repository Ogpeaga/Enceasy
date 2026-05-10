package com.enceasy.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public class RequestDTO {

    @NotBlank(message = "A URL não pode estar em branco")
    @Size(max = 2048, message = "A URL não pode ter mais de 2048 caracters")
    @URL(message = "Formato inválido. Informe uma URL completa ex:(https://www.google.com)")
    private String urlOriginal;

    @Size(max = 50, message = "O código personalizado deve ter entre 3 a 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Só pode conter apenas letras e números")
    private String urlEncurtada;

    public RequestDTO(){}

    public RequestDTO(String urlOriginal,String urlEncurtada){
        this.urlOriginal=urlOriginal;
        this.urlEncurtada=urlEncurtada;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public String getUrlEncurtada() {
        return urlEncurtada;
    }

    public void setUrlEncurtada(String urlEncurtada) {
        this.urlEncurtada = urlEncurtada;
    }
}
