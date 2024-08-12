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
public class EmailAwsDTO {

    @NotNull
    @Email
    @Size(max = 45)
    private String recipient;

    @NotNull
    @Size(max = 60)
    private String recipientName;

    @NotNull
    @Email
    @Size(max = 45)
    private String sender;

    @NotNull
    @Size(max = 120)
    private String subject;

    @NotNull
    @Size(max = 256)
    private String content;
}
