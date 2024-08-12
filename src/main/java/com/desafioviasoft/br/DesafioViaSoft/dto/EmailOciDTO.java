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
public class EmailOciDTO {

    @NotNull
    @Email
    @Size(max = 40)
    private String recipientEmail;

    @NotNull
    @Size(max = 50)
    private String recipientName;

    @NotNull
    @Email
    @Size(max = 40)
    private String senderEmail;

    @NotNull
    @Size(max = 100)
    private String subject;

    @NotNull
    @Size(max = 45)
    private String body;

}
