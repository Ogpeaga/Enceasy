package com.enceasy.Service;

import com.enceasy.Dto.RequestDTO;
import com.enceasy.Dto.ResponseDTO;
import com.enceasy.Entity.Enceasy;
import com.enceasy.Exception.UrlEncurtadaAlreadyExistsException;
import com.enceasy.Exception.UrlNotFoundException;
import com.enceasy.Repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class EnceasyService {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // É O GERADOR DE NÚMERO ALEATÓRIOS //
    // RANDOM É MAIS PREVISÍVEL POR ISSO USAR SECURE RANDOM //
    private static final SecureRandom RANDOM = new SecureRandom();

    // URL BASE NO INICIO DA URL ENCURTADA
    @Value("${app.base-url}")
    private String baseUrl;

    // TAMANHO DA URL ENCURTADA
    @Value("${app.code-length:6}")
    private int codeLength;


    public UrlRepository repository;

    public EnceasyService(UrlRepository repository){
        this.repository=repository;
    }


//==========================================================================
    // GERA CÓDIGO ALEATORIO ÚNICO //

    public String generatedUrlRandom(){
        String code;
        int maxAttemps = 6;
        int attemps = 0;

        // AQUI MONITORA AS TENTATIVAS //
        do {
            code = generatedUrlRandomUnique(codeLength);
            attemps++;
            if (attemps >= maxAttemps) {
                code = generatedUrlRandomUnique(codeLength + 2);
            }

        }while (repository.existsByurlEncurtada(code));

        return code;
    }

    // AQUI GERA O CÓDIGO //

    private String generatedUrlRandomUnique(int length){
        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        // TRANSFORMA EM UM UMA STRING
        return sb.toString();
    }

//============================================================================

    // CRIAR URL //
    // TRANSACTIONAL PARA IMPEDIR DE SALVAR NO BANCO MESMO VERIFICANDO SE TEM CHAVE IGUAL //

    @Transactional
    public ResponseDTO createUrlEncurtada (RequestDTO request){
        String urlEncurtada;

        if(request.getUrlEncurtada()!= null && !request.getUrlEncurtada().isBlank()){
            urlEncurtada = request.getUrlEncurtada().trim();
            if(repository.existsByurlEncurtada(urlEncurtada)){
                throw new UrlEncurtadaAlreadyExistsException(
                        "Essa personalização de URL " + urlEncurtada + "já existe");
            }
        }else{
            urlEncurtada = generatedUrlRandom();
        }

        Enceasy enceasy = new Enceasy(
                request.getUrlOriginal().trim(),
                urlEncurtada,
                LocalDate.now());

        Enceasy save = repository.save(enceasy);
        return toResponse(save);
    }


    // OQUE ELE VAI MANDAR PARA O RESPONSE DTO //
    public ResponseDTO toResponse(Enceasy enceasy){
        return new ResponseDTO(
                enceasy.getUrlOriginal(),
                baseUrl + "/" + enceasy.getUrlEncurtada(),
                enceasy.getData());
    }

    // REDIRECIONAR

    public String urlOriginal (String urlEncurtada){
        Optional<Enceasy> urlExist = repository.findByurlEncurtada(urlEncurtada);

        if(!urlExist.isPresent()){
            throw new UrlNotFoundException("Nenhuma URL encontrada" + urlEncurtada);
        }

        Enceasy enceasy = urlExist.get();
        return enceasy.getUrlOriginal();
    }


    // DELETAR UMA URL //
    @Transactional
    public void delete (String urlEncurtada){
        Optional <Enceasy> optional = repository.findByurlEncurtada(urlEncurtada);

        if(!optional.isPresent()){
            throw new UrlNotFoundException("Nenhum link encontrado para o código: " + urlEncurtada);
        }
        Enceasy enceasy = optional.get();
        repository.delete(enceasy);

    }


}
