package com.desafioviasoft.br.DesafioViaSoft.controller;


import com.desafioviasoft.br.DesafioViaSoft.dto.EmailDTO;
import com.desafioviasoft.br.DesafioViaSoft.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/send-email")
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailDTO emailDTO) {


        try {
            emailService.sendEmail(emailDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
