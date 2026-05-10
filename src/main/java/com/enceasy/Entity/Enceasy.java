package com.enceasy.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="Urls")
public class Enceasy {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name ="Original", nullable = false, length = 500)
    private String urlOriginal;

    @Column(name ="Encurtada", nullable = false,length = 100)
    private String urlEncurtada;

    @Column(name = "data",nullable = false)
    private LocalDate data;

    @PrePersist
    public void dataIn(){
        this.data = LocalDate.now();
    }

    public Enceasy(){

    }

    public Enceasy ( String urlOriginal, String urlEncurtada, LocalDate data){
        this.data=data;
        this.urlEncurtada=urlEncurtada;
        this.urlOriginal=urlOriginal;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
