package com.desafioviasoft.br.DesafioViaSoft.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDTO {

    private String recipient;
    private String recipientName;
    private String sender;
    private String subject;
    private String content;

}
