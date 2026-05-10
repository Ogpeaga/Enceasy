package com.enceasy.Dto;

import java.time.LocalDate;

public class ResponseDTO {

    private String urlOriginal;
    private String urlEncurtada;
    private LocalDate data;

    public ResponseDTO(){}

    public ResponseDTO(String urlOriginal, String urlEncurtada, LocalDate data){
        this.urlOriginal= urlOriginal;
        this.urlEncurtada=urlEncurtada;
        this.data = data;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
