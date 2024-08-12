package com.desafioviasoft.br.DesafioViaSoft.service;

import com.desafioviasoft.br.DesafioViaSoft.dto.EmailAwsDTO;
import com.desafioviasoft.br.DesafioViaSoft.dto.EmailDTO;
import com.desafioviasoft.br.DesafioViaSoft.dto.EmailOciDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${mail.integracao}")
    private String mailIntegration;

    public void sendEmail(EmailDTO emailDTO) throws Exception {
        Object dto;
        if ("AWS".equalsIgnoreCase(mailIntegration)) {
            dto = mapToAws(emailDTO);
        } else if ("OCI".equalsIgnoreCase(mailIntegration)) {
            dto = mapToOci(emailDTO);
        } else {
            throw new IllegalArgumentException("Invalid mail integration configuration");
        }

        // Serialização do objeto e impressão no console
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(dto);
        System.out.println(jsonString);
    }

    public EmailAwsDTO mapToAws(EmailDTO emailDTO) {
        EmailAwsDTO awsDTO = new EmailAwsDTO();
        awsDTO.setRecipient(truncateString(emailDTO.getRecipient(), 45));
        awsDTO.setRecipientName(truncateString(emailDTO.getRecipientName(), 60));
        awsDTO.setSender(truncateString(emailDTO.getSender(), 45));
        awsDTO.setSubject(truncateString(emailDTO.getSubject(), 120));
        awsDTO.setContent(truncateString(emailDTO.getContent(), 256));
        return awsDTO;
    }
    public EmailOciDTO mapToOci(EmailDTO emailDTO) {
        EmailOciDTO ociDTO = new EmailOciDTO();
        ociDTO.setRecipientEmail(truncateString(emailDTO.getRecipient(), 45));
        ociDTO.setRecipientName(truncateString(emailDTO.getRecipientName(), 60));
        ociDTO.setSenderEmail(truncateString(emailDTO.getSender(), 45));
        ociDTO.setSubject(truncateString(emailDTO.getSubject(), 120));
        ociDTO.setBody(truncateString(emailDTO.getContent(), 256));
        return ociDTO;
    }
    private String truncateString(String value, int maxLength) {
        if (value == null) {
            return null;
        }
        return value.length() > maxLength ? value.substring(0, maxLength) : value;
    }
}
