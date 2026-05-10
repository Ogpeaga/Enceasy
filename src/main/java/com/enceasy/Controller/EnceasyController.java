package com.enceasy.Controller;

import com.enceasy.Dto.RequestDTO;
import com.enceasy.Dto.ResponseDTO;
import com.enceasy.Service.EnceasyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class EnceasyController {

    private EnceasyService enceasyService;

    public EnceasyController(EnceasyService enceasyService){
        this.enceasyService= enceasyService;
    }

    // CRIAR UMA URL //
    @PostMapping
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody RequestDTO request){
        ResponseDTO response = enceasyService.createUrlEncurtada(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // REDIRECIONAR UMA URL
    @GetMapping("/{urlEncurtada}")
    public ResponseEntity<Void> redirecionar(@PathVariable String urlEncurtada){

        String urlOriginal = enceasyService.urlOriginal(urlEncurtada);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION,urlOriginal)
                .build();
    }


    // DELETAR UMA URL //
    @DeleteMapping("/delete/{urlEncurtada}")
    public ResponseEntity <Void> delete(@PathVariable String urlEncurtada ){
        enceasyService.delete(urlEncurtada);
        return ResponseEntity.noContent().build();
    }
}
